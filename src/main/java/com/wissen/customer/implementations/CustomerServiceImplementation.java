package com.wissen.customer.implementations;

import com.wissen.customer.models.Customer;
import com.wissen.customer.repositories.CustomerRepository;
import com.wissen.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomer(int customerId) {
        return customerRepository.findById(customerId).orElseThrow();
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public boolean isCustomerIdExists(int customerId) {
        return customerRepository.existsById(customerId);
    }

    @Override
    public boolean isCustomerPhNoExists(String phNo) {
        Customer c = customerRepository.findByPhNo(phNo).orElse(null);
        return c != null;
    }
}
