package ca.mcmaster.se2aa4.island.team207.Decisions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import ca.mcmaster.se2aa4.island.team207.Decisions.Decision;

// interface for decision states
public interface DecisionState {
    Decision decisionInstance = Decision.getInstance();
    public final Logger logger = LogManager.getLogger();

    String makeDecision(String rightDir, String leftDir, String oppositeDir, String initialDir);

}
