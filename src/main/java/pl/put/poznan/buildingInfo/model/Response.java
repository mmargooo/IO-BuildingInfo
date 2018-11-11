package pl.put.poznan.buildingInfo.model;

import java.util.ArrayList;

/**
 * class responsible for structuring server's response.
 * Depends on request type creates proper response
 * status - information if request succeeded or failed
 * value - requested location parameter
 * results - list of Rooms exceeding certain level of heating energy
 */
public class Response {
    private String status;
    private Float value;
    private String message;
    private ArrayList<Room> results;

    public Response(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Float getValue() {
        return value;
    }

    public void setValue(Float value) {
        this.value = value;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Room> getResults() {
        return results;
    }

    public void setResults(ArrayList<Room> results) {
        this.results = results;
    }
}
