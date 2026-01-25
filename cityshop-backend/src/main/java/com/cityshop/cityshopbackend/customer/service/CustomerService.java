package com.cityshop.cityshopbackend.customer.service;

import com.cityshop.cityshopbackend.customer.Customer;
import com.cityshop.cityshopbackend.customer.CustomerRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomersById(Long customerId) {
        return customerRepository.getCustomersById(customerId);
    }

    public Long getCurrentCustomerId() {
        return (Long) Objects.requireNonNull(SecurityContextHolder
                        .getContext()
                        .getAuthentication())
                .getPrincipal();
    }
}
