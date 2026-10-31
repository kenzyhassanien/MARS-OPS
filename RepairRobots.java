/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


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
    
    @Override
    public boolean repairEquipment() 
    { 
       
        if(!("HEALTHY".equals(getHealthStatus())))
        {
             System.out.println("Repair robot is not healthy.");
            return false;
        }
        if(getFatigueLevel() >= 75)
        {
             System.out.println("Repair robot is too fatigued.");
            return false;
        }
        if(getBatteryLevel() <= 0)
        {
            System.out.println("Repair robot has no battery");
            return false;
        }
        repairedEquipmentCount++; 
        System.out.println("Repair Robot is repairing damaged equipment.");
        return true;
    }
    
    @Override
public boolean canPerform(Mission mission)
{
    return mission instanceof RepairMission;
}

    @Override
    public boolean canHandleEmergency(Emergencies emergency)
    {
        if(emergency == null)
            return false;
        return emergency instanceof OxygenLeak;
    }
}