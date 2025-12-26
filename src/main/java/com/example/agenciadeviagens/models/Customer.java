package com.example.agenciadeviagens.models;

import static com.example.agenciadeviagens.helper.Randoms.pickRandomLong;

public class Customer {

    private static final Double DEFAULT_CASHBACK = 0.0;

    private Long id;
    private String name;
    private Double cashback;

    public Customer() {
    }

    public Customer(String name) {
        this.id = pickRandomLong(1000000L);
        this.name = name;
        this.cashback = DEFAULT_CASHBACK;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCashback() {
        return cashback;
    }

    public void setCashback(Double cashback) {
        this.cashback = cashback;
    }

    @Override
    public String toString() {
        return "Customer [id=" + id + ", name=" + name + ", cashback=" + cashback + "]";
    }
}
