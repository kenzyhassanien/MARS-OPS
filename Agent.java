/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package mars.ops;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author Lenovo
 */
public class Agent {

    private String name;
    private int id;
    private String healthStatus;
    private String currentLocation;
    private List<String> skills;
    private int fatigueLevel;
    private boolean available;

    public Agent(String name, int id, String healthStatus, String currentLocation, List<String> skills, int fatigueLevel, boolean available) {
        this.name = name;
        this.id = id;
        this.healthStatus = healthStatus;
        this.currentLocation = currentLocation;
        this.skills = skills;
        if(fatigueLevel>=0||fatigueLevel<=100)
            this.fatigueLevel = fatigueLevel;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public List<String> getSkills() {
        return skills;
    }

    public int getFatigueLevel() {
        return fatigueLevel;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setName(String name) {
        if(name==null||name.equals(""))
       {
           System.out.println("Error");
       }
       else
        this.name = name;
    }

    public void setId(int id) {
        if(id<=0)
            System.out.println("Error");
        else   
            this.id = id;
    }

    public void setHealthStatus(String healthStatus) {
        if(healthStatus== null)
            return;
        if(healthStatus.equals("HEALTHY")||healthStatus.equals("INJURED")||healthStatus.equals("CRITICAL")||healthStatus.equals("DEAD"))
            this.healthStatus = healthStatus;
        else 
            System.out.println("Error");
    }

    public void setCurrentLocation(String currentLocation) {
        if(currentLocation==null||currentLocation.equals(""))
       {
           System.out.println("Error");
       }
       else
        this.currentLocation = currentLocation;
    }

    public void addSkill(String skill ) {
        if (skill!=null&&!skill.isBlank()) {
            skills.add(skill);
        } else {
            System.out.println("Invalid skill.");
        }
    }

    public void setFatigueLevel(int fatigueLevel) {
        if(fatigueLevel>=0&&fatigueLevel<=100)
            this.fatigueLevel = fatigueLevel;
        else
            System.out.println("Error");
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    
    
}
