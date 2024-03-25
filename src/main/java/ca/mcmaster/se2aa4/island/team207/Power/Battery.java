package ca.mcmaster.se2aa4.island.team207.Power;
import ca.mcmaster.se2aa4.island.team207.Initialize;

public class Battery {
    private Integer battery = 1000;

    // get initial battery level
    public void getInit(String s) {
        Initialize initialize = new Initialize();
        initialize.setInitial(s);
        battery = initialize.getBatteryLevel();
    }
    
    // get battery level
    public Integer getBattery(){
        return battery;
    }
}
