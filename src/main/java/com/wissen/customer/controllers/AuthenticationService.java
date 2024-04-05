package com.wissen.customer.controllers;

import com.wissen.customer.config.JwtService;
import com.wissen.customer.models.Customer;
import com.wissen.customer.models.Role;
import com.wissen.customer.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final CustomerRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request){
        Customer customer = Customer.builder()
                .name(request.getName())
                .phno(request.getPhno())
                .password(passwordEncoder.encode(request.getPassword()))
                .address(request.getAddress())
                .role(Role.CUSTOMER)
                .build();
        repository.save(customer);
        String jwtToken = jwtService.generateToken(customer);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        System.out.println("here 1");
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getPhno(),
                        request.getPassword()
                )
        );
        System.out.println("here 2");
        Customer customer = repository.findByPhno(request.getPhno()).orElseThrow();
        String jwtToken = jwtService.generateToken(customer);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
