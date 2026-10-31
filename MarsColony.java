
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
public class MarsColony {
    private List<Mission> missions = new ArrayList<>();
    private Mission activeMission;
    private List<Agent> agents = new ArrayList<>();
    private List<Facility> facilities = new ArrayList<>();
    private Environment environment;
    private List<Emergencies> emergencies = new ArrayList<>();
  
    
    public MarsColony()
    {
        environment = new Environment("Normal");
    }
    
    public void addAgent(Agent agent)
    {
      if(agent==null)
          return;
      if(agents.contains(agent))
          return;
      
      agents.add(agent);
    }

    public void addFacility(Facility facility)
    {
     if(facility==null)
         return;
     if(facilities.contains(facility))
         return;
     facilities.add(facility);
    }
    
    public void addEmergency(Emergencies emergency)
    {
        if (emergency == null)
            return;
        if(emergencies.contains(emergency))
            return;
        emergencies.add(emergency);
    }

    public List<Emergencies> getEmergencies() {
        return new ArrayList<>(emergencies);
    }

    public Environment getEnvironment() {
        return environment;
    }
    

    public List<Facility> getFacilities() {
        return new ArrayList<>(facilities);
    }
    
    public int getResourceTotal(String resourceName)
    {
        int total=0;
        for(Facility facility:facilities)
        {
          if(facility.isOperationalStatus()) 
          {
              total += facility.getResourceQuantity(resourceName);
          }
        }
        return total;
    }
    
    public boolean hasEnoughResources(Mission mission)
    {
        if(mission == null)
            return false;
        
        for(ResourceRequirement requirement:mission.getResourceRequirements())
        {
         int totalRequired = 0;  
         for(ResourceRequirement other:mission.getResourceRequirements())
         {
             if(other.getResourceName().equalsIgnoreCase(requirement.getResourceName()))
             {
                 totalRequired += other.getRequiredQuantity();
             }
         }
         if(getResourceTotal(requirement.getResourceName()) < totalRequired)
             return false;
        }
        return true;
    }
    
    
    
public boolean consumeColonyResources(String resourceName, int amount,List<ResourceAllocation> allocations)
{
    if (resourceName == null || resourceName.isBlank() || allocations == null)
        return false;

    if (amount <= 0)
        return false;

    if (getResourceTotal(resourceName) < amount)
        return false;

    int remaining = amount;
    List<ResourceAllocation> consumed = new ArrayList<>();

    for (Facility facility : facilities)
    {
        if (facility.isOperationalStatus())
        {
            int available = facility.getResourceQuantity(resourceName);
            int amountToTake = Math.min(available, remaining);

            if (amountToTake > 0)
            {
                if (facility.consumeResource(resourceName, amountToTake))
                {
                    remaining -= amountToTake;
                    consumed.add(new ResourceAllocation(
                        facility, resourceName, amountToTake));
                }
                else
                {
                    for (ResourceAllocation previous : consumed)
                    {
                        previous.getFacility().addResourceQuantity(
                            previous.getResourceName(),
                            previous.getQuantityToConsume());
                    }
                    return false;
                }
            }
        }

        if (remaining == 0)
        {
            allocations.addAll(consumed);
            return true;
        }
    }

    for (ResourceAllocation previous : consumed)
    {
        previous.getFacility().addResourceQuantity(
            previous.getResourceName(),
            previous.getQuantityToConsume());
    }

    return false;
}

    
    
    public List<Agent> getAgents() {
        return new ArrayList<>(agents);
    }

    public List<Mission> getMissions() {
        return new ArrayList<> (missions);
    }

    public Mission getActiveMission() {
        return activeMission;
    }
    
