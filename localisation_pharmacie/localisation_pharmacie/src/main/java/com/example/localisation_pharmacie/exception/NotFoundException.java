package com.example.localisation_pharmacie.exception;

public class NotFoundException extends Exception{
    private String message;

    public NotFoundException(String message){
        super(message);
        this.message=message;
    }

    public NotFoundException() {}
}