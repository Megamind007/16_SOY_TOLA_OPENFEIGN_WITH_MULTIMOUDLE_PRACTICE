package org.example.service;


import org.example.model.request.CustomerRequest;

public interface CustomerService {
    Object createCustomer(CustomerRequest customerRequest);

    Object getCustomerById(Long id);

    Object getAllCustomers();

    Object updateCustomer(Long id, CustomerRequest customerRequest);

    Void deleteCustomerById(Long id);
}
