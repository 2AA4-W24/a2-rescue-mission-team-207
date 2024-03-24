package ca.mcmaster.se2aa4.island.team207;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class InitializeTest {

    @Test
    public void testSetInitial() {
        String input = "{\"budget\": 9999999, \"heading\": \"W\"}";

        Initialize initializer = new Initialize();
        initializer.setInitial(input);

        assertEquals(9999999, initializer.getBatteryLevel());
        assertEquals("W", initializer.getDirection());
    }
}
