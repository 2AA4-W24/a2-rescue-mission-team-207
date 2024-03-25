package ca.mcmaster.se2aa4.island.team207.POIs;

import java.util.List;
import java.lang.StringBuilder;

public class ShortestPath {
  
  // find the closest creek to the site using Pythagorean distance
  public String findClosestCreek(List<String> creeksList, List<Integer> creekX, List<Integer> creekY, int siteposx, int siteposy) {
    double min_distance = 100;
    String closest_creek = "";

    for (int i = 0; i < creeksList.size(); i++){
      int cx = creekX.get(i);
      int cy = creekY.get(i);
      double distance = Math.sqrt(Math.pow((siteposx - cx), 2) + Math.pow((siteposy - cy), 2));
      if (distance < min_distance) {
        min_distance = distance;
        closest_creek = creeksList.get(i);
      }
    }
    return closest_creek;
  }

}
