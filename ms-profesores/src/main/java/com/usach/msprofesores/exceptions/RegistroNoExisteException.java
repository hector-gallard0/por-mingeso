package com.usach.msprofesores.exceptions;


public class RegistroNoExisteException extends IllegalArgumentException{
    public RegistroNoExisteException(String message){
        super(message);
    }
}
