package com.example.agenciadeviagens.services;

import com.example.agenciadeviagens.helper.Inputs;
import com.example.agenciadeviagens.models.Customer;
import com.example.agenciadeviagens.models.Reservation;
import com.example.agenciadeviagens.models.TravelPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Component
public class ManualPackageCreator implements Actions {

    @Autowired
    private CustomerServices customerServices;

    private static final Logger LOGGER = Logger.getLogger(ManualPackageCreator.class.getName());

    @Override
    public Reservation createReservation() {
        System.out.println("\nIs hotel included? (yes/no)");
        boolean hotel = Inputs.getUserBooleanInput();

        System.out.println("\nAre the meals included?");
        boolean meals = Inputs.getUserBooleanInput();

        System.out.println("\nAre the flights included?");
        boolean flights = Inputs.getUserBooleanInput();

        System.out.println("\nAre the transports included?");
        boolean transports = Inputs.getUserBooleanInput();

        System.out.println("\nEnter the total price");
        double total = Inputs.getUserDoubleInput();
        System.out.println(total);
        return new Reservation(hotel, meals, flights, transports, total);
    }

    @Override
    public TravelPackage createTravelPackage() {
        Customer customer = createCustomerManually();
        LOGGER.info("Customer created: " + customer);

        List<Reservation> reservations = createReservationList();
        LOGGER.info("Reservation created: " + reservations);

        double totalPrice = calculateTravelPackageTotalPrice(reservations);
        LOGGER.info("Total price: " + totalPrice);

        TravelPackage travelPackage = new TravelPackage(customer, totalPrice, reservations);
        LOGGER.info("Travel Package created: " + travelPackage);
        return travelPackage;
    }

    public List<Reservation> createReservationList() {
        int numberOfReservations = getTotalOfReservationsFromUser();
        List<Reservation> reservations = new ArrayList<>();
        for (int i = 0; i < numberOfReservations; i++) {
            System.out.println("Creating reservation " + (i + 1));
            reservations.add(createReservation());
        }
        return reservations;
    }

    public Customer createCustomerManually() {
        System.out.println("Enter the customer name ");
        String userName = Inputs.getUserStringInput();
        return customerServices.create(userName);
    }

    private int getTotalOfReservationsFromUser() {
        System.out.println("Enter the number of reservations");
        return Inputs.getUserIntInput();
    }
}
