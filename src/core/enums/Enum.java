package core.enums;

public class Enum {

    public enum LocationType {
        CITY, HOSPITAL, FIRE_STATION, WATER_TANK, POWER_STATION,
        POLICE_STATION, SCHOOL
    }

    public enum StatusType {
        ACTIVE, DAMAGED, INOPERABLE
    }

    public enum ResourceType {
        FIRE_TRUCK, MEDICAL_PERSONNEL, RESCUE_PERSONNEL, AMBULANCES,
        FOOD_BOXES, WATER_BOTTLES, BLOOD_BAGS, FUEL_CONTAINERS, MEDICINE_BOXES,
    }

    public enum DisasterType {
        EARTHQUAKE, FLOOD, FIRE,
        TSUNAMI, LANDSLIDE,
    }

    public enum DisasterStatus {
        REPORTED, IN_PROGRESS, RESOLVED
    }

    public enum LogLevel {
        INFO, WARNING, ERROR
    }
}
