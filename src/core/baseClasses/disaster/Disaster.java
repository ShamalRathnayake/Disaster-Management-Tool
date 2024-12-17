package core.baseClasses.disaster;

import java.util.Date;
import java.util.Map;

import core.baseClasses.location.Location;
import core.enums.Enum.DisasterStatus;
import core.enums.Enum.DisasterType;
import core.enums.Enum.ResourceType;

public class Disaster {
    // Unique identifier for the disaster event
    private String disasterId;

    // Type of the disaster (e.g., EARTHQUAKE, FLOOD)
    private DisasterType disasterType;

    // Location where the disaster is happening
    private Location location;

    // Severity of the disaster (scale from 1 to 10, or predefined levels)
    private int severity;

    // Status of the disaster (whether it is in progress or resolved)
    private DisasterStatus status;

    // Resources required to handle the disaster
    private Map<ResourceType, Integer> requiredResources; // Resource type and quantity

    // Timestamp of when the disaster was reported
    private Date reportedAt;

    // Timestamp for when the disaster was resolved (if applicable)
    private Date resolvedAt;

    // Constructor to initialize a disaster event
    public Disaster(String disasterId, DisasterType disasterType, Location location,
            int severity, DisasterStatus status, Map<ResourceType, Integer> requiredResources) {
        this.disasterId = disasterId;
        this.disasterType = disasterType;
        this.location = location;
        this.severity = severity;
        this.status = status;
        this.requiredResources = requiredResources;
        this.reportedAt = new Date(); // Set the current time when the disaster is reported
        this.resolvedAt = null;
    }

    // Getter and Setter methods for all attributes

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

    // Method to resolve the disaster
    public void resolveDisaster() {
        this.status = DisasterStatus.RESOLVED;
        this.resolvedAt = new Date(); // Set the resolution time
    }

    // Method to update the severity of the disaster (can be used for dynamic
    // changes)
    public void updateSeverity(int severity) {
        this.severity = severity;
    }

    // Method to report the disaster (could be called when a disaster occurs)
    public void reportDisaster() {
        this.status = DisasterStatus.REPORTED;
        this.reportedAt = new Date(); // Set the report time
    }

    // Method to get a summary of the disaster details (for reporting/logging
    // purposes)
    public String getDisasterSummary() {
        return String.format("Disaster ID: %s\nType: %s\nLocation: %s\nSeverity: %d\n" +
                "Status: %s\nResources Required: %s\nReported At: %s\nResolved At: %s",
                disasterId, disasterType, location.getName(), severity,
                status, requiredResources.toString(), reportedAt,
                resolvedAt != null ? resolvedAt.toString() : "Not Resolved");
    }
}
