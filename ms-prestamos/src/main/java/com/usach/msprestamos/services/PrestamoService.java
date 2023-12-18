package com.usach.msprestamos.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usach.msprestamos.clients.EquipoFeignClient;
import com.usach.msprestamos.clients.ProfesorFeignClient;
import com.usach.msprestamos.clients.UsoFeignClient;
import com.usach.msprestamos.dtos.*;
import com.usach.msprestamos.entities.Devolucion;
import com.usach.msprestamos.entities.EstadoDevolucion;
import com.usach.msprestamos.entities.Prestamo;
import com.usach.msprestamos.exceptions.ApiErrorException;
import com.usach.msprestamos.exceptions.DevolucionRealizadaException;
import com.usach.msprestamos.exceptions.RegistroNoExisteException;
import com.usach.msprestamos.repositories.EstadoDevolucionRepository;
import com.usach.msprestamos.repositories.PrestamoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

@Service
public class PrestamoService {

    PrestamoRepository prestamoRepository;
    EstadoDevolucionRepository estadoDevolucionRepository;
    EquipoFeignClient equipoFeignClient;
    ProfesorFeignClient profesorFeignClient;
    UsoFeignClient usoFeignClient;
    KafkaTemplate<String, String> kafkaTemplate;
    ObjectMapper objectMapper;

