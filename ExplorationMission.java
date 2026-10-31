
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kenzy Hassanen
 */
public class ExplorationMission extends Mission{
 private String  targetRegion;
 private String requiredSkill;
 private int minimumBatteryLevel;
 private List<String> requiredEquipment;

    public ExplorationMission(String targetRegion, String requiredSkill, int minimumBatteryLevel, List<String> requiredEquipment, String name, String ID, int duration) {
        super(name, ID, duration);
        this.targetRegion = targetRegion;
        this.requiredSkill = requiredSkill;
        this.minimumBatteryLevel = minimumBatteryLevel;
        this.requiredEquipment = requiredEquipment;
    }

    public String getRequiredSkill() {
        return requiredSkill;
    }

    public int getMinimumBatteryLevel() {
        return minimumBatteryLevel;
    }

    public List<String> getRequiredEquipment() {
        return requiredEquipment;
    }

    public boolean checkRequirements()
    {
        boolean roverFound=false;
        boolean scientistFound=false;
        
       for(Agent agent:getAssignedAgents()) 
       {
         if(agent instanceof ExplorationRover)
         { 
             roverFound=true;
             if(!agent.canPerform(this))
             {
                 ExplorationRover rover=(ExplorationRover) agent;
                 failMission(rover.getFailureReason(this));
                 return false;
             }
         }
          if(agent instanceof Scientist)
             {
                 scientistFound=true;
               if(!agent.canPerform(this))
             {
                 failMission("Scientist lacks the required skill:" + getRequiredSkill());
                 return false;
             }
             }
       }
       if(roverFound==false)
       {
           failMission("No rover was found");
           return false;
       }
       
       if(scientistFound==false)
       {
           failMission("No scientist was found");
           return false;
       }  
       
           return true;
    }

    public String getTargetRegion() {
        return targetRegion;
    }

    public void setTargetRegion(String targetRegion) {
        this.targetRegion = targetRegion;
    }
 
 public String getMissionDetails()
 {
     return "Mission name: " + getName() + " " 
             + "\nTarget region: " + getTargetRegion() 
             + "\nRequired skills: " + getRequiredSkill() + " " 
             + "\nMinimum battery level: " + getMinimumBatteryLevel() + " " 
             + "\nRequired equipment: " + getRequiredEquipment();
 }
 
 public boolean canStart(Environment environment)
 {
     if(environment == null)
     {
         System.out.println("Environment not set");
         return false;
     }
     
   if(environment.isDustStorm())
   {
       System.out.println("Exploration mission cannot start in dust storms");
    return false;
   }
   return true;
 }
 
 public void handleDustStorm()
 {
     if("IN_PROGRESS".equals(getStatus()))
     {
         failMission("Mission failed due to a dust storm");
     }
 }
    
}
