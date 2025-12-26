package com.example.agenciadeviagens.models;

import java.util.List;

public class TravelPackage {
    private Customer customer;
    private Double total;
    List<Reservation> reservations;
    private Double discount;

    public TravelPackage() {
    }

    public TravelPackage(Customer customer, Double total, List<Reservation> reservations) {
        this.customer = customer;
        this.total = total;
        this.reservations = reservations;
        this.discount = 0.0;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "TravelPackage{" +
                "customer=" + customer +
                ", total=" + total +
                ", reservations=" + reservations +
                ", discount=" + discount +
                '}';
    }
}
