package ca.mcmaster.se2aa4.island.team207;

import ca.mcmaster.se2aa4.island.team207.Decisions.Decision;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import ca.mcmaster.se2aa4.island.team207.Position;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {
    private final Logger logger = LogManager.getLogger();
    private Decision decision = new Decision();
    private String decisionMade = decision.getDecisionMade();
    private String direction = decision.getDirection();

    // test the move north method
    @Test
    public void testMoveNorth() {
        Position position = new Position();
        position.updateDecision(decision);
        position.change_position();
        if (decisionMade.equals("fly")){
            if (direction.equals("N")){
                assertEquals("(0)", position.get_position());
                logger.info("going north");
            }
        }
    }
    
}
