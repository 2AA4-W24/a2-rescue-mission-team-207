package ca.mcmaster.se2aa4.island.team207.Decisions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

public class FindLand implements DecisionState {

    // make decision based on FindLand state
    @Override
    public String makeDecision(String rightDir, String leftDir, String oppositeDir, String initialDir) {

        JSONObject decision = new JSONObject();
        String decisionMade = decisionInstance.getDecisionMade();

        logger.info("Flying initial direction");
        logger.info("Echo result: {}", decisionInstance.getEchoResult());
        String currentParameter = decisionInstance.getCurrentParameter();
        if (decisionInstance.getEchoResult() != 200) {
            decisionInstance.setFoundGround(true);
            if (currentParameter.equals(rightDir)) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", rightDir));
                decisionInstance.setCurrentDirection(rightDir);
                decisionInstance.setDirection(initialDir + rightDir);
            }
            else {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", leftDir));
                decisionInstance.setCurrentDirection(leftDir);
                decisionInstance.setDirection(initialDir + leftDir);
            }
        }
        else if (decisionMade.equals("fly")){
            decision.put("action", "echo");
            decision.put("parameters", new JSONObject().put("direction", leftDir));
        }
        else if (decisionMade.equals("echo") && currentParameter.equals(leftDir)) {
            decision.put("action", "echo");
            decision.put("parameters", new JSONObject().put("direction", rightDir));
        }
        else {
            decision.put("action", "fly");
            decisionInstance.setDirection(initialDir);
        }

        if (decision.has("parameters")) {
            JSONObject parameters = decision.getJSONObject("parameters");
            if (parameters.has("direction")) {
                decisionInstance.setCurrentParameter(parameters.getString("direction"));
            }
        }

        String decisionString = decision.toString();
        decisionInstance.setDecisionMade(decision.getString("action"));
        logger.info("** Decision: {}",decisionString);
        return decisionString;
    }
}
