package pl.put.poznan.buildingInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import pl.put.poznan.buildingInfo.model.Building;
import pl.put.poznan.buildingInfo.model.Janitor;
import pl.put.poznan.buildingInfo.model.Level;
import pl.put.poznan.buildingInfo.model.Room;

import java.util.ArrayList;
import java.util.List;

public class JanitorTest {
    private Building building;
    private Level level;
    private List<Level> levels;
    private Room room;
    private Room exceedingRoom;
    private List<Room> rooms;

    @Before
    public void setUp(){
        building = Mockito.mock(Building.class);
        level = Mockito.mock(Level.class);
        room = Mockito.mock(Room.class);
        exceedingRoom = Mockito.mock(Room.class);
        levels = new ArrayList<>();
        rooms = new ArrayList<>();
        levels.add(level);
        rooms.add(room);
    }

    @After
    public void tearDown() {
        building = null;
        levels = null;
        level = null;
        rooms = null;
        room = null;
        exceedingRoom = null;
    }

    @Test
    public void testVerifyBuildingID_CorrectID () {
        Mockito.when(building.getId()).thenReturn("1-0-0");
        assertTrue(Janitor.verifyBuildingID(building));
    }

    @Test
    public void testVerifyBuildingID_IncorrectID () {
        Mockito.when(building.getId()).thenReturn("1-1-0");
        assertFalse(Janitor.verifyBuildingID(building));
    }

    @Test
    public void testVerifyLevelsID_CorrectID () {
        Mockito.when(building.getLevels()).thenReturn(levels);
        Mockito.when(building.getId()).thenReturn("4-0-0");
        Mockito.when(level.getId()).thenReturn("4-2-0");
        assertTrue(Janitor.verifyLevelsID(building));
    }

    @Test
    public void testVerifyLevelsID_IncorrectID () {
        Mockito.when(building.getLevels()).thenReturn(levels);
        Mockito.when(building.getId()).thenReturn("4-0-0");
        Mockito.when(level.getId()).thenReturn("4-2-1");
        assertFalse(Janitor.verifyLevelsID(building));
    }

    @Test
    public void testVerifyRoomsID_CorrectID () {
        Mockito.when(building.getLevels()).thenReturn(levels);
        Mockito.when(level.getRooms()).thenReturn(rooms);
        Mockito.when(building.getId()).thenReturn("32-0-0");
        Mockito.when(level.getId()).thenReturn("32-13-0");
        Mockito.when(room.getId()).thenReturn("32-13-8");
        assertTrue(Janitor.verifyRoomsID(building));
    }

    @Test
    public void testVerifyRoomsID_IncorrectID () {
        Mockito.when(building.getLevels()).thenReturn(levels);
        Mockito.when(level.getRooms()).thenReturn(rooms);
        Mockito.when(building.getId()).thenReturn("32-0-0");
        Mockito.when(level.getId()).thenReturn("32-13-0");
        Mockito.when(room.getId()).thenReturn("32-1-8");
        assertFalse(Janitor.verifyRoomsID(building));
    }

    @Test
    public void testCompareLocationID_BuildingLevel_Correct() {
        Mockito.when(building.getId()).thenReturn("2-0-0");
        Mockito.when(level.getId()).thenReturn("2-7-0");
        assertTrue(Janitor.compareLocationID(building, level, 0));
    }

    @Test
    public void testCompareLocationID_BuildingLevel_Incorrect () {
        Mockito.when(building.getId()).thenReturn("2-0-0");
        Mockito.when(level.getId()).thenReturn("1-7-0");
        assertFalse(Janitor.compareLocationID(building, level, 0));
    }

    @Test
    public void testCompareLocationID_LevelRoom_Correct () {
        Mockito.when(level.getId()).thenReturn("3-5-0");
        Mockito.when(room.getId()).thenReturn("3-5-1");
        assertTrue(Janitor.compareLocationID(level, room, 1));
    }

    @Test
    public void testCompareLocationID_LevelRoom_Incorrect () {
        Mockito.when(level.getId()).thenReturn("3-5-0");
        Mockito.when(room.getId()).thenReturn("3-4-1");
        assertFalse(Janitor.compareLocationID(level, room, 1));
    }

    @Test
    public void testCompareLocationID_Length_Incorrect (){
        Mockito.when(level.getId()).thenReturn("3-5-0");
        Mockito.when(room.getId()).thenReturn("3-5-1-2");
        assertFalse(Janitor.compareLocationID(level, room, 0));
    }

    @Test
    public void testGetExceedingRooms_Level_Correct () {
        rooms.add(exceedingRoom);
        Mockito.when(level.getRooms()).thenReturn(rooms);
        Mockito.when(room.getHeating()).thenReturn((float)40.0);
        Mockito.when(room.getCube()).thenReturn((float)40.0);
        Mockito.when(exceedingRoom.getHeating()).thenReturn((float)60.0);
        Mockito.when(exceedingRoom.getCube()).thenReturn((float)20.0);
        assertEquals(exceedingRoom, Janitor.getExceedingRooms(level, (float)2.0).get(0));
    }
}
