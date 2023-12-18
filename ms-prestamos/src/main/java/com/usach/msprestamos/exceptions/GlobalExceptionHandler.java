package com.usach.msprestamos.exceptions;

import com.usach.msprestamos.dtos.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler{
    @ExceptionHandler(ApiErrorException.class)
    public ResponseEntity<Response> handleApiErrorException(ApiErrorException ex){
        Response response = new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RegistroNoExisteException.class)
    public ResponseEntity<Response> handleRegistroNoExisteException(RegistroNoExisteException ex){
        Response response = new Response(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DevolucionRealizadaException.class)
    public ResponseEntity<Response> handleDevolucionRealizadaException(DevolucionRealizadaException ex){
        Response response = new Response(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
