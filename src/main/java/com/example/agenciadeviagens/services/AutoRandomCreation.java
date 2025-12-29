package com.example.agenciadeviagens.services;

import com.example.agenciadeviagens.AppRunner;
import com.example.agenciadeviagens.helper.Randoms;
import com.example.agenciadeviagens.models.Customer;
import com.example.agenciadeviagens.models.Reservation;
import com.example.agenciadeviagens.models.TravelPackage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static com.example.agenciadeviagens.helper.Randoms.*;

@Component
public class AutoRandomCreation implements  Actions {
    private static final Logger logger = Logger.getLogger(AppRunner.class.getName());

    private final Discounts discounts;

    public AutoRandomCreation(Discounts discounts) {
        this.discounts = discounts;
    }

    private final String PERSON_EMOJI = "\uD83E\uDDD1";
    private final String BOOK_EMOJI = "\uD83D\uDCD6";
    private final String CASHBACK_EMOJI = "\uD83D\uDCB6";
    private final int MAX_NUMBER_OF_RESERVATIONS = 3;

    @Override
    public Reservation createReservation() {
        logger.info("... Creating RANDOMLY a new Reservation ...");

        Boolean hotel = pickRandomBoolean();
        logger.info("Is hotel included? \n" + hotel);

        Boolean meal = pickRandomBoolean();
        logger.info("Are meals included?\n" + meal);

        Boolean flight = pickRandomBoolean();
        logger.info("Are flights included?\n" + flight);

        Boolean transport = pickRandomBoolean();
        logger.info("Are transports included?\n" + transport);

        Reservation reservation = new Reservation(hotel, meal, flight, transport, createRandomDouble());
        logger.info("New Reservation Created!\n" + reservation);
        return reservation;
    }

    @Override
    public TravelPackage createTravelPackage() {
        Customer customer = Randoms.pickRandomCustomer();
        List<Reservation> reservations = createReservationList();
        double totalPrice = calculateTravelPackageTotalPrice(reservations);
        TravelPackage travelPackage = new TravelPackage(customer, totalPrice, reservations);
        logger.info("New Travel Package Created!\n" + travelPackage);
        return travelPackage;
    }

    private List<Reservation> createReservationList() {
        int numberOfReservations = Randoms.pickRandomInt(MAX_NUMBER_OF_RESERVATIONS);
        List<Reservation> reservationList = new ArrayList<>();
        for (int i = 0; i < numberOfReservations; i++) {
            reservationList.add(createReservation());
        }
        return reservationList;
    }

}
