/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kenzy Hassanen
 */
import java.util.ArrayList;
import java.util.List;

public abstract class Mission {
    private String name;
    private String ID;
    private int duration;
    private int progress;
    private String status;
    private String failureReason;
    private List<Agent> assignedAgents= new ArrayList<Agent>();
    private List<ResourceRequirement> resourceRequirements = new ArrayList<>();
    
    public Mission(String name, String ID, int duration) {
        this.name = name;
        this.ID = ID;
        this.duration = duration;
        this.progress=0;
    }
    
    public void addResourceRequirement(ResourceRequirement requirement)
    {
         if(requirement==null)
             return;
         if(resourceRequirements.contains(requirement))
             return;
         resourceRequirements.add(requirement);
    }
    
    public void addAgent(Agent agent)
    {
        if(!(assignedAgents.contains(agent)))
        assignedAgents.add(agent);
    }

    public List<ResourceRequirement> getResourceRequirements() {
        return new ArrayList<>(resourceRequirements);
    }
    
    
    public List<Agent> getAssignedAgents()
    {
        return new ArrayList<>(assignedAgents);
    }

    private void setStatus(String status) {
        
        this.status = status;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
    
    public void failMission(String failureReason)
    {
        if("COMPLETED".equals(status))
            return;
        if("FAILED".equals(status))
            return;
        this.setStatus("FAILED");
        this.setFailureReason(failureReason);
        
    }
    
    public void assignMission()
    {
        if(status==null)
        setStatus("ASSIGNED");
    }

    public void startMission()
    {
       
        if("ASSIGNED".equals(getStatus()))
            setStatus("IN_PROGRESS");
    }
    public String getStatus() {
        return status;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public int getProgress() {
        return progress;
    }

    public int getDuration() {
        return duration;
    }

    public String getName() {
        return name;
    }

    public String getID() {
        return ID;
    }
      
    public int incProgress()
    {
     if("IN_PROGRESS".equals(status))
     {
        if(progress<duration)
        {   progress++;
            if(progress==duration)
            setStatus("COMPLETED");    
        }
     } 
        return progress;
    }
    
    
}
