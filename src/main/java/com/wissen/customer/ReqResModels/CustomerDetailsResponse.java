package com.wissen.customer.ReqResModels;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDetailsResponse {
    private Integer customerId;
    private String name;
    private String phoneNumber;
    private String address;
}
