package com.usach.msprestamos.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfesoresResponse extends Response {
    List<ProfesorDTO> profesores;

    public ProfesoresResponse(List<ProfesorDTO> profesores, Integer status) {
        super(status);
        this.profesores = profesores;
    }
}
