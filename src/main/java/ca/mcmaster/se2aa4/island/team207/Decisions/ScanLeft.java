package ca.mcmaster.se2aa4.island.team207.Decisions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class ScanLeft implements DecisionState {
    
    //make decision based on ScanLeft state
    @Override
    public String makeDecision(String rightDir, String leftDir, String oppositeDir, String initialDir) {

        JSONObject decision = new JSONObject();
        String decisionMade = decisionInstance.getDecisionMade();

        logger.info("Scanning for creeks (left)");
        if (decisionMade.equals("fly")){
            decision.put("action", "echo");
            decision.put("parameters", new JSONObject().put("direction", leftDir));
        } 
        else if (decisionMade.equals("echo")){
            decision.put("action", "scan");
        }
        else if (decisionInstance.getEchoResult() != 200) {
            decision.put("action", "fly");
            decisionInstance.setDirection(leftDir);
        }
        else {
            decisionInstance.setTurnCounter(0);
            decisionInstance.setTurnDirection(rightDir);
            decision.put("action", "fly");
            decisionInstance.setDirection(leftDir);
        }
        String decisionString = decision.toString();
        decisionInstance.setDecisionMade(decision.getString("action"));
        logger.info("** Decision: {}",decisionString);
        return decisionString;
    }
}