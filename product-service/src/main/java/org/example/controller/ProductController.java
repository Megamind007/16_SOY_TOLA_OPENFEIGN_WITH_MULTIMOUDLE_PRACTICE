package org.example.controller;


import org.example.dto.response.ApiResponse;
import org.example.model.request.ProductRequest;
import org.example.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/create-product")
    private ResponseEntity<ApiResponse<?>> createCustomer(@RequestBody ProductRequest productRequest) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Create product successfully")
                .data(productService.createCustomer(productRequest))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/get-product-by-id/{id}")
    private ResponseEntity<ApiResponse<?>> getCustomerById(@PathVariable Long id) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Get product successfully")
                .data(productService.getCustomerById(id))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-all-products")
    private ResponseEntity<ApiResponse<?>> getAllCustomers() {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Get products successfully")
                .data(productService.getAllCustomers())
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/update-product-by-id/{id}")
    private ResponseEntity<ApiResponse<?>> updateCustomer(@PathVariable Long id ,@RequestBody ProductRequest productRequest) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Update product successfully")
                .data(productService.updateCustomer(id, productRequest))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/delete-customer-by-id/{id}")
    private ResponseEntity<ApiResponse<?>> deleteCustomerById(@PathVariable Long id) {
        ApiResponse<?> response = ApiResponse.builder()
                .message("Delete customers successfully")
                .data(productService.deleteCustomerById(id))
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
