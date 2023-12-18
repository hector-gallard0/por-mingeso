package com.usach.msequipos.exceptions;

public class ApiErrorException extends RuntimeException {
    public ApiErrorException(String message){
        super(message);
    }
}
