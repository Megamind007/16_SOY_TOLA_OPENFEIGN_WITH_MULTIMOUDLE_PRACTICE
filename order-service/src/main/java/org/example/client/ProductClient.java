package org.example.client;


import org.example.dto.response.ApiResponse;
import org.example.dto.response.ProductResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "product-service",url = "localhost:8082/api/v1/products")
public interface ProductClient {
    @GetMapping("/get-product-by-id/{id}")
    ApiResponse<ProductResponse> getProductById(@PathVariable Long id);

    @GetMapping("/get-all-products")
    ApiResponse<List<ProductResponse>> getAllProducts();
}
