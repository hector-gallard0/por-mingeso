package com.usach.msequipos.dtos;

import com.usach.msequipos.entities.Equipo;
import lombok.Getter;

import java.util.List;

@Getter
public class EquiposResponse extends Response{
     List<Equipo> equipos;

    public EquiposResponse(List<Equipo> equipos, Integer status) {
        super(status);
        this.equipos = equipos;
    }
}
