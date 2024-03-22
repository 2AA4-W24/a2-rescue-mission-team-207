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
    private Integer echoResult = 200;
    private String creekResult = " ";
    private String siteResult = " ";
    private boolean onLand = false;
    private String current_direction;
    private String direction;
    private String turn_direction = " ";
    private int turn_counter = 0;
    private int borderRange = 200;
    private String currentParameter = " ";
  
    public String stopDecision() {
        JSONObject stop = new JSONObject();
        stop.put("action", "stop");
        String stopString = stop.toString();
        logger.info("** Decision: {}",stopString);
        return stopString;
    }
  
    public String decisionControl(String init_direction) {

        JSONObject decision = new JSONObject();
        Initialize initialize = new Initialize();
        Result result = new Result();

        Direction initialDirection = Direction.stringToDirection(init_direction);
        Direction leftDirection = initialDirection.getLeftDirection();
        Direction rightDirection = initialDirection.getRightDirection();
        Direction oppositeDirection = initialDirection.getOppositeDirection();

        logger.info("Initial direction: {}", initialDirection);

        if (borderRange == 1) {
            decision.put("action", "stop");
        }
        else if (!foundGround){
            logger.info("Flying initial direction");
            if (echoResult != 200) {
                foundGround = true;
                if (currentParameter.equals(rightDirection.directionToString())) {
                    decision.put("action", "heading");
                    decision.put("parameters", new JSONObject().put("direction", rightDirection.directionToString()));
                    current_direction = rightDirection.directionToString();
                    direction = "ES";
                }
                else {
                    decision.put("action", "heading");
                    decision.put("parameters", new JSONObject().put("direction", leftDirection.directionToString()));
                    current_direction = leftDirection.directionToString();
                    direction = "EN";
                }
            }
            else if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", rightDirection.directionToString()));
            }
            else if (decisionMade.equals("echo") && currentParameter.equals(rightDirection.directionToString())) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", leftDirection.directionToString()));
            }
            else{
                decision.put("action", "fly");
                direction = "E";
            }   
        }
        else if (turn_direction.equals(rightDirection.directionToString())) {
            logger.info("Turning south");
            if (turn_counter == 0) {
                decision.put("action", "scan");
            }
            
            else if (turn_counter == 1) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", initialDirection.directionToString()));
            }
            else if (turn_counter == 2) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", initialDirection.directionToString()));
                direction = "NE";
            }
            else if (turn_counter == 3) {
                decision.put("action", "scan");
            }
            else if (turn_counter == 4) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", rightDirection.directionToString()));
                direction = "ES";
            }
            else if (turn_counter == 5) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", oppositeDirection.directionToString()));
                direction = "SW";
            }
            else if (turn_counter == 6) {
                decision.put("action", "fly");
                direction = "W";
            }
            else if (turn_counter == 7) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", leftDirection.directionToString()));
                direction = "WN";
            }
            else if (turn_counter == 8) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", initialDirection.directionToString()));
                direction = "NE";
            }
            else if (turn_counter == 9) {
                decision.put("action", "scan");
            }
            else if (turn_counter == 10) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", rightDirection.directionToString()));
                direction = "ES";
            }
            else if (turn_counter == 11) {
                decision.put("action", "scan");
            }
            else {
                decision.put("action", "fly");
                direction = "S";
                turn_direction = " ";
                current_direction = rightDirection.directionToString();
            }
            turn_counter += 1;
        }
        else if (turn_direction.equals(leftDirection.directionToString())) {
            logger.info("Turning north");
            if (turn_counter == 0) {
                decision.put("action", "scan");
            }
            else if (turn_counter == 1) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", initialDirection.directionToString()));
            }
            else if (turn_counter == 2) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", initialDirection.directionToString()));
                direction = "SE";
            }
            else if (turn_counter == 3) {
                decision.put("action", "scan");
            }

            else if (turn_counter == 4) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", leftDirection.directionToString()));
                direction = "EN";
            }
            
            else if (turn_counter == 5) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", oppositeDirection.directionToString()));
                direction = "NW";
            }
            else if (turn_counter == 6) {
                decision.put("action", "fly");
                direction = "W";
            }
            else if (turn_counter == 7) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", rightDirection.directionToString()));
                direction = "WS";
            }
            else if (turn_counter == 8) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", initialDirection.directionToString()));
                direction = "SE";
            }

            else if (turn_counter == 9) {
                decision.put("action", "scan");
            }
        
            else if (turn_counter == 10){
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", leftDirection.directionToString()));
                direction = "EN";
                
            }
            else if (turn_counter == 11) {
                decision.put("action", "scan");
            }
            else {
                decision.put("action", "fly");
                direction = "N";
                turn_direction = " ";
                current_direction = leftDirection.directionToString();
            }
            turn_counter += 1;
        }
        else if (onLand && current_direction.equals(leftDirection.directionToString())) {
            logger.info("Flying north");
            if (decisionMade.equals("heading")) {
                decision.put("action", "scan");    
                echoResult = 0;
            }
            if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", leftDirection.directionToString()));
            } 
            else if (decisionMade.equals("echo")){
                decision.put("action", "scan");
            }
            else if (echoResult != 200) {
                decision.put("action", "fly");
                direction = "N";
            }
            else {
                turn_counter = 0;
                turn_direction = rightDirection.directionToString();
                decision.put("action", "fly");
                direction = "N";
            }
        }
        else if (onLand && current_direction.equals(rightDirection.directionToString())) {
            logger.info("Flying south");
            if (decisionMade.equals("heading")) {
                decision.put("action", "scan");
                echoResult = 0;
            }
            if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", rightDirection.directionToString()));
            } 
            else if (decisionMade.equals("echo")){
                decision.put("action", "scan");
            }
            else if (echoResult != 200) {
                decision.put("action", "fly");
                direction = "S";
            }
            else {
                turn_counter = 0;
                turn_direction = leftDirection.directionToString();
                decision.put("action", "fly");
                direction = "S";

            }
        }
        else {
            logger.info("Flying towards land");
            if (echoResult == 0){
                onLand = true;
                decision.put("action", "fly");
                direction = current_direction;
            }
            else if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", current_direction));
            }
            else {
                decision.put("action", "fly");
                direction = current_direction;
            }
        }
       
        String decisionString = decision.toString();
        decisionMade = decision.getString("action");

        if (decision.has("parameters")) {
            JSONObject parameters = decision.getJSONObject("parameters");
            if (parameters.has("direction")) {
                currentParameter = parameters.getString("direction");
            }
        }

        logger.info("** Decision: {}",decisionString);
        return decisionString;
    }

    public String getDecisionMade() {
        return decisionMade;
    }

    public String getDirection() {
        return direction;
   }

    public void useEchoResults(JSONObject resultData) {
        Result result = new Result();
        if (decisionMade.equals("echo")){
            echoResult = result.echo_result(resultData);
            borderRange = result.border_range(resultData);
        }       
    }

    public String useScanCreeks(JSONObject resultData) {
        Result result = new Result();
        if (decisionMade.equals("scan")) {
            creekResult = result.scan_creeks(resultData);
            return creekResult;
        }
        return "null";
    }
  
    public String useScanSite(JSONObject resultData) {
        Result result = new Result();
        if (decisionMade.equals("scan")) {
            siteResult = result.scan_site(resultData);
            return siteResult;
        }
        return "null";
    }

}