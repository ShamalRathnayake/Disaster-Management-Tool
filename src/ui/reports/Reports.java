package ui.reports;

import java.util.Scanner;

import core.baseClasses.network.Network;

public class Reports {

    private static Scanner scanner = new Scanner(System.in);
    private static Network network = Network.getInstance();

    public static void viewReports() {
        while (true) {
            System.out.println("\n===========================================");
            System.out.println("               📊 Reports                  ");
            System.out.println("===========================================\n");
            System.out.println();
            System.out.println("  [1] Network Report");
            System.out.println("  [2] Location Report");
            System.out.println("  [3] Disaster Report");
            System.out.println("  [4] Go Back");
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
                    viewLocationReport();
                    break;
                case 3:
                    viewDisasterReport();
                    break;
                case 4:
                    System.out.println("🔙 Going back...");
                    return;
                default:
                    System.out.println("❌ Invalid choice! Please enter a valid option.");
            }
        }
    }

    private static void viewNetworkReport() {
        network.saveNetworkStatusToFile();

    }

    private static void viewLocationReport() {
        network.saveLocationStatusToFile();
    }

    private static void viewDisasterReport() {
        network.saveDisasterStatusToFile();
    }
}
