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
            System.out.println("\n===========================================");
            System.out.println("               ⚠️ Report Disaster         ");
            System.out.println("===========================================\n");
            System.out.println();
            System.out.println("  [1] Report Location");
            System.out.println("  [2] Report Connection");
            System.out.println("  [3] Go Back");
            System.out.println();
            System.out.println("===========================================");
            System.out.print("  Please enter your choice: ");

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
                    System.out.println("\n🔙 Going back...");
                    return;
                default:
                    System.out.println("\n❌ Invalid choice! Please enter a valid option.");
            }
        }
    }

    public static void reportLocation() {
        Collection<Location> locations = network.getLocations();

        if (locations.isEmpty()) {
            System.out.println("❌ No locations available to report.");
            return;
        }

        System.out.println("\n\n===========================================");
        System.out.println(" 📍 Location List ");
        System.out.println("===========================================\n");

        network.printLocations();

        int choice = Menu.getIntInput("📍 Enter the index of the location to report: ");
        if (choice < 1 || choice > locations.size()) {
            System.out.println("❌ Invalid choice.");
            return;
        }

        Location selectedLocation = (Location) locations.toArray()[choice - 1];

        System.out.println("\n🌍 Select a status type for the location:\n");
        StatusType[] statusTypes = StatusType.values();
        for (int i = 0; i < statusTypes.length - 1; i++) {
            System.out.println("  [" + (i + 1) + "] " + (i == 0 ? "✅ Disaster Resolved" : "⭕  Disaster Happened"));
        }

        int statusChoice = Menu.getIntInput("\n📩 Enter the status type number: ");
        if (statusChoice < 1 || statusChoice > statusTypes.length) {
            System.out.println("❌ Invalid status choice.");
            return;
        }

        StatusType statusType = statusTypes[statusChoice - 1];

        if (statusType == StatusType.ACTIVE) {
            network.resolveDisaster(selectedLocation);
            System.out.println("\n✅ Status updated: Disaster Resolved.");
        } else if (statusType == StatusType.DAMAGED) {

            System.out.println("\n🔥 Select a disaster:\n");
            DisasterType[] disasterTypes = DisasterType.values();
            for (int i = 0; i < disasterTypes.length; i++) {
                System.out.println("  [" + (i + 1) + "] " + disasterTypes[i]);
            }

            int disasterChoice = Menu.getIntInput("\n📩 Enter the disaster type number: ");
            if (disasterChoice < 1 || disasterChoice > disasterTypes.length) {
                System.out.println("\n❌ Invalid disaster choice.");
                return;
            }

            DisasterType disasterType = disasterTypes[disasterChoice - 1];

            System.out.println("\n⚠️ Select disaster severity:");
            int severityChoice = Menu.getIntInput("\n📏 Enter a severity value between 1 and 10: ");
            if (severityChoice < 1 || severityChoice > 10) {
                System.out.println("\n❌ Invalid severity value. Please enter a number between 1 and 10.");
                return;
            }

            Disaster disaster = new Disaster(disasterType, selectedLocation, severityChoice);

            network.addDisaster(selectedLocation, disaster);

            System.out.println("\n✅ Disaster reported: " + disasterType + " with severity " + severityChoice);
        }
    }

    public static void reportConnection() {

        System.out.println("\n\n===========================================");
        System.out.println(" 📍 Location List ");
        System.out.println("===========================================\n");

        network.printLocations();

        Collection<Location> locations = network.getLocations();

        int startLocationIndex = Menu.getIntInput("📍 Enter the index of the start/end location: ");
        if (startLocationIndex < 1 || startLocationIndex > locations.size()) {
            System.out.println("\n❌ Invalid index for start/end location. Please try again.");
            return;
        }

        Location startLocation = (Location) locations.toArray()[startLocationIndex - 1];

        startLocation.printConnections();

        List<Connection> connections = startLocation.getConnections().toList();

        int connectionIndex = Menu.getIntInput("\n🔌 Enter the index of the connection: ");
        if (connectionIndex < 1 || connectionIndex > connections.size()) {
            System.out.println("\n❌ Invalid index for connection. Please try again.");
            return;
        }

        Connection connection = (Connection) connections.toArray()[connectionIndex - 1];

        System.out.println("\n⚙️ Select a status type for the connection:");
        StatusType[] statusTypes = StatusType.values();
        for (int i = 0; i < statusTypes.length; i++) {
            System.out.println("  [" + (i + 1) + "] " + statusTypes[i]);
        }

        int choice = Menu.getIntInput("\n📩 Enter the status type number: ");
        if (choice < 1 || choice > statusTypes.length) {
            System.out.println("\n❌ Invalid status choice.");
            return;
        }

        StatusType statusType = statusTypes[choice - 1];

        network.editConnectionStatus(connection, statusType);

        System.out.println("\n✅ Connection status updated successfully.");
        network.printNetwork();
    }

}
