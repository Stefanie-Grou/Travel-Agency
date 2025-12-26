package com.example.agenciadeviagens.services;

import com.example.agenciadeviagens.models.Reservation;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

import static com.example.agenciadeviagens.helper.Randoms.createRandomDouble;
import static com.example.agenciadeviagens.helper.Randoms.pickRandomBoolean;

@Component
public class TravelPackageCreator {
    Logger logger = Logger.getLogger(TravelPackageCreator.class.getName());

    public Reservation createNewReservation() {
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
}
