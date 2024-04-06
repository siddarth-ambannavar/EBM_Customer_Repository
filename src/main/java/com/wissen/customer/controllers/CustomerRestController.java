package com.wissen.customer.controllers;

import com.wissen.customer.exceptions.CustomerAlreadyExistsException;
import com.wissen.customer.exceptions.CustomerNotFoundException;
import com.wissen.customer.exceptions.InvalidSignUpCredentialsException;
import com.wissen.customer.models.Customer;
import com.wissen.customer.responseBodies.CustomerListResponse;
import com.wissen.customer.responseBodies.CustomerObjectResponse;
import com.wissen.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/add-customer")
    public ResponseEntity<CustomerObjectResponse> register(@RequestBody Customer customer) {
        if(customer.getPhNo().length() != 10)
            throw new InvalidSignUpCredentialsException("Phone number must be 10 digits long");
        if(customer.getPassword().length() < 8)
            throw new InvalidSignUpCredentialsException("Password must be atleast 8 characters long");
        if(customerService.isCustomerPhNoExists(customer.getPhNo()))
            throw new CustomerAlreadyExistsException("Phone number already registered");
        customerService.saveCustomer(customer);
        CustomerObjectResponse response = CustomerObjectResponse.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .phNo(customer.getPhNo())
                .address(customer.getAddress())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CustomerListResponse> getAllCustomers() {
        List<Customer> customers = customerService.getCustomers();
        CustomerListResponse customersList = CustomerListResponse.builder()
                .customers(customers)
                .build();
        return new ResponseEntity<>(customersList, HttpStatus.OK);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerObjectResponse> getCustomerDetails(@PathVariable int customerId) {
        if(!customerService.isCustomerIdExists(customerId))
            throw new CustomerNotFoundException("Customer with Id: " + customerId + " not found");
        Customer customer = customerService.getCustomer(customerId);
        CustomerObjectResponse response = CustomerObjectResponse.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .phNo(customer.getPhNo())
                .address(customer.getAddress())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
