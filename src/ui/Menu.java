package ui;

import java.util.*;

import core.baseClasses.network.Network;
import demo.DemoData;
import ui.configureTool.ConfigureTool;
import ui.reportDisaster.ReportDisaster;
import ui.reports.Reports;

public class Menu {

    private static Scanner scanner = new Scanner(System.in);

    public static void startTool() {
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
        System.out.println("\n\n===========================================");
        System.out.println("        🌍 Disaster Management Tool 🌟      ");
        System.out.println("===========================================");
        System.out.println();
        System.out.println("  🚨 1. Report Disaster");
        System.out.println("  🛠️  2. Configure Tool");
        System.out.println("  📊 3. View Reports");
        System.out.println("  🖥️  4. Load Demo Data");
        System.out.println("  ❌ 5. Exit");
        System.out.println();
        System.out.println("===========================================");
        System.out.print("  Please enter your choice: ");
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
                    loadDemoData();
                    break;
                case 5:
                    System.out.println("Exiting Disaster Management Tool...");
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    public static void loadDemoData() {

        System.out.println("\n=============================================");
        System.out.println("  💥  Loading Demo Data for the System  💥  ");
        System.out.println("=============================================\n");

        System.out.println("🚀 Step 1: Creating Locations...");
        DemoData.createLocations();
        System.out.println("✅ Locations created successfully.\n");

        System.out.println("🌐 Step 2: Creating Connections...");
        DemoData.createConnections();
        System.out.println("✅ Connections established successfully.\n");

        System.out.println("💡 Step 3: Adding Resources to Locations...");
        DemoData.addResources();
        System.out.println("✅ Resources assigned successfully.\n");

        System.out.println("\n=============================================");
        System.out.println(" 🎉 Demo Data Loaded Successfully! 🎉 ");
        System.out.println("=============================================\n");
    }

}
