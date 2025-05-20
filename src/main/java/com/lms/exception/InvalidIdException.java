package com.lms.exception;

public class InvalidIdException extends Exception{
    private String message;
    public InvalidIdException(String message) {
        this.message = message;
    }
    public String getMessage() {
        return message;
    }

}
