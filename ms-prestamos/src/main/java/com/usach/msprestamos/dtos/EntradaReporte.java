package com.usach.msprestamos.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EntradaReporte {
    LocalDate fechaPrestamo;
    LocalTime horaPrestamo;
    LocalDate fechaDevolucion;
    LocalTime horaDevolucion;
    String rutProfesor;
    String nombreProfesor;
    String apellidoProfesor;
    Integer duracionPrestamo;
    String estado;
    String uso;
}
