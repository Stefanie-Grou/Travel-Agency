package com.example.agenciadeviagens.services;

import com.example.agenciadeviagens.models.Customer;
import com.example.agenciadeviagens.models.Reservation;
import com.example.agenciadeviagens.models.TravelPackage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Discounts {

    private final Long MINIMUM_PACKAGES_TO_GET_DISCOUNT = 2L;
    private final Double DISCOUNT_FIVE_PERCENT = 0.05;
    private final Double DISCOUNT_TEN_PERCENT = 0.10;

    public Double calculateDiscount(List<TravelPackage> travelPackageList, Customer customer, TravelPackage travelPackage) {
        List<Reservation> fullTravelPackageDiscount = applyFullTravelPackageDiscount(travelPackage.getReservations());
        return null;
    }


    //If a customer has more than 2 travel packages,they get a 5% discount for a next travel package (cashback)
    public Double calculateCashback (List<TravelPackage> travelPackageList, Customer customer) {
        List<TravelPackage> totalOfTravelPackages = totalOfTravelPackagesByCustomer(travelPackageList, customer);
        if (totalOfTravelPackages.size() >= MINIMUM_PACKAGES_TO_GET_DISCOUNT) {
            return totalOfTravelPackages.stream()
                    .mapToDouble(TravelPackage::getTotal)
                    .sum() * (1 - DISCOUNT_FIVE_PERCENT);
        }
        return 0.0;
    }


    //If a reservation has all services, they get a 10% discount
    private List<Reservation> applyFullTravelPackageDiscount(List<Reservation> reservationList) {
        for (Reservation reservation : reservationList) {
            Reservation singleReservation = reservation;
            if (singleReservation.getTransport() && singleReservation.getMeal() && singleReservation.getFlight()
            && singleReservation.getHotel()) {
                System.out.println(" >>>>>>> " + singleReservation.getTotal());
                Double totalWithDiscount = singleReservation.getTotal() * DISCOUNT_TEN_PERCENT;
                System.out.println(" >>>>>>> " + totalWithDiscount);
                reservationList.get(reservationList.indexOf(singleReservation)).setTotal(totalWithDiscount);
            }
        }
        return reservationList;
    }

    private List<TravelPackage> totalOfTravelPackagesByCustomer(List<TravelPackage> travelPackageList, Customer customer) {
        return travelPackageList.stream()
                .filter(travelPackage -> travelPackage.getCustomer().equals(customer))
                .toList();
    }

    //If a customer buys >=2 hotel OR >=2 flight, they get a 10% discount in the current travel
    //todo: come back here
    public TravelPackage applyDiscountToCurrentTravelCombo(List<Reservation> reservationList, TravelPackage travelPackage) {
        long hotelCount = reservationList.stream()
                .filter(Reservation::getHotel)
                .count();

        long flightCount = reservationList.stream()
                .filter(Reservation::getFlight)
                .count();

        if (hotelCount >= MINIMUM_PACKAGES_TO_GET_DISCOUNT || flightCount >= MINIMUM_PACKAGES_TO_GET_DISCOUNT) {

        }

        return null;
    }



}
