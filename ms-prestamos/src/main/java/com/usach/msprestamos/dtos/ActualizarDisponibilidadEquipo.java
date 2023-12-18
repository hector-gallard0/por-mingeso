package com.usach.msprestamos.dtos;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActualizarDisponibilidadEquipo {
    Integer idEquipo;
    Boolean disponible;
}
