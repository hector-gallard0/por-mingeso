package com.usach.msprofesores.exceptions;

public class ApiErrorException extends RuntimeException {
    public ApiErrorException(String message){
        super(message);
    }
}
