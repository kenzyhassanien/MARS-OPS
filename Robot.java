/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

import java.util.List;


public abstract class Robot extends Agent{
    private int batteryLevel;

    public Robot(int batteryLevel, String name, int id, String healthStatus, String currentLocation, List<String> skills, int fatigueLevel, boolean available) {
        super(name, id, healthStatus, currentLocation, skills, fatigueLevel, available);
        this.batteryLevel = batteryLevel;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        if(batteryLevel>=0&&batteryLevel<=100)
        this.batteryLevel = batteryLevel;
        else 
            System.out.println("Error: batteryLevel cannot be negative.");
    }
    
    public void recharge() 
    { 
        batteryLevel = 100; System.out.println("Robot battery is fully charged.");
    }
    
    public abstract void performTask();
    
}
