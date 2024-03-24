package ca.mcmaster.se2aa4.island.team207.POIs;

import ca.mcmaster.se2aa4.island.team207.Decisions.Decision;
import ca.mcmaster.se2aa4.island.team207.Result;
import org.json.JSONObject;

public class CreekResults {

    private String creekResult = " ";
    private Decision decision;

    public CreekResults() {
        decision = Decision.getInstance();
    }
    
    public String useScanCreeks(JSONObject resultData) {
        GetPOIs pois = new GetPOIs();
        if (decision.getDecisionMade().equals("scan")) {
            creekResult = pois.scan_creeks(resultData);
            return creekResult;
        }
        return "null";
    }
}
