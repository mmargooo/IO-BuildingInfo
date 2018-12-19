package pl.put.poznan.buildingInfo.logic;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.put.poznan.buildingInfo.model.Location;
import pl.put.poznan.buildingInfo.repository.BuildingRepository;
import pl.put.poznan.buildingInfo.repository.LevelRepository;
import pl.put.poznan.buildingInfo.repository.RoomRepository;

@Service
public class BuildingInfo {

    @Autowired
    private BuildingRepository buildingRepository;

    @Autowired
    private LevelRepository levelRepository;

    @Autowired
    private RoomRepository roomRepository;

    public Location getLocation(String id) {
        Location location = buildingRepository.findOne(id);
        if (location == null) {
            location = levelRepository.findOne(id);
        }
        if (location == null) {
            location = roomRepository.findOne(id);
        }

        return location;
    }
}
