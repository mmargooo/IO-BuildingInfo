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

    public Float lightningPerArea() { return this.getLighting()/this.getArea(); }
}