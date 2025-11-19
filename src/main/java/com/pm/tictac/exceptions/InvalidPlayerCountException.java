package com.pm.tictac.exceptions;

public class InvalidPlayerCountException extends RuntimeException{
    public InvalidPlayerCountException(String message){
        super(message);
    }
}
