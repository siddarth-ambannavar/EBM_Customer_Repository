package com.wissen.customer.controllers;

import com.wissen.customer.entities.Customer;
import com.wissen.customer.reqResModels.CustomerDetails;
import com.wissen.customer.security.JwtHelper;
import com.wissen.customer.implementations.CustomerServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    @Autowired
    private CustomerServiceImplementation customerServiceImplementation;
    @Autowired
    private JwtHelper helper;

    @GetMapping("/user")
    public ResponseEntity<CustomerDetails> getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = (Customer) authentication.getPrincipal();
        CustomerDetails response = CustomerDetails.builder()
                .customerId(customer.getCustomerId())
                .name(customer.getName())
                .phoneNumber(customer.getPhoneNumber())
                .address(customer.getAddress())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
