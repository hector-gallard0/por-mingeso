package com.usach.msequipos.dtos;

import com.usach.msequipos.entities.Marca;
import com.usach.msequipos.entities.TipoEquipo;
import com.usach.msequipos.entities.Uso;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Catalogo {
    public List<Marca> marcas;
    public List<TipoEquipo> tipos;
    public List<Uso> usos;
}
