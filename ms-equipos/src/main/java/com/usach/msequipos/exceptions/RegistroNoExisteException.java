package com.usach.msequipos.exceptions;


public class RegistroNoExisteException extends RuntimeException{
    public RegistroNoExisteException(String message){
        super(message);
    }
}
