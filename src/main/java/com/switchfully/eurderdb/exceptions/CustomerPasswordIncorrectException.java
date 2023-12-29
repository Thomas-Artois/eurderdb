package com.switchfully.eurderdb.exceptions;

public class CustomerPasswordIncorrectException extends RuntimeException{
    public CustomerPasswordIncorrectException() {
        super("Password incorrect");
    }
}
