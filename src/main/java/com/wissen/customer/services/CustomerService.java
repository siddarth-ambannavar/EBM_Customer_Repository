package com.wissen.customer.services;

import com.wissen.customer.models.Customer;

import java.util.List;

public interface CustomerService {

    // TODO: JWT Authentication

    // register user
    void saveCustomer(Customer customer);

    // get customer details using id
    Customer getCustomer(int customerId);

    // get list of customers
    List<Customer> getCustomers();

    // check if customer exists
    boolean isCustomerIdExists(int customerId);

    // check if phone number already exists
    boolean isCustomerPhNoExists(String phNo);
}
