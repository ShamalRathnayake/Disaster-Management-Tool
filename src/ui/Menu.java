package ui;

import java.util.*;

import core.baseClasses.network.Network;
import ui.configureTool.ConfigureTool;
import ui.reportDisaster.ReportDisaster;
import ui.reports.Reports;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Network.getInstance();
        runMenu();
    }

    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static double getDoubleInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        scanner.nextLine();
        return scanner.nextLine();
    }

    public static void displayMenu() {
        System.out.println("\n--- Disaster Management Tool ---");
        System.out.println("1. Report Disaster");
        System.out.println("2. Configure Tool");
        System.out.println("3. Reports");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    public static void runMenu() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    ReportDisaster.report();
                    break;
                case 2:
                    ConfigureTool.configure();
                    break;
                case 3:
                    Reports.viewReports();
                    break;
                case 4:
                    System.out.println("Exiting Disaster Management Tool...");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

}
