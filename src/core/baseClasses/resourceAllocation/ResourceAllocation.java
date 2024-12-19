package core.baseClasses.resourceAllocation;

import java.util.HashMap;
import java.util.Map;

import core.baseClasses.disaster.Disaster;
import core.baseClasses.resource.Resource;

public class ResourceAllocation {
    // Unique identifier for this resource allocation
    private String allocationId;

    // The disaster this allocation belongs to
    private Disaster disaster;

    // A map to track resources allocated to this disaster (ResourceID ->
    // Quantity)
    private Map<String, Integer> resourcesAllocated;

    // Constructor for initializing a resource allocation for a specific disaster
    public ResourceAllocation(String allocationId, Disaster disaster) {
        this.allocationId = allocationId;
        this.disaster = disaster;
        this.resourcesAllocated = new HashMap<>();
    }

    // Getter and Setter methods for all attributes

    public String getAllocationId() {
        return allocationId;
    }

    public void setAllocationId(String allocationId) {
        this.allocationId = allocationId;
    }

    public Disaster getDisaster() {
        return disaster;
    }

    public void setDisaster(Disaster disaster) {
        this.disaster = disaster;
    }

    public Map<String, Integer> getResourcesAllocated() {
        return resourcesAllocated;
    }

    public void setResourcesAllocated(Map<String, Integer> resourcesAllocated) {
        this.resourcesAllocated = resourcesAllocated;
    }

    // Method to allocate resources to this disaster
    public boolean allocateResource(Resource resource, int quantity) {
        if (resource.availableForAllocation() >= quantity) {
            // If the resource is available, allocate it
            resourcesAllocated.put(resource.getResourceId(), quantity);
            resource.allocateToDisaster(disaster.getDisasterId(), quantity);
            return true;
        }
        return false;
    }

    // Method to release resources from this disaster
    public boolean releaseResource(Resource resource, int quantity) {
        if (resourcesAllocated.containsKey(resource.getResourceId()) &&
                resourcesAllocated.get(resource.getResourceId()) >= quantity) {
            // Deallocate the resource from this disaster
            resourcesAllocated.put(resource.getResourceId(),
                    resourcesAllocated.get(resource.getResourceId()) - quantity);
            resource.releaseFromDisaster(disaster.getDisasterId(), quantity);
            return true;
        }
        return false;
    }

    // Method to get a summary of all resource allocations for this disaster
    public String getResourceAllocationSummary() {
        StringBuilder summary = new StringBuilder();
        summary.append("Disaster: ").append(disaster.getDisasterType()).append("\n");
        summary.append("Allocated Resources:\n");
        for (Map.Entry<String, Integer> entry : resourcesAllocated.entrySet()) {
            summary.append("Resource: ").append(entry.getKey())
                    .append(", Quantity: ").append(entry.getValue()).append("\n");
        }
        return summary.toString();
    }

    // Method to reallocate resources between disasters
    public boolean reallocateResource(Resource resource, int quantity, Disaster newDisaster) {
        // First, release resources from the current disaster
        if (releaseResource(resource, quantity)) {
            // Then, allocate them to the new disaster
            ResourceAllocation newAllocation = newDisaster.getResourceAllocation();
            return newAllocation.allocateResource(resource, quantity);
        }
        return false;
    }
}
