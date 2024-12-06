package ui;
import data.Graph;
import java.util.*;

import java.util.Scanner;
import data.Graph;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);
    private static Graph graph = new Graph();

    public static void main(String[] args) {
        runMenu();
    }

    // Display the main menu
    public static void displayMenu() {
        System.out.println("\n--- Disaster Management Tool ---");
        System.out.println("1. Add Infrastructure");
        System.out.println("2. Add Connections");
        System.out.println("3. View Infrastructure");
        System.out.println("4. Add Disaster Details");
        System.out.println("5. View Disaster Details");
        System.out.println("6. Add Resources");
        System.out.println("7. View Available Resources");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    // Main menu loop
    public static void runMenu() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addInfrastructure();
                    break;
                case 2:
                    addConnections();
                    break;
                case 3:
                    viewInfrastructure();
                    break;
                case 4:
                    System.out.println("Exiting Disaster Management Tool...");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    // Method for adding infrastructure (submenu for each type)
    private static void addInfrastructure() {
        while (true) {
            System.out.println("\n--- Add Infrastructure ---");
            System.out.println("1. Add Towns");
            System.out.println("2. Add Hospitals");
            System.out.println("3. Add Power Grids");
            System.out.println("4. Add Water Grids");
            System.out.println("5. Go Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    addTowns();
                    break;
                case 2:
                    addHospitals();
                    break;
                case 3:
                    addPowerGrids();
                    break;
                case 4:
                    addWaterGrids();
                    break;
                case 5:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    // Submenu for adding towns
    private static void addTowns() {
        System.out.print("Enter the number of towns to add: ");
        int numTowns = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        for (int i = 1; i <= numTowns; i++) {
            System.out.print("Enter the name of town " + i + ": ");
            String townName = scanner.nextLine();
            graph.addNode(townName);
        }
    }

    // Submenu for adding hospitals
    private static void addHospitals() {
        System.out.print("Enter the number of hospitals to add: ");
        int numHospitals = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        for (int i = 1; i <= numHospitals; i++) {
            System.out.print("Enter the name of hospital " + i + ": ");
            String hospitalName = scanner.nextLine();
            graph.addNode(hospitalName);
        }
    }

    // Submenu for adding power grids
    private static void addPowerGrids() {
        System.out.print("Enter the number of power grids to add: ");
        int numGrids = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        for (int i = 1; i <= numGrids; i++) {
            System.out.print("Enter the name of power grid " + i + ": ");
            String gridName = scanner.nextLine();
            graph.addNode(gridName);
        }
    }

    // Submenu for adding water grids
    private static void addWaterGrids() {
        System.out.print("Enter the number of water grids to add: ");
        int numWaterGrids = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character
        for (int i = 1; i <= numWaterGrids; i++) {
            System.out.print("Enter the name of water grid " + i + ": ");
            String waterGridName = scanner.nextLine();
            graph.addNode(waterGridName);
        }
    }

    // Method for adding connections between nodes
    private static void addConnections() {
        System.out.println("\n--- Add Connections ---");
        System.out.println("Current Infrastructure and Connections:");
        viewInfrastructure(); // Show the existing graph before adding connections

        System.out.print("Enter the name of the first infrastructure: ");
        String node1 = scanner.nextLine();
        System.out.print("Enter the name of the second infrastructure: ");
        String node2 = scanner.nextLine();

        if (graph.getNeighbors(node1) != null && graph.getNeighbors(node2) != null) {
            graph.addEdge(node1, node2);
            System.out.println("Connection added between " + node1 + " and " + node2 + ".");
        } else {
            System.out.println("One or both infrastructures do not exist. Please add them first.");
        }
    }

    // Method to view the infrastructure graph
    private static void viewInfrastructure() {
        System.out.println("\nInfrastructure and Connections:");
        graph.display();
    }
}
