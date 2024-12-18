package core.baseClasses.network;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import core.baseClasses.connection.Connection;
import core.baseClasses.location.Location;
import core.enums.Enum.LocationType;
import core.enums.Enum.StatusType;

public class Network {

    private HashMap<String, Location> locations;
    private static Network instance;

    private Network() {
        this.locations = new HashMap<>();
    }

    public static Network getInstance() {
        if (instance == null) {
            instance = new Network();
        }
        return instance;
    }

    public void addLocation(LocationType locationType, String name, double latitude,
            double longitude) {

        Location location = new Location(name, locationType, latitude, longitude);
        locations.put(location.getId(), location);

    }

    public void removeLocation(String locationId) {

        Location removedLocation = locations.remove(locationId);

        if (removedLocation == null) {
            System.out.println("Location with ID " + locationId + " not found.");
        }

    }

    public void addConnection(String fromLocationId, String toLocationId, double distance) {

        Location fromLocation = locations.get(fromLocationId);
        Location toLocation = locations.get(toLocationId);

        if (fromLocation != null && toLocation != null) {
            Connection connection = new Connection(fromLocation, toLocation, distance, StatusType.ACTIVE);

            fromLocation.addConnection(connection);
            toLocation.addConnection(connection);
        }
    }

    public void removeConnection(Connection connection) {
        Location fromLocation = connection.getStartLocation();
        Location toLocation = connection.getEndLocation();

        if (fromLocation != null && toLocation != null) {

            fromLocation.removeConnection(connection);
            toLocation.removeConnection(connection);
        }
    }

    public Collection<Location> getLocations() {
        return locations.values();
    }

    public Location getLocationById(String locationId) {
        return locations.get(locationId);
    }

    public int getNumberOfLocations() {
        return locations.size();
    }

    public void printLocations() {
        System.out.println("");

        System.out.printf("%-5s %-40s %-20s %-15s %-15s %-15s %-10s\n",
                "Index", "ID", "Name", "Type", "Latitude", "Longitude", "Status");

        int index = 1;
        for (Location location : locations.values()) {

            System.out.printf("%-5s %-40s %-20s %-15s %-15.5f %-15.5f %-10s\n",
                    index++,
                    location.getId(),
                    location.getName(),
                    location.getType(),
                    location.getLatitude(),
                    location.getLongitude(),
                    location.getStatus());
        }

        System.out.println("");
    }

    public void printNetwork() {
        System.out.println("\nNetwork Graph:");

        for (Location location : locations.values()) {

            System.out.printf("Location: %-20s [ID: %s, Type: %s, Status: %s]\n",
                    location.getName(),
                    location.getId(),
                    location.getType(),
                    location.getStatus());

            List<Connection> connections = location.getConnections().toList();

            if (connections.isEmpty()) {
                System.out.println("  No connections.");
            } else {
                System.out.println("  Connections:");

                for (Connection connection : connections) {
                    System.out.printf("    -> %-20s [Distance: %.2f, Status: %s]\n",
                            connection.getEndLocation().getName(),
                            connection.getDistance(),
                            connection.getStatus());
                }
            }

            System.out.println();
        }
    }

}
