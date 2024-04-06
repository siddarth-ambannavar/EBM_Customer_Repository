package com.wissen.customer.repositories;

import com.wissen.customer.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Optional<Customer> findByPhNo(String phNo);
}
