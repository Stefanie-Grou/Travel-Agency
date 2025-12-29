package com.example.agenciadeviagens;

import com.example.agenciadeviagens.helper.Dummies;
import com.example.agenciadeviagens.helper.Inputs;
import com.example.agenciadeviagens.helper.Randoms;
import com.example.agenciadeviagens.models.Customer;
import com.example.agenciadeviagens.models.Reservation;
import com.example.agenciadeviagens.models.TravelPackage;
import com.example.agenciadeviagens.services.AutoRandomCreation;
import com.example.agenciadeviagens.services.ManualPackageCreator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class AppRunner implements CommandLineRunner {

    private final Dummies dummies;
    private final AutoRandomCreation autoRandomCreation;
    private final ManualPackageCreator manualCreator;

    public AppRunner(Dummies dummies,
                     AutoRandomCreation autoRandomCreation,
                     ManualPackageCreator manualCreator) {
        this.dummies = dummies;
        this.autoRandomCreation = autoRandomCreation;
        this.manualCreator = manualCreator;
    }

    public static final String AIRPLANE_EMOJI = "✈️";
    public static final String PERSON_EMOJI = "\uD83E\uDDD1";
    public static final String BOOK_EMOJI = "\uD83D\uDCD6";
    public static final String CASHBACK_EMOJI = "\uD83D\uDCB6";

    @Override
    public void run(String... args) {
        Set<Customer> customerList = dummies.createCustomerDummyList();
        Randoms.setCustomerList(customerList);
        List<Reservation> reservationList = dummies.createReservationDummyList();
        List<TravelPackage> travelPackageList = dummies.createTravelPackageDummyList(customerList, reservationList);

        travelPackageList.forEach(System.out::println);

        printMenu();
        int userOption = Inputs.getUserIntInput();
        dispatchUserChoice(userOption);
    }

    private void printMenu() {
        System.out.println(AIRPLANE_EMOJI + " Travel Agency");
        System.out.println(">>>>>> Menu <<<<<<<");
        System.out.println("1. Manually create a new Travel Package");
        System.out.println("2. Run auto-random Travel Package creation");
        System.out.println("3. Exit");
    }

    private void dispatchUserChoice(int userOption) {
        switch (userOption) {
            case 1 -> manualCreator.createTravelPackage();
            case 2 -> autoRandomCreation.createTravelPackage();
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid option");
        }
    }
}
