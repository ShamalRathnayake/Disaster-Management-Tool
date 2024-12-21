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
            System.out.println("\n\n===========================================");
            System.out.println("                📍  Location Menu              ");
            System.out.println("===========================================");
            System.out.println();
            System.out.println("  [1] Add a New Location");
            System.out.println("  [2] Remove an Existing Location");
            System.out.println("  [3] View All Locations");
            System.out.println("  [4] Go Back");
            System.out.println();
            System.out.println("===========================================");
            System.out.print("  Please enter your choice: ");
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
                    System.out.println("\n🔙 Going back...");
                    return;
                default:
                    System.out.println("\n❌ Invalid choice! Please enter a valid option.");
            }
        }
    }

    private static void addLocation() {
        System.out.println("\n\n===========================================");
        System.out.println("           🌍 Add a New Location           ");
        System.out.println("===========================================");
        System.out.println("\nSelect a location type:");

        LocationType[] locationTypes = LocationType.values();
        for (int i = 0; i < locationTypes.length; i++) {
            System.out.println("  [" + (i + 1) + "] " + locationTypes[i]);
        }

        int choice = Menu.getIntInput("\n👉 Enter the location type number: ");
        if (choice < 1 || choice > locationTypes.length) {
            System.out.println("\n❌ Invalid choice. Please enter a valid index.");
            return;
        }
        LocationType locationType = locationTypes[choice - 1];

        System.out.println("\nEnter the details for the new location:");
        String name = Menu.getStringInput("📍 Location Name: ");
        double latitude = Menu.getDoubleInput("🌐 Latitude: ");
        double longitude = Menu.getDoubleInput("🌐 Longitude: ");

        network.addLocation(locationType, name, latitude, longitude);
        System.out.println("\n✅ Location successfully added!");
        System.out.println("   Name: " + name);
        System.out.println("   Type: " + locationType);
        System.out.println("   Coordinates: (" + latitude + ", " + longitude + ")");

        System.out.println("\n📍 Current Locations in the Network:\\n");
        network.printLocations();
    }

    private static void removeLocation() {
        Collection<Location> locations = network.getLocations();

        if (locations.isEmpty()) {
            System.out.println("\n⚠️ No locations available to remove.");
            return;
        }

        System.out.println("\n===========================================");
        System.out.println("           📍 Available Locations          ");
        System.out.println("===========================================\n");

        network.printLocations();

        int choice = Menu.getIntInput("Enter the index of the location to remove: ");

        if (choice < 1 || choice > locations.size()) {
            System.out.println("\n❌ Invalid choice. Please enter a valid index.");
            return;
        }

        Location selectedLocation = (Location) locations.toArray()[choice - 1];

        network.removeLocation(selectedLocation.getId());
        System.out.println("\n✅ Location \"" + selectedLocation.getName() + "\" removed successfully.");
    }

    private static void viewLocations() {
        System.out.println("\n===========================================");
        System.out.println("              🌍 Viewing Locations          ");
        System.out.println("===========================================\n");

        network.printLocations();

        System.out.println("===========================================\n");
    }
}
