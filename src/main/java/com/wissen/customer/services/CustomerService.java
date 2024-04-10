package com.wissen.customer.services;

import com.wissen.customer.entities.Customer;
import com.wissen.customer.repositories.CustomerRepository;
import com.wissen.customer.reqResModels.CustomerDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return customerRepository.findByPhoneNumber(phoneNumber).orElse(null);
    }

    public Customer loadUserByPhoneNumber(String phoneNumber) throws UsernameNotFoundException {
        return customerRepository.findByPhoneNumber(phoneNumber).orElseThrow();
    }

    public boolean isCustomerIdExists(Integer customerId) {
        return customerRepository.existsById(customerId);
    }

    public boolean isCustomerPhoneNumberExists(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber).isPresent();
    }

    public CustomerDetails addCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        Customer newCustomer = customerRepository.save(customer);
        return CustomerDetails.builder()
                .customerId(newCustomer.getCustomerId())
                .name(newCustomer.getName())
                .phoneNumber(newCustomer.getPhoneNumber())
                .address(newCustomer.getAddress())
                .build();
    }
}
