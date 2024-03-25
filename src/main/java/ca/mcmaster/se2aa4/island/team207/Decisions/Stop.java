package ca.mcmaster.se2aa4.island.team207.Decisions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class Stop implements DecisionState {
    
    // make decision based on Stop state (stop if border range is reached)
    @Override
    public String makeDecision(String rightDir, String leftDir, String oppositeDir, String initialDir) {

        JSONObject decision = new JSONObject();
        String decisionMade = decisionInstance.getDecisionMade();

        logger.info("Stopping Exploration");
        decision.put("action", "stop");
        String decisionString = decision.toString();
        decisionInstance.setDecisionMade(decision.getString("action"));
        logger.info("** Decision: {}",decisionString);
        return decisionString;
    }
}