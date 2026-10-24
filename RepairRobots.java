/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

import java.util.List;


public class RepairRobots extends Robot{
    private int repairedEquipmentCount;

    public RepairRobots(int repairedEquipmentCount, int batteryLevel, String name, int id, String healthStatus, String currentLocation, List<String> skills, int fatigueLevel, boolean available) {
        super(batteryLevel, name, id, healthStatus, currentLocation, skills, fatigueLevel, available);
        this.repairedEquipmentCount = repairedEquipmentCount;
    }

    public int getRepairedEquipmentCount() {
        return repairedEquipmentCount;
    }
    
    
    
    @Override
    public void performTask()
    {
        System.out.println("Repair Robot is operating inside a facility."); 
    }
    
    public void repairEquipment() 
    { 
        repairedEquipmentCount++; 
        System.out.println("Repair Robot is repairing damaged equipment."); 
    }
}
