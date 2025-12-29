package com.example.agenciadeviagens.helper;

import com.example.agenciadeviagens.models.Customer;

import java.util.Random;
import java.util.Set;

import static com.example.agenciadeviagens.helper.Dummies.getCustomersList;

public class Randoms {

    private static final int MIN_LIMIT_FOR_DOUBLE = 1000;
    private static final int MAX_LIMIT_FOR_DOUBLE = 10000;

    public static Boolean pickRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

    public static double createRandomDouble() {
        Random random = new Random();
        return MIN_LIMIT_FOR_DOUBLE + (MAX_LIMIT_FOR_DOUBLE - MIN_LIMIT_FOR_DOUBLE) * random.nextDouble();
    }

    public static int pickRandomInt(int limit) {
        Random random = new Random();
        return random.nextInt(limit);
    }

    public static Customer pickRandomCustomer() {
        Random random = new Random();
        Set<Customer> customerList = getCustomersList();
        return customerList.stream().toList().get(random.nextInt(customerList.size()));
    }
}
