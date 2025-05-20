package com.lms.exception;

public class UserNotFoundException extends Exception{
    private String message;
    public UserNotFoundException(String msg){
        message=msg;
    }
    public String getMessage(){
        return message;
    }
}
