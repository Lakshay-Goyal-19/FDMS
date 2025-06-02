package com.abes.fdms;

import java.util.Scanner;

import com.abes.fdms.ui.CustomerConsoleUi;
import com.abes.fdms.ui.ManagerConsoleUi;

/**
 * Entry point for the Online Food Delivery Management System application.
 * Handles the main menu and role selection for Manager and User.
 */
public class Main {
    /**
     * Main method to start the application.
     * Displays the main menu and handles user input for role selection.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ManagerConsoleUi managerUI = new ManagerConsoleUi();
        CustomerConsoleUi customerUI = new CustomerConsoleUi();
        System.out.println("======================================================================================================");
        System.out.println("                       --- Welcome to Online Food Delivery Management System ---");
        while (true) {
            System.out.println("======================================================================================================");
            System.out.println("Select Role:\n1. Manager\n2. User\n3. Exit");
            System.out.println("======================================================================================================");
            String input = sc.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid option.");
                continue;
            }
            switch (choice) {
                case 1:
                    managerUI.showMenu();
                    break;
                case 2:
                    customerUI.showMenu();
                    break;
                case 3:
                    System.out.println("Exiting application.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
