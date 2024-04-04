package com.wissen.customer.responseBodies;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerObjectResponse {
    private Integer customerId;
    private String name;
    private String phNo;
    private String address;
    private String token;
}
