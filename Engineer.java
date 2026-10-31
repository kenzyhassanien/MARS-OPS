/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


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
   
    @Override
   public boolean repairEquipment() {

    if (!"HEALTHY".equals(getHealthStatus())) 
    {
        System.out.println("Engineer is not healthy enough to work.");
        return false;
    }
    
    if (getFatigueLevel()>=75) {
        System.out.println("Engineer is too tired.");
        return false;
    }

    System.out.println("Engineer is repairing damaged equipment.");
    repairCount++;
    System.out.println("Repair completed successfully.");
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