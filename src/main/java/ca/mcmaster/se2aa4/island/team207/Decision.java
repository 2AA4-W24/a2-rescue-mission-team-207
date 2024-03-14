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
  private boolean onLand = false;
  private String direction = "W";
  private String next_direction = "W";
  private int counter = 0;
  private boolean finished_check = false;
  
  public String decisionControl() {

        // Step 1: fly, echo south in a loop until we return not "out of range"
        // Step 2: when we echo land, head south and fly, echo in loop until on land
        // Step 3: fly south, scan, echo in direction in a loop until we are out of land
        // Step 4: head east, then head north and repeat process

        JSONObject decision = new JSONObject();

        if (counter == 700) {
            decision.put("action", "stop");
        }
        else if (direction == "E") {
            if (finished_check) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                finished_check = false;
            }
            if (newResult == 200) {
                decision.put("action", "stop");
            }
            if (next_direction == "S") {
                logger.info("Turning south");
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "S";
            }
            else if (next_direction == "N") {
                logger.info("Turning north");
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "N";
            }
        }
        else if (!foundGround){
            logger.info("Flying east");
            if (newResult != 200){
                foundGround = true;
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "S";
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
        else if (onLand && direction == "N") {
            logger.info("Flying north");
            if (decisionMade.equals("heading")) {
                    newResult = 0;
            }
            if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "N"));
            } 
            else if (decisionMade.equals("echo")){
                decision.put("action", "scan");
            }
            else if (newResult != 200) {
                decision.put("action", "fly");
            }
            else {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "E";
                finished_check = true;
                next_direction = "S";
            }
        }
        else if (onLand && direction == "S") {
            logger.info("Flying south");
            if (decisionMade.equals("heading")) {
                newResult = 0;
            }
            if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "S"));
            } 
            else if (decisionMade.equals("echo")){
                decision.put("action", "scan");
            }
            else if (newResult != 200) {
                decision.put("action", "fly");
            }
            else {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "E";
                finished_check = true;
                next_direction = "N";
            }
        }
        else {
            logger.info("Finding land");
            if (newResult == 0){
                onLand = true;
                decision.put("action", "fly");
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

        counter += 1;
        
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
