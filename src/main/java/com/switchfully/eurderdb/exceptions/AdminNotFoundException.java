package com.switchfully.eurderdb.exceptions;

public class AdminNotFoundException extends RuntimeException{

    public AdminNotFoundException() {
        super("Admin not found");
    }

}
