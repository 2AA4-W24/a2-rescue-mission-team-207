package ca.mcmaster.se2aa4.island.team207.Power;
import ca.mcmaster.se2aa4.island.team207.Initialize;

public class Battery {
    private Integer battery = 1000;

    public void getInit(String s) {
        Initialize initialize = new Initialize();
        initialize.initialize(s);
        setBattery(initialize.getBatteryLevel());
    }

    private void setBattery(Integer battery){
        this.battery = battery;
    }
    
    private Integer getBattery(){
        return this.battery;
    }

    public Integer useBattery(){
        return getBattery();
    }
}
