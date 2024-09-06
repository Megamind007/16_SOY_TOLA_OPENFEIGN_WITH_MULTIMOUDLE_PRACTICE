package org.example.client;


import org.example.dto.response.ApiResponse;
import org.example.dto.response.CustomerResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(name = "customer-service",url = "localhost:8081/api/v1/customers")
public interface CustomerClient {
    @GetMapping("/get-customer-by-id/{id}")
    ApiResponse<CustomerResponse> getCustomerById(@PathVariable Long id);
}
