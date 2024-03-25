package ca.mcmaster.se2aa4.island.team207.POIs;

import ca.mcmaster.se2aa4.island.team207.Decisions.Decision;
import ca.mcmaster.se2aa4.island.team207.Result;
import org.json.JSONObject;

public class CreekResults {

    private String creekResult = " ";
    private Decision decision;

    // construct CreekResults object
    public CreekResults() {
        decision = Decision.getInstance();
    }
    
    // scan for creeks
    public String useScanCreeks(JSONObject resultData) {
        GetPOIs pois = new GetPOIs();
        if (decision.getDecisionMade().equals("scan")) {
            creekResult = pois.scan_creeks(resultData);
            return creekResult;
        }
        return "null";
    }
}
