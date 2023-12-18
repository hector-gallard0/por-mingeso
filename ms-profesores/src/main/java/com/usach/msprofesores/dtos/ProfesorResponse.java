package com.usach.msprofesores.dtos;

import com.usach.msprofesores.entities.Profesor;
import lombok.Getter;

@Getter
public class ProfesorResponse extends Response{
    Profesor profesor;

    public ProfesorResponse(Profesor profesor, Integer status) {
        super(status);
        this.profesor = profesor;
    }
}
