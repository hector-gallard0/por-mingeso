package com.usach.msprestamos.dtos;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InhabilitarProfesor {
    String rut;
    LocalDate fechaInicio;
    LocalDate fechaFin;
}
