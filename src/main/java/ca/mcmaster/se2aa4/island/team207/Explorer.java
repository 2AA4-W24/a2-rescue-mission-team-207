package ca.mcmaster.se2aa4.island.team207;

import ca.mcmaster.se2aa4.island.team207.Decisions.Decision;
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

    public List<Integer> creekX = new ArrayList<>();
    public List<Integer> creekY = new ArrayList<>();
    
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
        Budget budget = new Budget();
        logger.info("** Initializing the Exploration Command Center");
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Initialization info:\n {}",info.toString(2));
        init_direction = info.getString("heading");
        // Integer batteryLevel = info.getInt("budget");
        logger.info("The drone is facing {}", init_direction);
        //logger.info("Battery level is {}", batteryLevel);
        initialize.initialize(s);
        budget.getInit(s);
        batteryLevel = budget.useBattery();

        Direction initialDirection = Direction.stringToDirection(init_direction);
        Direction leftDirection = initialDirection.getLeftDirection();
        Direction rightDirection = initialDirection.getRightDirection();
        Direction oppositeDirection = initialDirection.getOppositeDirection();

        rightDir = rightDirection.directionToString();
        leftDir = leftDirection.directionToString();
        oppositeDir = oppositeDirection.directionToString();
        initialDir = initialDirection.directionToString();

    }

    @Override
    public String takeDecision() {
        if (remainingBudget < 30) {
            logger.info("Remaining budget is less than 30. Stopping exploration.");
            return decision.stopDecision();
        }
        else {
            return decision.decisionControl(rightDir, leftDir, oppositeDir, initialDir);
        }     
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
        ShortestPath shortestPath = new ShortestPath();
        List<String> creeksList = creeks.getCreeks();
        String creeksFound = String.join(", ", creeksList);
        // String creekXpos = "";
        // for (Integer x : creekX) {
        //     creekXpos = creekXpos + String.valueOf(x) + " ";
        // }
        // String creekYpos = "";
        // for (Integer y : creekY) {
        //     creekYpos = creekYpos + String.valueOf(y) + " ";
        // }

        String closest_creek = shortestPath.findClosestCreek(creeksList, creekX, creekY, siteposx, siteposy);
        String creekPositions = shortestPath.printCreeks(creekX, creekY);

        logger.info("Creeks found: " + creeksFound);
        logger.info("Site found: " + site.getSite());
        logger.info("Site X position: " + siteposx);
        logger.info("Site Y position: " + siteposy);
        // logger.info("Creek X positions: " + creekXpos);
        // logger.info("Creek Y positions: " + creekYpos);
        logger.info(creekPositions);
        logger.info("Closest creek to site: " + closest_creek);
        
        return closest_creek;
    }

}
