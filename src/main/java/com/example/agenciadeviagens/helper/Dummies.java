package com.example.agenciadeviagens.helper;

import com.example.agenciadeviagens.models.Customer;
import com.example.agenciadeviagens.models.Reservation;
import com.example.agenciadeviagens.models.TravelPackage;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.agenciadeviagens.helper.Randoms.pickRandomInt;

@Component
public class Dummies {

    public Set<Customer> createCustomerDummyList() {
        return Set.of(new Customer("Joanne"),
                new Customer("Angelica"),
                new Customer("Rita"));
    }

    public List<Reservation> createReservationDummyList() {
        return List.of(new Reservation(true, true, true, true, 4500.65),
                new Reservation(false, true, false, true, 2345.77),
                new Reservation(true, true, false, false, 9876.34),
                new Reservation(true, false, true, false, 1234.56),
                new Reservation(false, false, true, true, 5678.90));
    }

    public List<TravelPackage> createTravelPackageDummyList(Set<Customer> customerList,
                                                             List<Reservation> reservationList) {
        List<TravelPackage> travelPackageList = new ArrayList<>();
        for (Customer c : customerList) {
            int getRandomNumberOfReservations = pickRandomInt(reservationList.size());
            List<Reservation> customerReservationList = new ArrayList<>();
            Double reservationTotal = 0.0;
            for (int i = 0; i < getRandomNumberOfReservations; i++) {
                Reservation reservation = reservationList.get(i);
                customerReservationList.add(reservation);
                reservationTotal += reservation.getTotal();
            }
            travelPackageList.add(new TravelPackage
                    (c, reservationTotal, customerReservationList));
        }
        return travelPackageList;
    }

}
