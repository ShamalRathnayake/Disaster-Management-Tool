package ui.configureTool.connectionMenu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import core.baseClasses.connection.Connection;
import core.baseClasses.location.Location;
import core.baseClasses.network.Network;
import ui.Menu;

public class ConnectionMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static Network network = Network.getInstance();

    public static void connectionSubmenu() {
        while (true) {

            System.out.println("\n===========================================");
            System.out.println("                🌐 Connection Menu          ");
            System.out.println("===========================================");
            System.out.println();
            System.out.println("  [1] Add a New Connection");
            System.out.println("  [2] Remove an Existing Connection");
            System.out.println("  [3] View All Connections");
            System.out.println("  [4] Go Back");
            System.out.println();
            System.out.println("===========================================");
            System.out.print("  Please enter your choice: ");

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
                    System.out.println("\n🔙 Going back...");
                    return;
                default:
                    System.out.println("\n❌ Invalid choice! Please enter a valid option.");
            }
        }
    }

    private static void addConnection() {
        System.out.println("\n=========================================");
        System.out.println("           🌐 Add Connection");
        System.out.println("=========================================");

        Collection<Location> locations = network.getLocations();

        if (locations.size() < 2) {
            System.out.println("❌ Not enough locations to create a connection. Please add more locations first.");
            return;
        }

        network.printLocations();

        int startLocationIndex = Menu.getIntInput("🔹 Select the index of the start location: ");
        if (startLocationIndex < 1 || startLocationIndex > locations.size()) {
            System.out.println("❌ Invalid index for start location. Please try again.");
            return;
        }

        int endLocationIndex = Menu.getIntInput("🔹 Select the index of the end location: ");
        if (endLocationIndex < 1 || endLocationIndex > locations.size()) {
            System.out.println("❌ Invalid index for end location. Please try again.");
            return;
        }

        if (startLocationIndex == endLocationIndex) {
            System.out.println("❌ The start and end locations cannot be the same. Please select different locations.");
            return;
        }

        Location startLocation = (Location) locations.toArray()[startLocationIndex - 1];
        Location endLocation = (Location) locations.toArray()[endLocationIndex - 1];

        double distance = Menu.getDoubleInput("📏 Enter the distance between the locations (in kilometers): ");

        try {
            network.addConnection(startLocation.getId(), endLocation.getId(), distance);

            System.out.println("\n✅ Connection successfully established between "
                    + startLocation.getName() + " and " + endLocation.getName() + "!");

        } catch (Exception e) {
            System.out.println("❌ The connection already exists.");
        }

    }

    private static void removeConnection() {
        System.out.println("\n===========================================");
        System.out.println("              🌐 Remove Connection         ");
        System.out.println("===========================================\n\n");

        Set<Connection> uniqueConnections = getUniqueConnections();

        if (uniqueConnections.isEmpty()) {
            System.out.println("❌ No connections available to remove.");
            return;
        }

        System.out.printf("%-5s %-25s %-20s %-10s %-10s\n",
                "Index", "Start Location", "End Location", "Distance", "Status");
        System.out.println("---------------------------------------------------------------");

        int index = 1;
        Map<Integer, Connection> connectionMap = new HashMap<>();
        for (Connection connection : uniqueConnections) {
            connectionMap.put(index, connection);
            System.out.printf("%-5d %-25s %-20s %-10.2f %-10s %-25s\n",
                    index,
                    connection.getStartLocation().getName(),
                    connection.getEndLocation().getName(),
                    connection.getDistance(),
                    connection.getStatus());
            index++;
        }

        int connectionIndex = Menu.getIntInput("📏 Enter the index of the connection to remove: ");
        if (!connectionMap.containsKey(connectionIndex)) {
            System.out.println("❌ Invalid index for connection. Please try again.");
            return;
        }

        Connection connectionToRemove = connectionMap.get(connectionIndex);

        network.removeConnection(connectionToRemove);

        System.out.println("\n\n✅ Connection between " + connectionToRemove.getStartLocation().getName() + " and "
                + connectionToRemove.getEndLocation().getName() + " successfully deleted.");

        System.out.println("===========================================");

    }

    private static void viewConnections() {
        System.out.println("\n===========================================");
        System.out.println("              🌐 View Connections          ");
        System.out.println("===========================================\n\n");

        Collection<Location> locations = network.getLocations();

        if (locations.isEmpty()) {
            System.out.println("No locations found. Add locations first.");
            return;
        }

        System.out.printf("%-25s %-20s %-10s %-10s %-25s\n",
                "Start Location", "End Location", "Distance", "Status", "Last Updated");
        System.out.println("---------------------------------------------------------------------------------------");

        Set<Connection> uniqueConnections = getUniqueConnections();

        if (uniqueConnections.isEmpty()) {
            System.out.println("No connections available.");
        } else {
            for (Connection connection : uniqueConnections) {
                System.out.printf("%-25s %-20s %-10.2f %-10s %-25s\n",
                        connection.getStartLocation().getName(),
                        connection.getEndLocation().getName(),
                        connection.getDistance(),
                        connection.getStatus(),
                        connection.getLastUpdated().toString());
            }
        }

        System.out.println("===========================================");
    }

    private static Set<Connection> getUniqueConnections() {
        Collection<Location> locations = network.getLocations();

        Set<Connection> uniqueConnections = new HashSet<>();

        for (Location location : locations) {
            List<Connection> connections = location.getConnections().toList();

            if (!connections.isEmpty()) {
                uniqueConnections.addAll(connections);
            }
        }

        return uniqueConnections;
    }

}
