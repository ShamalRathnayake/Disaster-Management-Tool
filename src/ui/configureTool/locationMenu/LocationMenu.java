package ui.configureTool.locationMenu;

import java.util.Collection;
import java.util.Scanner;

import core.baseClasses.location.Location;
import core.baseClasses.network.Network;
import core.enums.Enum.LocationType;
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

    private static LocationType getLocationTypeByChoice(int choice) {
        switch (choice) {
            case 1:
                return LocationType.CITY;
            case 2:
                return LocationType.HOSPITAL;
            case 3:
                return LocationType.FIRE_STATION;
            case 4:
                return LocationType.WATER_TANK;
            case 5:
                return LocationType.POWER_STATION;
            case 6:
                return LocationType.POLICE_STATION;
            case 7:
                return LocationType.SCHOOL;
            default:
                System.out.println("Invalid choice. Defaulting to CITY.");
                return LocationType.CITY;
        }
    }

    private static void addLocation() {
        System.out.println("Select a location type:");
        System.out.println("1. CITY");
        System.out.println("2. HOSPITAL");
        System.out.println("3. FIRE_STATION");
        System.out.println("4. WATER_TANK");
        System.out.println("5. POWER_STATION");
        System.out.println("6. POLICE_STATION");
        System.out.println("7. SCHOOL");

        int choice = Menu.getIntInput("Enter the location type number: ");
        LocationType locationType = getLocationTypeByChoice(choice);

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
