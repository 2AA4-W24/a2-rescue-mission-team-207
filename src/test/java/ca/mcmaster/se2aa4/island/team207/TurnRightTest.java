package ca.mcmaster.se2aa4.island.team207.Decisions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

public class TurnRightTest {

    private Decision decision;

    private final String rightDir = "E";
    private final String leftDir = "W";
    private final String oppositeDir = "S";
    private final String initialDir = "N";

    // setup the decision object
    @BeforeEach
    public void setUp() {
        decision = Decision.getInstance();
    }

    // test the make decision method for right turning
    @Test
    public void testMakeDecision() {
        TurnRight turnRight = new TurnRight();

        for (int turn_counter = 0; turn_counter <= 11; turn_counter++) {
            decision.setTurnCounter(turn_counter);

            String decisionString = turnRight.makeDecision(rightDir, leftDir, oppositeDir, initialDir);

            JSONObject decisionJson = new JSONObject(decisionString);
            String expectedAction = getExpectedAction(turn_counter);
            assertEquals(expectedAction, decisionJson.getString("action"));
        }
    }

    // get the expected action based on the turn counter
    private String getExpectedAction(int turn_counter) {
        switch (turn_counter) {
            case 0: case 3: case 9: case 11:
                return "scan";
            case 1: 
                return "echo";
            case 2: case 8: case 4: case 10: case 5: case 7:
                return "heading";
            case 6:
                return "fly";
            default:
                return "fly";
        }
    }
}

