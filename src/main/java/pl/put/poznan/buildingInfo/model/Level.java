package pl.put.poznan.buildingInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

/**
 * Represents class describing Level, that consist of Rooms
 */
@Entity
@Table(name = "levels")
public class Level extends Location {

    /**
     * List of rooms on the level
     */

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
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

    public Level() {

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
    @JsonIgnore
    public Float getArea() {
        return (float) (rooms.stream().mapToDouble(Room::getArea)).sum();
    }

    /**
     * Function that return cubage of whole level.
     *
     * @return cubage of all rooms on this level
     */
    @JsonIgnore
    public Float getCube() {
        return (float) (rooms.stream().mapToDouble(Room::getCube)).sum();
    }

    /**
     * Function that return heating of whole level.
     *
     * @return heating of all rooms on this level
     */
    @JsonIgnore
    public Float getHeating()  {
        return (float) (rooms.stream().mapToDouble(Room::getHeating)).sum();
    }

    /**
     * Function that return lighting of whole level.
     *
     * @return lighting of all rooms on this level
     */
    @JsonIgnore
    public Float getLighting()  {
        return (float) (rooms.stream().mapToDouble(Room::getLighting)).sum();
    }

    /**
     * Function that returns rooms exceeding the heating limit.
     *
     * @param limit - maximal usage of energy/m^3 per room
     * @return ArrayList of rooms
     */
    public ArrayList<Room> getExceedingRooms(float limit) {
        return (ArrayList) (rooms.stream().filter(room -> room.getHeating()/room.getCube() > limit).collect(Collectors.toList()));
    }
}
