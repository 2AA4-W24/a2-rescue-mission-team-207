package ca.mcmaster.se2aa4.island.team207;

import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;
import java.io.StringReader;

public class Budget {

  private Integer totalCost = 0;
  private Integer battery = 1000;
  private Integer newCost= 0;
  
  public void getInit(String s) {
    Initialize initialize = new Initialize();
    initialize.initialize(s);
    setBattery(initialize.getBatteryLevel());
  }

  public void getResult(String s){
    Result result = new Result();
    result.printResult(s);
    setTotalCost(result.getCost(s));
  }

  public Integer useBattery(){
    return getBattery();
  }

  public Integer useCost(){
    return getTotalCost();
  }


  public Integer getDifference(Integer battery, Integer totalCost) {
    newCost = battery - totalCost;
    return newCost;

  }
  
  private void setTotalCost(Integer totalCost){
    this.totalCost = totalCost;
  }

  private Integer getTotalCost(){
    return this.totalCost;
  }
  
  private void setBattery(Integer battery){
    this.battery = battery;
  }

  private Integer getBattery(){
    return this.battery;
  }

}
