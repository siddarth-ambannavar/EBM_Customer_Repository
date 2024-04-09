package com.wissen.customer.controllers;

import com.wissen.customer.customExceptions.CustomerAlreadyExistsException;
import com.wissen.customer.customExceptions.InValidSignInCredentialsException;
import com.wissen.customer.entities.Customer;
import com.wissen.customer.reqResModels.CustomerDetailsResponse;
import com.wissen.customer.reqResModels.JwtRequest;
import com.wissen.customer.reqResModels.JwtResponse;
import com.wissen.customer.security.JwtHelper;
import com.wissen.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin({"http://localhost:4200"})
public class AuthRestController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private JwtHelper helper;
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        this.doAuthenticate(request.getPhoneNumber(), request.getPassword());
        Customer userDetails = customerService.loadUserByPhoneNumber(request.getPhoneNumber());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .name(userDetails.getUsername())
                .phoneNumber(userDetails.getPhoneNumber())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String phoneNumber, String password) {
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(phoneNumber, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }
    }

    @PostMapping("/register")
    public ResponseEntity<CustomerDetailsResponse> createUser(@RequestBody Customer customer) {
        if(customerService.isCustomerPhoneNumberExists(customer.getPhoneNumber()))
            throw new CustomerAlreadyExistsException("Customer with this phone number is already registered");
        if(customer.getPhoneNumber().length() != 10)
            throw new InValidSignInCredentialsException("Please provide valid phone number");
        if(!customer.getPhoneNumber().matches("\\d+"))
            throw new InValidSignInCredentialsException("Please provide valid phone number");
        Customer newUser = customerService.addCustomer(customer);
        CustomerDetailsResponse response = CustomerDetailsResponse.builder()
                .customerId(newUser.getCustomerId())
                .name(newUser.getName())
                .phoneNumber(newUser.getPhoneNumber())
                .address(newUser.getAddress())
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }
}
