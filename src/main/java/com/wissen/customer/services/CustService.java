package com.wissen.customer.services;

import com.wissen.customer.entities.Customer;
import com.wissen.customer.reqResModels.CustomerDetails;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CustService  {
    List<Customer> retrieveAllCustomer();
    UserDetails loadUserByPhoneNumber(String phoneNumber);
    boolean isCustomerPhoneNumberExists();
    boolean isCustomerPhoneNumberExists(String phoneNumber);
    CustomerDetails addCustomer(Customer customer);
    CustomerDetails updateCustomer(Customer customer);
    String removeCustomer(int id);
}
