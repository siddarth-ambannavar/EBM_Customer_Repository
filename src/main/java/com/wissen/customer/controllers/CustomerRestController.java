package com.wissen.customer.controllers;

import com.wissen.customer.customExceptions.CustomerAlreadyExistsException;
import com.wissen.customer.entities.Customer;
import com.wissen.customer.externals.MeterService;
import com.wissen.customer.reqResModels.CustomerDetails;
import com.wissen.customer.security.JwtHelper;
import com.wissen.customer.implementations.CustomerServiceImplementation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@CrossOrigin({"http://localhost:4200"})
@Slf4j
public class CustomerRestController {

    @Autowired
    private CustomerServiceImplementation customerServiceImplementation;
    @Autowired
    private JwtHelper helper;
    @Autowired
    private MeterService meterService;

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomers() {
        log.info("List of All Customers (ADMIN)");
        List<Customer> customers = customerServiceImplementation.retrieveAllCustomer();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

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
        log.info("User Details: {}", response.getName());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/get-id")
    public Integer getCustomerId(@RequestHeader("Authorization") String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = (Customer) authentication.getPrincipal();
        log.info("User id: {}", customer.getCustomerId());
        return customer.getCustomerId();
    }

    @Transactional
    @PutMapping("/updateprofile")
    public ResponseEntity<CustomerDetails> updateCustomerProfile(@RequestHeader("Authorization") String token, @RequestBody Customer customer) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer oldCustomer = (Customer) authentication.getPrincipal();
        customer.setCustomerId(oldCustomer.getCustomerId());
        if(customerServiceImplementation.isCustomerPhoneNumberExists(customer.getPhoneNumber()))
            throw new CustomerAlreadyExistsException("Please provide a new phone number");
        CustomerDetails updatedCustomer = customerServiceImplementation.updateCustomer(customer);
        log.info("Customer Profile Updated: {}", updatedCustomer.getName());
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/delete")
    public String deleteCustomer(@RequestHeader("Authorization") String token) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = (Customer) authentication.getPrincipal();
        int id = customer.getCustomerId();
        log.info(" ========== Customer ID: {}", id);
        String deleteMeterMsg = meterService.removeCustomerMeters(id);
        log.info("Meters and Usages Deleted");
        log.info(deleteMeterMsg);
        log.info("Customer Delete : {}", customer.getName());
        return customerServiceImplementation.removeCustomer(id);
    }
}
