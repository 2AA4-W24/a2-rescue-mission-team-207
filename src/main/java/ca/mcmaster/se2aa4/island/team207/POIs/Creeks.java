package ca.mcmaster.se2aa4.island.team207.POIs;

import java.util.ArrayList;
import java.util.List;

public class Creeks {
    private List<String> creekIDs = new ArrayList<>();

    // add creek to list of creeks
    public void addCreek(String creekID) {
        creekIDs.add(creekID);
    }

    // get list of creeks
    public List<String> getCreeks() {
        return creekIDs;
    }

    // print creeks
    public String printCreeks(List<Integer> creekX, List<Integer> creekY) {
        StringBuilder creekXpos = new StringBuilder();
        for (Integer x : creekX) {
            creekXpos.append(x).append(" ");
        }
        StringBuilder creekYpos = new StringBuilder();
        for (Integer y : creekY) {
            creekYpos.append(y).append(" ");
        }
        return "Creek X positions: " + creekXpos.toString() + "\nCreek Y positions: " + creekYpos.toString();
    }
}
