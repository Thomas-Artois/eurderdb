package com.switchfully.eurderdb.exceptions;

public class AdminPasswordIncorrectException extends RuntimeException{
    public AdminPasswordIncorrectException() {
        super("Password incorrect");
    }
}
