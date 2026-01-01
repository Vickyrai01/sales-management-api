package com.github.vickyrai01.salesmanagement.exceprion;

public class BodyIsEmptyException extends RuntimeException{
    public BodyIsEmptyException(String message) {
        super(message);
    }
}
