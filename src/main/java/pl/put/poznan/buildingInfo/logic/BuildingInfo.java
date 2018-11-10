package pl.put.poznan.buildingInfo.logic;

import java.util.HashMap;
import java.util.Map;

import pl.put.poznan.buildingInfo.model.Location;

public class BuildingInfo {

    static private HashMap<String, Location> locations = new HashMap<>();

    static public HashMap<String, Location> getLocations() {
        return BuildingInfo.locations;
    }

    static public Location getLocation(String key) {
        return BuildingInfo.locations.get(key);
    }

    static public void setLocations(HashMap<String, Location> locations) {
        BuildingInfo.locations = locations;
    }
}
