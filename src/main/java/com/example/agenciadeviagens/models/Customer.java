package com.example.agenciadeviagens.models;

import com.example.agenciadeviagens.helper.IdCreator;

import static com.example.agenciadeviagens.AppRunner.DEFAULT_DOUBLE;

public class Customer {

    private static IdCreator idCreator;


    private Long id;
    private String name;
    private Double cashback;

    public Customer() {
    }

    public Customer(String name) {
        this.id = idCreator.nextId();
        this.name = name;
        this.cashback = DEFAULT_DOUBLE;
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
