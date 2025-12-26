package com.example.agenciadeviagens.services;

import com.example.agenciadeviagens.models.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerServices implements Actions<Customer> {
    @Override
    public Customer create(String customer) {
        return new Customer(customer);
    }

    @Override
    public Customer fetch(Long id, List<Customer> list) {
        return list.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
    }
}
