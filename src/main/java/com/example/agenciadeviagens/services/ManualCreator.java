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
public class ManualCreator {

    @Autowired
    private CustomerServices customerServices;

    private static final Logger LOGGER = Logger.getLogger(ManualCreator.class.getName());

    public void createTravelPackageManually() {
        //Add to SET
        Customer customer = createCustomerManually();
        LOGGER.info("Customer created: " + customer);

        List<Reservation> reservations = createReservationManually();
        LOGGER.info("Reservation created: " + reservations);

        double totalPrice = calculateTravelPackageTotalPrice(reservations);
        LOGGER.info("Total price: " + totalPrice);

        TravelPackage travelPackage = new TravelPackage(customer, totalPrice, reservations);
        LOGGER.info("Travel Package created: " + travelPackage);
    }

    public List<Reservation> createReservationManually() {
        int numberOfReservations = getTotalOfReservations();
        List<Reservation> reservations = new ArrayList<>();
        for (int i = 0; i < numberOfReservations; i++) {
            String countReservations = "Creation reservation: " + (i + 1);
            System.out.println(countReservations + "\nIs hotel included? (yes/no)");
            boolean hotel = Inputs.getUserBooleanInput();

            System.out.println(countReservations + "\nAre the meals included?");
            boolean meals = Inputs.getUserBooleanInput();

            System.out.println(countReservations + "\nAre the flights included?");
            boolean flights = Inputs.getUserBooleanInput();

            System.out.println(countReservations + "\nAre the transports included?");
            boolean transports = Inputs.getUserBooleanInput();

            System.out.println(countReservations + "\nEnter the total price");
            double total = Inputs.getUserDoubleInput();
            System.out.println(total);
            reservations.add(new Reservation(hotel, meals, flights, transports, total));
        }
        return reservations;
    }

    public Customer createCustomerManually() {
        System.out.println("Enter the customer name ");
        String userName = Inputs.getUserStringInput();
        return customerServices.create(userName);
    }

    private int getTotalOfReservations() {
        System.out.println("Enter the number of reservations");
        return Inputs.getUserIntInput();
    }

    //todo -> calculate total price -> make this work
    private double calculateTravelPackageTotalPrice(List<Reservation> reservations) {
        return reservations.stream().mapToDouble(Reservation::getTotal).sum();
    }

}
