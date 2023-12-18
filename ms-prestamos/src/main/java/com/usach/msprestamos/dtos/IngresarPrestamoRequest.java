package com.usach.msprestamos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;

@Getter
@Setter
public class IngresarPrestamoRequest {
    String descripcion;
    String rutProfesor;
    Integer idUso;
    Integer idEquipo;
}
