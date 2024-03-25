package ca.mcmaster.se2aa4.island.team207;

import ca.mcmaster.se2aa4.island.team207.Decisions.Decision;
public class Position {

    private int x_position = 0;
    private int y_position = 0;
    private Decision decision = new Decision();
    private String decisionMade = decision.getDecisionMade();
    private String direction = decision.getDirection();

    // construct Position object
    public void updateDecision(Decision decision) {
        this.decision = decision;
        this.decisionMade = decision.getDecisionMade();
        this.direction = decision.getDirection();
    }
    
    // change position
    public void change_position() {
        if (decisionMade.equals("fly")) {
            if (direction.equals("N")) {
                y_position += 1;
            }
            if (direction.equals("E")) {
                x_position += 1;
            }
            if (direction.equals("S")) {
                y_position -= 1;
            }
            if (direction.equals("W")) {
                x_position -= 1;
            }
        }
        else if (decisionMade.equals("heading")) {
            if (direction.equals("NE") || direction.equals("EN")) {
                x_position += 1;
                y_position += 1;
            }
            if (direction.equals("NW") || direction.equals("WN")) {
                x_position -= 1;
                y_position += 1;
            }
            if (direction.equals("SE") || direction.equals("ES")) {
                x_position += 1;
                y_position -= 1;
            }
            if (direction.equals("SW") || direction.equals("WS")) {
                x_position -= 1;
                y_position -= 1;
            }  
        }
    }

    // get position
    public String get_position() {
        String position = "(" + x_position + ", " + y_position + ")";
        return position;
    }

    // get x position
    public int get_positionX() {
        return x_position;
    }

    // get y position
    public int get_positionY() {
        return y_position;
    }


}
