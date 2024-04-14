package com.wissen.customer.externals;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("METERSERVICE")
public interface MeterService {

    @DeleteMapping("/removecustmeters/{customerId}")
    String removeCustomerMeters(@PathVariable int customerId);
}
