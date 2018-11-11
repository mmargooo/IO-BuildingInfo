package pl.put.poznan.buildingInfo.model;

import java.util.ArrayList;

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
