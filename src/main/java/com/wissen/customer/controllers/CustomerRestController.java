package com.wissen.customer.Controllers;

import com.wissen.customer.Entities.Customer;
import com.wissen.customer.Services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    @Autowired
    private CustomerService customerService;

    @GetMapping
    public List<Customer> getUser() {
        return customerService.getCustomers();
    }

    @GetMapping("/user")
    public String getLoggedInUser(Principal principal) {
        return principal.getName();
    }
}
