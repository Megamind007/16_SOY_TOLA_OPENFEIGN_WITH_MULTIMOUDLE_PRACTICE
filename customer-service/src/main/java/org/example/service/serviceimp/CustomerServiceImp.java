package org.example.service.serviceimp;

import org.example.model.Customer;
import org.example.model.request.CustomerRequest;
import org.example.repository.CustomerRepository;
import org.example.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer createCustomer(CustomerRequest customerRequest) {
        return customerRepository.save(customerRequest.toEntity());
    }

    @Override
    public Optional<Customer> getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).get();
        return Optional.of(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Optional<Customer> updateCustomer(Long id, CustomerRequest customerRequest) {
        Optional<Customer> customer = Optional.of(customerRepository.findById(id).get());
        if(customer.isPresent()){
            customer.get().setName(customerRequest.getName());
            customer.get().setEmail(customerRequest.getEmail());
            customerRepository.save(customer.get());
        }
        return customer;
    }

    @Override
    public Void deleteCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).get();
        if(customer != null){
            customerRepository.delete(customer);
        }
        return null;
    }
}
