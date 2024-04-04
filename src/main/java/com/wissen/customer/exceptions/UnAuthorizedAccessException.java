package com.wissen.customer.exceptions;

public class UnAuthorizedAccessException extends RuntimeException{

    public UnAuthorizedAccessException() {
        super("Customer not authorized");
    }

    public UnAuthorizedAccessException(String errorMessage) {
        super(errorMessage);
    }
}
