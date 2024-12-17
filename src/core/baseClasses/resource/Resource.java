package core.baseClasses.resource;

import java.util.HashMap;
import java.util.Map;

import core.baseClasses.location.Location;
import core.enums.Enum.ResourceType;

public class Resource {

    private String resourceId;

    private ResourceType resourceType;

    private int totalQuantity;

    private int allocatedQuantity;

    private Location stationedAt;

    private Map<String, Integer> disasterAllocations;

    public Resource(String resourceId, ResourceType resourceType, int totalQuantity, Location stationedAt) {
        this.resourceId = resourceId;
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

    // // Method to allocate a certain number of this resource to a disaster
    // public boolean allocateToDisaster(String disasterId, int quantity) {
    // if (quantity <= (totalQuantity - allocatedQuantity)) {
    // // Allocate resources
    // allocatedQuantity += quantity;
    // disasterAllocations.put(disasterId,
    // disasterAllocations.getOrDefault(disasterId, 0) + quantity);
    // return true;
    // } else {
    // System.out.println("Not enough resources available for allocation.");
    // return false;
    // }
    // }

    // // Method to reallocate a certain number of resources from one disaster to
    // // another
    // public boolean reallocateToDisaster(String disasterIdFrom, String
    // disasterIdTo, int quantity) {
    // // Check if the resource is already allocated to the source disaster
    // if (disasterAllocations.containsKey(disasterIdFrom) &&
    // disasterAllocations.get(disasterIdFrom) >= quantity) {
    // // Deallocate from the original disaster
    // disasterAllocations.put(disasterIdFrom,
    // disasterAllocations.get(disasterIdFrom) - quantity);

    // // Allocate to the new disaster
    // return allocateToDisaster(disasterIdTo, quantity);
    // } else {
    // System.out.println("Not enough resources allocated to the source disaster for
    // reallocation.");
    // return false;
    // }
    // }

    // // Method to release a certain number of resources from a disaster
    // (unallocate
    // // them)
    // public boolean releaseFromDisaster(String disasterId, int quantity) {
    // if (disasterAllocations.containsKey(disasterId) &&
    // disasterAllocations.get(disasterId) >= quantity) {
    // // Release resources
    // disasterAllocations.put(disasterId, disasterAllocations.get(disasterId) -
    // quantity);
    // allocatedQuantity -= quantity;
    // return true;
    // } else {
    // System.out.println("Invalid request: Insufficient resources allocated to the
    // disaster.");
    // return false;
    // }
    // }

    // // Method to check how many resources are available for allocation
    // public int availableForAllocation() {
    // return totalQuantity - allocatedQuantity;
    // }

    // // Method to get a summary of the resource details
    // public String getResourceSummary() {
    // return String.format(
    // "Resource ID: %s\nType: %s\nTotal Quantity: %d\nAllocated Quantity:
    // %d\nStationed At: %s\nDisaster Allocations: %s",
    // resourceId, resourceType, totalQuantity, allocatedQuantity,
    // stationedAt.getName(),
    // disasterAllocations.toString());
    // }
}
