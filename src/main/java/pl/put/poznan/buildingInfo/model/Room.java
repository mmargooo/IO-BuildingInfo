package pl.put.poznan.buildingInfo.model;

public class Room extends Location {
    private Float area;
    private Float cube;
    private Float heating;

    public Room(String id, Float area, Float cube, Float heating, Float lighting) {
        super(id);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.lighting = lighting;
    }

    public Room(String id, String name, Float area, Float cube, Float heating, Float lighting) {
        super(id, name);
        this.area = area;
        this.cube = cube;
        this.heating = heating;
        this.lighting = lighting;
    }

    private Float lighting;

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
