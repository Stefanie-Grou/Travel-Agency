package com.example.agenciadeviagens.services;


import com.example.agenciadeviagens.models.Reservation;
import com.example.agenciadeviagens.models.TravelPackage;

import java.util.List;

public interface Actions {
    Reservation createReservation();
    TravelPackage createTravelPackage();
    default double calculateTravelPackageTotalPrice(List<Reservation> reservations) {
        return reservations.stream().mapToDouble(Reservation::getTotal).sum();
    }
}
