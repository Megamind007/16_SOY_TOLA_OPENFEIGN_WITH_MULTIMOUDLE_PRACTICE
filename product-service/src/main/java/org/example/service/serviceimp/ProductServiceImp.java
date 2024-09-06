package org.example.service.serviceimp;

import org.example.model.Product;
import org.example.model.request.ProductRequest;
import org.example.repository.ProductRepository;
import org.example.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImp implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product createCustomer(ProductRequest productRequest) {
        return productRepository.save(productRequest.toEntity());
    }

    @Override
    public Optional<Product> getCustomerById(Long id) {
        Product product = productRepository.findById(id).get();
        return Optional.of(product);
    }

    @Override
    public List<Product> getAllCustomers() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> updateCustomer(Long id, ProductRequest productRequest) {
        Optional<Product> customer = Optional.of(productRepository.findById(id).get());
        if(customer.isPresent()){
            customer.get().setName(productRequest.getName());
            customer.get().setPrice(productRequest.getPrice());
            productRepository.save(customer.get());
        }
        return customer;
    }

    @Override
    public Void deleteCustomerById(Long id) {
        Product product = productRepository.findById(id).get();
        if(product != null){
            productRepository.delete(product);
        }
        return null;
    }
}