    public void setEnvironmentCondition(String condition)
    {
        environment.setCondition(condition);
    }
    
    
    private List<ResourceAllocation> planResourceAllocation(Mission mission)
    {
     List<ResourceAllocation> resourceAllocation = new ArrayList<>();
     if(mission == null)
         return null;
     
     for(ResourceRequirement requirement:mission.getResourceRequirements())
     {
         int remaining = requirement.getRequiredQuantity();
         
          for(Facility facility:facilities)
     {
         if(facility.isOperationalStatus())
         {
          int alreadyAllocated =0; 
          for(ResourceAllocation item:resourceAllocation)
          {
              if(item.getFacility() == facility && item.getResourceName().equalsIgnoreCase(requirement.getResourceName()))
              {
                  alreadyAllocated += item.getQuantityToConsume();
              }
          }
          
          int available = facility.getResourceQuantity(requirement.getResourceName());
          available -= alreadyAllocated;
          int amountToTake=Math.min(available, remaining);
          
          
          if(amountToTake > 0)
          {
              resourceAllocation.add(new ResourceAllocation(facility,requirement.getResourceName(),amountToTake));
              remaining -= amountToTake;
          }
         }
         if (remaining == 0)
             break;
     }
           if(remaining > 0)
         return null;
     }
    
     return resourceAllocation;
    
    }
    
    
    public boolean submitMission(Mission mission,List<Agent> agentsToAssign)
    {
        if(mission==null)
            return false;
       
        if(activeMission != null)
        {
            mission.failMission("Another mission is active");
            return false;
        }
        
        if(mission instanceof ExplorationMission && environment.isDustStorm())
       {
            mission.failMission("Exploration cannot be submitted during a dust storm");
            return false;
       }
        
        if(mission instanceof RescueMission && environment.isCommunicationDisruption())
        {
            mission.failMission("Rescue mission cannot be initiated during communication disruption");
            return false;
        }
        
         if(agentsToAssign == null || agentsToAssign.isEmpty())
         { 
             mission.failMission("Invalid agent");
             return false;
         }
  
             
         for(Agent agent:agentsToAssign)
         { 
             if(agent == null)
             {
                 mission.failMission("Invalid agent");
                 return false;
             }
             
             if(!agent.isAvailable())
             {
                 mission.failMission("Agent is not available");
                 return false;
             }
             
             if(!agent.canPerform(mission))
             {
                 mission.failMission("Agent cannot perform this mission");
                 return false;
             }
         }
         
         if(!hasEnoughResources(mission))
         {
             mission.failMission("Insufficient resources for mission");
             return false;
         }
         for(Agent agent:agentsToAssign)
         {
             mission.addAgent(agent);
             agent.setAvailable(false);
         }
         mission.assignMission();
         missions.add(mission);
         activeMission = mission;
         return true;
    }
    
    public boolean startActiveMission()
    {
        if(activeMission == null)
            return false;
        if(!"ASSIGNED".equals(activeMission.getStatus()))
            return false;
        
        if(activeMission instanceof ExplorationMission && environment.isDustStorm())
        {
            activeMission.failMission("Exploration cannot start during a dust storm");
            releaseMissionAgents(activeMission);
            activeMission = null;
            return false;
        }
      
          if(activeMission instanceof RescueMission && environment.isCommunicationDisruption())
        {
            
            activeMission.failMission("Rescue mission cannot be initiated during communication disruption");
            releaseMissionAgents(activeMission);
            activeMission = null;
            return false;
        }
          
        List <ResourceAllocation> allocation = planResourceAllocation(activeMission);
        if(allocation == null)
        {
            activeMission.failMission("Insufficient resources for mission");
            releaseMissionAgents(activeMission);
            activeMission=null;
            return false;
        }
        
        List<ResourceAllocation> consumed = new ArrayList<>();
        for(ResourceAllocation item:allocation)
        {
         if(!item.getFacility().consumeResource(item.getResourceName(),item.getQuantityToConsume()))
         {
          for(ResourceAllocation previous:consumed)
          {
           previous.getFacility().addResourceQuantity(previous.getResourceName(),previous.getQuantityToConsume());
           
          }
          activeMission.failMission("Resource consumption failed");
          releaseMissionAgents(activeMission);
          activeMission=null;
          return false;
         }
         consumed.add(item);
        }
        activeMission.startMission();
        return true;
    }
    
    public void releaseMissionAgents(Mission mission)
    {
        if(mission == null)
            return;
        
        for(Agent agent:mission.getAssignedAgents())
        {
            agent.setAvailable(true);
        }
    }
    
