package ca.mcmaster.se2aa4.island.team207.Power;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;
import java.io.StringReader;

public class Budget {
  private Integer newCost= 0;

  // get difference between battery and total cost
  public Integer getDifference(Integer battery, Integer totalCost) {
    newCost =  battery - totalCost;
    return newCost;
  }

}
