package ui.reportDisaster;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import core.baseClasses.connection.Connection;
import core.baseClasses.disaster.Disaster;
import core.baseClasses.location.Location;
import core.baseClasses.network.Network;
import core.enums.Enum.DisasterType;
import core.enums.Enum.LocationType;
import core.enums.Enum.StatusType;
import ui.Menu;

public class ReportDisaster {

    private static Scanner scanner = new Scanner(System.in);
    private static Network network = Network.getInstance();

    public static void report() {

        while (true) {
            System.out.println("\n--- Report Disaster ---");
            System.out.println("1. Report Location");
            System.out.println("2. Report Connection");
            System.out.println("3. Go Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    reportLocation();
                    break;
                case 2:
                    reportConnection();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }

        // create disaster
        // add to location
        // let user choose affected connections
        // update connection status
        // update network view
        // find the resource config for disaster
        // find the nearest node for each resource
        // allocate each resource from locations
        //
    }

    public static void reportLocation() {
        Collection<Location> locations = network.getLocations();

        if (locations.isEmpty()) {
            System.out.println("No locations available to remove.");
            return;
        }

        network.printLocations();

        int choice = Menu.getIntInput("Enter the index of the location to report: ");
        if (choice < 1 || choice > locations.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Location selectedLocation = (Location) locations.toArray()[choice - 1];

        System.out.println("Select a status type:");
        StatusType[] statusTypes = StatusType.values();
        for (int i = 0; i < statusTypes.length; i++) {
            System.out.println((i + 1) + ". " + statusTypes[i]);
        }

        int statusChoice = Menu.getIntInput("Enter the status type number: ");
        StatusType statusType = statusTypes[statusChoice - 1];

        if (statusType == StatusType.ACTIVE) {
            selectedLocation.setStatus(statusType);
            return;
        } else if (statusType == StatusType.DAMAGED) {

            System.out.println("Select a disaster:");
            DisasterType[] disasterTypes = DisasterType.values();
            for (int i = 0; i < disasterTypes.length; i++) {
                System.out.println((i + 1) + ". " + disasterTypes[i]);
            }

            int disasterChoice = Menu.getIntInput("Enter the disaster type number: ");
            DisasterType disasterType = disasterTypes[disasterChoice - 1];

            System.out.println("Select disaster severity:");
            int severityChoice = Menu.getIntInput("Enter a value between 1 and 10: ");

            Disaster disaster = new Disaster(disasterType, selectedLocation, severityChoice);
        }

    }

    public static void reportConnection() {
        network.printLocations();

        Collection<Location> locations = network.getLocations();

        int startLocationIndex = Menu.getIntInput("Enter the index of the start/end location: ");
        if (startLocationIndex < 1 || startLocationIndex > locations.size()) {
            System.out.println("Invalid index for start/end location. Please try again.");
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

        System.out.println("Select a status type:");
        StatusType[] statusTypes = StatusType.values();
        for (int i = 0; i < statusTypes.length; i++) {
            System.out.println((i + 1) + ". " + statusTypes[i]);
        }

        int choice = Menu.getIntInput("Enter the status type number: ");
        StatusType statusType = statusTypes[choice - 1];

        network.editConnectionStatus(connection, statusType);

        network.printNetwork();
    }
}
