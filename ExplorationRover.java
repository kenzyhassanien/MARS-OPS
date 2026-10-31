/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.List;


public class ExplorationRover extends Robot {
    private List<String> carriedEquipment;

    public ExplorationRover(List carriedEquipment, int batteryLevel, String name, int id, String healthStatus, String currentLocation, List<String> skills, int fatigueLevel, boolean available) {
        super(batteryLevel, name, id, healthStatus, currentLocation, skills, fatigueLevel, available);
        this.carriedEquipment = carriedEquipment;
    }

    public List<String> getCarriedEquipment() {
        return carriedEquipment;
    }
    
    public void carryEquipment(String equipment) {
        if (equipment!=null&&!equipment.isBlank()) {
            carriedEquipment.add(equipment);
        } else {
            System.out.println("Invalid equipment.");
        }
    }
    
    @Override
    public void performTask()
    {
        System.out.println("Exploration Rover is exploring the Martian surface.");
    }
    
    @Override
    public boolean canPerform(Mission mission)
    {
         if(mission instanceof ExplorationMission)
        {
            ExplorationMission exploration = (ExplorationMission) mission;
      
             if(getBatteryLevel() >= exploration.getMinimumBatteryLevel())
             {
                for(String equipment : exploration.getRequiredEquipment())
                {
                    if(getCarriedEquipment().contains(equipment))
                    {}
                    else
                    {
                        return false;
                    }
                }
             }
             else
             {
              return false;
             }
             return true;
        }
         if(mission instanceof RescueMission)
         {
             if(isCommunicationCapable())
                 return true;
         }
        return false;

    }
    
    public String getFailureReason(ExplorationMission mission) {
    if (getBatteryLevel() < mission.getMinimumBatteryLevel()) {
        return "Battery level too low";
    }

    for (String equipment : mission.getRequiredEquipment()) {
        if (!getCarriedEquipment().contains(equipment)) {
            return "Missing equipment: " + equipment;
        }
    }
    return null;
    }
    
    @Override
    public boolean canHandleEmergency(Emergencies emergency)
    {
        return false;
    }
}