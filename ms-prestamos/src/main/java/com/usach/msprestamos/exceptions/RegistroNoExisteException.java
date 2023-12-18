package com.usach.msprestamos.exceptions;


public class RegistroNoExisteException extends RuntimeException{
    public RegistroNoExisteException(String message){
        super(message);
    }
}
