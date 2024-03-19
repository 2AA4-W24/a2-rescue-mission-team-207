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
    int siteposx;
    int siteposy;
    double min_distance = 100;
    String closest_creek = "";


    public int totalCost = 0;
    // public String creeks = "";
    // public String site = "";
    // public List<String> creekIDs = new ArrayList<>();

    public List<Integer> creekX = new ArrayList<>();
    public List<Integer> creekY = new ArrayList<>();
    

    private Decision decision = new Decision();
    private Position position = new Position();
    private Integer batteryLevel = 0;
    private Integer remainingBudget;
    
    
    @Override
    public void initialize(String s) {
        Initialize initialize = new Initialize();
        Budget budget = new Budget();
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        String direction = info.getString("heading");
        // Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", direction);
        //logger.info("Battery level is {}", batteryLevel);
        initialize.initialize(s);
        budget.getInit(s);
        batteryLevel = budget.useBattery();

    }

    @Override
    public String takeDecision() {
        return decision.decisionControl();
        
    }

    @Override
    public void acknowledgeResults(String s) {
        Result result = new Result();
        Initialize initialize = new Initialize();
        Budget budget = new Budget();
        JSONObject resultData = result.printResult(s);
        decision.useEchoResults(resultData);

        String creeksResult = decision.useScanCreeks(resultData);
        if (creeksResult != "null") {
            creeks.addCreek(creeksResult);
            creekX.add(position.get_positionX());
            creekY.add(position.get_positionY());
        }

        String siteResult = decision.useScanSite(resultData);
        if (siteResult != "null") {
            site.setSite(siteResult);
            siteposx = position.get_positionX();
            siteposy = position.get_positionY();
        }

        //batteryLevel = initialize.getBatteryLevel();
        //totalCost += result.getCost(s);
        budget.getResult(s);
        totalCost += budget.useCost();
        remainingBudget = budget.getDifference(batteryLevel, totalCost);
        logger.info("Total cost: " + totalCost);
        logger.info("Remaining budget: " + remainingBudget);

        position.updateDecision(decision);
        position.change_position();
        String current_position = position.get_position();
        logger.info("Current position: " + current_position);
    }

    @Override
    public String deliverFinalReport() {
        List<String> creeksList = creeks.getCreeks();
        String creeksFound = String.join(", ", creeksList);
        String creekXpos = "";
        for (Integer x : creekX) {
            creekXpos = creekXpos + String.valueOf(x) + " ";
        }
        String creekYpos = "";
        for (Integer y : creekY) {
            creekYpos = creekYpos + String.valueOf(y) + " ";
        }

        for (int i = 0; i < creeksList.size(); i++) {
            int cx = creekX.get(i);
            int cy = creekY.get(i);
            double distance = Math.sqrt(Math.pow((siteposx - cx), 2) + Math.pow((siteposy - cy), 2));
            if (distance < min_distance) {
                min_distance = distance;
                closest_creek = creeksList.get(i);
            }
        }

        logger.info("Creeks found: " + creeksFound);
        logger.info("Site found: " + site.getSite());
        logger.info("Site X position: " + siteposx);
        logger.info("Site Y position: " + siteposy);
        logger.info("Creek X positions: " + creekXpos);
        logger.info("Creek Y positions: " + creekYpos);
        logger.info("Closest creek to site: " + closest_creek);
        
        return closest_creek;
    }

}
