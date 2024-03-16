package ca.mcmaster.se2aa4.island.team207;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;
import java.util.ArrayList;
import java.util.List;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    
    // private Integer counter = 0;

    // private String decisionMade = " "; 

    // private Integer newResult = 200;

    // private boolean foundGround = false;

    public int totalCost = 0;
    public String creeks = "";
    public String site = "";
    public List<String> creekIDs = new ArrayList<>();

    private Decision decision = new Decision(); 


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
        return decision.decisionControl();
        
    }

    @Override
    public void acknowledgeResults(String s) {
        Result result = new Result();
        JSONObject resultData = result.printResult(s);
        decision.useEchoResults(resultData);
        String creeksResult = decision.useScanCreeks(resultData);
        if (creeksResult != "null") {
            creekIDs.add(creeksResult);
        }
        String siteResult = decision.useScanSite(resultData);
        if (siteResult != "null") {
            site = siteResult;
        }
        totalCost += result.getCost(s);
        logger.info("Total cost: " + totalCost);
    }

    @Override
    public String deliverFinalReport() {
        for (String str : creekIDs) {
            creeks = creeks + " " + str;
        }
        logger.info("Creeks found: " + creeks);
        logger.info("Site found: " + site);
        return creeks;
    }

}
