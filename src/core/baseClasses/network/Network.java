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

    public void removeConnection(String fromLocationId, String toLocationId) {

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

        System.out.printf("%-5s %-5s %-20s %-15s %-10s %-10s %-10s %-20s\n",
                "Index", "ID", "Name", "Type", "Latitude", "Longitude", "Status");

        int index = 1;
        for (Location location : locations.values()) {

            System.out.printf("%-5s %-20s %-15s %-10.5f %-10.5f %-10s\n",
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

    // // Method to update the road status (damaged or operational) between two
    // // locations
    // public void updateConnectionStatus(String fromLocationId, String
    // toLocationId, boolean isDamaged) {
    // Location fromLocation = locations.get(fromLocationId);
    // Location toLocation = locations.get(toLocationId);

    // if (fromLocation != null && toLocation != null) {
    // fromLocation.updateConnectionStatus(toLocation, isDamaged);
    // toLocation.updateConnectionStatus(fromLocation, isDamaged); // As it's
    // bidirectional
    // }
    // }

    // // Method to generate a report of all locations and their connections
    // public String generateReport() {
    // StringBuilder report = new StringBuilder();
    // for (Location location : locations.values()) {
    // report.append(location.getName())
    // .append(" (").append(location.getLocationType()).append(") Connections: ")
    // .append(location.getConnections().size()).append("\n");
    // for (Map.Entry<Location, Double> entry :
    // location.getConnections().entrySet()) {
    // report.append(" - ").append(entry.getKey().getName())
    // .append(" (Distance: ").append(entry.getValue()).append(")\n");
    // }
    // }
    // return report.toString();
    // }

    // // Method to find the shortest path using Dijkstra's Algorithm
    // public List<Location> findShortestPath(String startLocationId, String
    // endLocationId) {
    // Location start = locations.get(startLocationId);
    // Location end = locations.get(endLocationId);

    // if (start == null || end == null) {
    // return Collections.emptyList();
    // }

    // // Dijkstra's algorithm setup
    // PriorityQueue<Location> pq = new
    // PriorityQueue<>(Comparator.comparingDouble(Location::getDistanceFromStart));
    // start.setDistanceFromStart(0);
    // pq.add(start);

    // Map<Location, Location> previousLocations = new HashMap<>();
    // Map<Location, Double> distances = new HashMap<>();
    // distances.put(start, 0.0);

    // while (!pq.isEmpty()) {
    // Location current = pq.poll();

    // // If we've reached the destination, reconstruct the path
    // if (current.equals(end)) {
    // List<Location> path = new LinkedList<>();
    // while (previousLocations.containsKey(current)) {
    // path.add(current);
    // current = previousLocations.get(current);
    // }
    // Collections.reverse(path);
    // return path;
    // }

    // for (Map.Entry<Location, Double> entry : current.getConnections().entrySet())
    // {
    // Location neighbor = entry.getKey();
    // double newDist = distances.get(current) + entry.getValue();

    // if (newDist < distances.getOrDefault(neighbor, Double.MAX_VALUE)) {
    // distances.put(neighbor, newDist);
    // pq.add(neighbor);
    // previousLocations.put(neighbor, current);
    // }
    // }
    // }

    // return Collections.emptyList(); // No path found
    // }
}
