package ca.mcmaster.se2aa4.island.team207.POIs;

import java.util.ArrayList;
import java.util.List;

public class Creeks {
    private List<String> creekIDs = new ArrayList<>();

    public void addCreek(String creekID) {
        creekIDs.add(creekID);
    }

    public List<String> getCreeks() {
        return creekIDs;
    }
}
