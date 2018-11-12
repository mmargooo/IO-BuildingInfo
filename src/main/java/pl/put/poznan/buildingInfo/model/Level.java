package pl.put.poznan.buildingInfo.model;

import java.util.List;

/**
 * Represents class describing Level, that consist of Rooms
 */
public class Level extends Location {
    private List<Room> rooms;

    /**
     * Constructor creating Level instance with unique location id and list of rooms
     *
     * @param id    - unique location id
     * @param rooms - list of levels that building consists of
     */
    public Level(String id, List<Room> rooms) {
        super(id);
        this.rooms = rooms;
    }

    /**
     * Constructor creating Level instance with unique location id, optional location description and list of rooms
     *
     * @param id    - unique location id
     * @param name  - optional name parameter describing location
     * @param rooms - list of rooms that level consists of
     */
    public Level(String id, String name, List<Room> rooms) {
        super(id, name);
        this.rooms = rooms;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    /**
     * Function that return area of whole level.
     *
     * @return area of all rooms on this level.
     */
    public Float getArea() {
        return (float) (rooms.stream().mapToDouble(Room::getArea)).sum();
    }

    /**
     * Function that return cubage of whole level.
     *
     * @return cubage of all rooms on this level
     */
    public Float getCube() {
        return (float) (rooms.stream().mapToDouble(Room::getCube)).sum();
    }

    public Float getHeating() {
        // calculate heating demand of all levels

        // return temporary value in order to compile code properly
        return (float) 0.0;
    }

    public Float getLighting()  {
        return (float) (rooms.stream().mapToDouble(Room::getLighting)).sum();
    }
}
