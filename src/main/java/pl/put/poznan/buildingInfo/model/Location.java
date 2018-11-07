package pl.put.poznan.buildingInfo.model;

public abstract class Location {
    private String id;
    private String name;

    // second constructor due to fact, that name is optional
    public Location(String id) {
        this.id = id;
    }

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
}
