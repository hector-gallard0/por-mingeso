package com.usach.msequipos.listeners;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.usach.msequipos.dtos.ActualizarDisponibilidadEquipo;
import com.usach.msequipos.entities.Equipo;
import com.usach.msequipos.exceptions.RegistroNoExisteException;
import com.usach.msequipos.repositories.EquipoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class KafkaListeners {

    EquipoRepository equipoRepository;
    ObjectMapper objectMapper;

    @Autowired
    public KafkaListeners(EquipoRepository equipoRepository,
                          ObjectMapper objectMapper) {
        this.equipoRepository = equipoRepository;
        this.objectMapper = objectMapper;
    }

    @KafkaListener(
            topics = "actualizar-disponibilidad-equipo",
            groupId = "groupId")
    @Transactional
    public void actualizarDisponibilidadEquipo(String request) {
        try{
            JsonParser parser = objectMapper.createParser(request);
            ActualizarDisponibilidadEquipo actualizarDisponibilidadEquipo = parser.readValueAs(ActualizarDisponibilidadEquipo.class);
            Optional<Equipo> equipoOptional = equipoRepository.findById(actualizarDisponibilidadEquipo.getIdEquipo());
            if(equipoOptional.isEmpty()) throw new RegistroNoExisteException("El equipo no existe");
            Equipo equipo = equipoOptional.get();
            equipo.setDisponible(actualizarDisponibilidadEquipo.getDisponible());
            equipoRepository.save(equipo);
        }catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