    public boolean advanceActiveMission()
    {
        if(activeMission == null)
            return false;
        
        if(!"IN_PROGRESS".equals(activeMission.getStatus()))
            return false;
        
        if(environment.isDustStorm())
        {
            for(Agent agent : activeMission.getAssignedAgents())
            {
                if(agent instanceof ExplorationRover)
                {
                    activeMission.failMission("Active rover mission failed due to a dust storm");
                    releaseMissionAgents(activeMission);
                    activeMission = null;
                    return false;
                }
            }
        }
        if(environment.isCommunicationDisruption() && activeMission instanceof RescueMission)  
        {
           activeMission.failMission("Active mission failed due to communication disruption");
           releaseMissionAgents(activeMission);
           activeMission=null;
           return false;
        }
        activeMission.incProgress();
        if("FAILED".equals(activeMission.getStatus()))
        {
            releaseMissionAgents(activeMission);
            activeMission = null;
        }
        else if("COMPLETED".equals(activeMission.getStatus()))
        {
            releaseMissionAgents(activeMission);
            activeMission=null;
        }
        return true;
    }
    
    public void consumeTimeResources()
    {
        List<ResourceAllocation> resourcesO2 = new ArrayList<>();
       if(!consumeColonyResources("Oxygen",2,resourcesO2))
            System.out.println("Insufficient Oxygen!");
       
       List<ResourceAllocation> resourcesWater = new ArrayList<>();
       if(!consumeColonyResources("Water",2,resourcesWater))
            System.out.println("Insufficient Water!");
       
       List<ResourceAllocation> resourcesFood = new ArrayList<>();
       if(!consumeColonyResources("Food",1,resourcesFood))
            System.out.println("Insufficient Food!");
    }
    
     public void getColonyStatusReport()
    {
        System.out.println("    Mars Colony Status Report  ");
        for(String resourceName : getAllResourceNames())
        {
            System.out.println(resourceName + ": " +getResourceTotal(resourceName));
            
        }
    }
    
    
       public List<String> getAllResourceNames()
      {
         List<String> names = new ArrayList<>();

            names.add("Oxygen");
            names.add("Water");
            names.add("Food");
            names.add("Fuel");
            names.add("Battery Power");
            names.add("Medical Supplies");
            names.add("Spare Parts");

        return names;
      }
       
    
    public boolean canDroneOperate(Drone drone)
    {
        if(drone == null)
            return false;
        
        if(!agents.contains(drone))
            return false;
        
        if(environment.isCommunicationDisruption())
            return false;
        
        return true;
    }
    
    public boolean operateDrone(Drone drone)
    {
        if(!canDroneOperate(drone))
            return false;
        drone.performTask();
            return true;
    }
    
