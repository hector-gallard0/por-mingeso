package com.usach.msequipos.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatalogoResponse extends Response {
    Catalogo catalogo;

    public CatalogoResponse(Catalogo catalogo, Integer status) {
        super(status);
        this.catalogo = catalogo;
    }
}
