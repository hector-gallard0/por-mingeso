package com.usach.msprofesores.exceptions;

import com.usach.msprofesores.dtos.Response;
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

    @ExceptionHandler(ProfesorInhabilitadoException.class)
    public ResponseEntity<Response> handleProfesor(ProfesorInhabilitadoException ex){
        Response response = new Response(HttpStatus.FORBIDDEN.value(), ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
