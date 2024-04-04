package com.wissen.customer.exceptions;

public class UnAuthenticatedAccessException extends RuntimeException {

    public UnAuthenticatedAccessException() {
        super("Customer not authenticated!");
    }

    public UnAuthenticatedAccessException(String errorMessage) {
        super(errorMessage);
    }
}
