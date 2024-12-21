package ui.reports;

import java.util.Scanner;

public class Reports {

    private static Scanner scanner = new Scanner(System.in);

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
        System.out.println("Displaying network report...");
    }

    private static void viewLogsReport() {
        System.out.println("\n--- Logs Report ---");
        System.out.println("Displaying logs report...");
    }
}
