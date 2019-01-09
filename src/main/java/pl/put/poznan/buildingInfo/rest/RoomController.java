package pl.put.poznan.buildingInfo.rest;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.put.poznan.buildingInfo.model.Response;
import pl.put.poznan.buildingInfo.model.Room;
import pl.put.poznan.buildingInfo.repository.RoomRepository;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/api/room")
public class RoomController {

    private static final Gson gson = new Gson();

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/")
    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        roomRepository.findAll().forEach(rooms::add);
        return rooms;
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable String id) {
        return roomRepository.findOne(id);
    }

    @PatchMapping("/{id}")
    public Response updateRoom(@PathVariable String id, @RequestBody String payload) {
        Room previousRoom = roomRepository.findOne(id);

        if (previousRoom== null)
            return new Response("Room with such id doesn't exists");

        Room updatedRoom = gson.fromJson(payload, Room.class);
        roomRepository.save(updatedRoom);

        return new Response("Successfully updated room");
    }
}
