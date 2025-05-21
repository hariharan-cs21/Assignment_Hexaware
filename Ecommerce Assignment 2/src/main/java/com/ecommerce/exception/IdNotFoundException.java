package com.ecommerce.exception;

public class IdNotFoundException extends Exception {
    private String message;

    public IdNotFoundException(String message) {
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
