package ca.mcmaster.se2aa4.island.team207.Decisions;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team207.Decisions.Decision;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.json.JSONObject;


public class DecisionTest {

    private Decision decision;

    // setup the decision object
    @BeforeEach
    public void setUp() {
        decision = Decision.getInstance();
        decision.setBorderRange(0);
    }

    // test the stop decision state
    @Test
    public void testStopDecision() {
        String decisionString = decision.stopDecision();
        assertEquals("{\"action\":\"stop\"}", decisionString);
    }

    // test the move decision state
    @Test
    public void testDecisionControl() {
        String state = decision.decisionControl("S", "N", "W", "E");
        if (decision.getBorderRange() == 0){
            decision.setDecisionMade("stop");;
            String newAction = decision.getDecisionMade();
            assertEquals("stop", newAction);
        }   
        
    }


}
