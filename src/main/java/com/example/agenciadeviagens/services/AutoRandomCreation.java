package com.example.agenciadeviagens.services;

import com.example.agenciadeviagens.AppRunner;
import com.example.agenciadeviagens.helper.Randoms;
import com.example.agenciadeviagens.models.Customer;
import com.example.agenciadeviagens.models.Reservation;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class AutoRandomCreation {

    private static final Logger logger = Logger.getLogger(AppRunner.class.getName());

    private final Discounts discounts;

    public AutoRandomCreation(Discounts discounts) {
        this.discounts = discounts;
    }

    private final String PERSON_EMOJI = "\uD83E\uDDD1";
    private final String BOOK_EMOJI = "\uD83D\uDCD6";
    private final String CASHBACK_EMOJI = "\uD83D\uDCB6";

    public void createAutoRandomTravelPackage() {
        logger.info("Creating a new Travel Package");
/*
        Customer randomCustomer = Randoms.pickRandomCustomer(customerList);
        Reservation randomReservation = travelPackageCreator.createNewReservation();
        logger.info(PERSON_EMOJI + "Picked random customer: " + randomCustomer);
        logger.info(BOOK_EMOJI + "Created random reservation: " + randomReservation);

        System.out.println(">>>>>>>>>>    <<<<<<<<<<<");
        Double cashback = discounts.calculateCashback(travelPackageList, randomCustomer);
        randomCustomer.setCashback(cashback);
        logger.info(CASHBACK_EMOJI + "Cashback: " + cashback);
        logger.info(PERSON_EMOJI + "User Updated:\n" + randomCustomer);

        logger.entering(Discounts.class.getName(), "calculateDiscount");

 */
    }

}
