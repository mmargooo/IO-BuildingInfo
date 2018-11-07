package pl.put.poznan.buildingInfo.model;

import java.util.List;

public class Level extends Location {
    private List<Room> rooms;

    public Level(String id, List<Room> rooms) {
        super(id);
        this.rooms = rooms;
    }

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

    public Float getArea() {
        // calculate area of all levels

        // return temporary value in order to compile code properly
        return (float) 0.0;
    }

    public Float getCube() {
        // calculate cubage of all levels

        // return temporary value in order to compile code properly
        return (float) 0.0;
    }

    public Float getHeating() {
        // calculate heating demand of all levels

        // return temporary value in order to compile code properly
        return (float) 0.0;
    }

    public Float getLighting() {
        // calculate lighting power of all levels

        // return temporary value in order to compile code properly
        return (float) 0.0;
    }
}