    @Autowired
    public PrestamoService(PrestamoRepository prestamoRepository,
                           EstadoDevolucionRepository estadoDevolucionRepository,
                           EquipoFeignClient equipoFeignClient,
                           ProfesorFeignClient profesorFeignClient,
                           UsoFeignClient usoFeignClient,
                           KafkaTemplate<String, String> kafkaTemplate,
                           ObjectMapper objectMapper) {
        this.prestamoRepository = prestamoRepository;
        this.estadoDevolucionRepository = estadoDevolucionRepository;
        this.equipoFeignClient = equipoFeignClient;
        this.profesorFeignClient = profesorFeignClient;
        this.usoFeignClient = usoFeignClient;
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void verificarEquipoYUso(Integer idEquipo, Integer idUso) {
        Response response = equipoFeignClient.verificarEquipoYUso(idEquipo, idUso);
        if(response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            throw new ApiErrorException(response.getMessage());
        }
    }

    public void verificarEquipo(Integer idEquipo) {
        Response response = equipoFeignClient.verificarEquipo(idEquipo);
        if(response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            throw new ApiErrorException(response.getMessage());
        }
    }

    public void verificarProfesor(String rutProfesor) {
        Response response = profesorFeignClient.verificarProfesor(rutProfesor);
        if(response.getStatus() == HttpStatus.NOT_FOUND.value()) {
            throw new ApiErrorException(response.getMessage());
        }
    }

    public Prestamo ingresarPrestamo(IngresarPrestamoRequest ingresarPrestamoRequest) throws JsonProcessingException {
        //verificar existe el equipo
        //verificar existe el uso
        //verificar si el equipo tiene permisos para ese uso
        //verificar que el equipo esté disponible
        verificarEquipoYUso(ingresarPrestamoRequest.getIdEquipo(), ingresarPrestamoRequest.getIdUso());

        //verificar si el profesor existe
        //verificar si el profesor esta inhabilitado
        verificarProfesor(ingresarPrestamoRequest.getRutProfesor());

        Prestamo prestamo = Prestamo.builder()
                .fecha(LocalDate.now())
                .hora(LocalTime.now())
                .descripcion(ingresarPrestamoRequest.getDescripcion())
                .idUso(ingresarPrestamoRequest.getIdUso())
                .idEquipo(ingresarPrestamoRequest.getIdEquipo())
                .rutProfesor(ingresarPrestamoRequest.getRutProfesor())
                .build();

        //Enviar evento para ocupar equipo
        actualizarDisponibilidadEquipo(ingresarPrestamoRequest.getIdEquipo(), false);

        return prestamoRepository.save(prestamo);
    }

    public Devolucion ingresarDevolucion(IngresarDevolucionRequest ingresarDevolucionRequest) throws JsonProcessingException {
        verificarEquipo(ingresarDevolucionRequest.getIdEquipo());

        Optional<Prestamo> prestamo = prestamoRepository.findPrestamosSinDevolucionByIdEquipo(ingresarDevolucionRequest.getIdEquipo());
        if(prestamo.isEmpty()) throw new RegistroNoExisteException("El equipo no ha sido prestado o ya ha sido devuelto.");

        Prestamo prestamoEntity = prestamo.get();
        //si esta dañado, enviar evento para aumentar contador daños en profesor e inhabilitarlo si hace falta
        verificarEstado(ingresarDevolucionRequest.getIdEstado(), prestamoEntity.getRutProfesor());

        //inhabilitar por una semana, enviar evento para inhabilitar por una semana
        verificarHora(prestamoEntity.getHora(), prestamoEntity.getFecha(), prestamoEntity.getRutProfesor());

        Devolucion devolucion = Devolucion.builder()
                .fecha(LocalDate.now())
                .hora(LocalTime.now())
                .descripcion(ingresarDevolucionRequest.getDescripcion())
                .estado(new EstadoDevolucion(ingresarDevolucionRequest.getIdEstado()))
                .build();
        prestamoEntity.setDevolucion(devolucion);

        actualizarDisponibilidadEquipo(prestamoEntity.getIdEquipo(), true);

        return prestamoRepository.save(prestamoEntity).getDevolucion();
    }

    private void actualizarDisponibilidadEquipo(Integer idEquipo, Boolean disponible) throws JsonProcessingException {
        //enviar evento para disponibilizar equipo
        ActualizarDisponibilidadEquipo actualizarDisponibilidadEquipo = ActualizarDisponibilidadEquipo.builder()
                .idEquipo(idEquipo)
                .disponible(disponible)
                .build();
        kafkaTemplate.send("actualizar-disponibilidad-equipo", objectMapper.writeValueAsString(actualizarDisponibilidadEquipo));
    }

    private void verificarEstado(Integer idEstado, String rutProfesor) {
        if(idEstado.equals(2)){
            kafkaTemplate.send("aumentar-cantidad-danios", rutProfesor);
        }
    }

    void verificarHora(LocalTime horaPrestamo, LocalDate fechaPrestamo, String rutProfesor) throws JsonProcessingException {
        LocalDateTime fechaHoraPrestamo = LocalDateTime.of(fechaPrestamo.getYear(), fechaPrestamo.getMonth(), fechaPrestamo.getDayOfMonth(), horaPrestamo.getHour(), horaPrestamo.getMinute());
        LocalDateTime fechaHoraPrestamoMaxima = fechaHoraPrestamo.plusHours(6);

        boolean excedeHoraMaxima = LocalDateTime.now().isAfter(fechaHoraPrestamoMaxima);

        if(excedeHoraMaxima) {
            InhabilitarProfesor inhabilitarProfesor = InhabilitarProfesor.builder()
                    .rut(rutProfesor)
                    .fechaInicio(LocalDate.now())
                    .fechaFin(LocalDate.now().plusWeeks(1))
                    .build();
            kafkaTemplate.send("inhabilitar-profesor", objectMapper.writeValueAsString(inhabilitarProfesor));
        }
    }

    public List<EntradaReporte> obtenerReporte(Integer idEquipo) {
        List<EntradaReporteQuery> reporteQuery = prestamoRepository.findReporteByIdEquipo(idEquipo);
        List<String> rutsProfesores = reporteQuery.stream().map(EntradaReporteQuery::getRutProfesor).distinct().toList();
        List<ProfesorDTO> profesores = obtenerProfesoresPorRuts(rutsProfesores);
        List<Integer> idsUso = reporteQuery.stream().map(EntradaReporteQuery::getIdUso).distinct().toList();
        Map<Integer, String> usos = usoFeignClient.obtenerNombresPorIdsUso(idsUso);
        Map<String, ProfesorDTO> profesoresMap = new HashMap<>();

        for(ProfesorDTO profesor : profesores) {
            profesoresMap.put(profesor.getRut(), profesor);
        }

        List<EntradaReporte> reporte = new ArrayList<>();
        for(EntradaReporteQuery entradaQ : reporteQuery) {
            ProfesorDTO profesor = profesoresMap.get(entradaQ.getRutProfesor());
            Devolucion devolucion = entradaQ.getDevolucion();
            EntradaReporte entradaReporte = EntradaReporte.builder()
                    .fechaPrestamo(entradaQ.getFechaPrestamo())
                    .horaPrestamo(entradaQ.getHoraPrestamo())
                    .rutProfesor(entradaQ.getRutProfesor())
                    .nombreProfesor(profesor.getNombre())
                    .apellidoProfesor(profesor.getApellido())
                    .fechaDevolucion(devolucion == null ? null : devolucion.getFecha())
                    .horaDevolucion(devolucion == null ? null : devolucion.getHora())
                    .duracionPrestamo(entradaQ.getDuracionPrestamo())
                    .estado(devolucion == null ? null : devolucion.getEstado().getDescripcion())
                    .uso(usos.get(entradaQ.getIdUso()))
                    .build();
            reporte.add(entradaReporte);
        }

        return reporte;
    }

    public List<ProfesorDTO> obtenerProfesoresPorRuts(List<String> rutsProfesores) {
        ProfesoresResponse response = profesorFeignClient.obtenerProfesores(rutsProfesores);
        return response.getProfesores();
    }

    public List<EstadoDevolucion> obtenerEstados() {
        return estadoDevolucionRepository.findAll();
    }
}
