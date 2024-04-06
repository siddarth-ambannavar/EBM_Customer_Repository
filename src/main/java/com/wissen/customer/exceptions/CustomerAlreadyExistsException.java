package com.wissen.customer.exceptions;

public class CustomerAlreadyExistsException extends RuntimeException{

    public CustomerAlreadyExistsException() {
        super("Customer already exists");
    }

    public CustomerAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
