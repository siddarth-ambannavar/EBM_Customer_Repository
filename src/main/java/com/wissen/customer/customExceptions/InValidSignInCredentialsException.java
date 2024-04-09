package com.wissen.customer.customExceptions;

public class InValidSignInCredentialsException extends RuntimeException{

    public InValidSignInCredentialsException() {
        super("Please provide valid details");
    }

    public InValidSignInCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
