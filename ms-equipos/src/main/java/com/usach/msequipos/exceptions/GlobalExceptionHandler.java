package com.usach.msequipos.exceptions;

import com.usach.msequipos.dtos.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(RegistroNoExisteException.class)
    public ResponseEntity<Response> handleRegistroNoExisteException(RegistroNoExisteException ex){
        Response response = new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EquipoNoDisponibleException.class)
    public ResponseEntity<Response> handleRegistroNoExisteException(EquipoNoDisponibleException ex){
        Response response = new Response(HttpStatus.FORBIDDEN.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
