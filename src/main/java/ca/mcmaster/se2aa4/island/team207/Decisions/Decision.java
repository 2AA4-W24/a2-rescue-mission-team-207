package ca.mcmaster.se2aa4.island.team207.Decisions;

import ca.mcmaster.se2aa4.island.team207.Result;
import ca.mcmaster.se2aa4.island.team207.Initialize;
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
    private boolean onLand = false;
    private String current_direction;
    private String direction;
    private String turn_direction = " ";
    private int turn_counter = 0;
    private int borderRange = 200;
    private String currentParameter = " ";

    private String creekResult = " ";
    private String siteResult = " ";

    private static Decision instance;

    public static Decision getInstance() {
        if (instance == null) {
            instance = new Decision();
        }
        return instance;
    }
  
    public String stopDecision() {
        JSONObject stop = new JSONObject();
        stop.put("action", "stop");
        String stopString = stop.toString();
        logger.info("** Decision: {}",stopString);
        return stopString;
    }

    public String decisionControl(String rightDir, String leftDir, String oppositeDir, String initialDir) {
        JSONObject decision = new JSONObject();
        Initialize initialize = new Initialize();
        Result result = new Result();

        DecisionState state;

        if (borderRange == 1) {
            state = new Stop();
        }
        else if (!foundGround){
            state = new FindLand();
        }
        else if (turn_direction.equals(rightDir)) {
            state = new TurnRight();
        }
        else if (turn_direction.equals(leftDir)) {
            state = new TurnLeft();
        }
        else if (onLand && current_direction.equals(leftDir)) {
            state = new ScanLeft();
        }
        else if (onLand && current_direction.equals(rightDir)) {
            state = new ScanRight();
        }
        else {
            state = new FlyingToLand();
        }

        return state.makeDecision(rightDir, leftDir, oppositeDir, initialDir);
    }

    public boolean getFoundGround() {
        return foundGround;
    }

    public void setFoundGround(boolean ground) {
        foundGround = ground;
    }

    public boolean getOnLand() {
        return onLand;
    }

    public void setOnLand(boolean land) {
        onLand = land;
    }

    public String getDecisionMade() {
        return decisionMade;
    }

    public void setDecisionMade(String decision) {
        decisionMade = decision;
    }

    public String getTurnDirection() {
        return turn_direction;
    }

    public void setTurnDirection(String direction) {
        turn_direction = direction;
    }

    public String getCurrentParameter() {
        return currentParameter;
    }

    public void setCurrentParameter(String parameter) {
        currentParameter = parameter;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String dir) {
        direction = dir;
    }

    public String getCurrentDirection() {
        return current_direction;
    }

    public void setCurrentDirection(String dir) {
        current_direction = dir;
    }

    public int getTurnCounter() {
        return turn_counter;
    }

    public void setTurnCounter(int counter) {
        turn_counter = counter;
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

    public int getEchoResult() {
        return echoResult;
    }

    public void setEchoResult(int result) {
        echoResult = result;
    }

    public int getBorderRange() {
        return borderRange;
    }

    public void setBorderRange(int range) {
        borderRange = range;
    }

}