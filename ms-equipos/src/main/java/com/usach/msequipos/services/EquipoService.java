package com.usach.msequipos.services;

import com.usach.msequipos.dtos.Catalogo;
import com.usach.msequipos.dtos.requests.IngresarEquiposRequest;
import com.usach.msequipos.entities.Equipo;
import com.usach.msequipos.entities.Marca;
import com.usach.msequipos.entities.TipoEquipo;
import com.usach.msequipos.entities.Uso;
import com.usach.msequipos.exceptions.EquipoNoDisponibleException;
import com.usach.msequipos.exceptions.RegistroNoExisteException;
import com.usach.msequipos.repositories.EquipoRepository;
import com.usach.msequipos.repositories.MarcaRepository;
import com.usach.msequipos.repositories.TipoEquipoRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EquipoService {
    EquipoRepository equipoRepository;
    MarcaRepository marcaRepository;
    TipoEquipoRepository tipoEquipoRepository;
    UsoService usoService;

    public EquipoService(EquipoRepository equipoRepository,
                         MarcaRepository marcaRepository,
                         TipoEquipoRepository tipoEquipoRepository,
                         UsoService usoService) {
        this.equipoRepository = equipoRepository;
        this.marcaRepository = marcaRepository;
        this.tipoEquipoRepository = tipoEquipoRepository;
        this.usoService = usoService;
    }

    public List<Equipo> ingresarEquipos(IngresarEquiposRequest ingresarEquiposRequest) {
        TipoEquipo tipo = new TipoEquipo();
        tipo.setId(ingresarEquiposRequest.getIdTipo());

        Marca marca = new Marca();
        marca.setId(ingresarEquiposRequest.getIdMarca());

        List<Uso> permisos = ingresarEquiposRequest.getIdsUsos().stream()
                .map(Uso::new).toList();

        Integer cantidad = ingresarEquiposRequest.getCantidadEquipos();

        List<Equipo> equipos = new ArrayList<>();
        for(int i = 0; i < cantidad; i++) {
            Equipo equipo = Equipo.builder()
                .tipo(tipo)
                .marca(marca)
                .usos(permisos)
                .disponible(true)
                .build();

            equipos.add(equipo);
        }

        return equipoRepository.saveAll(equipos);
    }

    public void verificarEquipoYUso(Integer idEquipo, Integer idUso) {
        Optional<Equipo> equipo = equipoRepository.findById(idEquipo);
        if(equipo.isEmpty()) throw new RegistroNoExisteException("No existe el equipo.");
        if(!equipo.get().getDisponible()) throw new EquipoNoDisponibleException("El equipo se encuentra en préstamo o no está disponible.");
        if(equipo.get().getUsos().stream().filter(uso -> uso.getId().equals(idUso)).findFirst().isEmpty()) throw new RegistroNoExisteException("El uso no existe o no está permitido para este equipo.");
    }

    public void verificarEquipo(Integer idEquipo) {
        if(!equipoRepository.existsById(idEquipo)) throw new EquipoNoDisponibleException("El equipo no existe.");
    }

    public Catalogo obtenerCatalogo() {
        List<Marca> marcas = marcaRepository.findAll();
        List<TipoEquipo> tiposEquipos = tipoEquipoRepository.findAll();
        List<Uso> usos = usoService.obtenerUsos();
        return Catalogo.builder()
                .marcas(marcas)
                .tipos(tiposEquipos)
                .usos(usos)
                .build();
    }

    public List<Equipo> obtenerEquipos(Boolean disponible) {
        return equipoRepository.findAllByDisponible(disponible);
    }
}
