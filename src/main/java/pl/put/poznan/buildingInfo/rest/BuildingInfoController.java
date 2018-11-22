package pl.put.poznan.buildingInfo.rest;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildingInfo.logic.BuildingInfo;
import pl.put.poznan.buildingInfo.model.Building;
import pl.put.poznan.buildingInfo.model.Location;
import pl.put.poznan.buildingInfo.model.Response;
import pl.put.poznan.buildingInfo.repository.BuildingRepository;

/**
 * Class responsible for creating new buildings and and retrieving information about them
 */
@RestController
@RequestMapping("/api")
public class BuildingInfoController {
    private static final Logger logger = LoggerFactory.getLogger(BuildingInfoController.class);
    private static final Gson gson = new Gson();

    @Autowired
    private BuildingInfo buildingInfo;

    @Autowired
    private BuildingRepository buildingRepository;

    /**
     * Endpoint that creats Building instance from JSON placed in request's body. Takes JSON and produces JSON.
     *
     * @param payload - contains JSON to create proper Building instance.
     *
     * @return confirmation of creating a building
     */
    @RequestMapping(value = "/new", method = RequestMethod.POST, produces = "application/json")
    public String createBuilding(@RequestBody String payload) {
        Building building = gson.fromJson(payload, Building.class);
        buildingRepository.save(building);
        Response res = new Response("Successfully created new building");
        return gson.toJson(res);
    }

    /**
     * Function that returns area of asked location.
     *
     * @param id - id of the location.
     *
     * @return area of requested location or information that such location doesn't exist
     */
    @RequestMapping(value = "/area/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getArea(@PathVariable String id) {
        logger.debug("getArea " + id);
        Location location = buildingInfo.getLocation(id);
        if (location == null) {
            logger.debug("Such location doesnt exist");
            Response response = new Response("failure");
            response.setMessage("Location doesn't exist");
            return gson.toJson(response, Response.class);
        }
        logger.info("The area was successfully given");
        Response response = new Response("success");
        response.setValue(location.getArea());
        return gson.toJson(response, Response.class);
    }

    /**
     * Function that returns cube of asked location.
     *
     * @param id - id of the location.
     *
     * @return cubage of requested location or information that such location doesn't exist
     */
    @RequestMapping(value = "/cube/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getCube(@PathVariable String id) {
        logger.debug("getCube " + id);
        Location location = buildingInfo.getLocation(id);
        if (location == null) {
            logger.debug("Such location doesnt exist");
            Response response = new Response("failure");
            response.setMessage("Location doesn't exist");
            return gson.toJson(response, Response.class);
        }
        logger.info("The cube was successfully given");
        Response response = new Response("success");
        response.setValue(location.getCube());
        return gson.toJson(response, Response.class);
    }

    /**
     * Function that returns lighting per area of asked location.
     *
     * @param id - id of the location.
     *
     * @return lighting per area of requested location or information that such location doesn't exist
     */
    @RequestMapping(value = "/lightingPerArea/{id}", method = RequestMethod.GET, produces = "application/json")
    public String lightingPerArea(@PathVariable String id) {
        logger.debug("lightingPerArea " + id);
        Location location = buildingInfo.getLocation(id);
        if (location == null) {
            logger.debug("Such location doesnt exist");
            Response response = new Response("failure");
            response.setMessage("Location doesn't exist");
            return gson.toJson(response, Response.class);
        }
        logger.info("The value of lighting per area was successfully given");
        Response response = new Response("success");
        response.setValue(location.lightingPerArea());
        return gson.toJson(response, Response.class);
    }

    /**
     * Function that returns heating per cube of asked location.
     *
     * @param id - id of the location.
     *
     * @return energy usage per m^3 of requested location or information that such location doesn't exist
     */
    @RequestMapping(value = "/heatingPerCube/{id}", method = RequestMethod.GET, produces = "application/json")
    public String heatingPerCube(@PathVariable String id) {
        logger.debug("heatingPerCube " + id);
        Location location = buildingInfo.getLocation(id);
        if (location == null) {
            logger.debug("Such location doesnt exist");
            Response response = new Response("failure");
            response.setMessage("Location doesn't exist");
            return gson.toJson(response, Response.class);
        }
        logger.info("The value of heating per cube was successfully given");
        Response response = new Response("success");
        response.setValue(location.heatingPerCube());
        return gson.toJson(response, Response.class);
    }

    /**
     * Function that returns list of rooms exceeding the heating limit in given building.
     *
     * @param id - id of the location.
     *
     * @return list of rooms that exceed energy/m^3 limit;
     * if location doesn't exist or it isn't an instance of building returns adequate error message
     */
    @RequestMapping(value = "/exceeding/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getExceedingRooms(@PathVariable String id) {
        logger.debug("getExceedingRooms " + id);
        Location location = buildingInfo.getLocation(id);
        if (location == null) {
            logger.debug("Such location doesnt exist");
            Response response = new Response("failure");
            response.setMessage("Location doesn't exist");
            return gson.toJson(response, Response.class);
        }
        else if (!(location instanceof Building)) {
            logger.debug("Location is not an instance of Building");
            Response response = new Response("failure");
            response.setMessage("Location is not an instance of Building");
            return gson.toJson(response, Response.class);
        }
        logger.info("The rooms that exceed the heating limit were successfully given");
        Response response = new Response("succes");
        response.setResults(((Building) location).getExceedingRooms());
        return gson.toJson(response, Response.class);
    }
}