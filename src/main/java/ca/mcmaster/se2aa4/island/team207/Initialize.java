package ca.mcmaster.se2aa4.island.team207;

import java.io.StringReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;

import ca.mcmaster.se2aa4.island.team207.Result;


public class Initialize {
    private int batteryLevel;
    private String direction;

    public void initialize(String s) {
        JSONObject info = new JSONObject(new JSONTokener(new StringReader(s)));
        this.batteryLevel = info.getInt("budget");
        this.direction = info.getString("heading");
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public String getDirection() {
        return direction;
    }
}


    
    

