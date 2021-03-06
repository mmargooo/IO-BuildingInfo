package pl.put.poznan.buildingInfo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

/**
 * Represents class describing Building, that consist of Levels
 */
@Entity
@Table(name = "buildings")
public class Building extends Location {

    /**
     * List of levels inside the building
     */

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn
    private List<Level> levels;

    /**
     * Maximal usage of energy/m^3 per room (optional)
     */
    private float heatingLimit;

    /**
     * Constructor creating Building instance with unique location id and list of levels
     *
     * @param id     - unique location id
     * @param levels - list of levels that building consists of
     */
    public Building(String id, List<Level> levels) {
        super(id);
        this.levels = levels;
    }

    /**
     * Constructor creating Building instance with unique location id, optional name parameter and list of levels
     *
     * @param id     - unique location id
     * @param name   - optional name parameter describing location
     * @param levels - list of levels that building consists of
     */
    public Building(String id, String name, List<Level> levels) {
        super(id, name);
        this.levels = levels;
    }

    /**
     * Constructor creating Building instance with unique location id, optional name parameter and list of levels
     *
     * @param id     - unique location id
     * @param name   - optional name parameter describing location
     * @param levels - list of levels that building consists of
     * @param heatingLimit - optional parameter describing heating limit per room
     */
    public Building(String id, String name, List<Level> levels, float heatingLimit) {
        super(id, name);
        this.levels = levels;
        this.heatingLimit = heatingLimit;
    }

    public Building() {

    }

    public List<Level> getLevels() {
        return levels;
    }

    public void setLevels(List<Level> levels) {
        this.levels = levels;
    }

    /**
     * Function that return area of whole building.
     *
     * @return area of all levels
     */
    @JsonIgnore
    public Float getArea() {
        return (float) (levels.stream().mapToDouble(Level::getArea)).sum();
    }

    /**
     * Function that return cubage of whole building.
     *
     * @return cubage of all levels
     */
    @JsonIgnore
    public Float getCube() {
        return (float) (levels.stream().mapToDouble(Location::getCube)).sum();
    }

    /**
     * Function that return heating of whole building.
     *
     * @return heating of all levels
     */
    @JsonIgnore
    public Float getHeating()   {
        return (float) (levels.stream().mapToDouble(Location::getHeating)).sum();
    }

    /**
     * Function that return lighting of whole building.
     *
     * @return lighting of all levels
     */

    @JsonIgnore
    public Float getLighting()  {
        return (float) (levels.stream().mapToDouble(Location::getLighting)).sum();
    }

    public float getHeatingLimit() {
        return heatingLimit;
    }

    public void setHeatingLimit(float heatingLimit) {
        this.heatingLimit = heatingLimit;
    }
}
