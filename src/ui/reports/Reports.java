package ui.reports;

import core.baseClasses.location.Location;
import core.enums.Enum.LocationType;
import core.baseClasses.log.LogEntry;
import core.enums.Enum;
import core.baseClasses.connection.Connection;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Reports {

    private static Scanner scanner = new Scanner(System.in);
    private static List<LogEntry> logs = new ArrayList<>();

    public static void main(String[] args) {
        // Simulate some logs for the project
        generateProjectLogs();

        // Display the reports menu
        viewReports();
    }



    public static void viewReports() {
        while (true) {
            System.out.println("\n===========================================");
            System.out.println("               📊 Reports                  ");
            System.out.println("===========================================\n");
            System.out.println();
            System.out.println("  [1] Network Report");
            System.out.println("  [2] Logs Report");
            System.out.println("  [3] Go Back");
            System.out.println();
            System.out.println("===========================================");
            System.out.print("  Please enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewNetworkReport();
                    break;
                case 2:
                    viewLogsReport();
                    break;
                case 3:
                    System.out.println("🔙 Going back...");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please enter a valid option.");
            }
        }
    }

    private static void viewNetworkReport() {
        System.out.println("\n--- Network Report ---");

        // Generate mock data for the network
        List<Location> locations = generateNetworkData();

        // Display and generate report
        try (FileWriter writer = new FileWriter("NetworkReport.txt")) {
            writer.write("Network Report\n");
            writer.write("----------------------------\n");

            for (Location location : locations) {
                System.out.printf("Location: %-20s [ID: %s, Type: %s, Status: %s]\n",
                        location.getName(),
                        location.getId(),
                        location.getType(),
                        location.getStatus());

                writer.write(String.format("Location: %-20s [ID: %s, Type: %s, Status: %s]\n",
                        location.getName(),
                        location.getId(),
                        location.getType(),
                        location.getStatus()));

                List<Connection> connections = location.getConnections();


                if (connections.isEmpty()) {
                    System.out.println("  No connections.");
                    writer.write("  No connections.\n");
                } else {
                    System.out.println("  Connections:");
                    writer.write("  Connections:\n");

                    for (Connection connection : connections) {
                        System.out.printf("    -> %-20s [Distance: %.2f, Status: %s]\n",
                                connection.getEndLocation().getName(),
                                connection.getDistance(),
                                connection.getStatus());

                        writer.write(String.format("    -> %-20s [Distance: %.2f, Status: %s]\n",
                                connection.getEndLocation().getName(),
                                connection.getDistance(),
                                connection.getStatus()));
                    }
                }

                System.out.println();
                writer.write("\n");
            }

            System.out.println("\nNetwork Report saved to 'NetworkReport.txt'.");
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }

    private static void viewLogsReport() {
        System.out.println("\n--- Logs Report ---");

        if (logs.isEmpty()) {
            System.out.println("No logs available.");
            return;
        }

        // Display logs to console
        System.out.println("Log Entries:");
        for (LogEntry log : logs) {
            System.out.println(log.toString());
        }

        // Save logs to a text file
        try (FileWriter writer = new FileWriter("LogsReport.txt")) {
            writer.write("Logs Report\n");
            writer.write("----------------------------\n");
            for (LogEntry log : logs) {
                writer.write(log.toString() + "\n");
            }
            System.out.println("\nLogs Report saved to 'LogsReport.txt'.");
        } catch (IOException e) {
            System.out.println("Error writing report: " + e.getMessage());
        }
    }
    private static List<Location> generateNetworkData() {
        // Simulate network data
        Location locA = new Location("Node A", LocationType.FIRE_STATION, 10.5, 56.5);
        Location locB = new Location("Node B", LocationType.SCHOOL, 12.3, 20.3);
        Location locC = new Location("Node C", LocationType.CITY, 56.2, 96.3);

//        locA.addConnection(new Connection(NodeA,NodeB, 5, Enum.StatusType.ACTIVE));
//        locA.addConnection(new Connection(NodeB,NodeC, 5, Enum.StatusType.ACTIVE));


        List<Location> locations = new ArrayList<>();
        locations.add(locA);
        locations.add(locB);
        locations.add(locC);

        return locations;
    }


    private static void generateProjectLogs() {
        // Simulating log entries related to a disaster management project
        logs.add(new LogEntry(Enum.LogLevel.INFO, "System initialized", "Disaster Management Tool is up and running."));
        logs.add(new LogEntry(Enum.LogLevel.WARN, "Network delay detected", "Connection to Node C is taking longer than expected."));
        logs.add(new LogEntry(Enum.LogLevel.ERROR, "Data fetch failed", "Failed to retrieve real-time data from Sensor X."));
        logs.add(new LogEntry(Enum.LogLevel.INFO, "User logged in", "User ID: admin123 accessed the tool."));
        logs.add(new LogEntry(Enum.LogLevel.DEBUG, "Debugging connection issue", "Retrying connection to Node D."));
    }


}

// Connection class to represent edges in the network
//class Connection {
//    private Location endLocation;
//    private double distance;
//    private String status;
//
//    public Connection(Location endLocation, double distance, String status) {
//        this.endLocation = endLocation;
//        this.distance = distance;
//        this.status = status;
//    }
//
//    public Location getEndLocation() {
//        return endLocation;
//    }
//
//    public void setEndLocation(Location endLocation) {
//        this.endLocation = endLocation;
//    }
//
//    public double getDistance() {
//        return distance;
//    }
//
//    public void setDistance(double distance) {
//        this.distance = distance;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//}

