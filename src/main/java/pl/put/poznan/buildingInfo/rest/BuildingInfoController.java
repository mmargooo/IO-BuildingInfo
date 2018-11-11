package pl.put.poznan.buildingInfo.rest;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildingInfo.logic.BuildingInfo;
import pl.put.poznan.buildingInfo.model.Building;
import pl.put.poznan.buildingInfo.model.Location;

import java.util.HashMap;


@RestController
@RequestMapping("/api")
public class BuildingInfoController {
    private static final Logger logger = LoggerFactory.getLogger(BuildingInfoController.class);
    private static final Gson gson = new Gson();

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
        return gson.toJson("Building " + building.getId() + " successfully created");
    }

    @RequestMapping(value = "/area/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getArea(@PathVariable String id) {
        logger.debug("getArea " + id);
        Location location = BuildingInfo.getLocation(id);
        if (location == null) {
            logger.debug("Such location doesnt exist");
            return "{\"status\": \"failure\", \"value\":\"Location doesnt exist\"}";
        }
        return "{\"status\": \"success\", \"value\": \"" + location.getArea() + "\"}";
    }
}