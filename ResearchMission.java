
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kenzy Hassanen
 */
public class ResearchMission extends Mission {
    private String researchTopic;
    private String requiredSkill;
    private List<String> requiredEquipment;
    private List<ResourceRequirement> requiredResources;

    public ResearchMission(String researchTopic, String requiredSkill, List<String> requiredEquipment, List<ResourceRequirement> requiredResources, String name, String ID, int duration) {
        super(name, ID, duration);
        this.researchTopic = researchTopic;
        this.requiredSkill = requiredSkill;
        if (requiredEquipment == null) 
        {
          this.requiredEquipment = new ArrayList<>();
}       else
        {
          this.requiredEquipment = new ArrayList<>(requiredEquipment);
}       if (requiredResources == null) {
          this.requiredResources = new ArrayList<>();
}       else 
        {
          this.requiredResources = new ArrayList<>(requiredResources);
        }
    }

    public List<ResourceRequirement> getRequiredResources() {
        return requiredResources;
    }

    

    public List<String> getRequiredEquipment() {
        return requiredEquipment;
    }

   

    public String getResearchTopic() {
        return researchTopic;
    }

    public String getRequiredSkill() {
        return requiredSkill;
    }
    
    public boolean checkScientistRequirements()
    {

        boolean scientistFound=false;
        
       for(Agent agent:getAssignedAgents()) 
       {
         
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
      
       
       if(scientistFound==false)
       {
           failMission("No scientist was found");
           return false;
       }  
       
           return true;
    }
    
    public boolean hasRequiredEquipment(Laboratories laboratory)
    {
        if(laboratory==null)
            return false;
        
                   for(String item: requiredEquipment)
                   {
                       if(!laboratory.getEquipment().contains(item))
                           return false;
                   }
                   return true;
    }
}
