/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Scientist extends Human {
    private String researchField;
    private List<String> researchProjects;

    public Scientist(String researchField, List<String> researchProjects, String name, int id, String healthStatus, String currentLocation, List<String> skills, int fatigueLevel, boolean available) {
        super(name, id, healthStatus, currentLocation, skills, fatigueLevel, available);
        this.researchField = researchField;
        this.researchProjects = researchProjects;
    }

    public String getResearchField() {
        return researchField;
    }

    public List<String> getResearchProjects() {
        return researchProjects;
    }

    public void setResearchField(String researchField) {
        if (researchField==null||researchField.isBlank()) {
              System.out.println("Error");
    }
        else
        this.researchField = researchField;
    }

    public void setResearchProjects(List<String> researchProjects) {
        for(String researchProject:researchProjects) {
          if (researchProject==null||researchProject.isBlank()) {
              System.out.println("Error");
    }
}
        this.researchProjects = researchProjects;
    }

    
    
    @Override
    public void performDuty()
    {
        System.out.println("conduct scientific research and experiments to support the mission's objectives");
    }
    
    public boolean conductResearch() {
    if (!isAvailable()) {
        System.out.println("Scientist is not available.");
        return false;
    }

    if (!"HEALTHY".equals(getHealthStatus())) {
        System.out.println("Scientist is not healthy.");
        return false;
    }

    if (getFatigueLevel()>=75) {
        System.out.println("Scientist is too tired.");
        return false;
    }

    System.out.println("Conducting scientific research.");
    return true;
}
    @Override
    public boolean canPerform(Mission mission)
    {
        if(mission instanceof ExplorationMission)
        {
            ExplorationMission exploration = (ExplorationMission) mission;
            return getSkills().contains(exploration.getRequiredSkill());
        }
        
        if(mission instanceof ResearchMission)
        {
            ResearchMission research = (ResearchMission) mission;
            return getSkills().contains(research.getRequiredSkill());
        }
        return false;
    }
    
    @Override
    public boolean canHandleEmergency(Emergencies emergency)
    {
        return false;
    }
}