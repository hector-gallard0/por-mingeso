package com.usach.msprestamos.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReporteResponse extends Response {
    List<EntradaReporte> reporte;

    public ReporteResponse(List<EntradaReporte> reporte, Integer status) {
        super(status);
        this.reporte = reporte;
    }
}
