package pl.put.poznan.buildingInfo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Janitor {

    /**
     * Function that combines all other verifying methods and returns whether the building structure is in order
     *
     * @param building - building whose structure is verified
     * @return true if building structure is correct, false otherwise
     */
    public static boolean verifyBuildingStructure (Building building) {
        return verifyBuildingID(building) && verifyLevelsID(building) && verifyRoomsID(building) && verifyUnique(building);
    }

    /**
     * Function that returns whether the building's id is correct (matches the pattern b-0-0)
     *
     * @param building - building whose id is verified
     * @return true if building's id is correct, false otherwise
     */
    public static boolean verifyBuildingID (Building building) {
        String split[] = building.getId().split("-");
        return split.length == 3 && split[1].equals("0") && split[2].equals("0");
    }

    /**
     * Function that returns whether the levels' ids are correct (matches the pattern b-l-0)
     *
     * @param building - building whose levels' ids are verified
     * @return true if all levels' ids are correct, false otherwise
     */
    public static boolean verifyLevelsID (Building building) {
        return building.getLevels().stream()
            .allMatch(level -> compareLocationID(building, level, 0) && level.getId().split("-")[2].equals("0"));
    }

    /**
     * Function that returns whether the rooms' ids are correct (matches the pattern b-l-r)
     *
     * @param building - building whose rooms' ids are verified
     * @return true if all rooms' ids are correct, false otherwise
     */
    public static boolean verifyRoomsID (Building building) {
        return building.getLevels().stream()
            .allMatch(level -> level.getRooms().stream()
                .allMatch(room -> compareLocationID(level, room, 0) && compareLocationID(level, room, 1)));
    }

    /**
     * Function that returns whether the ids in building are all unique
     *
     * @param building - building whose ids are tested for uniqueness
     * @return true if all ids are unique, false otherwise
     */
    public static boolean verifyUnique (Building building) {
        boolean levels = building.getLevels().size() == building.getLevels().stream().filter(distinctByKey(level -> level.getId())).count();
        boolean rooms = building.getLevels().stream()
            .allMatch(level -> level.getRooms().size() == level.getRooms().stream().filter(distinctByKey(room -> room.getId())).count());
        return levels && rooms;
    }

    /**
     * Function that returns whether given location is part of another -
     * whether the level is in the right building or room on the right level and in the right building
     *
     * @param l1 - the containing location (building for level, building and level for room)
     * @param l2 - the contained location (level for building, room for level and building)
     * @param i - what kind of location we are dealing with: 0 for building, 1 for level
     * @return true if l2 is part of l1, false otherwise
     */
    public static boolean compareLocationID(Location l1, Location l2, int i) {
        String split1[] = l1.getId().split("-");
        String split2[] = l2.getId().split("-");
        return split1.length == 3 && split2.length == 3 && split1[i].equals(split2[i]);
    }

    /**
     * Function that returns whether an object was already seen
     *
     * @param keyExtractor - field by which the object is defined
     * @param <T> - object that is being checked
     * @return true if object wasn't seen before, false otherwise
     */
    public static  <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

    /**
     * Function that returns lighting per area of the location.
     *
     * @param location - location on which the calculations are computed
     * @return lighting per area of the location
     */
    public static Float lightingPerArea(Location location) { return location.getLighting()/location.getArea(); }

    /**
     * Function that returns heating per cube of the location.
     *
     * @param location - location on which the calculations are computed
     * @return heating per cube of the location
     */
    public static Float heatingPerCube(Location location) { return location.getHeating()/location.getCube(); }

    /**
     * Function that returns list of rooms exceeding the heating limit.
     *
     * @param building - building on which the calculations are computed
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
