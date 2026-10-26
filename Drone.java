/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

import java.util.List;


public class Drone extends Robot {
    private int reconnaissanceMissions;

    public Drone(int reconnaissanceMissions, int batteryLevel, String name, int id, String healthStatus, String currentLocation, List<String> skills, int fatigueLevel, boolean available) {
        super(batteryLevel, name, id, healthStatus, currentLocation, skills, fatigueLevel, available);
        this.reconnaissanceMissions = reconnaissanceMissions;
    }

    public int getReconnaissanceMissions() {
        return reconnaissanceMissions;
    }
    
    @Override
    public void performTask()
    {
        System.out.println("Drone is exploring areas."); 
        performAerialReconnaissance();
    }
    
    public void performAerialReconnaissance() 
    { reconnaissanceMissions++; 
    System.out.println("Drone is performing aerial reconnaissance."); 
    }
}
