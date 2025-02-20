package ca.mcmaster.se2aa4.island.team207;

import eu.ace_design.island.bot.IExplorerRaid;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;
import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.se2aa4.island.team207.Decisions.Decision;
import ca.mcmaster.se2aa4.island.team207.POIs.*;
import ca.mcmaster.se2aa4.island.team207.Power.*;
import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Explorer implements IExplorerRaid {

    private final Logger logger = LogManager.getLogger();
    private final Creeks creeks = new Creeks();
    private final Site site = new Site();
    private CreekResults creekResult = new CreekResults();
    private SiteResults siteResult = new SiteResults();
    private static final int minBudget = 30;
    private int siteposx;
    private int siteposy;
    private double min_distance = 100;
    private String closest_creek = "";
    private int totalCost = 0;
    private List<Integer> creekX = new ArrayList<>();
    private List<Integer> creekY = new ArrayList<>();
    private Decision decision = Decision.getInstance();
    private Position position = new Position();
    private Integer batteryLevel = 0;
    private Integer remainingBudget = 100;
    private String init_direction;
    private String rightDir;
    private String leftDir;
    private String oppositeDir;
    private String initialDir;
    
    @Override
    public void initialize(String s) {
        Initialize initialize = new Initialize();
        Battery battery = new Battery();
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        init_direction = info.getString("heading");
        logger.info("The drone is facing {}", init_direction);
        battery.getInit(s);
        batteryLevel = battery.getBattery();

        Direction initialDirection = Direction.stringToDirection(init_direction);
        Direction leftDirection = initialDirection.getLeftDirection();
        Direction rightDirection = initialDirection.getRightDirection();
        Direction oppositeDirection = initialDirection.getOppositeDirection();

        // initialize directions
        rightDir = rightDirection.directionToString();
        leftDir = leftDirection.directionToString();
        oppositeDir = oppositeDirection.directionToString();
        initialDir = initialDirection.directionToString();

    }

    @Override
    public String takeDecision() {
        // check if budget is low and then stop
        if (remainingBudget < minBudget) {
            logger.info("Remaining budget is low. Stopping exploration.");
            return decision.stopDecision();
        }
        // make decision based on current state
        else {
            return decision.decisionControl(rightDir, leftDir, oppositeDir, initialDir);
        }     
    }

    @Override
    public void acknowledgeResults(String s) {
        Result result = new Result();
        Initialize initialize = new Initialize();
        Cost cost = new Cost();
        Budget budget = new Budget();
        JSONObject resultData = result.printResult(s);
        decision.useEchoResults(resultData);

        // get creek positions
        String creekPOI = creekResult.useScanCreeks(resultData);
        if (creekPOI != "null") {
            creeks.addCreek(creekPOI);
            creekX.add(position.get_positionX());
            creekY.add(position.get_positionY());
        }

        // get site position
        String sitePOI = siteResult.useScanSite(resultData);
        if (sitePOI != "null") {
            site.setSite(sitePOI);
            siteposx = position.get_positionX();
            siteposy = position.get_positionY();
        }

        // calculate remaining budget
        totalCost += cost.getCost(s);
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
        ShortestPath shortestPath = new ShortestPath();
        List<String> creeksList = creeks.getCreeks();
        String creeksFound = String.join(", ", creeksList);
        String siteFound = site.getSite();

        // find closest creek to site
        String closest_creek = shortestPath.findClosestCreek(creeksList, creekX, creekY, siteposx, siteposy);

        //log info for checking
        String creekPositions = creeks.printCreeks(creekX, creekY);
        logger.info("Creeks found: " + creeksFound);
        logger.info("Site found: " + siteFound);
        logger.info("Site X position: " + siteposx);
        logger.info("Site Y position: " + siteposy);
        logger.info(creekPositions);
        logger.info("Closest creek to site: " + closest_creek);
        
        // creek data to ACME
        return closest_creek;
    }

}
