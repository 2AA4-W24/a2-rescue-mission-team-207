package ca.mcmaster.se2aa4.island.team207;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

public class Decision {
  private final Logger logger = LogManager.getLogger();
  

  private boolean foundGround = false;
  private String decisionMade = " "; 
  private Explorer explorer;
  private Integer newResult = 200;
  
  public String decisionControl() {

    // Step 1: fly, echo south in a loop until we return not "out of range"
        // Step 2: when we echo land, head south and fly, echo in loop until on land
        // Step 3: fly south, scan, echo in direction in a loop until we are out of land
        // Step 4: head east, then head north and repeat process

        JSONObject decision = new JSONObject();

        // if (counter < 5) {
        //     decision.put("action", "fly");
        // }
        // else {
        //     decision.put("action", "stop");
        // }
        if (!foundGround){
            if (newResult != 200){
                foundGround = true;
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                
            }
            else if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "S"));
            } 
            else if (decisionMade.equals("echo")){
                decision.put("action", "scan");
            }
            else{
                decision.put("action", "fly");
            }   
        }
        else {
            if (newResult == 0){
                decision.put("action", "stop");
            }
            else if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "S"));
            } 
            else if (decisionMade.equals("echo")){
                decision.put("action", "scan");
            }
            else {
                decision.put("action", "fly");
            } 
        }
        
        // if (counter > 30) {
        //     if (counter == 31) {
        //         decision.put("action", "heading");
        //         decision.put("parameters", new JSONObject().put("direction", "S"));                
        //     }
        //     else if (counter > 82) {
        //         if (counter > 132) {
        //             decision.put("action", "stop");
        //         }
        //         else if (counter % 3 == 0) {
        //             decision.put("action", "fly");
        //         }
        //         else if (counter % 3 == 1) {
        //             decision.put("action", "scan");
        //         }
        //         else {
        //             decision.put("action", "echo");
        //             decision.put("parameters", new JSONObject().put("direction", "S"));
        //         }
        //     }
        //     else if (counter % 2 == 0) {
        //         decision.put("action", "fly");
        //     }
        //     else {
        //         decision.put("action", "echo");
        //         decision.put("parameters", new JSONObject().put("direction", "S"));
        //     }
        // }
        // else if (counter % 2 == 0) {
        //     decision.put("action", "fly");
        // }
        // else {
        //     decision.put("action", "echo");
        //     decision.put("parameters", new JSONObject().put("direction", "S"));
        // }        

        // logger.info("** Counter: " + counter);
        // counter += 1;
        
        String decisionString = decision.toString();
        decisionMade = decision.getString("action");

        logger.info("** Decision: {}",decisionString);
        return decisionString;

  }

  public void useResults(JSONObject resultData) {
    Result result = new Result();
    if (decisionMade.equals("echo")){
        newResult = result.echo_result(resultData);
    }
  }

}
