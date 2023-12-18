package com.usach.msequipos.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    Integer status;
    String message;

    public Response(Integer status){
        this.status = status;
    }

    public Response(Integer status, String message){
        this.status = status;
        this.message = message;
    }

}
