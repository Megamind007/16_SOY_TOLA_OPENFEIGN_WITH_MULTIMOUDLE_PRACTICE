package org.example.service;


import org.example.model.Product;
import org.example.model.request.ProductRequest;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product createCustomer(ProductRequest productRequest);

    Optional<Product> getCustomerById(Long id);

    List<Product> getAllCustomers();

    Optional<Product> updateCustomer(Long id, ProductRequest productRequest);

    Void deleteCustomerById(Long id);
}
