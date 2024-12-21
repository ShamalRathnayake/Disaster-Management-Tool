package ui.configureTool;

import java.util.Scanner;

import ui.configureTool.connectionMenu.ConnectionMenu;
import ui.configureTool.locationMenu.LocationMenu;
import ui.configureTool.resourceMenu.ResourceMenu;

public class ConfigureTool {

    private static Scanner scanner = new Scanner(System.in);

    public static void configure() {
        while (true) {
            System.out.println("\n\n===========================================");
            System.out.println("              🛠️  Configure Tool            ");
            System.out.println("===========================================");
            System.out.println();
            System.out.println("  [1] Manage Locations");
            System.out.println("  [2] Configure Connections");
            System.out.println("  [3] Manage Resources");
            System.out.println("  [4] Go Back");
            System.out.println();
            System.out.println("===========================================");
            System.out.print("  Please enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    LocationMenu.locationSubmenu();
                    break;
                case 2:
                    ConnectionMenu.connectionSubmenu();
                    break;
                case 3:
                    ResourceMenu.resourceSubmenu();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

}
