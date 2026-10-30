/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class RoverBreakdown extends Emergencies {
    
    private ExplorationRover rover;
    private Mission mission;
    private String breakdownLocation;
    private List<ResourceRequirement> repairRequirements;

    public RoverBreakdown(ExplorationRover rover, Mission mission, String breakdownLocation, List repairRequirements, Facility affectedFacility, String status, String reason) {
        super(affectedFacility, "PENDING", "");
        
        if (rover == null || mission == null) {
            System.out.println("Rover and Mission cannot be null.");
            return;
        }
        
        
        
        this.rover = rover;
        this.mission = mission;
        this.breakdownLocation = breakdownLocation;
        
        
        this.repairRequirements = new ArrayList<>();
        
         if (repairRequirements != null) {
            this.repairRequirements.addAll(repairRequirements);
        }
         
    }

    public ExplorationRover getRover() {
        return rover;
    }

    public Mission getMission() {
        return mission;
    }

    public String getBreakdownLocation() {
        return breakdownLocation;
    }

    public List<ResourceRequirement> getRepairRequirements() {
        return repairRequirements;
    }

    public Facility getAffectedFacility() {
        return affectedFacility;
    }
    
    
     public void detectBreakdown() {

        if ("RESOLVED".equals(getStatus())) 
        {
            return;
        }

        rover.setCurrentLocation(breakdownLocation);


        rover.setAvailable(false);


        mission.failMission("Rover breakdown");

        setStatus("UNRESOLVED");
        setReason("The rover broke down at " + breakdownLocation);

        System.out.println("Rover breakdown detected.");
        System.out.println("Rover location: " + breakdownLocation);
        System.out.println("Mission status: " + mission.getStatus());
    
    
    
 }
}
