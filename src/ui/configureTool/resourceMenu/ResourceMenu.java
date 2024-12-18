package ui.configureTool.resourceMenu;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import core.baseClasses.location.Location;
import core.baseClasses.network.Network;
import core.baseClasses.resource.Resource;
import core.enums.Enum.ResourceType;
import ui.Menu;

public class ResourceMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static Network network = Network.getInstance();

    public static void resourceSubmenu() {
        while (true) {
            System.out.println("\n--- Resource ---");
            System.out.println("1. Add Resource");
            System.out.println("2. Remove Resource");
            System.out.println("3. View Resources");
            System.out.println("4. Go Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addResource();
                    break;
                case 2:
                    removeResource();
                    break;
                case 3:
                    viewResources();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    private static void addResource() {
        System.out.println("Add Resource...............");

        System.out.println("Available Resource Types:");
        ResourceType[] resourceTypes = ResourceType.values();
        for (int i = 0; i < resourceTypes.length; i++) {
            System.out.println((i + 1) + ". " + resourceTypes[i]);
        }

        int resourceTypeChoice = Menu.getIntInput("Choose a Resource Type (enter the number): ");
        if (resourceTypeChoice < 1 || resourceTypeChoice > resourceTypes.length) {
            System.out.println("Error: Invalid choice.");
            return;
        }
        ResourceType resourceType = resourceTypes[resourceTypeChoice - 1];

        int totalQuantity = Menu.getIntInput("Enter Total Quantity: ");
        if (totalQuantity < 0) {
            System.out.println("Error: Quantity cannot be negative.");
            return;
        }

        Collection<Location> locations = network.getLocations();

        network.printLocations();

        int locationIndex = Menu.getIntInput("Enter the index of the stationed location: ");
        if (locationIndex < 1 || locationIndex > locations.size()) {
            System.out.println("Invalid index for stationed location. Please try again.");
            return;
        }

        Location location = (Location) locations.toArray()[locationIndex - 1];

        Resource resource = new Resource(resourceType, totalQuantity, location);
        location.addResource(resource);

        System.out.println("Resource added successfully!");

    }

    private static void removeResource() {
        System.out.println("Remove Resource...............");

        Collection<Location> locations = network.getLocations();

        network.printLocations();

        int locationIndex = Menu.getIntInput("Enter the index of the selected location: ");
        if (locationIndex < 1 || locationIndex > locations.size()) {
            System.out.println("Invalid index for selected location. Please try again.");
            return;
        }

        Location location = (Location) locations.toArray()[locationIndex - 1];

        List<Resource> resources = location.getResources().toList();

        location.printResources();

        int resourceIndex = Menu.getIntInput("Enter the index of the selected resource: ");
        if (resourceIndex < 1 || resourceIndex > resources.size()) {
            System.out.println("Invalid index for selected resource. Please try again.");
            return;
        }

        Resource resource = (Resource) resources.toArray()[resourceIndex - 1];
        location.removeResource(resource);

        System.out.println("Resource removed successfully!");
    }

    private static void viewResources() {
        System.out.println("Viewing resources...");

        Collection<Location> locations = network.getLocations();

        network.printLocations();

        int locationIndex = Menu.getIntInput("Enter the index of the selected location: ");
        if (locationIndex < 1 || locationIndex > locations.size()) {
            System.out.println("Invalid index for selected location. Please try again.");
            return;
        }

        Location location = (Location) locations.toArray()[locationIndex - 1];

        location.printResources();
    }
}
