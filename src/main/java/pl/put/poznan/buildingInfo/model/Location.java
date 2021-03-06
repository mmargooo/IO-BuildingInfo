package pl.put.poznan.buildingInfo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

/**
 * Represents abstract class, gathering common Building, Level and Room properties.
 * Describes methods extended in other classes
 */

@MappedSuperclass
public abstract class Location {

    /**
     * Location's unique id used to retrieve information
     */
    @Id
    private String id;

    /**
     * Name describing location (optional)
     */

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String name;

    public Location() {
    }

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
     * @param id - unique location id
     * @param name - optional name parameter describing location
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
}