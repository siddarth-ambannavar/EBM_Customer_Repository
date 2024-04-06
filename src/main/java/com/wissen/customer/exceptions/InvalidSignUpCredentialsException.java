package com.wissen.customer.exceptions;

public class InvalidSignUpCredentialsException extends RuntimeException{

    public InvalidSignUpCredentialsException() {
        super("Invalid Credentials");
    }

    public InvalidSignUpCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
