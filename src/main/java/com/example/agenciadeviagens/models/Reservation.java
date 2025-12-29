package com.example.agenciadeviagens.models;

public class Reservation {

    private Boolean hotel;
    private Boolean meal;
    private Boolean flight;
    private Boolean transport;
    private Double total;
    private Double discount;

    public Reservation() {
    }

    public Reservation(Boolean hotel, Boolean meal, Boolean flight, Boolean transport, Double total) {
        this.hotel = hotel;
        this.meal = meal;
        this.flight = flight;
        this.transport = transport;
        this.total = total;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Boolean getHotel() {
        return hotel;
    }

    public void setHotel(Boolean hotel) {
        this.hotel = hotel;
    }

    public Boolean getMeal() {
        return meal;
    }

    public void setMeal(Boolean meal) {
        this.meal = meal;
    }

    public Boolean getFlight() {
        return flight;
    }

    public void setFlight(Boolean flight) {
        this.flight = flight;
    }

    public Boolean getTransport() {
        return transport;
    }

    public void setTransport(Boolean transport) {
        this.transport = transport;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "hotel=" + hotel +
                ", meal=" + meal +
                ", flight=" + flight +
                ", transport=" + transport +
                ", total=" + total +
                ", discount=" + discount +
                '}';
    }
}
