package com.usach.msprestamos.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IngresarDevolucionRequest {
    Integer idEquipo;
    String descripcion;
    Integer idEstado;
}
