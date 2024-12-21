package core.baseClasses.resource;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import core.baseClasses.location.Location;
import core.enums.Enum.ResourceType;

public class Resource {

    private String resourceId;

    private ResourceType resourceType;

    private int totalQuantity;

    private int allocatedQuantity;

    private Location stationedAt;

    private Map<String, Integer> disasterAllocations;

    public Resource(ResourceType resourceType, int totalQuantity, Location stationedAt) {
        this.resourceId = UUID.randomUUID().toString();
        this.resourceType = resourceType;
        this.totalQuantity = totalQuantity;
        this.allocatedQuantity = 0;
        this.stationedAt = stationedAt;
        this.disasterAllocations = new HashMap<>();
    }

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public ResourceType getResourceType() {
        return resourceType;
    }

    public void setResourceType(ResourceType resourceType) {
        this.resourceType = resourceType;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }

    public int getAllocatedQuantity() {
        return allocatedQuantity;
    }

    public void setAllocatedQuantity(int allocatedQuantity) {
        this.allocatedQuantity = allocatedQuantity;
    }

    public int getAvailableQuantity() {
        return totalQuantity - allocatedQuantity;
    }

    public Location getStationedAt() {
        return stationedAt;
    }

    public void setStationedAt(Location stationedAt) {
        this.stationedAt = stationedAt;
    }

    public Map<String, Integer> getDisasterAllocations() {
        return disasterAllocations;
    }

    public void setDisasterAllocations(Map<String, Integer> disasterAllocations) {
        this.disasterAllocations = disasterAllocations;
    }

}
