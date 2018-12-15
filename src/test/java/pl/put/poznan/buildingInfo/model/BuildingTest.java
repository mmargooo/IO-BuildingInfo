package pl.put.poznan.buildingInfo.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BuildingTest {
    private Building building;
    private List<Level> levelList;

    @Before
    public void setUp() {
        this.building = new Building();
        this.levelList = new ArrayList<>();
    }

    @After
    public void destroy() {
        this.building = null;
        levelList = null;
    }

    @Test
    public void getAreaTest() {
        Level level1 = Mockito.mock(Level.class);
        Level level2 = Mockito.mock(Level.class);

        Mockito.when(level1.getArea()).thenReturn((float) 45.0);
        Mockito.when(level2.getArea()).thenReturn((float) 63.0);

        levelList.addAll(Arrays.asList(level1, level2));
        building.setLevels(levelList);

        assertEquals((float) 108.0, building.getArea(), (float) 0.01);
        Mockito.verify(level1, Mockito.times(1)).getArea();
        Mockito.verify(level2, Mockito.times(1)).getArea();
    }

    @Test
    public void getCubeTest() {
        Level level1 = Mockito.mock(Level.class);
        Level level2 = Mockito.mock(Level.class);

        Mockito.when(level1.getCube()).thenReturn((float) 13.2);
        Mockito.when(level2.getCube()).thenReturn((float) 53.4);

        levelList.addAll(Arrays.asList(level1, level2));
        building.setLevels(levelList);

        assertEquals((float) 66.6, building.getCube(), (float) 0.01);
        Mockito.verify(level1, Mockito.times(1)).getCube();
        Mockito.verify(level2, Mockito.times(1)).getCube();
    }

    @Test
    public void getHeatingTest() {
        Level level1 = Mockito.mock(Level.class);
        Level level2 = Mockito.mock(Level.class);

        Mockito.when(level1.getHeating()).thenReturn((float) 21.0);
        Mockito.when(level2.getHeating()).thenReturn((float) 14.0);

        levelList.addAll(Arrays.asList(level1, level2));
        building.setLevels(levelList);

        assertEquals((float) 35.0, building.getHeating(), (float) 0.01);
        Mockito.verify(level1, Mockito.times(1)).getHeating();
        Mockito.verify(level2, Mockito.times(1)).getHeating();
    }

    @Test
    public void getLightingTest() {
        Level level1 = Mockito.mock(Level.class);
        Level level2 = Mockito.mock(Level.class);

        Mockito.when(level1.getLighting()).thenReturn((float) 55.0);
        Mockito.when(level2.getLighting()).thenReturn((float) 10.0);

        levelList.addAll(Arrays.asList(level1, level2));
        building.setLevels(levelList);

        assertEquals((float) 65.0, building.getLighting(), (float) 0.01);
        Mockito.verify(level1, Mockito.times(1)).getLighting();
        Mockito.verify(level2, Mockito.times(1)).getLighting();
    }

    @Test
    public void getLevelsTest() {
        Level level1 = Mockito.mock(Level.class);
        Level level2 = Mockito.mock(Level.class);

        levelList.addAll(Arrays.asList(level1, level2));
        building.setLevels(levelList);

        List<Level> gotLevels = building.getLevels();
        assertSame(gotLevels, levelList);
    }
}