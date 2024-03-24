package ca.mcmaster.se2aa4.island.team207.POIs;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestPathTest {

    @Test
    public void testFindClosestCreek() {
        List<String> creeksList = Arrays.asList("Creek1", "Creek2", "Creek3");
        List<Integer> creekX = Arrays.asList(3, 5, 8);
        List<Integer> creekY = Arrays.asList(4, 6, 9);
        int siteposx = 1;
        int siteposy = 2;
        String expectedClosestCreek = "Creek1";

        ShortestPath shortestPath = new ShortestPath();
        String closestCreek = shortestPath.findClosestCreek(creeksList, creekX, creekY, siteposx, siteposy);

        assertEquals(expectedClosestCreek, closestCreek);
        
    }
}

