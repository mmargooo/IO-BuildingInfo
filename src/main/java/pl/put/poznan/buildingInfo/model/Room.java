package pl.put.poznan.buildingInfo.model;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Represents class describing Room, that contains features: area, cubage, heating demand and lighting power of certain Room
 */
@Entity
@Table(name = "rooms")
public class Room extends Location {

    /**
     * Room's area
     */
    private Float area;

    /**
     * Room's cubage
     */
    private Float cube;

    /**
     * Room's heating
     */
    private Float heating;

    /**
     * Room's lightning
     */
    private Float lighting;

    /**
     * Constructor creating Room instance described by unique location id and other parameters: area, cubage, heating demand, lighting power
     *
     * @param id       - unique location id
     * @param area     - Room's area
     * @param cube     - Room's cubage
     * @param heating  - Room's hearing demand
     * @param lighting - Room's lighting power
     */
    public Room(String id, Float area, Float cube, Float heating, Float lighting) {
        super(id);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.lighting = lighting;
    }

    /**
     * Constructor creating Room instance described by unique location id, optional location description and other parameters: area, cubage, heating demand, lighting power
     *
     * @param id       - unique location id
     * @param name     - optional name parameter describing location
     * @param area     - Room's area
     * @param cube     - Room's cubage
     * @param heating  - Room's hearing demand
     * @param lighting - Room's lighting power
     */
    public Room(String id, String name, Float area, Float cube, Float heating, Float lighting) {
        super(id, name);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.lighting = lighting;
    }

    public Room() {
    }

    @Override
    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    @Override
    public Float getCube() {
        return cube;
    }

    public void setCube(Float cube) {
        this.cube = cube;
    }

    @Override
    public Float getHeating() {
        return heating;
    }

    public void setHeating(Float heating) {
        this.heating = heating;
    }

    @Override
    public Float getLighting() {
        return lighting;
    }

    public void setLighting(Float lighting) {
        this.lighting = lighting;
    }
}
