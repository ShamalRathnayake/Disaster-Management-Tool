package core.baseClasses.location;

import java.util.*;

import core.baseClasses.connection.Connection;
import core.baseClasses.disaster.Disaster;
import core.baseClasses.log.LogEntry;
import core.baseClasses.resource.Resource;
import core.enums.Enum.DisasterStatus;
import core.enums.Enum.DisasterType;
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

    private HashMap<ResourceType, Integer> resourcesCount;

    private CustomLinkedList<Resource> resources;

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
        this.resourcesCount = new HashMap<>();
        this.resources = new CustomLinkedList<>();
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

    public HashMap<ResourceType, Integer> getResourcesCount() {
        return resourcesCount;
    }

    // public void setResources(HashMap<ResourceType, Integer> resources) {
    // this.resources = resources;
    // }

    public CustomLinkedList<Resource> getResources() {
        return resources;
    }

    public void setResources(CustomLinkedList<Resource> resources) {
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

    public boolean isOperational() {
        return this.status == StatusType.ACTIVE;
    }

    public CustomLinkedList<LogEntry> getLogs() {
        return logs;
    }

    public void setLogs(CustomLinkedList<LogEntry> logs) {
        this.logs = logs;
    }

    public List<Connection> getConnectionsByStartLocation(Location startLocation) {
        List<Connection> selectedConnections = new ArrayList<>();

        for (Connection connection : connections.toList()) {
            if (connection.getStartLocation().equals(startLocation)) {
                selectedConnections.add(connection);
            }
        }

        return selectedConnections;
    }

    public List<Connection> getConnectionsByEndLocation(Location endLocation) {
        List<Connection> selectedConnections = new ArrayList<>();

        for (Connection connection : connections.toList()) {
            if (connection.getEndLocation().equals(endLocation)) {
                selectedConnections.add(connection);
            }
        }

        return selectedConnections;
    }

    public void addConnection(Connection connection, Boolean isStart) throws Exception {
        if ((isStart && getConnectionsByStartLocation(connection.getStartLocation()).isEmpty())
                || (!isStart && getConnectionsByEndLocation(connection.getEndLocation()).isEmpty())) {
            connections.insertEnd(connection);
        } else {
            throw new Exception("Connection already exists for the specified location.");
        }
    }

    public void removeConnection(Connection connection) {

        connections.deleteNode(connection);

    }

    public void printConnections() {
        System.out.println("\n===========================================");
        System.out.println("            Connections Overview           ");
        System.out.println("===========================================\n");

        System.out.printf("%-5s %-20s %-20s %-15s %-12s %-25s\n",
                "Index", "Start Location", "End Location", "Distance (km)", "Status", "Last Updated");

        System.out.println("-------------------------------------------------------------");

        int index = 1;
        for (Connection connection : this.connections.toList()) {
            System.out.printf("%-5d %-20s %-20s %-15.2f %-12s %-25s\n",
                    index++,
                    connection.getStartLocation().getName(),
                    connection.getEndLocation().getName(),
                    connection.getDistance(),
                    connection.getStatus(),
                    connection.getLastUpdated().toString());
        }

        System.out.println("\n");
    }

    // // Resource Management Methods
    public void addResource(Resource resource) {
        resources.insertEnd(resource);
        int count = resourcesCount.getOrDefault(resource.getResourceType(), 0);
        resourcesCount.put(resource.getResourceType(), count + resource.getTotalQuantity());
    }

    public void printResources() {

        System.out.printf("%-5s %-20s %-15s %-15s %-15s %-20s\n",
                "Index", "Resource Type", "Total Quantity",
                "Allocated Qty", "Available Qty", "Stationed At");

        int index = 1;

        for (Resource resource : this.resources.toList()) {
            System.out.printf("%-5d %-20s %-15d %-15d %-15d %-20s\n",
                    index++,
                    resource.getResourceType().name(),
                    resource.getTotalQuantity(),
                    resource.getAllocatedQuantity(),
                    resource.getTotalQuantity() - resource.getAllocatedQuantity(),
                    resource.getStationedAt().getName());
        }

        System.out.println("");
    }

    public void removeResource(Resource resource) {
        if (resource == null) {
            System.out.println("Invalid resource. Cannot remove.");
            return;
        }

        Node<Resource> resourceNode = resources.getNode(resource);

        if (resourceNode == null) {
            System.out.println("Resource not found in the location.");
            return;
        }

        int currentCount = resourcesCount.get(resource.getResourceType());
        resourcesCount.put(resource.getResourceType(), currentCount - resource.getTotalQuantity());

        resources.deleteNode(resource);

        System.out.println("Resource removed successfully: " + resource.getResourceType());
    }

    public void addDisaster(Disaster disaster) {

        disasters.insertEnd(disaster);
        status = StatusType.DAMAGED;
    }

    public List<Resource> getResourcesByType(ResourceType resourceType) {
        List<Resource> filteredResources = new ArrayList<>();

        for (Resource resource : resources.toList()) {
            if (resource.getResourceType().equals(resourceType)) {
                filteredResources.add(resource);
            }
        }

        return filteredResources;
    }

    public Resource getResourceById(String resourceId) {
        Resource filteredResource = null;

        for (Resource resource : resources.toList()) {
            if (resource.getResourceId().equals(resourceId)) {
                filteredResource = resource;
            }
        }

        return filteredResource;
    }

    public Disaster getActiveDisaster() {

        Disaster activeDisaster = null;
        for (Disaster disaster : disasters.toList()) {
            if (disaster.getStatus().equals(DisasterStatus.IN_PROGRESS)) {
                activeDisaster = disaster;
            }
        }

        return activeDisaster;
    }

    public void updateResourceCount(ResourceType resourceType, int amountReleased) {
        if (resourcesCount.containsKey(resourceType)) {
            int currentCount = resourcesCount.get(resourceType);
            int updatedCount = currentCount + amountReleased;
            resourcesCount.put(resourceType, updatedCount);

            System.out.println("Updated " + resourceType + " count in " + getName() + ": " + updatedCount);
        } else {
            System.out.println("Error: ResourceType " + resourceType + " not found in resourcesCount.");
        }
    }

}