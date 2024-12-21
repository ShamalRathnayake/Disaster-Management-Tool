package core.baseClasses.network;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
import core.baseClasses.resource.Resource;
import core.enums.Enum.DisasterStatus;
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

    public void addConnection(String fromLocationId, String toLocationId, double distance) throws Exception {

        Location fromLocation = locations.get(fromLocationId);
        Location toLocation = locations.get(toLocationId);

        if (fromLocation != null && toLocation != null) {
            Connection connection = new Connection(fromLocation, toLocation, distance, StatusType.ACTIVE);

            fromLocation.addConnection(connection, true);
            toLocation.addConnection(connection, false);
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
        // System.out.println("\n\n===========================================");
        // System.out.println(" 📍 Location List ");
        // System.out.println("===========================================\n");

        System.out.printf("%-5s %-20s %-15s %-15s %-15s %-10s\n",
                "Index", "Name", "Type", "Latitude", "Longitude", "Status");
        System.out.println("----------------------------------------------------------------------------------------");

        int index = 1;
        for (Location location : locations.values()) {
            System.out.printf("%-5s %-20s %-15s %-15.5f %-15.5f %-10s\n",
                    index++,
                    location.getName(),
                    location.getType(),
                    location.getLatitude(),
                    location.getLongitude(),
                    location.getStatus());
        }

        System.out.println("\n===========================================\n");
    }

    public void saveLocationStatusToFile() {
        StringBuilder output = new StringBuilder();

        output.append("\n═══════════════════════════════════════════════════════════════════════════════════════\n")
                .append("                              🌍 Location List\n")
                .append("═══════════════════════════════════════════════════════════════════════════════════════\n");

        output.append(String.format("%-5s %-25s %-15s %-15s %-15s %-10s\n",
                "No.", "Name", "Type", "Latitude", "Longitude", "Status"))
                .append("───────────────────────────────────────────────────────────────────────────────────────\n");

        int index = 1;
        for (Location location : locations.values()) {
            output.append(String.format("%-5d %-25s %-15s %-15.5f %-15.5f %-10s\n",
                    index++,
                    location.getName(),
                    location.getType(),
                    location.getLatitude(),
                    location.getLongitude(),
                    location.getStatus()));
        }

        output.append("═══════════════════════════════════════════════════════════════════════════════════════\n");

        System.out.print(output.toString());

        String timestamp = java.time.LocalDateTime.now().toString().replace(":", "-");
        String fileName = "location-list" + "_" + timestamp + ".txt";

        try (java.io.FileWriter writer = new java.io.FileWriter(fileName)) {
            writer.write(output.toString());
            System.out.println("📂 Location list saved to file: " + fileName);
        } catch (java.io.IOException e) {
            System.err.println("❌ Error saving to file: " + e.getMessage());
        }
    }

    public void printNetwork() {
        // System.out.println("\nNetwork Graph:");

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

    public void saveNetworkStatusToFile() {

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String fileName = "network_graph_" + timestamp + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            StringBuilder output = new StringBuilder();

            output.append("\n╔══════════════════════════════════════════════╗\n");
            output.append("║              🌐 NETWORK GRAPH                ║\n");
            output.append("╚══════════════════════════════════════════════╝\n\n");

            for (Location location : locations.values()) {
                output.append(String.format("📌 %-30s\n", location.getName()));
                output.append("════════════════════════════════════════════════\n");
                output.append(String.format("  🆔 ID: %-10s  📂 Type: %-10s  🔄 Status: %-10s\n",
                        location.getId(), location.getType(), location.getStatus()));

                List<Connection> connections = location.getConnections().toList();

                if (connections.isEmpty()) {
                    output.append("  ❌ No connections available.\n");
                } else {
                    output.append("  📡 Connections:\n");
                    for (Connection connection : connections) {
                        output.append(String.format("     🔗 From: %-20s → To: %-20s\n",
                                connection.getStartLocation().getName(),
                                connection.getEndLocation().getName()));
                        output.append(String.format("        📏 Distance: %-7.2f KM  🛠 Status: %s\n",
                                connection.getDistance(), connection.getStatus()));
                    }
                }

                output.append("════════════════════════════════════════════════\n\n");
            }

            output.append("╔══════════════════════════════════════════════╗\n");
            output.append("║              🔚 END OF NETWORK               ║\n");
            output.append("╚══════════════════════════════════════════════╝\n");

            System.out.println(output);

            writer.print(output);
            System.out.println("Network graph saved to " + fileName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }

    public void addDisaster(Location location, Disaster disaster) {

        Disaster activeDisaster = location.getActiveDisaster();

        if (activeDisaster != null) {
            System.out.println("\n⚠️  Location already has an active disaster!");
            return;
        }

        location.addDisaster(disaster);

        Map<ResourceType, Integer> requiredResources = disaster.getRequiredResources();

        System.out.println("\n===========================================");
        System.out.println("           Required Resources for the Disaster           ");
        System.out.println("===========================================\n");

        requiredResources.forEach(
                (resourceType, amount) -> System.out.println("- " + resourceType + ": " + amount + " units required"));

        System.out.println("\n===========================================");
        System.out.println("           Resource Availability and Nearest Locations           ");
        System.out.println("===========================================\n");

        for (Map.Entry<ResourceType, Integer> entry : requiredResources.entrySet()) {
            ResourceType resourceType = entry.getKey();
            Integer amount = entry.getValue();

            System.out.println("\n🛠️  Resource: " + resourceType + ", Amount Required: " + amount + " units\n");

            List<LocationPath> nearestLocations = findNearestLocationsWithResourceAndPaths(location, resourceType);

            if (nearestLocations.isEmpty()) {
                System.out.println("  🔴 No locations with the required resource found.");
                continue;
            }

            System.out.println("  🌍 Available Locations:");
            nearestLocations
                    .forEach(locationPath -> System.out.println("\n    - " + locationPath.getLocation().getName()
                            + " (" + locationPath.getLocation().getType() + ", "
                            + formatToTwoDecimalPlaces(locationPath
                                    .getDistance())
                            + " KM) \n        -> "
                            + String.join(" -> ", locationPath.getPath()) + "\n"));

            LocationPath nearestLocation = nearestLocations.get(0);

            System.out.println("\n🏆 Nearest Location: " + nearestLocation.getLocation().getName()
                    + " (" + nearestLocation.getLocation().getType() + ", "
                    + formatToTwoDecimalPlaces(nearestLocation
                            .getDistance())
                    + " KM)\n      Path: "
                    + String.join(" -> ", nearestLocation.getPath()) + "\n");

            System.out.println("---------------------------------------------------");

            // Allocate resources
            List<Resource> resources = nearestLocation.getLocation().getResourcesByType(resourceType);

            if (resources.isEmpty()) {
                System.out.println("  🔴 No resources of type " + resourceType + " available at "
                        + nearestLocation.getLocation().getName());
                continue;
            }

            int remainingAmount = amount;

            for (Resource resource : resources) {
                if (resource.getAvailableQuantity() > 0) {
                    int allocationAmount = Math.min(resource.getAvailableQuantity(), remainingAmount);
                    resource.setAllocatedQuantity(resource.getAllocatedQuantity() + allocationAmount);
                    disaster.addResourceAllocation(resource.getResourceId(), allocationAmount);

                    System.out.println("    ✅ Allocated " + allocationAmount + " units of " + resourceType
                            + " from " + nearestLocation.getLocation().getName() + "\n");

                    remainingAmount -= allocationAmount;

                    if (remainingAmount == 0) {
                        break;
                    }
                }
            }

            if (remainingAmount > 0) {
                System.out.println("    🔴 Unable to fully allocate all required resources.");
            } else {
                System.out.println("    ✅ Resource allocation successful.");
                disaster.setStatus(DisasterStatus.IN_PROGRESS);
            }

            System.out.println(
                    "----------------------------------------------------------------------------------------");
        }
    }

    public List<LocationPath> findNearestLocationsWithResourceAndPaths(
            Location startLocation,
            ResourceType resourceType) {
        if (!locations.containsKey(startLocation.getId())) {
            throw new IllegalArgumentException("Invalid start location ID.");
        }

        PriorityQueue<LocationDistancePair> priorityQueue = new PriorityQueue<>(
                Comparator.comparingDouble(pair -> pair.distance));
        HashMap<String, Double> distances = new HashMap<>();
        HashMap<String, String> predecessors = new HashMap<>();

        for (String locationId : locations.keySet()) {
            distances.put(locationId, Double.MAX_VALUE);
            predecessors.put(locationId, null);
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
                    predecessors.put(neighbor.getId(), currentLocation.getId());
                    priorityQueue.add(new LocationDistancePair(neighbor, newDistance));
                }
            }
        }

        List<LocationPath> result = new ArrayList<>();
        for (Map.Entry<String, Double> entry : distances.entrySet()) {
            Location location = locations.get(entry.getKey());
            if (!location.getId().equals(startLocation.getId())
                    && location.getResourcesCount().getOrDefault(resourceType, 0) > 0) {
                List<String> path = reconstructPath(predecessors, startLocation.getId(), location.getId());
                result.add(new LocationPath(location, entry.getValue(), path));
            }
        }

        result.sort(Comparator.comparingDouble(LocationPath::getDistance));

        return result;
    }

    private List<String> reconstructPath(HashMap<String, String> predecessors, String startId, String targetId) {
        List<String> path = new ArrayList<>();
        String currentId = targetId;

        while (currentId != null) {
            Location currentLocation = locations.get(currentId);
            if (currentLocation != null) {
                path.add(currentLocation.getName());
            }
            currentId = predecessors.get(currentId);
        }

        // Collections.reverse(path);
        return path;
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

    private static class LocationPath {
        private Location location;
        private double distance;
        private List<String> path;

        LocationPath(Location location, double distance, List<String> path) {
            this.location = location;
            this.distance = distance;
            this.path = path;
        }

        public Location getLocation() {
            return location;
        }

        public double getDistance() {
            return distance;
        }

        public List<String> getPath() {
            return path;
        }
    }

    public void resolveDisaster(Location location) {
        Disaster disaster = location.getActiveDisaster();

        if (disaster == null) {
            System.out.println("⚠️  No active disaster to resolve at location: " + location.getName());
            return;
        }

        Map<String, Integer> allocatedResources = disaster.getAllocatedResources();

        System.out.println("\n===========================================");
        System.out.println("      Resolving Disaster: " + disaster.getDisasterType());
        System.out.println("===========================================");

        for (Map.Entry<String, Integer> entry : allocatedResources.entrySet()) {
            String resourceId = entry.getKey();
            int allocatedAmount = entry.getValue();

            Location resourceLocation = getLocationByResource(resourceId);
            Resource resource = resourceLocation.getResourceById(resourceId);

            if (resource != null) {
                resource.setAllocatedQuantity(resource.getAllocatedQuantity() - allocatedAmount);

                System.out.println("\n🔄  Released " + allocatedAmount + " unit(s) of " + resource.getResourceType()
                        + " from location: " + location.getName());
                System.out.println("     ➡️  Resource Location: " + resourceLocation.getName());

                resourceLocation.updateResourceCount(resource.getResourceType(), allocatedAmount);

                System.out.println("     ✅  Resource update successful.");
            } else {

                System.out.println("\n❌  Resource with ID: " + resourceId + " not found at location: "
                        + resourceLocation.getName());
            }
        }

        System.out.println("\n===========================================");
        System.out.println("     Disaster Resolution Completed");
        System.out.println("===========================================");
    }

    public Location getLocationByResource(String resourceId) {
        Location selectedLocation = null;
        for (Location location : locations.values()) {
            if (location.getResourceById(resourceId) != null) {
                selectedLocation = location;
            }
        }

        return selectedLocation;
    }

    public static String formatToTwoDecimalPlaces(double value) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(value);
    }

}
