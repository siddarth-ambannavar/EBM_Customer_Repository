package com.wissen.customer.customExceptions;

public class CustomerAlreadyExistsException extends RuntimeException{

    public CustomerAlreadyExistsException() {
        super("Customer already registered");
    }

    public CustomerAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
