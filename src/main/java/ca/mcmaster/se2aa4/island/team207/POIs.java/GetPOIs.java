package ca.mcmaster.se2aa4.island.team207.POIs;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import java.io.StringReader;

public class GetPOIs {
    public String scan_creeks(JSONObject extraInfo) {
        JSONArray creeks = extraInfo.getJSONArray("creeks");
        if (creeks.isNull(0)) {
            return "null";
        }
        else {
            String creekID = creeks.getString(0);
            return creekID;
        }
    }
    public String scan_site(JSONObject extraInfo) {
        JSONArray sites = extraInfo.getJSONArray("sites");
        if (sites.isNull(0)) {
            return "null";
        }
        else {
            String siteID = sites.getString(0);
            return siteID;
        }
    }
}
