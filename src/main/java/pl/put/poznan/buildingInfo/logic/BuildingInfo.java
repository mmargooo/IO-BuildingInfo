package pl.put.poznan.buildingInfo.logic;

import java.util.Arrays;

public class BuildingInfo {

    private final String[] buildings;

    public BuildingInfo(String[] buildings){
        this.buildings = buildings;
    }

    public String getBuilding(String buildingName) {
        String[] filteredBuildings = Arrays.stream(buildings).filter(x -> x.toString().equals(buildingName)).toArray(String[]::new);

        if (filteredBuildings.length > 0)
            return filteredBuildings[0];
        else
            return "Building not found";
    }
}
