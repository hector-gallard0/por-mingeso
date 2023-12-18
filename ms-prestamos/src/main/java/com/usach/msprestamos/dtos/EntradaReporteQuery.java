package com.usach.msprestamos.dtos;

import com.usach.msprestamos.entities.Devolucion;
import com.usach.msprestamos.entities.EstadoDevolucion;

import java.time.LocalDate;
import java.time.LocalTime;

public interface EntradaReporteQuery {
    LocalDate getFechaPrestamo();
    LocalTime getHoraPrestamo();
    Devolucion getDevolucion();
    String getRutProfesor();
    Integer getDuracionPrestamo();
    EstadoDevolucion getEstado();
    Integer getIdUso();
}
