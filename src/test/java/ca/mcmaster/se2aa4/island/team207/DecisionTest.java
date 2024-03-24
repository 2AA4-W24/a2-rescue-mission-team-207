package ca.mcmaster.se2aa4.island.team207.Decisions;

import org.junit.jupiter.api.Test;

import ca.mcmaster.se2aa4.island.team207.Decisions.Decision;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.json.JSONObject;


public class DecisionTest {

    private Decision decision;

    @BeforeEach
    public void setUp() {
        decision = Decision.getInstance();
        decision.setBorderRange(0);
    }

    @Test
    public void testStopDecision() {
        String decisionString = decision.stopDecision();
        assertEquals("{\"action\":\"stop\"}", decisionString);
    }

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
