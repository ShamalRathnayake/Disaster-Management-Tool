package core.baseClasses.disaster;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import core.baseClasses.disasterConfig.DisasterConfig;
import core.baseClasses.location.Location;
import core.enums.Enum.DisasterStatus;
import core.enums.Enum.DisasterType;
import core.enums.Enum.ResourceType;

public class Disaster {

    private String disasterId;

    private DisasterType disasterType;

    private Location location;

    private int severity;

    private DisasterStatus status;

    private Map<ResourceType, Integer> requiredResources;

    private Map<String, Integer> allocatedResources;

    private Date reportedAt;

    private Date resolvedAt;

    public Disaster(DisasterType disasterType, Location location, int severity) {
        this.disasterId = UUID.randomUUID().toString();
        this.disasterType = disasterType;
        this.location = location;
        this.severity = severity;
        this.status = DisasterStatus.REPORTED;
        this.requiredResources = DisasterConfig.getRequiredResources(disasterType, severity);
        this.allocatedResources = new HashMap<>();
        this.reportedAt = new Date();
        this.resolvedAt = null;
    }

    public String getDisasterId() {
        return disasterId;
    }

    public void setDisasterId(String disasterId) {
        this.disasterId = disasterId;
    }

    public DisasterType getDisasterType() {
        return disasterType;
    }

    public void setDisasterType(DisasterType disasterType) {
        this.disasterType = disasterType;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        if (severity < 1 || severity > 10) {
            throw new IllegalArgumentException("Severity must be between 1 and 10.");
        }
        this.severity = severity;
    }

    public DisasterStatus getStatus() {
        return status;
    }

    public void setStatus(DisasterStatus status) {
        this.status = status;
    }

    public Map<ResourceType, Integer> getRequiredResources() {
        return requiredResources;
    }

    public void setRequiredResources(Map<ResourceType, Integer> requiredResources) {
        this.requiredResources = requiredResources;
    }

    public Date getReportedAt() {
        return reportedAt;
    }

    public void setReportedAt(Date reportedAt) {
        this.reportedAt = reportedAt;
    }

    public Date getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(Date resolvedAt) {
        this.resolvedAt = resolvedAt;
    }

    public void resolveDisaster() {
        this.status = DisasterStatus.RESOLVED;
        this.resolvedAt = new Date();
    }

    public void updateSeverity(int severity) {
        this.severity = severity;
    }

    public void reportDisaster() {
        this.status = DisasterStatus.REPORTED;
        this.reportedAt = new Date();
    }

    public String getDisasterSummary() {
        return String.format("Disaster ID: %s\nType: %s\nLocation: %s\nSeverity: %d\n" +
                "Status: %s\nResources Required: %s\nReported At: %s\nResolved At: %s",
                disasterId, disasterType, location.getName(), severity,
                status, requiredResources.toString(), reportedAt,
                resolvedAt != null ? resolvedAt.toString() : "Not Resolved");
    }

    public void addResourceAllocation(String resourceId, int allocatedAmount) {
        if (allocatedResources.containsKey(resourceId)) {
            allocatedResources.put(resourceId, allocatedResources.get(resourceId) + allocatedAmount);
        } else {
            allocatedResources.put(resourceId, allocatedAmount);
        }
    }

    public Map<String, Integer> getAllocatedResources() {
        return this.allocatedResources;
    }
}
