package ca.mcmaster.se2aa4.island.team207;

import ca.mcmaster.se2aa4.island.team207.Decisions.Decision;
public class Position {

    public int x_position = 0;
    public int y_position = 0;
    private Decision decision = new Decision();
    private String decisionMade = decision.getDecisionMade();
    private String direction = decision.getDirection();

    public void updateDecision(Decision decision) {
        this.decision = decision;
        this.decisionMade = decision.getDecisionMade();
        this.direction = decision.getDirection();
    }
    
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

    public String get_position() {
        String position = "(" + x_position + ", " + y_position + ")";
        return position;
    }

    public int get_positionX() {
        int posX = x_position;
        return posX;
    }

    public int get_positionY() {
        int posY = y_position;
        return posY;
    }


}
