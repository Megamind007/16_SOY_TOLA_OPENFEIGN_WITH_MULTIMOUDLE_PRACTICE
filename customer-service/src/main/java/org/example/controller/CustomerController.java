package org.example.controller;
import org.example.dto.response.ApiResponse;
import org.example.model.request.CustomerRequest;
import org.example.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/create-customer")
    private ResponseEntity<ApiResponse<?>> createCustomer(@RequestBody CustomerRequest customerRequest) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Create customer successfully")
                .data(customerService.createCustomer(customerRequest))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-customer-by-id/{id}")
    private ResponseEntity<ApiResponse<?>> getCustomerById(@PathVariable Long id) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Get customer successfully")
                .data(customerService.getCustomerById(id))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-all-customer")
    private ResponseEntity<ApiResponse<?>> getAllCustomers() {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Get customers successfully")
                .data(customerService.getAllCustomers())
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update-customer-by-id/{id}")
    private ResponseEntity<ApiResponse<?>> updateCustomer(@PathVariable Long id ,@RequestBody CustomerRequest customerRequest) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Update customer successfully")
                .data(customerService.updateCustomer(id,customerRequest))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete-customer-by-id/{id}")
    private ResponseEntity<ApiResponse<?>> deleteCustomerById(@PathVariable Long id) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Delete customers successfully")
                .data(customerService.deleteCustomerById(id))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
