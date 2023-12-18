package com.usach.msprestamos.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ReporteResponse extends Response {
    List<EntradaReporte> reporte;

    public ReporteResponse(List<EntradaReporte> reporte, Integer status) {
        super(status);
        this.reporte = reporte;
    }
}
