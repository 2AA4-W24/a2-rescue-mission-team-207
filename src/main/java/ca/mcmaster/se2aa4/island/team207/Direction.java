package ca.mcmaster.se2aa4.island.team207;

public enum Direction {
  north, south, east, west;

  public static Direction turnAround(Direction d) {
      if (d == north) {
          return south;
      } else if (d == south) {
          return north;
      } else if (d == east) {
          return west;
      } else {
          return east;
      }
  }

  public static Direction turnLeft(Direction d) {
      if (d == north) {
          return west;
      } else if (d == south) {
          return east;
      } else if (d == east) {
          return north;
      } else {
          return south;
      }
  }

  public static Direction turnRight(Direction d) {
      if (d == north) {
          return east;
      } else if (d == south) {
          return west;
      } else if (d == east) {
          return south;
      } else {
          return north;
      }
  }
  
}
  
