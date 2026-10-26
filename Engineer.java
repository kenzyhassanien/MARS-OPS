/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

import java.util.List;


public class Engineer extends Human {
    
    private String specialization;
    private int repairCount;

    public Engineer(String specialization, int repairCount, String name, int id, String healthStatus, String currentLocation, List<String> skills, int fatigueLevel, boolean available) {
        super(name, id, healthStatus, currentLocation, skills, fatigueLevel, available);
        setSpecialization(specialization);
        setRepairCount(repairCount);
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getRepairCount() {
        return repairCount;
    }

    public void setSpecialization(String specialization) {
        if (specialization==null||specialization.isBlank()) {
              System.out.println("Error");
    }
        else
        this.specialization = specialization;
    }

    public void setRepairCount(int repairCount) {
        if (repairCount>=0){
        this.repairCount = repairCount;     
        }
        else
            System.out.println("Error: Repair count cannot be negative.");
    }
    
    @Override
    public void performDuty()
    {
        System.out.println("Repairing damaged equipment.");   
    }
   
   public void repairEquipment() {
    if (!isAvailable())
    {
        System.out.println("Engineer is not available.");
        return;
    }

    if (!"HEALTHY".equals(getHealthStatus())) 
    {
        System.out.println("Engineer is not healthy enough to work.");
        return;
    }
    
    if (getFatigueLevel()>=75) {
        System.out.println("Engineer is too tired.");
        return;
    }

    System.out.println("Engineer is repairing damaged equipment.");
    repairCount++;
    System.out.println("Repair completed successfully.");
}
    
    
    
}
