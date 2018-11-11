package pl.put.poznan.buildingInfo.model;

import java.util.List;

/**
 * Represents class describing Building, that consist of Levels
 */
public class Building extends Location {
    private List<Level> levels;

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
    public Float getArea() {
        return (float) (levels.stream().mapToDouble(Level::getArea)).sum();
    }

    /**
     * Function that return cubage of whole building.
     *
     * @return cubage of all levels
     */
    public Float getCube() {
        return (float) (levels.stream().mapToDouble(Location::getCube)).sum();
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
