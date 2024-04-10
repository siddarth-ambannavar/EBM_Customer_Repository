package com.wissen.customer.services;

import com.wissen.customer.entities.Customer;
import com.wissen.customer.reqResModels.CustomerDetails;
import org.springframework.security.core.userdetails.UserDetails;

public interface CustService  {
    public UserDetails loadUserByPhoneNumber(String phoneNumber);
    public boolean isCustomerPhoneNumberExists();
    public boolean isCustomerPhoneNumberExists(String phoneNumber);
    public CustomerDetails addCustomer(Customer customer);
}
