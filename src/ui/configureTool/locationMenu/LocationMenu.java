package ui.configureTool.locationMenu;

import java.util.Collection;
import java.util.Scanner;

import core.baseClasses.location.Location;
import core.baseClasses.network.Network;
import core.enums.Enum.LocationType;
import core.enums.Enum.ResourceType;
import ui.Menu;

public class LocationMenu {

    private static Scanner scanner = new Scanner(System.in);
    private static Network network = Network.getInstance();

    public static void locationSubmenu() {
        while (true) {
            System.out.println("\n--- Location ---");
            System.out.println("1. Add Location");
            System.out.println("2. Remove Location");
            System.out.println("3. View Locations");
            System.out.println("4. Go Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addLocation();
                    break;
                case 2:
                    removeLocation();
                    break;
                case 3:
                    viewLocations();
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a valid option.");
            }
        }
    }

    private static void addLocation() {
        System.out.println("Select a location type:");

        LocationType[] locationTypes = LocationType.values();
        for (int i = 0; i < locationTypes.length; i++) {
            System.out.println((i + 1) + ". " + locationTypes[i]);
        }

        int choice = Menu.getIntInput("Enter the location type number: ");
        LocationType locationType = locationTypes[choice - 1];

        String name = Menu.getStringInput("Enter the location name: ");
        double latitude = Menu.getDoubleInput("Enter the latitude: ");
        double longitude = Menu.getDoubleInput("Enter the longitude: ");

        network.addLocation(locationType, name, latitude, longitude);
        System.out.println("Location added: " + name + " (" + locationType + ")");

        network.printLocations();
    }

    private static void removeLocation() {

        Collection<Location> locations = network.getLocations();

        if (locations.isEmpty()) {
            System.out.println("No locations available to remove.");
            return;
        }

        network.printLocations();

        int choice = Menu.getIntInput("Enter the index of the location to remove: ");
        if (choice < 1 || choice > locations.size()) {
            System.out.println("Invalid choice.");
            return;
        }

        Location selectedLocation = (Location) locations.toArray()[choice - 1];

        network.removeLocation(selectedLocation.getId());
        System.out.println("Location " + selectedLocation.getName() + " removed.");
    }

    private static void viewLocations() {
        System.out.println("Viewing locations...");
        System.out.println("");

        network.printLocations();
    }
}
