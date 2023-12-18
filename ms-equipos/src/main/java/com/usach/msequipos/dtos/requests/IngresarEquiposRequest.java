package com.usach.msequipos.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class IngresarEquiposRequest {
    Integer idMarca;
    Integer idTipo;
    Integer cantidadEquipos;
    List<Integer> idsUsos;
}
