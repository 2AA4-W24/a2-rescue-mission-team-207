package ca.mcmaster.se2aa4.island.team207;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

public class Result {

    private final Logger logger = LogManager.getLogger();
    
    // print the result of the action
    public JSONObject printResult (String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        logger.info("** Response received:\n"+response.toString(2));
        Integer cost = response.getInt("cost");
        logger.info("The cost of the action was {}", cost);
        String status = response.getString("status");
        logger.info("The status of the drone is {}", status);
        JSONObject extraInfo = response.getJSONObject("extras");
        logger.info("Additional information received: {}", extraInfo);
        return extraInfo;
    }

    // get the cost of the action
    public int echo_result(JSONObject extraInfo) {
        String found = extraInfo.getString("found");
        logger.info("Found: {}", found);
        Integer range = extraInfo.getInt("range");
        logger.info("Range: {}", range);
        if (found.equals("GROUND")) {
            return range;
        }
        else {
            return 200;
        }
    }

    // get the range of the border
    public int border_range(JSONObject extraInfo) {
        String found = extraInfo.getString("found");
        Integer range = extraInfo.getInt("range");
        if (found.equals("OUT_OF_RANGE")) {
            return range;
        }
        return 200;
    }   
}