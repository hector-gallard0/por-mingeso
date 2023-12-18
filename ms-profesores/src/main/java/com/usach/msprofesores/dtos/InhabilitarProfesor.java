package com.usach.msprofesores.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class InhabilitarProfesor {
    String rut;
    LocalDate fechaInicio;
    LocalDate fechaFin;
}
