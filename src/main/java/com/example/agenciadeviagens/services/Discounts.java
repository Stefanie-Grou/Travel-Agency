package com.example.agenciadeviagens.services;

import com.example.agenciadeviagens.helper.Dummies;
import com.example.agenciadeviagens.models.Customer;
import com.example.agenciadeviagens.models.Reservation;
import com.example.agenciadeviagens.models.TravelPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

import static com.example.agenciadeviagens.AppRunner.CASHBACK_EMOJI;
import static com.example.agenciadeviagens.AppRunner.DEFAULT_DOUBLE;

@Component
public class Discounts {

    @Autowired
    private CustomerServices customerServices;

    private static final int MINIMUM_PACKAGES_TO_GET_DISCOUNT = 2;
    private static final Double TEN_PERCENT_DISCOUNT = 0.1;
    private static final Double FIVE_PERCENT_DISCOUNT = 0.05;

    private static final Logger LOGGER = Logger.getLogger(Discounts.class.getName());

    public void calculateDiscounts(TravelPackage travelPackage) {
        double originalTotal = travelPackage.getTotal();

        calculateFullTravelPackageDiscount(travelPackage, originalTotal);
        calculateHotelFlightDiscount(travelPackage, originalTotal);
        calculateCashbackDiscount(travelPackage, originalTotal);
        calculateTotalDiscount(travelPackage);
        applyTotalDiscount(travelPackage);
    }

    private void calculateTotalDiscount(TravelPackage travelPackage) {
        travelPackage.setTotalDiscount(travelPackage.getDiscountAppliedByFullTravelPackage()
                + travelPackage.getDiscountAppliedByHotelFlight()
                + travelPackage.getDiscountAppliedByCashback());
    }

    private void applyTotalDiscount(TravelPackage travelPackage) {
        travelPackage.setTotal(travelPackage.getTotal() - travelPackage.getTotalDiscount());
    }

    private void calculateFullTravelPackageDiscount(TravelPackage travelPackage, double originalTotal) {
        for (Reservation reservation : travelPackage.getReservations()) {
            if (reservation.getTransport() && reservation.getMeal() && reservation.getFlight()
                    && reservation.getHotel() && travelPackage.getDiscountAppliedByFullTravelPackage() == 0) {
                travelPackage.setDiscountAppliedByFullTravelPackage(originalTotal * TEN_PERCENT_DISCOUNT);
            }
        }
    }

    public void calculateHotelFlightDiscount(TravelPackage travelPackage, double originalTotal) {
        int totalOfHotels = totalOfHotels(travelPackage.getReservations());
        int totalOfFlights = totalOfFlights(travelPackage.getReservations());
        if (totalOfHotels >= MINIMUM_PACKAGES_TO_GET_DISCOUNT || totalOfFlights >= MINIMUM_PACKAGES_TO_GET_DISCOUNT) {
            travelPackage.setDiscountAppliedByHotelFlight(originalTotal * FIVE_PERCENT_DISCOUNT);
        }
    }

    private void calculateCashbackDiscount(TravelPackage travelPackage, double originalTotal) {
        if (travelPackage.getCustomer().getCashback() > DEFAULT_DOUBLE) {
            double discountFromCashback = originalTotal * TEN_PERCENT_DISCOUNT;
            travelPackage.setDiscountAppliedByCashback(discountFromCashback);
            travelPackage.getCustomer().setCashback(DEFAULT_DOUBLE);
            LOGGER.info(String.format(CASHBACK_EMOJI + "Cashback applied!"));
        }
    }

    private void calculateCashbackDiscount(TravelPackage travelPackage) {
        if (travelPackage.getCustomer().getCashback() > DEFAULT_DOUBLE) {
            double discountFromCashback = travelPackage.getTotal() * TEN_PERCENT_DISCOUNT;
            travelPackage.setDiscountAppliedByCashback(discountFromCashback);
            travelPackage.getCustomer().setCashback(DEFAULT_DOUBLE);
            LOGGER.info(String.format(CASHBACK_EMOJI + "Cashback applied!"));
        }
    }


    private int totalOfHotels(List<Reservation> reservationList) {
        return (int) reservationList.stream()
                .filter(Reservation::getHotel)
                .count();
    }

    private int totalOfFlights(List<Reservation> reservationList) {
        return (int) reservationList.stream()
                .filter(Reservation::getFlight)
                .count();
    }

    public void checkIfCustomerIsCashbackEligible(Customer customer) {
        Customer storedCustomer = customerServices.fetch(customer.getName());
        if (storedCustomer == null) return;

        List<TravelPackage> travelPackageList = Dummies.getTravelPackageList();
        int count = (int) travelPackageList.stream()
                .filter(travelPackage -> travelPackage.getCustomer().getName().equals(storedCustomer.getName()))
                .count();

        if (count >= 2) {
            storedCustomer.setCashback(0.05);
            LOGGER.info(String.format(CASHBACK_EMOJI + " Customer " + storedCustomer.getName())
                    + " is has a 5% cashback discount for the next travel package!");
        }
    }
}
