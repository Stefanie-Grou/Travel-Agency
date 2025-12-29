package com.example.agenciadeviagens;

import com.example.agenciadeviagens.helper.Dummies;
import com.example.agenciadeviagens.helper.Inputs;
import com.example.agenciadeviagens.models.Customer;
import com.example.agenciadeviagens.models.Reservation;
import com.example.agenciadeviagens.models.TravelPackage;
import com.example.agenciadeviagens.services.AutoRandomCreation;
import com.example.agenciadeviagens.services.ManualPackageCreator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

import static com.example.agenciadeviagens.helper.Dummies.travelPackageList;
import static com.example.agenciadeviagens.helper.Dummies.customersList;

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

    public static final Double DEFAULT_DOUBLE = 0.0;

    public static final String AIRPLANE_EMOJI = "✈️";
    public static final String BOOK_EMOJI = "\uD83D\uDCD6";
    public static final String CASHBACK_EMOJI = "\uD83D\uDCB6";

    @Override
    public void run(String... args) {
        Set<Customer> customerList = dummies.createCustomerDummyList();
        Dummies.setCustomersList(customerList);
        List<Reservation> reservationList = dummies.createReservationDummyList();
        List<TravelPackage> travelPackageList = dummies.createTravelPackageDummyList(customerList, reservationList);
        Dummies.setTravelPackageList(travelPackageList);

        boolean keepAlive;
        do {
            printMenu();
            int userOption = Inputs.getUserIntInput();
            keepAlive = dispatchUserChoice(userOption);
        } while (keepAlive);

    }

    private void printMenu() {
        System.out.println(AIRPLANE_EMOJI + " Travel Agency");
        System.out.println(">>>>>> Menu <<<<<<<");
        System.out.println("1. Manually create a new Travel Package");
        System.out.println("2. Run auto-random Travel Package creation");
        System.out.println("3. Print customers");
        System.out.println("4. Print Travel Packages");
        System.out.println("5. Print Travel Packages Profit Average");
        System.out.println("6. Exit");
    }

    private boolean dispatchUserChoice(int userOption) {
        switch (userOption) {
            case 1 -> manualCreator.createTravelPackage();
            case 2 -> autoRandomCreation.createTravelPackage();
            case 3 -> customersList.forEach(System.out::println);
            case 4 -> travelPackageList.forEach(System.out::println);
            case 5 -> dummies.printProfitAverage(travelPackageList);
            case 6 -> {
                return false;
            }
            default -> System.out.println("Invalid option");
        }
        return true;
    }
}
