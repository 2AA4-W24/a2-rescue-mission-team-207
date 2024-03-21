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
    private String creekResult = " ";
    private String siteResult = " ";
    private boolean onLand = false;
    private String current_direction;
    private String direction;
    private int counter = 0;
    private String turn_direction;
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
  
    public String decisionControlEast() {

        JSONObject decision = new JSONObject();
        Initialize initialize = new Initialize();
        Result result = new Result();

        if (borderRange == 1) {
            decision.put("action", "stop");
        }
        else if (!foundGround){
            logger.info("Flying east");
            if (echoResult != 200) {
                foundGround = true;
                if (currentParameter.equals("S")) {
                    decision.put("action", "heading");
                    decision.put("parameters", new JSONObject().put("direction", "S"));
                    current_direction = "S";
                    direction = "ES";
                }
                else {
                    decision.put("action", "heading");
                    decision.put("parameters", new JSONObject().put("direction", "N"));
                    current_direction = "N";
                    direction = "EN";
                }
            }
            else if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "S"));
            }
            else if (decisionMade.equals("echo") && currentParameter.equals("S")) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "N"));
            }
            else{
                decision.put("action", "fly");
                direction = "E";
            }   
        }
        else if (turn_direction == "S") {
            logger.info("Turning south");
            if (turn_counter == 0) {
                decision.put("action", "scan");
            }
            
            else if (turn_counter == 1) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "E"));
            }
            else if (turn_counter == 2) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "NE";
            }
            else if (turn_counter == 3) {
                decision.put("action", "scan");
            }
            else if (turn_counter == 4) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "ES";
            }
            else if (turn_counter == 5) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "SW";
            }
            else if (turn_counter == 6) {
                decision.put("action", "fly");
                direction = "W";
            }
            else if (turn_counter == 7) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "WN";
            }
            else if (turn_counter == 8) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "NE";
            }
            else if (turn_counter == 9) {
                decision.put("action", "scan");
            }
            else if (turn_counter == 10) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "ES";
            }
            else if (turn_counter == 11) {
                decision.put("action", "scan");
            }
            else {
                decision.put("action", "fly");
                direction = "S";
                turn_direction = "W";
                current_direction = "S";
            }
            turn_counter += 1;
        }
        else if (turn_direction == "N") {
            logger.info("Turning north");
            if (turn_counter == 0) {
                decision.put("action", "scan");
            }
            else if (turn_counter == 1) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "E"));
            }
            else if (turn_counter == 2) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "SE";
            }
            else if (turn_counter == 3) {
                decision.put("action", "scan");
            }

            else if (turn_counter == 4) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "EN";
            }
            
            else if (turn_counter == 5) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "NW";
            }
            else if (turn_counter == 6) {
                decision.put("action", "fly");
                direction = "W";
            }
            else if (turn_counter == 7) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "WS";
            }
            else if (turn_counter == 8) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "SE";
            }

            else if (turn_counter == 9) {
                decision.put("action", "scan");
            }
        
            else if (turn_counter == 10){
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "EN";
                
            }
            else if (turn_counter == 11) {
                decision.put("action", "scan");
            }
            else {
                decision.put("action", "fly");
                direction = "N";
                turn_direction = "W";
                current_direction = "N";
            }
            turn_counter += 1;
        }
        else if (onLand && current_direction == "N") {
            logger.info("Flying north");
            if (decisionMade.equals("heading")) {
                decision.put("action", "scan");    
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
                direction = "N";
            }
            else {
                turn_counter = 0;
                turn_direction = "S";
                decision.put("action", "fly");
                direction = "N";
            }
        }
        else if (onLand && current_direction == "S") {
            logger.info("Flying south");
            if (decisionMade.equals("heading")) {
                decision.put("action", "scan");
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
                direction = "S";
            }
            else {
                turn_counter = 0;
                turn_direction = "N";
                decision.put("action", "fly");
                direction = "S";

            }
        }
        else {
            logger.info("Flying towards land");
            if (current_direction == "N") {
                if (echoResult == 0){
                    onLand = true;
                    decision.put("action", "fly");
                    direction = "N";
                }
                else if (decisionMade.equals("fly")){
                    decision.put("action", "echo");
                    decision.put("parameters", new JSONObject().put("direction", "N"));
                }
                else {
                    decision.put("action", "fly");
                    direction = "N";
                }
            }
            else {
                if (echoResult == 0){
                    onLand = true;
                    decision.put("action", "fly");
                    direction = "S";
                }
                else if (decisionMade.equals("fly")){
                    decision.put("action", "echo");
                    decision.put("parameters", new JSONObject().put("direction", "S"));
                }
                else {
                    decision.put("action", "fly");
                    direction = "S";
                }
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

    public String decisionControlWest() {

        JSONObject decision = new JSONObject();
        Initialize initialize = new Initialize();
        Result result = new Result();

        if (borderRange == 1) {
            decision.put("action", "stop");
        }
        else if (!foundGround){
            logger.info("Flying west");
            if (echoResult != 200) {
                foundGround = true;
                if (currentParameter.equals("S")) {
                    decision.put("action", "heading");
                    decision.put("parameters", new JSONObject().put("direction", "S"));
                    current_direction = "S";
                    direction = "WS";
                }
                else {
                    decision.put("action", "heading");
                    decision.put("parameters", new JSONObject().put("direction", "N"));
                    current_direction = "N";
                    direction = "WN";
                }
            }
            else if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "S"));
            }
            else if (decisionMade.equals("echo") && currentParameter.equals("S")) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "N"));
            }
            else{
                decision.put("action", "fly");
                direction = "W";
            }   
        }
        else if (turn_direction == "S") {
            logger.info("Turning south");
            if (turn_counter == 0) {
                decision.put("action", "scan");
            }
            else if (turn_counter == 1) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "W"));
            }
            else if (turn_counter == 2) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "NE";
            }
            else if (turn_counter == 3) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "ES";
            }
            else if (turn_counter == 4) {
                decision.put("action", "fly");
                direction = "S";
            }
            else if (turn_counter == 5) {
                decision.put("action", "fly");
                direction = "S";
            }
            else if (turn_counter == 6) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "SW";
            }
            else if (turn_counter == 7) {
                decision.put("action", "fly");
                direction = "W";
            }
            else if (turn_counter == 8) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "WN";
            }
            else if (turn_counter == 9) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "NE";
            }
            else if (turn_counter == 10) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "EN";
            }
            else if (turn_counter == 11) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "NW";
            }
            else {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "WS";
                turn_direction = "W";
                current_direction = "S";
            }
            turn_counter += 1;
        }
        else if (turn_direction == "N") {
            logger.info("Turning north");
            if (turn_counter == 0) {
                decision.put("action", "scan");
            }
            else if (turn_counter == 1) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "W"));
            }
            else if (turn_counter == 2) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "SE";
            }
            else if (turn_counter == 3) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "EN";
            }
            else if (turn_counter == 4) {
                decision.put("action", "fly");
                direction = "N";
            }
            else if (turn_counter == 5) {
                decision.put("action", "fly");
                direction = "N";
            }
            else if (turn_counter == 6) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "NW";
            }
            else if (turn_counter == 7) {
                decision.put("action", "fly");
                direction = "W";
            }
            else if (turn_counter == 8) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "WS";
            }
            else if (turn_counter == 9) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "SE";
            }
            else if (turn_counter == 10) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "ES";
            }
            else if (turn_counter == 11) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "SW";
            }
            else {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "WN";
                turn_direction = "W";
                current_direction = "N";
            }
            turn_counter += 1;
        }
        else if (onLand && current_direction == "N") {
            logger.info("Flying north");
            if (decisionMade.equals("heading")) {
                decision.put("action", "scan");    
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
                direction = "N";
            }
            else {
                turn_counter = 0;
                turn_direction = "S";
                decision.put("action", "fly");
                direction = "N";
            }
        }
        else if (onLand && current_direction == "S") {
            logger.info("Flying south");
            if (decisionMade.equals("heading")) {
                decision.put("action", "scan");
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
                direction = "S";
            }
            else {
                turn_counter = 0;
                turn_direction = "N";
                decision.put("action", "fly");
                direction = "S";

            }
        }
        else {
            logger.info("Flying towards land");
            if (current_direction == "N") {
                if (echoResult == 0){
                    onLand = true;
                    decision.put("action", "fly");
                    direction = "N";
                }
                else if (decisionMade.equals("fly")){
                    decision.put("action", "echo");
                    decision.put("parameters", new JSONObject().put("direction", "N"));
                }
                else {
                    decision.put("action", "fly");
                    direction = "N";
                }
            }
            else {
                if (echoResult == 0){
                    onLand = true;
                    decision.put("action", "fly");
                    direction = "S";
                }
                else if (decisionMade.equals("fly")){
                    decision.put("action", "echo");
                    decision.put("parameters", new JSONObject().put("direction", "S"));
                }
                else {
                    decision.put("action", "fly");
                    direction = "S";
                }
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

    public String decisionControlNorth() {

        JSONObject decision = new JSONObject();
        Initialize initialize = new Initialize();
        Result result = new Result();

        if (borderRange == 1) {
            decision.put("action", "stop");
        }
        else if (!foundGround){
            logger.info("Flying north");
            if (echoResult != 200) {
                foundGround = true;
                if (currentParameter.equals("E")) {
                    decision.put("action", "heading");
                    decision.put("parameters", new JSONObject().put("direction", "E"));
                    current_direction = "E";
                    direction = "NE";
                }
                else {
                    decision.put("action", "heading");
                    decision.put("parameters", new JSONObject().put("direction", "W"));
                    current_direction = "W";
                    direction = "NW";
                }
            }
            else if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "E"));
            }
            else if (decisionMade.equals("echo") && currentParameter.equals("E")) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "W"));
            }
            else{
                decision.put("action", "fly");
                direction = "N";
            }   
        }
        else if (turn_direction == "W") {
            logger.info("Turning west");
            if (turn_counter == 0) {
                decision.put("action", "scan");
            }
            else if (turn_counter == 1) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "N"));
            }
            else if (turn_counter == 2) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "ES";
            }
            else if (turn_counter == 3) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "SW";
            }
            else if (turn_counter == 4) {
                decision.put("action", "fly");
                direction = "W";
            }
            else if (turn_counter == 5) {
                decision.put("action", "fly");
                direction = "W";
            }
            else if (turn_counter == 6) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "WN";
            }
            else if (turn_counter == 7) {
                decision.put("action", "fly");
                direction = "N";
            }
            else if (turn_counter == 8) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "NE";
            }
            else if (turn_counter == 9) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "ES";
            }
            else if (turn_counter == 10) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "SE";
            }
            else if (turn_counter == 11) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "EN";
            }
            else {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "NW";
                turn_direction = "N";
                current_direction = "W";
            }
            turn_counter += 1;
        }
        else if (turn_direction == "E") {
            logger.info("Turning east");
            if (turn_counter == 0) {
                decision.put("action", "scan");
            }
            else if (turn_counter == 1) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "N"));
            }
            else if (turn_counter == 2) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "WS";
            }
            else if (turn_counter == 3) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "SE";
            }
            else if (turn_counter == 4) {
                decision.put("action", "fly");
                direction = "E";
            }
            else if (turn_counter == 5) {
                decision.put("action", "fly");
                direction = "E";
            }
            else if (turn_counter == 6) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "EN";
            }
            else if (turn_counter == 7) {
                decision.put("action", "fly");
                direction = "N";
            }
            else if (turn_counter == 8) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "NW";
            }
            else if (turn_counter == 9) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "WS";
            }
            else if (turn_counter == 10) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "SW";
            }
            else if (turn_counter == 11) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "WN";
            }
            else {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "NE";
                turn_direction = "N";
                current_direction = "E";
            }
            turn_counter += 1;
        }
        else if (onLand && current_direction == "E") {
            logger.info("Flying east");
            if (decisionMade.equals("heading")) {
                decision.put("action", "scan");    
                echoResult = 0;
            }
            if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "E"));
            } 
            else if (decisionMade.equals("echo")){
                decision.put("action", "scan");
            }
            else if (echoResult != 200) {
                decision.put("action", "fly");
                direction = "E";
            }
            else {
                turn_counter = 0;
                turn_direction = "W";
                decision.put("action", "fly");
                direction = "E";
            }
        }
        else if (onLand && current_direction == "W") {
            logger.info("Flying west");
            if (decisionMade.equals("heading")) {
                decision.put("action", "scan");
                echoResult = 0;
            }
            if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "W"));
            } 
            else if (decisionMade.equals("echo")){
                decision.put("action", "scan");
            }
            else if (echoResult != 200) {
                decision.put("action", "fly");
                direction = "W";
            }
            else {
                turn_counter = 0;
                turn_direction = "E";
                decision.put("action", "fly");
                direction = "W";

            }
        }
        else {
            logger.info("Flying towards land");
            if (current_direction == "E") {
                if (echoResult == 0){
                    onLand = true;
                    decision.put("action", "fly");
                    direction = "E";
                }
                else if (decisionMade.equals("fly")){
                    decision.put("action", "echo");
                    decision.put("parameters", new JSONObject().put("direction", "E"));
                }
                else {
                    decision.put("action", "fly");
                    direction = "E";
                }
            }
            else {
                if (echoResult == 0){
                    onLand = true;
                    decision.put("action", "fly");
                    direction = "W";
                }
                else if (decisionMade.equals("fly")){
                    decision.put("action", "echo");
                    decision.put("parameters", new JSONObject().put("direction", "W"));
                }
                else {
                    decision.put("action", "fly");
                    direction = "W";
                }
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

    public String decisionControlSouth() {

        JSONObject decision = new JSONObject();
        Initialize initialize = new Initialize();
        Result result = new Result();

        if (borderRange == 1) {
            decision.put("action", "stop");
        }
        else if (!foundGround){
            logger.info("Flying south");
            if (echoResult != 200) {
                foundGround = true;
                if (currentParameter.equals("E")) {
                    decision.put("action", "heading");
                    decision.put("parameters", new JSONObject().put("direction", "E"));
                    current_direction = "E";
                    direction = "SE";
                }
                else {
                    decision.put("action", "heading");
                    decision.put("parameters", new JSONObject().put("direction", "W"));
                    current_direction = "W";
                    direction = "SW";
                }
            }
            else if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "E"));
            }
            else if (decisionMade.equals("echo") && currentParameter.equals("E")) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "W"));
            }
            else{
                decision.put("action", "fly");
                direction = "S";
            }   
        }
        else if (turn_direction == "W") {
            logger.info("Turning west");
            if (turn_counter == 0) {
                decision.put("action", "scan");
            }
            else if (turn_counter == 1) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "S"));
            }
            else if (turn_counter == 2) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "EN";
            }
            else if (turn_counter == 3) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "NW";
            }
            else if (turn_counter == 4) {
                decision.put("action", "fly");
                direction = "W";
            }
            else if (turn_counter == 5) {
                decision.put("action", "fly");
                direction = "W";
            }
            else if (turn_counter == 6) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "WS";
            }
            else if (turn_counter == 7) {
                decision.put("action", "fly");
                direction = "S";
            }
            else if (turn_counter == 8) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "SE";
            }
            else if (turn_counter == 9) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "EN";
            }
            else if (turn_counter == 10) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "NE";
            }
            else if (turn_counter == 11) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "ES";
            }
            else {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "SW";
                turn_direction = "S";
                current_direction = "W";
            }
            turn_counter += 1;
        }
        else if (turn_direction == "E") {
            logger.info("Turning east");
            if (turn_counter == 0) {
                decision.put("action", "scan");
            }
            else if (turn_counter == 1) {
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "S"));
            }
            else if (turn_counter == 2) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "WN";
            }
            else if (turn_counter == 3) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "NE";
            }
            else if (turn_counter == 4) {
                decision.put("action", "fly");
                direction = "E";
            }
            else if (turn_counter == 5) {
                decision.put("action", "fly");
                direction = "E";
            }
            else if (turn_counter == 6) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "ES";
            }
            else if (turn_counter == 7) {
                decision.put("action", "fly");
                direction = "S";
            }
            else if (turn_counter == 8) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "SW";
            }
            else if (turn_counter == 9) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "N"));
                direction = "WN";
            }
            else if (turn_counter == 10) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "W"));
                direction = "NW";
            }
            else if (turn_counter == 11) {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "S"));
                direction = "WS";
            }
            else {
                decision.put("action", "heading");
                decision.put("parameters", new JSONObject().put("direction", "E"));
                direction = "SE";
                turn_direction = "S";
                current_direction = "E";
            }
            turn_counter += 1;
        }
        else if (onLand && current_direction == "E") {
            logger.info("Flying east");
            if (decisionMade.equals("heading")) {
                decision.put("action", "scan");    
                echoResult = 0;
            }
            if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "E"));
            } 
            else if (decisionMade.equals("echo")){
                decision.put("action", "scan");
            }
            else if (echoResult != 200) {
                decision.put("action", "fly");
                direction = "E";
            }
            else {
                turn_counter = 0;
                turn_direction = "W";
                decision.put("action", "fly");
                direction = "E";
            }
        }
        else if (onLand && current_direction == "W") {
            logger.info("Flying west");
            if (decisionMade.equals("heading")) {
                decision.put("action", "scan");
                echoResult = 0;
            }
            if (decisionMade.equals("fly")){
                decision.put("action", "echo");
                decision.put("parameters", new JSONObject().put("direction", "W"));
            } 
            else if (decisionMade.equals("echo")){
                decision.put("action", "scan");
            }
            else if (echoResult != 200) {
                decision.put("action", "fly");
                direction = "W";
            }
            else {
                turn_counter = 0;
                turn_direction = "E";
                decision.put("action", "fly");
                direction = "W";

            }
        }
        else {
            logger.info("Flying towards land");
            if (current_direction == "E") {
                if (echoResult == 0){
                    onLand = true;
                    decision.put("action", "fly");
                    direction = "E";
                }
                else if (decisionMade.equals("fly")){
                    decision.put("action", "echo");
                    decision.put("parameters", new JSONObject().put("direction", "E"));
                }
                else {
                    decision.put("action", "fly");
                    direction = "E";
                }
            }
            else {
                if (echoResult == 0){
                    onLand = true;
                    decision.put("action", "fly");
                    direction = "W";
                }
                else if (decisionMade.equals("fly")){
                    decision.put("action", "echo");
                    decision.put("parameters", new JSONObject().put("direction", "W"));
                }
                else {
                    decision.put("action", "fly");
                    direction = "W";
                }
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
            // if (currentParameter.equals("E") || currentParameter.equals("W")) {
            //     borderRange = result.border_range(resultData);
            // }
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