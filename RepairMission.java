/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kenzy Hassanen
 */
public class RepairMission extends Mission{
    
    private String target;
    private int requiredSpareParts;

    public RepairMission(String target, int requiredSpareParts, String name, String ID, int duration) {
        super(name, ID, duration);
        this.target = target;
        if(requiredSpareParts >= 0)
        {
            this.requiredSpareParts=requiredSpareParts;
        }
        else
        {
            System.out.println("Required spare parts cannot be negative ");
            this.requiredSpareParts=0;
        }
    }

    public int getRequiredSpareParts() {
        return requiredSpareParts;
    }

    public String getTarget() {
        return target;
    }
    
     public boolean checkRequirements()
    {
        boolean engineerFound=false;
        boolean repairRobotFound=false;
        
       for(Agent agent:getAssignedAgents()) 
       {
         if(agent instanceof RepairRobots)
         { 
             repairRobotFound=true;
             if(!agent.canPerform(this))
             {
                 failMission("Repair robot cannot perform this mission");
                 return false;
             }
         }
          if(agent instanceof Engineer)
             {
                 engineerFound=true;
               if(!agent.canPerform(this))
             {
                 failMission("Engineer can't perform this mission");
                 return false;
             }
             }
       }
       if(repairRobotFound==false)
       {
           failMission("No robot was found");
           return false;
       }
       
       if(engineerFound==false)
       {
           failMission("No engineer was found");
           return false;
       }  
       
           return true;
    }
    
}
