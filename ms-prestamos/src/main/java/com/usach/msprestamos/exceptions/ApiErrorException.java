package com.usach.msprestamos.exceptions;

public class ApiErrorException extends RuntimeException {
    public ApiErrorException(String message){
        super(message);
    }
}
