package com.wissen.customer.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", initialValue = 1, allocationSize = 1)
//public class Customer implements UserDetails {
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    private Integer customerId;
    private String name;
    private String phNo;
    private String password;
    private String address;

    public Customer() {
        // Default Constructor
    }

    public Customer(String name, String phNo, String password, String address) {
        this.name = name;
        this.phNo = phNo;
        this.password = password;
        this.address = address;
    }

    //    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
}
