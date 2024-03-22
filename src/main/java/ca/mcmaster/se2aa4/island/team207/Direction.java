package ca.mcmaster.se2aa4.island.team207;

public enum Direction {
    NORTH,
    SOUTH,
    EAST,
    WEST;

    public Direction getOppositeDirection() {
        switch (this) {
            case NORTH:
                return SOUTH;
            case SOUTH:
                return NORTH;
            case EAST:
                return WEST;
            case WEST:
                return EAST;
            default:
                throw new IllegalArgumentException("Unknown direction");
        }
    }

    public Direction getLeftDirection() {
        switch (this) {
            case NORTH:
                return WEST;
            case SOUTH:
                return EAST;
            case EAST:
                return NORTH;
            case WEST:
                return SOUTH;
            default:
                throw new IllegalArgumentException("Unknown direction");
        }
    }

    public Direction getRightDirection() {
        switch (this) {
            case NORTH:
                return EAST;
            case SOUTH:
                return WEST;
            case EAST:
                return SOUTH;
            case WEST:
                return NORTH;
            default:
                throw new IllegalArgumentException("Unknown direction");
        }
    }

    public static Direction stringToDirection(String directionString) {
        switch (directionString) {
            case "N":
                return NORTH;
            case "S":
                return SOUTH;
            case "E":
                return EAST;
            case "W":
                return WEST;
            default:
                throw new IllegalArgumentException("Invalid direction string: " + directionString);
        }
    }

    public String directionToString() {
        switch (this) {
            case NORTH:
                return "N";
            case SOUTH:
                return "S";
            case EAST:
                return "E";
            case WEST:
                return "W";
            default:
                throw new IllegalArgumentException("Invalid direction: " + this);
        }
    }
}