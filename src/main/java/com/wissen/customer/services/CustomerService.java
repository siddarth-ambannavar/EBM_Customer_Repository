package com.wissen.customer.Services;

import com.wissen.customer.CustomExceptions.UnAuthenticatedAccessException;
import com.wissen.customer.Entities.Customer;
import com.wissen.customer.Repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Customer addCustomer(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }
}
