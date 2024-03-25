package ca.mcmaster.se2aa4.island.team207.Decisions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class TurnRight implements DecisionState {
    
    //make decision based on TurnRight state
    @Override
    public String makeDecision(String rightDir, String leftDir, String oppositeDir, String initialDir) {

        JSONObject decision = new JSONObject();
        String decisionMade = decisionInstance.getDecisionMade();

        logger.info("Turning around");
        int turn_counter = decisionInstance.getTurnCounter();
        if (turn_counter == 0) {
            decision.put("action", "scan");
        }
        else if (turn_counter == 1) {
            decision.put("action", "echo");
            decision.put("parameters", new JSONObject().put("direction", initialDir));
        }
        else if (turn_counter == 2) {
            decision.put("action", "heading");
            decision.put("parameters", new JSONObject().put("direction", initialDir));
            decisionInstance.setDirection(leftDir + initialDir);
        }
        else if (turn_counter == 3) {
            decision.put("action", "scan");
        }
        else if (turn_counter == 4) {
            decision.put("action", "heading");
            decision.put("parameters", new JSONObject().put("direction", rightDir));
            decisionInstance.setDirection(initialDir + rightDir);
        }
        else if (turn_counter == 5) {
            decision.put("action", "heading");
            decision.put("parameters", new JSONObject().put("direction", oppositeDir));
            decisionInstance.setDirection(rightDir + oppositeDir);
        }
        else if (turn_counter == 6) {
            decision.put("action", "fly");
            decisionInstance.setDirection(oppositeDir);
        }
        else if (turn_counter == 7) {
            decision.put("action", "heading");
            decision.put("parameters", new JSONObject().put("direction", leftDir));
            decisionInstance.setDirection(oppositeDir + leftDir);
        }
        else if (turn_counter == 8) {
            decision.put("action", "heading");
            decision.put("parameters", new JSONObject().put("direction", initialDir));
            decisionInstance.setDirection(leftDir + initialDir);
        }
        else if (turn_counter == 9) {
            decision.put("action", "scan");
        }
        else if (turn_counter == 10) {
            decision.put("action", "heading");
            decision.put("parameters", new JSONObject().put("direction", rightDir));
            decisionInstance.setDirection(initialDir + rightDir);
        }
        else if (turn_counter == 11) {
            decision.put("action", "scan");
        }
        else {
            decision.put("action", "fly");
            decisionInstance.setDirection(rightDir);
            decisionInstance.setTurnDirection(" ");
            decisionInstance.setCurrentDirection(rightDir);
        }
        decisionInstance.setTurnCounter(turn_counter + 1);
        String decisionString = decision.toString();
        decisionInstance.setDecisionMade(decision.getString("action"));
        logger.info("** Decision: {}",decisionString);
        return decisionString;
    }
}