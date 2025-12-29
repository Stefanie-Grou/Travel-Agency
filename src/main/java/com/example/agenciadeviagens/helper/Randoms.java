package com.example.agenciadeviagens.helper;

import com.example.agenciadeviagens.models.Customer;

import java.util.Random;
import java.util.Set;

public class Randoms {

    private static final int MIN_LIMIT_FOR_DOUBLE = 1000;
    private static final int MAX_LIMIT_FOR_DOUBLE = 10000;

    private static Set<Customer> customerList;

    public static void setCustomerList(Set<Customer> customerList) {
        Randoms.customerList = customerList;
    }

    public static Long pickRandomLong(Long limit) {
        Random random = new Random();
        return random.nextLong(limit);
    }

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
        return customerList.stream().toList().get(random.nextInt(customerList.size()));
    }
}
