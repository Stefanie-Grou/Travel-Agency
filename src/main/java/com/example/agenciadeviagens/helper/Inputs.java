package com.example.agenciadeviagens.helper;

import java.util.Scanner;

public class Inputs {

    public static int getUserIntInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public static String getUserStringInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static Boolean getUserBooleanInput() {
        Scanner scanner = new Scanner(System.in);
        String userBooleanEntry = scanner.nextLine();
        if (userBooleanEntry.equalsIgnoreCase("Yes") || userBooleanEntry.equalsIgnoreCase("Y")) {
            return true;
        } else {
            return false;
        }
    }

    public static Double getUserDoubleInput() {
        Scanner scanner = new Scanner(System.in);
        return Double.parseDouble(scanner.nextLine());
    }
}
