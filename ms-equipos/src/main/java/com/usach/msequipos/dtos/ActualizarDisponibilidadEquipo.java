package com.usach.msequipos.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarDisponibilidadEquipo {
    Integer idEquipo;
    Boolean disponible;
}
