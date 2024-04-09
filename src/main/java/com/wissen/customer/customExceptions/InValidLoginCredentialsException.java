package com.wissen.customer.customExceptions;

public class InValidLoginCredentialsException extends RuntimeException{

    public InValidLoginCredentialsException() {
        super("Please provide valid details");
    }

    public InValidLoginCredentialsException(String errorMessage) {
        super(errorMessage);
    }
}
