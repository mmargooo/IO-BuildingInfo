package pl.put.poznan.buildingInfo.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

/**
 * Class responsible for structuring server's response
 */
public class Response {

    /**
     * Informs if request succeeded or failed
     */
    private String status;

    /**
     * Requested location parameter
     */

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float value;

    /**
     * Message sent to the user about the request status
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    /**
     * List of Rooms exceeding certain level of heating energy
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
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
