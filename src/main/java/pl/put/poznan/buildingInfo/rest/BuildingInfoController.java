package pl.put.poznan.buildingInfo.rest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildingInfo.logic.BuildingInfo;

import java.util.Arrays;


@RestController
@RequestMapping("/{text}")
public class BuildingInfoController {

    private static final Logger logger = LoggerFactory.getLogger(BuildingInfoController.class);

    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public String get(@PathVariable String text,
                              @RequestParam(value="buildings", defaultValue="exampleBuilding") String[] buildings) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(buildings));

        // look for the building - you should run logic here
        BuildingInfo buildingInfo = new BuildingInfo(buildings);
        return buildingInfo.getBuilding(text);
    }

    @RequestMapping(method = RequestMethod.POST, produces = "application/json")
    public String post(@PathVariable String text,
                      @RequestBody String[] buildings) {

        // log the parameters
        logger.debug(text);
        logger.debug(Arrays.toString(buildings));

        // do the transformation, you should run your logic here, below just a silly example
        BuildingInfo buildingInfo = new BuildingInfo(buildings);
        return buildingInfo.getBuilding(text);
    }



}


