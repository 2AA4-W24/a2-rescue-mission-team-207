package ca.mcmaster.se2aa4.island.team207;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

public class Result {

    private final Logger logger = LogManager.getLogger();

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
}