package ui.configureTool.connectionMenu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import core.baseClasses.connection.Connection;
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
            System.out.println("3. View Connections");
            System.out.println("4. Go Back");
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
                case 3:
                    viewConnections();
                    break;
                case 4:
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

        startLocation.printConnections();

        List<Connection> connections = startLocation.getConnections().toList();

        int connectionIndex = Menu.getIntInput("Enter the index of the connection: ");
        if (connectionIndex < 1 || connectionIndex > connections.size()) {
            System.out.println("Invalid index for connection. Please try again.");
            return;
        }

        Connection connection = (Connection) connections.toArray()[connectionIndex - 1];

        network.removeConnection(connection);

        System.out.println("Connection between " + startLocation.getName() + " and "
                + connection.getEndLocation().getName() + " successfully deleted.");

    }

    private static void viewConnections() {
        System.out.println("Viewing connections...");

        Collection<Location> locations = network.getLocations();
        if (locations.size() == 0) {
            System.out.println("No locations found. Add locations first.");
            return;
        }

        System.out.printf("%-40s %-20s %-20s %-10s %-10s %-25s\n",
                "Connection ID", "Start Location", "End Location",
                "Distance", "Status", "Last Updated");

        List<String> connectionRecords = new ArrayList<String>();

        for (Location location : locations) {

            List<Connection> connections = location.getConnections().toList();

            if (!connections.isEmpty()) {

                for (Connection connection : connections) {
                    String rec = String.format("%-40s %-20s %-20s %-10.2f %-10s %-25s\n",
                            connection.getConnectionId(),
                            connection.getStartLocation().getName(),
                            connection.getEndLocation().getName(),
                            connection.getDistance(),
                            connection.getStatus(),
                            connection.getLastUpdated().toString());

                    connectionRecords.add(rec);
                }
            }

            for (String rec : connectionRecords) {
                System.out.println(rec);
            }

            System.out.println();
        }

    }
}
