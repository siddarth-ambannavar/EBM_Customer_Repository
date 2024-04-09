package com.wissen.customer.reqResModels;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RegisterResponse {
    private int id;
    private String name;
    private String phoneNumber;
    private String token;
}
