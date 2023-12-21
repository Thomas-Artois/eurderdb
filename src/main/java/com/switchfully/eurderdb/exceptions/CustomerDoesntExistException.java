package com.switchfully.eurderdb.exceptions;

public class CustomerDoesntExistException extends RuntimeException{
    public CustomerDoesntExistException() {
        super("Customer not found");
    }
}
