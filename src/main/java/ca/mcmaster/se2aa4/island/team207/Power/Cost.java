package ca.mcmaster.se2aa4.island.team207.Power;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;
import java.io.StringReader;

public class Cost {

    public int getCost (String s) {
        JSONObject response = new JSONObject(new JSONTokener(new StringReader(s)));
        Integer cost = response.getInt("cost");
        return cost;
    }
    
}
