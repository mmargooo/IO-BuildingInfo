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

    public Float getArea() {
        // calculate area of all levels

        // return temporary value in order to compile code properly
        return (float) (levels.stream().mapToDouble(Level::getArea)).sum();
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
