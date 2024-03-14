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
  private Integer echoResult = 200;
  private boolean onLand = false;
  private String direction;
  private String next_direction;
  private int counter = 0;
  private String turn_direction;
  private int turn_counter = 0;
  
  public String decisionControl() {

        // Step 1: fly, echo south in a loop until we return not "out of range"
        // Step 2: when we echo land, head south and fly, echo in loop until on land
        // Step 3: fly south, scan, echo in direction in a loop until we are out of land
        // Step 4: head east, then head north and repeat process

        JSONObject decision = new JSONObject();

        // if (counter == 800) {
        //     decision.put("action", "stop");
        // }
        if (!foundGround){
            logger.info("Flying east");
            if (echoResult != 200){
                foundGround = true;
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "S";
            }
            else if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "S"));
            }
            else{
                decision.put("action", "fly");
            }   
        }
        else if (turn_direction == "S") {
            if (turn_counter == 0) {
                decision.put("action", "fly");
            }
            else if (turn_counter == 1) {
                decision.put("action", "fly");
            }
            else if (turn_counter == 2) {
                decision.put("action", "fly");
            }
            else if (turn_counter == 3) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
            }
            else if (turn_counter == 4) {
                decision.put("action", "fly");
            }
            else if (turn_counter == 5) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
            }
            else if (turn_counter == 6) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
            }
            else {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                turn_direction = "W";
                direction = "S";
            }
            turn_counter += 1;
        }
        else if (turn_direction == "N") {
            if (turn_counter == 0) {
                decision.put("action", "fly");
            }
            else if (turn_counter == 1) {
                decision.put("action", "fly");
            }
            else if (turn_counter == 2) {
                decision.put("action", "fly");
            }
            else if (turn_counter == 3) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
            }
            else if (turn_counter == 4) {
                decision.put("action", "fly");
            }
            else if (turn_counter == 5) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
            }
            else if (turn_counter == 6) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
            }
            else {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                turn_direction = "W";
                direction = "N";
            }
            turn_counter += 1;
        }
        else if (onLand && direction == "N") {
            logger.info("Flying north");
            if (decisionMade.equals("heading")) {
                    echoResult = 0;
            }
            if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "N"));
            } 
            else if (decisionMade.equals("echo")){
                decision.put("action", "scan");
            }
            else if (echoResult != 200) {
                decision.put("action", "fly");
            }
            else {
                //function to turn south

                turn_counter = 0;
                turn_direction = "S";
                decision.put("action", "fly");

                // decision.put("action", "heading");
                // decision.put("parameters", new JSONObject().put("direction", "E"));
                // direction = "E";
                // next_direction = "S";
            }
        }
        else if (onLand && direction == "S") {
            logger.info("Flying south");
            if (decisionMade.equals("heading")) {
                echoResult = 0;
            }
            if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "S"));
            } 
            else if (decisionMade.equals("echo")){
                decision.put("action", "scan");
            }
            else if (echoResult != 200) {
                decision.put("action", "fly");
            }
            else {
                //function to turn north

                turn_counter = 0;
                turn_direction = "N";
                decision.put("action", "fly");

                // decision.put("action", "heading");
                // decision.put("parameters", new JSONObject().put("direction", "E"));
                // direction = "E";
                // next_direction = "N";
            }
        }
        else {
            logger.info("Finding land");
            if (echoResult == 0){
                onLand = true;
                decision.put("action", "fly");
            }
            else if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "S"));
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
        echoResult = result.echo_result(resultData);
    }
  }

}
