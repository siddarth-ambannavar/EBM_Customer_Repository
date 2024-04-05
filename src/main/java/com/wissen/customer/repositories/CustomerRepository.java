package com.wissen.customer.repositories;

import com.wissen.customer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    public Optional<Customer> findByPhno(String phone);
}
