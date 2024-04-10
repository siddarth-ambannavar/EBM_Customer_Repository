package com.wissen.customer.reqResModels;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterResponse {
    private String message;
    private final boolean success = true;
    private HttpStatus status;
    private CustomerDetails customer;
}
