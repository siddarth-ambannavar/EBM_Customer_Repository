package com.wissen.customer.reqResModels;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDetails {
    private Integer customerId;
    private String name;
    private String phoneNumber;
    private String address;
}
