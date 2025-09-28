package com.philipnerubenko.spring.mvc.rest.exception_handling;

public class NoSuchEmployeeException extends RuntimeException{

    public NoSuchEmployeeException(String message) {
        super(message);
    }
    
}
