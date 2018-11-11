package pl.put.poznan.buildingInfo.rest;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildingInfo.logic.BuildingInfo;
import pl.put.poznan.buildingInfo.model.Building;
import pl.put.poznan.buildingInfo.model.Location;
import pl.put.poznan.buildingInfo.model.Response;

import java.util.HashMap;


@RestController
@RequestMapping("/api")
public class BuildingInfoController {
    private static final Logger logger = LoggerFactory.getLogger(BuildingInfoController.class);
    private static final Gson gson = new Gson();

    /**
     * Endpoint that creats Building instance from JSON placed in request's body. Takes JSON and produces JSON.
     *
     * @param payload - contains JSON to create proper Building instance.
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
    public String createBuilding(@RequestBody String payload) {
        HashMap<String, Location> locations = new HashMap<>();
        Building building = gson.fromJson(payload, Building.class);
        locations.put(building.getId(), building);
        building.getLevels().forEach(level -> {
            locations.put(level.getId(), level);
            level.getRooms().forEach(room -> locations.put(room.getId(), room));
        });
        BuildingInfo.setLocations(locations);
        Response response = new Response("success");
        response.setMessage("Building id: " + building.getId() + " successfully created");
        return gson.toJson(response, Response.class);
    }

    /**
     * Function that returns area of asked location.
     *
     * @param id - id of the location.
     */
    @RequestMapping(value = "/area/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getArea(@PathVariable String id) {
        logger.debug("getArea " + id);
        Location location = BuildingInfo.getLocation(id);
        if (location == null) {
            logger.debug("Such location doesnt exist");
            Response response = new Response("failure");
            response.setMessage("Location doesn't exist");
            return gson.toJson(response, Response.class);
        }
        Response response = new Response("success");
        response.setValue(location.getArea());
        return gson.toJson(response, Response.class);
    }

    /**
     * Function that returns cube of asked location.
     *
     * @param id - id of the location.
     */
    @RequestMapping(value = "/cube/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getCube(@PathVariable String id) {
        logger.debug("getCube " + id);
        Location location = BuildingInfo.getLocation(id);
        if (location == null) {
            logger.debug("Such location doesnt exist");
            Response response = new Response("failure");
            response.setMessage("Location doesn't exist");
            return gson.toJson(response, Response.class);
        }
        Response response = new Response("success");
        response.setValue(location.getCube());
        return gson.toJson(response, Response.class);
    }
}