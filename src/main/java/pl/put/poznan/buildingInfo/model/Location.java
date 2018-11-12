package pl.put.poznan.buildingInfo.model;

/**
 * Represents abstract class, gathering common Building, Level and Room properties.
 * Describes methods extended in other classes
 */
public abstract class Location {
    private String id;
    private String name;

    /**
     * Creates Location with certain id
     *
     * @param id - unique location id
     */
    public Location(String id) {
        this.id = id;
    }

    /**
     * Creates Location with certain id and optional name field
     *
     * @param id
     * @param name
     */
    public Location(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Float getArea();

    public abstract Float getCube();

    public abstract Float getHeating();

    public abstract Float getLighting();

    /**
     * Function that return lighting per area of the location.
     *
     * @return lighting per area of the location
     */
    public Float lightingPerArea() { return this.getLighting()/this.getArea(); }

    /**
     * Function that return heating per cube of the location.
     *
     * @return heating per cube of the location
     */
    public Float heatingPerCube() { return this.getHeating()/this.getCube(); }
}