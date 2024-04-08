package com.wissen.customer.ReqResModels;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JwtRequest {
    private String phoneNumber;
    private String password;
}
