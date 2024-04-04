package com.wissen.customer.exceptions;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException() {
        super("Customer account not found!");
    }

    public CustomerNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
