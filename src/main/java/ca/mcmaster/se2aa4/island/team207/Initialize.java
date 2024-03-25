package ca.mcmaster.se2aa4.island.team207;
import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;


public class Initialize {
    private int batteryLevel;
    private String direction;

    // set initial battery level and direction
    public void setInitial(String s) {
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        this.batteryLevel = info.getInt("budget");
        this.direction = info.getString("heading");
    }

    // get battery level
    public int getBatteryLevel() {
        return batteryLevel;
    }

    // get direction
    public String getDirection() {
        return direction;
    }
}


    
    

