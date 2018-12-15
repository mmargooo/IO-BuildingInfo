package pl.put.poznan.buildingInfo.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class LevelTest {
    private Level level;
    private List<Room> roomList;

    @Before
    public void setUp() {
        level = new Level();
        roomList = new ArrayList<>();
    }

    @After
    public void destroy() {
        level = null;
        roomList = null;
    }

    @Test
    public void getArea() {
        Room room1 = Mockito.mock(Room.class);
        Room room2 = Mockito.mock(Room.class);
        Mockito.when(room1.getArea()).thenReturn((float) 45.0);
        Mockito.when(room2.getArea()).thenReturn((float) 63.0);
        roomList.addAll(Arrays.asList(room1, room2));
        level.setRooms(roomList);
        assertEquals((float) 108.0, level.getArea(), (float) 0.01);
        Mockito.verify(room1, Mockito.times(1)).getArea();
        Mockito.verify(room2, Mockito.times(1)).getArea();
    }

    @Test
    public void getCube() {
        Room room1 = Mockito.mock(Room.class);
        Room room2 = Mockito.mock(Room.class);
        Mockito.when(room1.getCube()).thenReturn((float) 15.0);
        Mockito.when(room2.getCube()).thenReturn((float) 33.0);
        roomList.addAll(Arrays.asList(room1, room2));
        level.setRooms(roomList);
        assertEquals((float) 48.0, level.getCube(), (float) 0.01);
        Mockito.verify(room1, Mockito.times(1)).getCube();
        Mockito.verify(room2, Mockito.times(1)).getCube();
    }

    @Test
    public void getHeating() {
        Room room1 = Mockito.mock(Room.class);
        Room room2 = Mockito.mock(Room.class);
        Mockito.when(room1.getHeating()).thenReturn((float) 5.0);
        Mockito.when(room2.getHeating()).thenReturn((float) 9.0);
        roomList.addAll(Arrays.asList(room1, room2));
        level.setRooms(roomList);
        assertEquals((float) 14.0, level.getHeating(), (float) 0.01);
        Mockito.verify(room1, Mockito.times(1)).getHeating();
        Mockito.verify(room2, Mockito.times(1)).getHeating();
    }

    @Test
    public void getLighting() {
        Room room1 = Mockito.mock(Room.class);
        Room room2 = Mockito.mock(Room.class);
        Mockito.when(room1.getLighting()).thenReturn((float) 7.0);
        Mockito.when(room2.getLighting()).thenReturn((float) 6.38);
        roomList.addAll(Arrays.asList(room1, room2));
        level.setRooms(roomList);
        assertEquals((float) 13.38, level.getLighting(), (float) 0.01);
        Mockito.verify(room1, Mockito.times(1)).getLighting();
        Mockito.verify(room2, Mockito.times(1)).getLighting();
    }

    @Test
    public void getRooms() {
        Room room1 = Mockito.mock(Room.class);
        Room room2 = Mockito.mock(Room.class);
        roomList.addAll(Arrays.asList(room1, room2));
        level.setRooms(roomList);
        List<Room> rooms = level.getRooms();
        assertSame(rooms, roomList);
    }
}