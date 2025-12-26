package com.example.agenciadeviagens;

import com.example.agenciadeviagens.helper.Dummies;
import com.example.agenciadeviagens.helper.Inputs;
import com.example.agenciadeviagens.models.Customer;
import com.example.agenciadeviagens.models.Reservation;
import com.example.agenciadeviagens.models.TravelPackage;
import com.example.agenciadeviagens.services.AutoRandomCreation;
import com.example.agenciadeviagens.services.ManualCreator;
import com.example.agenciadeviagens.services.TravelPackageCreator;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class AppRunner implements CommandLineRunner {

    private final TravelPackageCreator travelPackageCreator;
    private final Dummies dummies;
    private final AutoRandomCreation autoRandomCreation;
    private final ManualCreator manualCreator;

    public AppRunner(TravelPackageCreator travelPackageCreator, Dummies dummies,
                     AutoRandomCreation autoRandomCreation, ManualCreator manualCreator) {
        this.travelPackageCreator = travelPackageCreator;
        this.dummies = dummies;
        this.autoRandomCreation = autoRandomCreation;
        this.manualCreator = manualCreator;
    }

    public final String AIRPLANE_EMOJI = "✈\uFE0F";

    @Override
    public void run(String... args) {
        Set<Customer> customerList = dummies.createCustomerDummyList();
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
            case 1 -> manualCreator.createTravelPackageManually();
            case 2 -> autoRandomCreation.createAutoRandomTravelPackage();
            case 3 -> System.exit(0);
            default -> System.out.println("Invalid option");
        }
    }
}
