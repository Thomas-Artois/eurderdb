package com.switchfully.eurderdb.exceptions;

public class EurderDoesntExistException extends RuntimeException{
    public EurderDoesntExistException() {
        super("Eurder doesn't exist");
    }
}
