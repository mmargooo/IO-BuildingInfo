package pl.put.poznan.buildingInfo.model;

import java.util.ArrayList;

public class Janitor {

    private Building building;

    public Janitor (Building building) {
        this.building = building;
    }

    public boolean verifyBuildingStructure () {
        return verifyBuildingID() && verifyLevelsID() && verifyRoomsID();
    }

    public boolean verifyBuildingID () {
        String split[] = building.getId().split("-");
        return split.length == 3 && split[1].equals("0") && split[2].equals("0");
    }

    public boolean verifyLevelsID () {
        return building.getLevels().stream()
            .allMatch(level -> compareLocationID(building, level, 0) && level.getId().split("-")[2].equals("0"));
    }

    public boolean verifyRoomsID () {
        return building.getLevels().stream()
            .allMatch(level -> level.getRooms().stream()
                .allMatch(room -> compareLocationID(level, room, 0) && compareLocationID(level, room, 1)));
    }

    public boolean verifyUnique (ArrayList<Location> locationList) {
        ArrayList<String> IDList = new ArrayList<>();
        locationList.stream().forEach(location -> IDList.add(location.getId()));
        return IDList.size() == IDList.stream().distinct().count();
    }

    public boolean compareLocationID(Location l1, Location l2, int i) {
        String split1[] = l1.getId().split("-");
        String split2[] = l2.getId().split("-");
        return split1.length == 3 && split2.length == 3 && split1[i].equals(split2[i]);
    }
}