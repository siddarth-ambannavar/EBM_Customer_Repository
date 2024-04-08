package com.wissen.customer.CustomExceptions;

public class UnAuthenticatedAccessException extends RuntimeException{

    public UnAuthenticatedAccessException() {
        super("Please login");
    }

    public UnAuthenticatedAccessException(String errorMessage) {
        super(errorMessage);
    }
}
