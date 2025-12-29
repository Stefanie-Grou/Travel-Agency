package com.example.agenciadeviagens.services;

import com.example.agenciadeviagens.helper.Dummies;
import com.example.agenciadeviagens.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.logging.Logger;

@Component
public class CustomerServices {
    private static final Logger LOGGER = Logger.getLogger(CustomerServices.class.getName());

    public Customer create(String customer) {
        Customer customerFromDatabase = fetch(customer);
        if (customerFromDatabase != null) return customerFromDatabase;

        Customer createdCustomer = new Customer(customer);
        Dummies.addCustomer(createdCustomer);
        return new Customer(customer);
    }

    public Customer fetch(String userName) {
        Set<Customer> customers = Dummies.getCustomersList();
        return customers.stream()
                .filter(customer -> customer.getName().equalsIgnoreCase(userName))
                .findFirst().orElse(null);
    }
}
