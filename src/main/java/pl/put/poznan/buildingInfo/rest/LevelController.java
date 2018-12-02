package pl.put.poznan.buildingInfo.rest;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildingInfo.model.Level;
import pl.put.poznan.buildingInfo.model.Response;
import pl.put.poznan.buildingInfo.repository.LevelRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/level")
public class LevelController {

    private static final Gson gson = new Gson();

    @Autowired
    private LevelRepository levelRepository;

    @GetMapping("/")
    public List<Level> getAllLevels() {
        List<Level> levels = new ArrayList<>();
        levelRepository.findAll().forEach(levels::add);
        return levels;
    }

    @GetMapping("/{id}")
    public Level getLevel(@PathVariable String id) {
        return levelRepository.findOne(id);
    }

    @PostMapping("/")
    public Response createLevel(@RequestBody String payload) {
        Level level = gson.fromJson(payload, Level.class);
        levelRepository.save(level);

        Response res = new Response("Successfully created new level");
        return res;
    }

}
