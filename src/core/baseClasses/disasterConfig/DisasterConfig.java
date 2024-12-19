package core.baseClasses.disasterConfig;

import java.util.HashMap;
import java.util.Map;

import core.enums.Enum.DisasterType;
import core.enums.Enum.ResourceType;

public class DisasterConfig {

    public static Map<ResourceType, Integer> getEarthquakeResources(int severity) {
        Map<ResourceType, Integer> resources = new HashMap<>();
        resources.put(ResourceType.MEDICAL_PERSONNEL, severity * 2);
        resources.put(ResourceType.RESCUE_PERSONNEL, severity * 3);
        resources.put(ResourceType.FIRE_TRUCK, severity);
        resources.put(ResourceType.WATER_BOTTLES, severity * 10);
        return resources;
    }

    public static Map<ResourceType, Integer> getFloodResources(int severity) {
        Map<ResourceType, Integer> resources = new HashMap<>();
        resources.put(ResourceType.FOOD_BOXES, severity * 5);
        resources.put(ResourceType.WATER_BOTTLES, severity * 20);
        resources.put(ResourceType.MEDICAL_PERSONNEL, severity * 2);
        resources.put(ResourceType.BOATS, severity);
        return resources;
    }

    public static Map<ResourceType, Integer> getFireResources(int severity) {
        Map<ResourceType, Integer> resources = new HashMap<>();
        resources.put(ResourceType.FIRE_TRUCK, severity * 2);
        resources.put(ResourceType.RESCUE_PERSONNEL, severity * 2);
        resources.put(ResourceType.MEDICINE_BOXES, severity * 5);
        return resources;
    }

    public static Map<ResourceType, Integer> getTsunamiResources(int severity) {
        Map<ResourceType, Integer> resources = new HashMap<>();
        resources.put(ResourceType.RESCUE_PERSONNEL, severity * 5);
        resources.put(ResourceType.FOOD_BOXES, severity * 10);
        resources.put(ResourceType.WATER_BOTTLES, severity * 30);
        resources.put(ResourceType.BLOOD_BAGS, severity * 3);
        return resources;
    }

    public static Map<ResourceType, Integer> getLandslideResources(int severity) {
        Map<ResourceType, Integer> resources = new HashMap<>();
        resources.put(ResourceType.RESCUE_PERSONNEL, severity * 4);
        resources.put(ResourceType.FIRE_TRUCK, severity);
        resources.put(ResourceType.FOOD_BOXES, severity * 3);
        resources.put(ResourceType.MEDICINE_BOXES, severity * 2);
        return resources;
    }

    public static Map<ResourceType, Integer> getRequiredResources(DisasterType disasterType, int severity) {
        switch (disasterType) {
            case EARTHQUAKE:
                return getEarthquakeResources(severity);
            case FLOOD:
                return getFloodResources(severity);
            case FIRE:
                return getFireResources(severity);
            case TSUNAMI:
                return getTsunamiResources(severity);
            case LANDSLIDE:
                return getLandslideResources(severity);
            default:
                throw new IllegalArgumentException("Invalid disaster type: " + disasterType);
        }
    }
}