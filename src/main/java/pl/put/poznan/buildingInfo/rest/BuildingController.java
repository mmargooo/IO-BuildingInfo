package pl.put.poznan.buildingInfo.rest;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildingInfo.model.Building;
import pl.put.poznan.buildingInfo.model.Response;
import pl.put.poznan.buildingInfo.repository.BuildingRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/building")
public class BuildingController {

    private static final Gson gson = new Gson();

    @Autowired
    private BuildingRepository buildingRepository;

    @GetMapping("/")
    public List<Building> getAllBuildings() {
        List<Building> buildings = new ArrayList<>();
        buildingRepository.findAll().forEach(buildings::add);
        return buildings;
    }

    @GetMapping("/{id}")
    public Building getBuilding(@PathVariable String id) {
        return buildingRepository.findOne(id);
    }

    @PostMapping("/")
    public Response createBuilding(@RequestBody String payload) {
        Building building = gson.fromJson(payload, Building.class);
        buildingRepository.save(building);

        Response res = new Response("Successfully created new building");
        return res;
    }

}