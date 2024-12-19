package core.baseClasses.network;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

import core.baseClasses.connection.Connection;
import core.baseClasses.disaster.Disaster;
import core.baseClasses.location.Location;
import core.enums.Enum.LocationType;
import core.enums.Enum.ResourceType;
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

    public void editConnectionStatus(Connection connection, StatusType status) {
        connection.setStatus(status);
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

            System.out.printf("** Location: %-20s [ID: %s, Type: %s, Status: %s]\n",
                    location.getName(),
                    location.getId(),
                    location.getType(),
                    location.getStatus());

            List<Connection> connections = location.getConnections().toList();

            if (connections.isEmpty()) {
                System.out.println("  -- No connections.");
            } else {
                System.out.println("  -- Connections:");

                for (Connection connection : connections) {
                    System.out.printf("\n   %-20s ---> %-20s [Distance: %.2f KM, Status: %s]\n",
                            connection.getStartLocation().getName(),
                            connection.getEndLocation().getName(),
                            connection.getDistance(),
                            connection.getStatus());
                }
            }

            System.out.println();
        }
    }

    public void addDisaster(Location location, Disaster disaster) {
        // add disaster to location
        location.addDisaster(disaster);

        // list the resources required to resolve the disaster
        Map<ResourceType, Integer> requiredResources = disaster.getRequiredResources();

        // find the shortest path to each resource

        for (Map.Entry<ResourceType, Integer> entry : requiredResources.entrySet()) {

            ResourceType resourceType = entry.getKey();
            Integer amount = entry.getValue();

            // Process the resource type and amount
            System.out.println("Resource: " + resourceType + ", Amount Required: " + amount);
        }

        // allocate resource to location

    }

    // public List<Location> findNearestLocationsWithResource(Location
    // startLocation, ResourceType resourceType) {
    // if (!locations.containsKey(startLocation.getId())) {
    // throw new IllegalArgumentException("Invalid start location ID.");
    // }

    // PriorityQueue<LocationDistancePair> priorityQueue = new PriorityQueue<>(
    // Comparator.comparingDouble(pair -> pair.distance));
    // HashMap<String, Double> distances = new HashMap<>();
    // HashMap<String, Location> previous = new HashMap<>();

    // for (String locationId : locations.keySet()) {
    // distances.put(locationId, Double.MAX_VALUE);
    // }
    // distances.put(startLocation.getId(), 0.0);

    // priorityQueue.add(new LocationDistancePair(startLocation, 0.0));

    // Set<String> visited = new HashSet<>();

    // while (!priorityQueue.isEmpty()) {
    // LocationDistancePair currentPair = priorityQueue.poll();
    // Location currentLocation = currentPair.location;

    // if (visited.contains(currentLocation.getId())) {
    // continue;
    // }
    // visited.add(currentLocation.getId());

    // for (Connection connection : currentLocation.getConnections().toList()) {
    // if (!connection.isOperational()) {
    // continue;
    // }

    // Location neighbor =
    // connection.getEndLocation().getId().equals(currentLocation.getId())
    // ? connection.getStartLocation()
    // : connection.getEndLocation();

    // if (visited.contains(neighbor.getId()) || !neighbor.isOperational()) {
    // continue;
    // }

    // double newDistance = distances.get(currentLocation.getId()) +
    // connection.getDistance();

    // if (newDistance < distances.get(neighbor.getId())) {
    // distances.put(neighbor.getId(), newDistance);
    // previous.put(neighbor.getId(), currentLocation);
    // priorityQueue.add(new LocationDistancePair(neighbor, newDistance));
    // }
    // }
    // }

    // List<Location> result = new ArrayList<>();
    // for (Map.Entry<String, Double> entry : distances.entrySet()) {
    // Location location = locations.get(entry.getKey());
    // if (location.getResourcesCount().getOrDefault(resourceType, 0) > 0) {
    // result.add(location);
    // }
    // }

    // result.sort(Comparator.comparingDouble(location ->
    // distances.get(location.getId())));

    // return result;
    // }

    public List<LocationDistancePair> findNearestLocationsWithResource(
            Location startLocation,
            ResourceType resourceType) {
        if (!locations.containsKey(startLocation.getId())) {
            throw new IllegalArgumentException("Invalid start location ID.");
        }

        PriorityQueue<LocationDistancePair> priorityQueue = new PriorityQueue<>(
                Comparator.comparingDouble(pair -> pair.distance));
        HashMap<String, Double> distances = new HashMap<>();
        HashMap<String, Location> previous = new HashMap<>();

        for (String locationId : locations.keySet()) {
            distances.put(locationId, Double.MAX_VALUE);
        }
        distances.put(startLocation.getId(), 0.0);

        priorityQueue.add(new LocationDistancePair(startLocation, 0.0));

        Set<String> visited = new HashSet<>();

        while (!priorityQueue.isEmpty()) {
            LocationDistancePair currentPair = priorityQueue.poll();
            Location currentLocation = currentPair.location;

            if (visited.contains(currentLocation.getId())) {
                continue;
            }
            visited.add(currentLocation.getId());

            for (Connection connection : currentLocation.getConnections().toList()) {
                if (!connection.isOperational()) {
                    continue;
                }

                Location neighbor = connection.getEndLocation().getId().equals(currentLocation.getId())
                        ? connection.getStartLocation()
                        : connection.getEndLocation();

                if (visited.contains(neighbor.getId()) || !neighbor.isOperational()) {
                    continue;
                }

                double newDistance = distances.get(currentLocation.getId()) + connection.getDistance();

                if (newDistance < distances.get(neighbor.getId())) {
                    distances.put(neighbor.getId(), newDistance);
                    previous.put(neighbor.getId(), currentLocation);
                    priorityQueue.add(new LocationDistancePair(neighbor, newDistance));
                }
            }
        }

        List<LocationDistancePair> result = new ArrayList<>();
        for (Map.Entry<String, Double> entry : distances.entrySet()) {
            Location location = locations.get(entry.getKey());
            if (location.getResourcesCount().getOrDefault(resourceType, 0) > 0) {
                result.add(new LocationDistancePair(location, entry.getValue()));
            }
        }

        result.sort(Comparator.comparingDouble(LocationDistancePair::getDistance));

        return result;
    }

    private static class LocationDistancePair {
        Location location;
        double distance;

        LocationDistancePair(Location location, double distance) {
            this.location = location;
            this.distance = distance;
        }

        public Location getLocation() {
            return location;
        }

        public double getDistance() {
            return distance;
        }
    }

}
