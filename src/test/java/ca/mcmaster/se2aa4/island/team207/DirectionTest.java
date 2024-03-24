package ca.mcmaster.se2aa4.island.team207;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class DirectionTest {

    @Test
    public void testGetOppositeDirection() {
        assertEquals(Direction.SOUTH, Direction.NORTH.getOppositeDirection());
        assertEquals(Direction.NORTH, Direction.SOUTH.getOppositeDirection());
        assertEquals(Direction.WEST, Direction.EAST.getOppositeDirection());
        assertEquals(Direction.EAST, Direction.WEST.getOppositeDirection());
    }

    @Test
    public void testGetLeftDirection() {
        assertEquals(Direction.WEST, Direction.NORTH.getLeftDirection());
        assertEquals(Direction.EAST, Direction.SOUTH.getLeftDirection());
        assertEquals(Direction.NORTH, Direction.EAST.getLeftDirection());
        assertEquals(Direction.SOUTH, Direction.WEST.getLeftDirection());
    }

    @Test
    public void testGetRightDirection() {
        assertEquals(Direction.EAST, Direction.NORTH.getRightDirection());
        assertEquals(Direction.WEST, Direction.SOUTH.getRightDirection());
        assertEquals(Direction.SOUTH, Direction.EAST.getRightDirection());
        assertEquals(Direction.NORTH, Direction.WEST.getRightDirection());
    }

    @Test
    public void testStringToDirection() {
        assertEquals(Direction.NORTH, Direction.stringToDirection("N"));
        assertEquals(Direction.SOUTH, Direction.stringToDirection("S"));
        assertEquals(Direction.EAST, Direction.stringToDirection("E"));
        assertEquals(Direction.WEST, Direction.stringToDirection("W"));
    }

    @Test
    public void testDirectionToString() {
        assertEquals("N", Direction.NORTH.directionToString());
        assertEquals("S", Direction.SOUTH.directionToString());
        assertEquals("E", Direction.EAST.directionToString());
        assertEquals("W", Direction.WEST.directionToString());
    }
}