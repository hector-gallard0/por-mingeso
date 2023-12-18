package com.usach.msprofesores.listeners;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usach.msprofesores.dtos.InhabilitarProfesor;
import com.usach.msprofesores.entities.Inhabilitacion;
import com.usach.msprofesores.entities.Profesor;
import com.usach.msprofesores.exceptions.RegistroNoExisteException;
import com.usach.msprofesores.repositories.InhabilitacionRepository;
import com.usach.msprofesores.repositories.ProfesorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class KafkaListeners {

    InhabilitacionRepository inhabilitacionRepository;
    ProfesorRepository profesorRepository;
    ObjectMapper objectMapper;

    @Autowired
    public KafkaListeners(InhabilitacionRepository inhabilitacionRepository,
                          ProfesorRepository profesorRepository,
                          ObjectMapper objectMapper
                          ) {
        this.inhabilitacionRepository = inhabilitacionRepository;
        this.profesorRepository = profesorRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(topics = "aumentar-cantidad-danios",
                    groupId = "groupId"
    )
    @Transactional
    public void aumentarCantidadDanios(String rutProfesor) {
        Optional<Profesor> profesor = profesorRepository.findById(rutProfesor);
        if(profesor.isEmpty()) throw new RegistroNoExisteException("El profesor no existe.");
        Profesor profesorEntity = profesor.get();
        profesorEntity.setCantidadDanios(profesorEntity.getCantidadDanios() + 1);
        if(profesorEntity.getCantidadDanios() >= 3) {
            Inhabilitacion inhabilitacion = Inhabilitacion.builder()
                    .profesor(profesorEntity)
                    .fechaInicio(LocalDate.now())
                    .fechaFin(LocalDate.now().plusYears(1))
                    .build();
            inhabilitacionRepository.save(inhabilitacion);
        }
        profesorRepository.save(profesorEntity);
    }

    @KafkaListener(
            topics = "inhabilitar-profesor",
            groupId = "groupId"
    )
    void inhabilitarProfesor(String request) {
        try{
            JsonParser parser = objectMapper.createParser( request);
            InhabilitarProfesor inhabilitarProfesor = parser.readValueAs(InhabilitarProfesor.class);
            Optional<Profesor> profesor = profesorRepository.findById(inhabilitarProfesor.getRut());
            if(profesor.isEmpty()) throw new RegistroNoExisteException("El profesor no existe.");
            Profesor profesorEntity = profesor.get();
            Inhabilitacion inhabilitacion = Inhabilitacion.builder()
                    .profesor(profesorEntity)
                    .fechaInicio(inhabilitarProfesor.getFechaInicio())
                    .fechaFin(inhabilitarProfesor.getFechaFin())
                    .build();
            inhabilitacionRepository.save(inhabilitacion);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
