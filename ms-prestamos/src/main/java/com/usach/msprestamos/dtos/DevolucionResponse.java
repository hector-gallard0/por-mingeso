package com.usach.msprestamos.dtos;

import com.usach.msprestamos.entities.Devolucion;
import com.usach.msprestamos.entities.Prestamo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DevolucionResponse extends Response {
    Devolucion devolucion;

    public DevolucionResponse(Devolucion devolucion, Integer status) {
        super(status);
        this.devolucion = devolucion;
    }
}
