package ca.mcmaster.se2aa4.island.team207.POIs;

import ca.mcmaster.se2aa4.island.team207.Decisions.Decision;
import ca.mcmaster.se2aa4.island.team207.Result;
import org.json.JSONObject;

public class SiteResults {
    
    private String siteResult = " ";
    private Decision decision;

    // construct SiteResults object
    public SiteResults() {
        decision = Decision.getInstance();
    }

    // scan for sites
    public String useScanSite(JSONObject resultData) {
        GetPOIs pois = new GetPOIs();
        if (decision.getDecisionMade().equals("scan")) {
            siteResult = pois.scan_site(resultData);
            return siteResult;
        }
        return "null";
    }
}