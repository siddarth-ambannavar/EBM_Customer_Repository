package com.wissen.customer.implementations;

import com.wissen.customer.customExceptions.InValidLoginCredentialsException;
import com.wissen.customer.entities.Customer;
import com.wissen.customer.repositories.CustomerRepository;
import com.wissen.customer.reqResModels.CustomerDetails;
import com.wissen.customer.services.CustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustService, UserDetailsService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<Customer> retrieveAllCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public Customer loadUserByPhoneNumber(String phoneNumber) throws UsernameNotFoundException {
        return customerRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> {
            return new InValidLoginCredentialsException("Customer Not Found");
        });
    }

    @Override
    public boolean isCustomerPhoneNumberExists() {
        return false;
    }

    @Override
    public boolean isCustomerPhoneNumberExists(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber).isPresent();
    }
    @Override
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

    @Override
    public CustomerDetails updateCustomer(Customer customer) {
        Customer updatedCustomer = customerRepository.save(customer);
        return CustomerDetails.builder()
                .customerId(updatedCustomer.getCustomerId())
                .name(updatedCustomer.getName())
                .phoneNumber(updatedCustomer.getPhoneNumber())
                .address(updatedCustomer.getAddress())
                .build();
    }

    @Override
    public String removeCustomer(int id) {
        customerRepository.deleteById(id);
        return "Customer Deleted Successfully!";
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        return customerRepository.findByPhoneNumber(phoneNumber).orElse(null);
    }
}
