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
    
    private final Creeks creeks = new Creeks();
    private final Site site = new Site();


    public int totalCost = 0;
    // public String creeks = "";
    // public String site = "";
    // public List<String> creekIDs = new ArrayList<>();

    private Decision decision = new Decision();
    private Position position = new Position();


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
            creeks.addCreek(creeksResult);
        }

        String siteResult = decision.useScanSite(resultData);
        if (siteResult != "null") {
            site.setSite(siteResult);
        }

        totalCost += result.getCost(s);
        logger.info("Total cost: " + totalCost);

        position.updateDecision(decision);
        position.change_position();
        String current_position = position.get_position();
        logger.info("Current position: " + current_position);
    }

    @Override
    public String deliverFinalReport() {
        List<String> creeksList = creeks.getCreeks();
        String creeksFound = String.join(" ", creeksList);

        logger.info("Creeks found: " + creeksFound);
        logger.info("Site found: " + site.getSite());
        return creeksFound;
    }

}
