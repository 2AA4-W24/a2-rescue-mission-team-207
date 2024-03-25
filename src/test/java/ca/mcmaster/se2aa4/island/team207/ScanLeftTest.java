package ca.mcmaster.se2aa4.island.team207.Decisions;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

public class ScanLeftTest {

    private Decision decision;

    private final String rightDir = "S";
    private final String leftDir = "N";
    private final String oppositeDir = "W";
    private final String initialDir = "E";

    // setup the decision object
    @BeforeEach
    public void setUp() {
        decision = Decision.getInstance();
    }

    // test the make decision method for left scanning
    @Test
    public void testMakeDecision() {
        ScanLeft scanLeft = new ScanLeft();

        decision.setDecisionMade("fly");

        String decisionString = scanLeft.makeDecision(rightDir, leftDir, oppositeDir, initialDir);

        JSONObject decisionJson = new JSONObject(decisionString);
        assertEquals("echo", decisionJson.getString("action"));
        assertEquals(leftDir, decisionJson.getJSONObject("parameters").getString("direction"));

    }
}
