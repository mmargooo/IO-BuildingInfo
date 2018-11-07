package pl.put.poznan.buildingInfo.model;

import java.util.List;

public class Building extends Location {
    private List<Level> levels;

    public Building(String id, List<Level> levels) {
        super(id);
        this.levels = levels;
    }

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
