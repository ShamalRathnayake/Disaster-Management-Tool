package core.baseClasses.connection;

import java.util.Date;
import java.util.UUID;

import core.baseClasses.location.Location;
import core.enums.Enum.*;
import core.enums.Enum.StatusType;

public class Connection {

    private String connectionId;

    private Location startLocation;
    private Location endLocation;

    private double distance;

    private StatusType status;

    private Date lastUpdated;

    public Connection(Location startLocation, Location endLocation,
            double distance, StatusType status) {
        this.connectionId = UUID.randomUUID().toString();
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.distance = distance;
        this.status = status;
        this.lastUpdated = new Date();
    }

    public String getConnectionId() {
        return connectionId;
    }

    public Location getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(Location startLocation) {
        this.startLocation = startLocation;
    }

    public Location getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(Location endLocation) {
        this.endLocation = endLocation;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public void updateConnectionStatus(StatusType status) {
        this.status = status;
        this.lastUpdated = new Date();
    }

    public boolean isOperational() {
        return this.status == StatusType.ACTIVE;
    }

    public String getConnectionDetails() {
        return String.format("Connection ID: %s\nStart Location: %s\nEnd Location: %s\nDistance: %.2f km\n" +
                "Status: %s\nDamage Status: %s\nLast Updated: %s",
                connectionId, startLocation.getName(), endLocation.getName(), distance,
                status, lastUpdated);
    }

}