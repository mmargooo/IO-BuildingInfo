package pl.put.poznan.buildingInfo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;
import pl.put.poznan.buildingInfo.model.Building;
import pl.put.poznan.buildingInfo.model.Janitor;

public class JanitorTest {
    private Building building;

    @Before
    public void setUp(){
        building = Mockito.mock(Building.class);
    }

    @After
    public void tearDown() {
        building = null;
    }

    @Test
    public void testVerifyBuildingID () {
        Mockito.when(building.getId()).thenReturn("1-0-0");
        assertTrue(Janitor.verifyBuildingID(building));
    }

}
