package com.usach.msprestamos.dtos;

import com.usach.msprestamos.entities.Prestamo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class PrestamoResponse extends Response {
    Prestamo prestamo;

    public PrestamoResponse(Prestamo prestamo, Integer status) {
        super(status);
        this.prestamo = prestamo;
    }
}
