package ui.configureTool;

import java.util.Scanner;

import core.baseClasses.network.Network;
import ui.configureTool.connectionMenu.ConnectionMenu;
import ui.configureTool.locationMenu.LocationMenu;

public class ConfigureTool {

    private static Scanner scanner = new Scanner(System.in);
    private static Network network = Network.getInstance();

    public static void configure() {
        while (true) {
            System.out.println("\n--- Configure Tool ---");
            System.out.println("1. Location");
            System.out.println("2. Connection");
            System.out.println("3. Resource");
            System.out.println("4. Disaster");
            System.out.println("5. Go Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    LocationMenu.locationSubmenu();
                    break;
                case 2:
                    ConnectionMenu.connectionSubmenu();
                    break;
                case 3:
                    resourceSubmenu();
                    break;
                case 4:
                    disasterSubmenu();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    private static void resourceSubmenu() {
        while (true) {
            System.out.println("\n--- Resource ---");
            System.out.println("1. Add Resource");
            System.out.println("2. Remove Resource");
            System.out.println("3. Edit Resource");
            System.out.println("4. View Resources");
            System.out.println("5. Go Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                // case 1:
                // addResource();
                // break;
                // case 2:
                // removeResource();
                // break;
                // case 3:
                // editResource();
                // break;
                // case 4:
                // viewResources();
                // break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    private static void disasterSubmenu() {
        while (true) {
            System.out.println("\n--- Disaster ---");
            System.out.println("1. Add Disaster");
            System.out.println("2. Remove Disaster");
            System.out.println("3. Edit Disaster");
            System.out.println("4. View Disasters");
            System.out.println("5. Go Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                // case 1:
                // addDisaster();
                // break;
                // case 2:
                // removeDisaster();
                // break;
                // case 3:
                // editDisaster();
                // break;
                // case 4:
                // viewDisasters();
                // break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    // private static void addResource() {
    // System.out.println("Resource added.");
    // }

    // private static void removeResource() {
    // System.out.println("Resource removed.");
    // }

    // private static void editResource() {
    // System.out.println("Resource edited.");
    // }

    // private static void viewResources() {
    // System.out.println("Viewing resources...");
    // }

    // private static void addDisaster() {
    // System.out.println("Disaster added.");
    // }

    // private static void removeDisaster() {
    // System.out.println("Disaster removed.");
    // }

    // private static void editDisaster() {
    // System.out.println("Disaster edited.");
    // }

    // private static void viewDisasters() {
    // System.out.println("Viewing disasters...");
    // }
}