    public boolean handleOxygenLeak(OxygenLeak emergency)
    {
        Human selectedEngineer=null;
        Robot selectedRobot=null;
        
        if(emergency == null)
            return false;
        
        
        if(!emergencies.contains(emergency))
            return false;
        
        if (!emergency.isLeakDetected()) 
        {
          if (!emergency.detectLeak()) 
          {
          return false;
          }
        }
        
        for(Agent agent:agents)
        {
            if(agent instanceof Human)
            {
                Human engineer = (Human) agent;
             if(engineer.canHandleEmergency(emergency) && engineer.isAvailable() && "HEALTHY".equals(engineer.getHealthStatus()) && engineer.getFatigueLevel() < 75)
              selectedEngineer = engineer;
            }
          
          if(agent instanceof Robot)
          {
              Robot robot = (Robot) agent;
              
              if(robot.canHandleEmergency(emergency) && robot.isAvailable()  && "HEALTHY".equals(robot.getHealthStatus())&& robot.getFatigueLevel() < 75 && robot.getBatteryLevel() > 0)
                  selectedRobot = robot;
          }
           if(selectedEngineer != null && selectedRobot != null)
            break;
        }
       
        
        if(selectedEngineer == null || selectedRobot == null)
        {
            emergency.finishEmergency(false);
            emergency.setReason("Required engineer or repair robot is unavailable.");
            return false;
        }
        
        Habitat habitat = (Habitat) emergency.getAffectedFacility();
        selectedEngineer.setCurrentLocation(habitat.getLocation());
        selectedRobot.setCurrentLocation(habitat.getLocation());

        selectedEngineer.setAvailable(false);
        selectedRobot.setAvailable(false); 
        
        if(getResourceTotal("Spare Parts") < 2 || getResourceTotal("Battery Power") < 5)
        {
            emergency.finishEmergency(false);
            emergency.setReason("Insufficient resources to repair oxygen leak.");

            selectedEngineer.setAvailable(true);
            selectedRobot.setAvailable(true);

            return false;
        }
        
List<ResourceAllocation> sparePartsConsumed = new ArrayList<>();

if (!consumeColonyResources("Spare Parts", 2, sparePartsConsumed))
{
    emergency.finishEmergency(false);
    emergency.setReason("Failed to consume spare parts.");

    selectedEngineer.setAvailable(true);
    selectedRobot.setAvailable(true);

    return false;
}

if (!consumeColonyResources("Battery Power", 5, new ArrayList<>()))
{
    for (ResourceAllocation allocation : sparePartsConsumed)
    {
        allocation.getFacility().addResourceQuantity(
            allocation.getResourceName(),
            allocation.getQuantityToConsume());
    }

    emergency.finishEmergency(false);
    emergency.setReason("Failed to consume battery power.");

    selectedEngineer.setAvailable(true);
    selectedRobot.setAvailable(true);

    return false;
}       
        boolean engineerRepaired = selectedEngineer.repairEquipment();
        boolean robotRepaired = selectedRobot.repairEquipment();

        if (engineerRepaired && robotRepaired) 
        {
           emergency.finishEmergency(true);
        } 
        else 
        {
          emergency.finishEmergency(false);
        }

        selectedEngineer.setAvailable(true);
        selectedRobot.setAvailable(true);

       return "RESOLVED".equalsIgnoreCase(emergency.getStatus());
        }
    
    public boolean handleEmergency(Emergencies emergency,Agent responder )
    {
        if(emergency == null || responder == null)
            return false;
         
        if("RESOLVED".equalsIgnoreCase(emergency.getStatus()))
            return false;
        
        
        if(!emergencies.contains(emergency))
            return false;
        
        if(!(emergency instanceof MedicalEmergency))
            return false;
        
        MedicalEmergency medicalEmergency = (MedicalEmergency) emergency;
        Human patient = medicalEmergency.getPatient();
        
        if(patient == null)
            return false;
        
        Facility facility = emergency.getAffectedFacility();
        
        if(facility == null || !facility.isOperationalStatus())
        {
            emergency.setStatus("UNRESOLVED");
            emergency.setReason("The affected facility is not operational");
            return false;
        } 
        
       
        
        if(!(responder instanceof MedicalOfficer))
            return false;
        MedicalOfficer officer = (MedicalOfficer) responder;
        
        if(!officer.canHandleEmergency(emergency))
            return false;
        
        if(!officer.isAvailable())
        {
            emergency.setStatus("UNRESOLVED");
            emergency.setReason("Medical officer is not available.");
            return false;
        }
        
        if(!"HEALTHY".equalsIgnoreCase(officer.getHealthStatus()))
        {
            emergency.setStatus("UNRESOLVED");
            emergency.setReason("Medical officer is not healthy.");
            return false;
        }
        
        if(officer.getFatigueLevel() >= 75)
        {   
            emergency.setStatus("UNRESOLVED");
            emergency.setReason("Medical officer fatigue level is high.");
            return false;
        }
       
        if(getResourceTotal("Medical Supplies") < 1)
        {
            emergency.setStatus("UNRESOLVED");
            emergency.setReason("Insufficient medical supplies");
            return false;
        } 
        
        List<ResourceAllocation> medicalSuppliesConsumed = new ArrayList<>();

        if (!consumeColonyResources("Medical Supplies", 1, medicalSuppliesConsumed))
       {
         emergency.setStatus("UNRESOLVED");
         emergency.setReason("Failed to consume medical supplies.");
         return false;
       }
        
        if(officer.respondToEmergency())
        { 
           if("CRITICAL".equals(patient.getHealthStatus()))
           patient.setHealthStatus("INJURED");
           else if("INJURED".equals(patient.getHealthStatus()))
           patient.setHealthStatus("HEALTHY");
            
          emergency.setStatus("RESOLVED");
          return true;
        }
        
        for (ResourceAllocation allocation : medicalSuppliesConsumed)
        {
          allocation.getFacility().addResourceQuantity(
          allocation.getResourceName(),
          allocation.getQuantityToConsume());
        }
        
        emergency.setStatus("UNRESOLVED");
        emergency.setReason("Medical officer could not handle the emergency");
        return false;
    }
    
