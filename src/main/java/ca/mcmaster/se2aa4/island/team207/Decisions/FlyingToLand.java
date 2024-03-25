package ca.mcmaster.se2aa4.island.team207.Decisions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class FlyingToLand implements DecisionState {
    
    // make decision based on FlyingToLand state
    @Override
    public String makeDecision(String rightDir, String leftDir, String oppositeDir, String initialDir) {

        JSONObject decision = new JSONObject();
        String decisionMade = decisionInstance.getDecisionMade();

        logger.info("Flying towards land");
        String current_direction = decisionInstance.getCurrentDirection();
        if (decisionInstance.getEchoResult() == 0){
            decisionInstance.setOnLand(true);
            decision.put("action", "fly");
            decisionInstance.setDirection(current_direction);
        }
        else if (decisionMade.equals("fly")){
            decision.put("action", "echo");
            decision.put("parameters", new JSONObject().put("direction", current_direction));
        }
        else {
            decision.put("action", "fly");
            decisionInstance.setDirection(current_direction);
        }
        String decisionString = decision.toString();
        decisionInstance.setDecisionMade(decision.getString("action"));
        logger.info("** Decision: {}",decisionString);
        return decisionString;
    }
}