package com.switchfully.eurderdb.exceptions;

public class ItemDoesntExistException extends RuntimeException{
    public ItemDoesntExistException() {
        super("Item does not exist");
    }
}
