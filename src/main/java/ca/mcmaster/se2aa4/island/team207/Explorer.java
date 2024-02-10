package ca.mcmaster.se2aa4.island.team207;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    
    private Integer counter = 0;

    @Override
    public void initialize(String s) {
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        logger.info("Battery level is {}", batteryLevel);
    }

    @Override
    public String takeDecision() {
        JSONObject decision = new JSONObject();
        
        if (counter <= 25) {
            decision.put("action", "fly");
        }
        else if (counter <= 26) {
            decision.put("action", "heading");
            decision.put("parameters", new JSONObject().put("direction", "S"));
        }
        else if (counter <= 150) {
            if (counter % 3 == 0) {
                decision.put("action", "fly");
            }
            else if (counter % 3 == 1) {
                decision.put("action", "scan");
            }
            else {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "S"));
            }            
        }
        //else if (counter <= 27) {
            //decision.put("action", "echo");
            //decision.put("parameters", new JSONObject().put("direction", "S"));
        //}
        else {
            decision.put("action", "stop");
        }
        
        // else if (counter % 4 == 1) {
        //     decision.put("action", "echo");
        //     decision.put("parameters", new JSONObject().put("direction", "E"));
        // }
        // else if (counter % 4 == 2) {
        //     decision.put("action", "echo");
        //     decision.put("parameters", new JSONObject().put("direction", "S"));
        // }
        // else {
        //     decision.put("action", "echo");
        //     decision.put("parameters", new JSONObject().put("direction", "N"));
        // }
        counter += 1;

        logger.info("** Counter: " + counter);

        logger.info("** Decision: {}",decision.toString());
        return decision.toString();
    }

    @Override
    public void acknowledgeResults(String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
    }

    @Override
    public String deliverFinalReport() {
        return "no creek found";
    }

}
