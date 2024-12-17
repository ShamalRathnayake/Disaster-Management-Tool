package ui.configureTool.connectionMenu;

import java.util.Collection;
import java.util.Scanner;

import core.baseClasses.location.Location;
import core.baseClasses.network.Network;
import ui.Menu;

public class ConnectionMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static Network network = Network.getInstance();

    public static void connectionSubmenu() {
        while (true) {
            System.out.println("\n--- Connection ---");
            System.out.println("1. Add Connection");
            System.out.println("2. Remove Connection");
            System.out.println("3. Edit Connection");
            System.out.println("4. View Connections");
            System.out.println("5. Go Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addConnection();
                    break;
                case 2:
                    removeConnection();
                    break;
                // case 3:
                // editConnection();
                // break;
                // case 4:
                // viewConnections();
                // break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    private static void addConnection() {
        System.out.println("Adding a connection...");

        Collection<Location> locations = network.getLocations();

        if (locations.size() < 2) {
            System.out.println("Not enough locations to create a connection. Add more locations first.");
            return;
        }

        network.printLocations();

        int startLocationIndex = Menu.getIntInput("Enter the index of the start location: ");
        if (startLocationIndex < 1 || startLocationIndex > locations.size()) {
            System.out.println("Invalid index for start location. Please try again.");
            return;
        }

        int endLocationIndex = Menu.getIntInput("Enter the index of the end location: ");
        if (endLocationIndex < 1 || endLocationIndex > locations.size()) {
            System.out.println("Invalid index for end location. Please try again.");
            return;
        }

        if (startLocationIndex == endLocationIndex) {
            System.out.println("Start and end locations cannot be the same. Please try again.");
            return;
        }

        Location startLocation = (Location) locations.toArray()[startLocationIndex - 1];
        Location endLocation = (Location) locations.toArray()[endLocationIndex - 1];

        double distance = Menu.getDoubleInput("Enter the distance between the locations(KM): ");

        network.addConnection(startLocation.getId(), endLocation.getId(), distance);

        System.out.println("Connection successfully added between " + startLocation.getName() + " and "
                + endLocation.getName() + ".");

    }

    private static void removeConnection() {
        System.out.println("Removing a connection...");

        network.printLocations();

        Collection<Location> locations = network.getLocations();

        int startLocationIndex = Menu.getIntInput("Enter the index of the start location: ");
        if (startLocationIndex < 1 || startLocationIndex > locations.size()) {
            System.out.println("Invalid index for start location. Please try again.");
            return;
        }

        Location startLocation = (Location) locations.toArray()[startLocationIndex - 1];

    }

    // private static void editConnection() {
    // System.out.println("Connection edited.");
    // }

    // private static void viewConnections() {
    // System.out.println("Viewing connections...");
    // }
}
