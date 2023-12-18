package com.usach.msprofesores.dtos;

import com.usach.msprofesores.entities.Profesor;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfesoresResponse extends Response{
    List<ProfesorDTO> profesores;

    public ProfesoresResponse(List<ProfesorDTO> profesores, Integer status) {
        super(status);
        this.profesores = profesores;
    }
}
