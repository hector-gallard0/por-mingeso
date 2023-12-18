package com.usach.msprestamos.dtos;

import com.usach.msprestamos.entities.EstadoDevolucion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class EstadoDevolucionResponse extends Response{
    List<EstadoDevolucion> estados;

    public EstadoDevolucionResponse(List<EstadoDevolucion> estados, Integer status) {
        super(status);
        this.estados = estados;
    }

}
