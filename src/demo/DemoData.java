package demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import core.baseClasses.location.Location;
import core.baseClasses.network.Network;
import core.baseClasses.resource.Resource;
import core.enums.Enum.LocationType;
import core.enums.Enum.ResourceType;

public class DemoData {
    private static Network network = Network.getInstance();

    public static void createLocations() {

        Object[][] locations = new Object[][] {

                { LocationType.CITY, "New York", 40.7128, -74.0060 },
                { LocationType.CITY, "Los Angeles", 34.0522, -118.2437 },
                { LocationType.CITY, "Chicago", 41.8781, -87.6298 },
                // { LocationType.CITY, "Houston", 29.7604, -95.3698 },
                // { LocationType.CITY, "Phoenix", 33.4484, -112.0740 },
                // { LocationType.CITY, "Philadelphia", 39.9526, -75.1652 },
                // { LocationType.CITY, "San Jose", 37.3382, -121.8863 },

                { LocationType.HOSPITAL, "General Hospital", 40.7306, -73.9352 },
                { LocationType.HOSPITAL, "Memorial Hospital", 41.1234, -80.4532 },
                // { LocationType.HOSPITAL, "City Hospital", 40.6444, -73.7790 },

                { LocationType.FIRE_STATION, "Fire Station 12", 39.9801, -75.1553 },
                // { LocationType.FIRE_STATION, "Fire Station 6", 38.9173, -77.0146 },

                { LocationType.WATER_TANK, "Water Tank Alpha", 40.7125, -74.0055 },
                // { LocationType.WATER_TANK, "Water Tank Bravo", 41.2123, -75.1234 },
                // { LocationType.WATER_TANK, "Water Tank Echo", 38.7435, -77.0412 },

                { LocationType.POWER_STATION, "Power Station X", 40.7000, -74.0100 },
                // { LocationType.POWER_STATION, "Power Station Y", 41.1233, -75.9876 },
                // { LocationType.POWER_STATION, "Power Station Z", 39.7891, -77.1213 },
                // { LocationType.POWER_STATION, "Power Station Alpha", 38.6500, -76.1956 },
                // { LocationType.POWER_STATION, "Power Station Beta", 37.8800, -75.5612 },

                { LocationType.POLICE_STATION, "Police Station 7", 40.7600, -73.9800 },
                // { LocationType.POLICE_STATION, "Police Station 14", 41.2000, -75.3500 },
                // { LocationType.POLICE_STATION, "Police Station 18", 38.9223, -77.0222 },

                { LocationType.SCHOOL, "Greenwood High School", 40.7300, -73.9900 },
                { LocationType.SCHOOL, "Riverdale Academy", 41.0900, -74.1200 },

        };

        for (Object[] location : locations) {
            LocationType locationType = (LocationType) location[0];
            String name = (String) location[1];
            double latitude = (double) location[2];
            double longitude = (double) location[3];

            network.addLocation(locationType, name, latitude, longitude);
        }
    }

    public static void createConnections() {
        Collection<Location> locations = network.getLocations();

        List<Location> locationList = new ArrayList<>(locations);
        Set<Location> connectedLocations = new HashSet<>();

        connectedLocations.add(locationList.get(0));

        for (Location location1 : locationList) {

            for (Location location2 : locationList) {
                if (location1.equals(location2) || connectedLocations.contains(location2)) {
                    continue;
                }

                double distance = generateRandomDistance();

                try {

                    network.addConnection(location1.getId(), location2.getId(), distance);

                    // System.out.println("Connection created between " + location1.getName() + "
                    // and "
                    // + location2.getName() + " with distance: " + distance + " km.");

                    connectedLocations.add(location2);

                } catch (Exception e) {

                    // System.err.println("Error creating connection between " + location1.getName()
                    // + " and "
                    // + location2.getName() + ": " + e.getMessage());
                    // e.printStackTrace();
                }
            }
        }
    }

    private static Location getRandomLocation(List<Location> locationList) {
        Random random = new Random();
        return locationList.get(random.nextInt(locationList.size()));
    }

    private static double generateRandomDistance() {
        Random random = new Random();
        return 1 + (20 * random.nextDouble());
    }

    public static void addResources() {

        Collection<Location> locations = network.getLocations();

        List<Location> locationList = new ArrayList<>(locations);

        for (Location location : locationList) {

            LocationType type = location.getType();
            switch (type) {
                case CITY:
                    location.addResource(new Resource(ResourceType.FIRE_TRUCK, 5, location));
                    location.addResource(new Resource(ResourceType.MEDICAL_PERSONNEL, 100, location));
                    location.addResource(new Resource(ResourceType.RESCUE_PERSONNEL, 50, location));
                    location.addResource(new Resource(ResourceType.FOOD_BOXES, 500, location));
                    location.addResource(new Resource(ResourceType.WATER_BOTTLES, 1000, location));
                    break;

                case HOSPITAL:
                    location.addResource(new Resource(ResourceType.MEDICAL_PERSONNEL, 200, location));
                    location.addResource(new Resource(ResourceType.AMBULANCES, 10, location));
                    location.addResource(new Resource(ResourceType.BLOOD_BAGS, 500, location));
                    location.addResource(new Resource(ResourceType.MEDICINE_BOXES, 1000, location));
                    break;

                case FIRE_STATION:
                    location.addResource(new Resource(ResourceType.FIRE_TRUCK, 10, location));
                    location.addResource(new Resource(ResourceType.RESCUE_PERSONNEL, 30, location));
                    location.addResource(new Resource(ResourceType.WATER_BOTTLES, 300, location));
                    break;

                case WATER_TANK:
                    location.addResource(new Resource(ResourceType.FUEL_CONTAINERS, 200, location));
                    location.addResource(new Resource(ResourceType.WATER_BOTTLES, 3000, location));
                    break;

                case POWER_STATION:
                    location.addResource(new Resource(ResourceType.FUEL_CONTAINERS, 1000, location));
                    location.addResource(new Resource(ResourceType.FUEL_CONTAINERS, 50, location));
                    break;

                case POLICE_STATION:
                    location.addResource(new Resource(ResourceType.RESCUE_PERSONNEL, 50, location));
                    location.addResource(new Resource(ResourceType.AMBULANCES, 5, location));
                    location.addResource(new Resource(ResourceType.FOOD_BOXES, 200, location));
                    break;

                case SCHOOL:
                    location.addResource(new Resource(ResourceType.FOOD_BOXES, 300, location));
                    location.addResource(new Resource(ResourceType.MEDICAL_PERSONNEL, 20, location));
                    location.addResource(new Resource(ResourceType.WATER_BOTTLES, 1000, location));
                    break;

            }
        }

    }
}
