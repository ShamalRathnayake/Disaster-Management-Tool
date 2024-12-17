package core.baseClasses.location;

import java.util.*;

import core.baseClasses.connection.Connection;
import core.baseClasses.disaster.Disaster;
import core.baseClasses.log.LogEntry;
import core.enums.Enum.LocationType;
import core.enums.Enum.ResourceType;
import core.enums.Enum.StatusType;
import data.CustomLinkedList;
import data.Node;

public class Location {

    private String id;

    private String name;

    private LocationType type;

    private double latitude;
    private double longitude;

    private HashMap<ResourceType, Integer> resources;

    private CustomLinkedList<Disaster> disasters;

    private CustomLinkedList<Connection> connections;

    private StatusType status;

    private CustomLinkedList<LogEntry> logs;

    public Location(String name, LocationType type, double latitude, double longitude) {

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.type = type;
        this.latitude = latitude;
        this.longitude = longitude;
        this.resources = new HashMap<>();
        this.disasters = new CustomLinkedList<>();
        this.connections = new CustomLinkedList<>();
        this.status = StatusType.ACTIVE;
        this.logs = new CustomLinkedList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocationType getType() {
        return type;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public HashMap<ResourceType, Integer> getResources() {
        return resources;
    }

    public void setResources(HashMap<ResourceType, Integer> resources) {
        this.resources = resources;
    }

    public CustomLinkedList<Disaster> getDisasters() {
        return disasters;
    }

    public void setDisasters(CustomLinkedList<Disaster> disasters) {
        this.disasters = disasters;
    }

    public CustomLinkedList<Connection> getConnections() {
        return connections;
    }

    // public void setConnections(CustomLinkedList<Connection> connections) {
    // this.connections = connections;
    // }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public CustomLinkedList<LogEntry> getLogs() {
        return logs;
    }

    public void setLogs(CustomLinkedList<LogEntry> logs) {
        this.logs = logs;
    }

    public void addConnection(Connection connection) {
        if (connections.getNode(connection) == null) {
            connections.insertEnd(connection);
            // logs.add("Connection added between " + name + " and " +
            // connection.getDestination().getName());
        }
    }

    public void removeConnection(Connection connection) {

        Node<Connection> deletedConnection = connections.deleteNode(connection);

        if (deletedConnection != null) {
            // logs.add("Connection removed between " + name + " and " +
            // connection.getDestination().getName());
        }
    }

    public void printConnections() {
        System.out.println("\nConnections:");

        System.out.printf("%-5s %-20s %-20s %-20s %-10s %-10s %-25s\n",
                "Index", "Connection ID", "Start Location", "End Location",
                "Distance", "Status", "Last Updated");

        int index = 1;

        for (Connection connection : this.connections.toList()) {
            System.out.printf("%-5d %-20s %-20s %-20s %-10.2f %-10s %-25s\n",
                    index++,
                    connection.getConnectionId(),
                    connection.getStartLocation().getName(),
                    connection.getEndLocation().getName(),
                    connection.getDistance(),
                    connection.getStatus(),
                    connection.getLastUpdated().toString());
        }

        System.out.println("");
    }

    // // Resource Management Methods
    // public void addResource(ResourceType resource, int quantity) {
    // int currentQuantity = resources.getOrDefault(resource, 0);
    // int maxCapacity = capacity.getOrDefault(resource, Integer.MAX_VALUE);

    // if (currentQuantity + quantity <= maxCapacity) {
    // resources.put(resource, currentQuantity + quantity);
    // // logs.add("Added " + quantity + " of " + type + " to " + name);
    // } else {
    // // logs.add("Attempted to exceed capacity for " + type + " at " + name);
    // }
    // }

    // public void removeResource(ResourceType type, int quantity) {
    // int currentQuantity = resources.getOrDefault(type, 0);

    // if (currentQuantity >= quantity) {
    // resources.put(type, currentQuantity - quantity);
    // logs.add("Removed " + quantity + " of " + type + " from " + name);
    // } else {
    // logs.add("Not enough " + type + " at " + name);
    // }
    // }

    // public Map<ResourceType, Integer> getAvailableResources() {
    // return new HashMap<>(resources); // return a copy to preserve integrity
    // }

    // // Disaster Management Methods
    // public void addDisaster(Disaster disaster) {
    // if (!disasters.contains(disaster)) {
    // disasters.add(disaster);
    // logs.add("Disaster reported at " + name + ": " + disaster.getType());
    // }
    // }

    // public void removeDisaster(Disaster disaster) {
    // if (disasters.remove(disaster)) {
    // logs.add("Disaster resolved at " + name + ": " + disaster.getType());
    // }
    // }

    // public List<Disaster> getActiveDisasters() {
    // return new ArrayList<>(disasters); // return a copy to preserve integrity
    // }

    // // Status Management Methods
    // public void markAsActive() {
    // this.status = LocationStatus.ACTIVE;
    // logs.add(name + " marked as ACTIVE.");
    // }

    // public void markAsDamaged() {
    // this.status = LocationStatus.DAMAGED;
    // logs.add(name + " marked as DAMAGED.");
    // }

    // public boolean isOperable() {
    // return status == LocationStatus.ACTIVE;
    // }

    // // Logging Methods
    // public void addLog(String logEntry) {
    // logs.add(logEntry);
    // }

    // public List<String> getLogs() {
    // return new ArrayList<>(logs); // return a copy to preserve integrity
    // }

    // // Utility Methods
    // public double calculateDistance(Location other) {
    // // Simple Euclidean distance or use Haversine formula for more precision in
    // // real-world scenarios
    // return Math.sqrt(Math.pow(this.latitude - other.latitude, 2) +
    // Math.pow(this.longitude - other.longitude, 2));
    // }
}