    public boolean handleFireEmergency(Fire fire)
    {
        if(fire == null)
        {
            return false;
        }
        
        if(!emergencies.contains(fire))
        {
            System.out.println("Fire emergency is not registered");
            return false;
        }
        
        fire.detectFire();
        
        return "IN_PROGRESS".equals(fire.getStatus());
    }
    
    public boolean handleRoverBreakdown(RoverBreakdown breakdown)
    {
        if(breakdown == null)
            return false;
        if(!emergencies.contains(breakdown))
            return false;
        
        ExplorationRover rover = breakdown.getRover();
        Mission mission = breakdown.getMission();
        
        if(rover == null || mission == null)
            return false;
        
        breakdown.detectBreakdown();
        releaseMissionAgents(mission);
        rover.setAvailable(false);
        
        if(activeMission == mission)
            activeMission = null;
        
        return "FAILED".equals(mission.getStatus());
    }
    
    public boolean repairRover(RoverBreakdown breakdown)
    {
        if(breakdown == null)
            return false;
        if(!emergencies.contains(breakdown))
            return false;
        
        ExplorationRover rover = breakdown.getRover();
        Mission mission = breakdown.getMission();
        
        if(rover == null || mission == null)
            return false;
        
        if(!"UNRESOLVED".equalsIgnoreCase(breakdown.getStatus()))
            return false;
        
        List <ResourceRequirement> repairRequirements = breakdown.getRepairRequirements();
        
        if(repairRequirements == null || repairRequirements.isEmpty())
         return false;    
        
        for(ResourceRequirement requirement : repairRequirements)
        {
            int totalRequired = 0;
            for(ResourceRequirement other : repairRequirements)
            {
                if(other.getResourceName().equalsIgnoreCase(requirement.getResourceName()))
                {
                    totalRequired += other.getRequiredQuantity();
                }
            }
            if(getResourceTotal(requirement.getResourceName()) < totalRequired)
                return false;
        }
        
        RepairRobots repairRobot = null;
        for(Agent agent:agents)
        {
            if(agent instanceof RepairRobots)
            {
                RepairRobots candidate= (RepairRobots) agent;
                if(candidate.isAvailable() && "HEALTHY".equals(candidate.getHealthStatus()) && candidate.getFatigueLevel() < 75 && candidate.getBatteryLevel() > 0)
                {
                    repairRobot = candidate;
                    break;
                }
            }
        }
        if(repairRobot == null)
            return false;
        
        List<ResourceAllocation> consumedResources = new ArrayList<>();

     for(ResourceRequirement requirement : repairRequirements)
   {
    if(!consumeColonyResources(requirement.getResourceName(),requirement.getRequiredQuantity(),consumedResources))
    {
        for(ResourceAllocation allocation : consumedResources)
        {
            allocation.getFacility().addResourceQuantity(
                allocation.getResourceName(),
                allocation.getQuantityToConsume());
        }

        return false;
    }
    }
     
     repairRobot.setAvailable(false);
     boolean repaired = repairRobot.repairEquipment();
     repairRobot.setAvailable(true);
     
     if(!repaired)
     {
          for (ResourceAllocation allocation : consumedResources)
            {
                allocation.getFacility().addResourceQuantity(
                    allocation.getResourceName(),
                    allocation.getQuantityToConsume());
            }

            return false;
     }
     
     rover.setAvailable(true);
     breakdown.setStatus("RESOLVED");
     breakdown.setReason("Rover repaired successfully");
     return true;
    } 
    
    
}
