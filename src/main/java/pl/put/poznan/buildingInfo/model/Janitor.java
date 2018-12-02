package pl.put.poznan.buildingInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Janitor {
    public static boolean verifyBuildingStructure (Building building) {
        return verifyBuildingID(building) && verifyLevelsID(building) && verifyRoomsID(building) && verifyUnique(building);
    }

    public static boolean verifyBuildingID (Building building) {
        String split[] = building.getId().split("-");
        return split.length == 3 && split[1].equals("0") && split[2].equals("0");
    }

    public static boolean verifyLevelsID (Building building) {
        return building.getLevels().stream()
            .allMatch(level -> compareLocationID(building, level, 0) && level.getId().split("-")[2].equals("0"));
    }

    public static boolean verifyRoomsID (Building building) {
        return building.getLevels().stream()
            .allMatch(level -> level.getRooms().stream()
                .allMatch(room -> compareLocationID(level, room, 0) && compareLocationID(level, room, 1)));
    }

    public static boolean verifyUnique (Building building) {
        boolean levels = building.getLevels().size() == building.getLevels().stream().filter(distinctByKey(level -> level.getId())).count();
        boolean rooms = building.getLevels().stream()
            .allMatch(level -> level.getRooms().size() == level.getRooms().stream().filter(distinctByKey(room -> room.getId())).count());
        return levels && rooms;
    }

    public static boolean compareLocationID(Location l1, Location l2, int i) {
        String split1[] = l1.getId().split("-");
        String split2[] = l2.getId().split("-");
        return split1.length == 3 && split2.length == 3 && split1[i].equals(split2[i]);
    }

    public static  <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor)
    {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Function that return lighting per area of the location.
     *
     * @return lighting per area of the location
     */
    public static Float lightingPerArea(Location location) { return location.getLighting()/location.getArea(); }

    /**
     * Function that return heating per cube of the location.
     *
     * @return heating per cube of the location
     */
    public static Float heatingPerCube(Location location) { return location.getHeating()/location.getCube(); }

    /**
     * Function that returns list of rooms exceeding the heating limit.
     *
     * @return ArrayList of rooms
     */
    public static ArrayList<Room> getExceedingRooms(Building building) {
        ArrayList<ArrayList<Room>> ar = (ArrayList) building.getLevels().stream()
            .map(level -> Janitor.getExceedingRooms(level, building.getHeatingLimit())).collect(Collectors.toList());
        return (ArrayList) ar.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    /**
     * Function that returns list of rooms exceeding the heating limit.
     *
     * @param level - floor on which the calculations are computed
     * @param limit - maximal usage of energy/m^3 per room
     * @return ArrayList of rooms
     */
    public static ArrayList<Room> getExceedingRooms(Level level, float limit) {
        return (ArrayList) (level.getRooms().stream()
            .filter(room -> room.getHeating()/room.getCube() > limit).collect(Collectors.toList()));
    }
}
