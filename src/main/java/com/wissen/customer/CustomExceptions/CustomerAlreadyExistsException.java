package com.wissen.customer.CustomExceptions;

public class CustomerAlreadyExistsException extends RuntimeException{

    public CustomerAlreadyExistsException() {
        super("Customer already registered");
    }

    public CustomerAlreadyExistsException(String errorMessage) {
        super(errorMessage);
    }
}
