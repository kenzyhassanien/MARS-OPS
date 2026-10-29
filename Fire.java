/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

import java.util.List;
import java.util.ArrayList;


/**
 *
 * @author Lenovo
 */
public class Fire extends Emergencies {
    

    private boolean fireDetected;
    private int oxygenConsumption;
    private List<Agent> responders;
    private List<Agent> personnelPresent;

    public Fire(boolean fireDetected, int oxygenConsumption, List<Agent> responders, List<Agent> personnelPresent, Facility affectedFacility, String status, String reason) {
        super(affectedFacility, status, reason);
        this.fireDetected = false;
        this.oxygenConsumption = oxygenConsumption;
        this.responders = new ArrayList<>();
        this.personnelPresent = new ArrayList<>();
        if (responders != null)
        {
            this.responders.addAll(responders);
        }
        if (personnelPresent != null)
        {
            this.personnelPresent.addAll(personnelPresent);
        }
        if (affectedFacility == null) 
        {
            System.out.println("Affected facility cannot be null.");
        }
        else if (!(affectedFacility instanceof Laboratories))
        {
            System.out.println("Affected facility must be a laboratory.");
        }
        if (oxygenConsumption <= 0) 
        {
            System.out.println("Oxygen consumption must be positive.");
        }
    }

    public boolean isFireDetected() {
        return fireDetected;
    }

    public int getOxygenConsumption() {
        return oxygenConsumption;
    }

    public List<Agent> getResponders() {
        return responders;
    }

    public List<Agent> getPersonnelPresent() {
        return personnelPresent;
    }

        
   

    public void detectFire() {

        if (fireDetected) {
            System.out.println("Fire has already been detected.");
            return;
        }

        Facility lab = getAffectedFacility();

        if (!(lab instanceof Laboratories)) {
            setStatus("UNRESOLVED");
            setReason("Affected facility must be a laboratory.");
            System.out.println("Affected facility must be a laboratory.");
            return;
        }

        List<Agent> availableResponders = new ArrayList<>();

        for (Agent agent : responders) {
            if (agent != null&& agent.isAvailable()&& agent.getSkills() != null&& (agent.getSkills().contains("Fire")|| agent.getSkills().contains("Fire Response"))) {

                availableResponders.add(agent);
            }
        }

        if (availableResponders.isEmpty()) {
            setStatus("UNRESOLVED");
            setReason("No available fire-response personnel.");
            System.out.println("No available fire-response personnel.");
            return;
        }

        Resources oxygen = lab.findResource("Oxygen");

        if (oxygen == null || oxygen.getQuantity() < oxygenConsumption
                || oxygenConsumption <= 0) {
            setStatus("UNRESOLVED");
            setReason("Not enough oxygen available.");
            System.out.println("Not enough oxygen available.");
            return;
        }

        lab.setOperationalStatus(false);
        oxygen.consumeQuantity(oxygenConsumption);

        for (Agent agent : availableResponders) {
            agent.setAvailable(false);
            agent.setCurrentLocation(lab.getLocation());
            System.out.println("Fire responder assigned: " + agent.getName());
        }

        for (Agent person : personnelPresent) {
            if (person != null) {
                System.out.println(
                        "Medical assistance may be required for: "
                        + person.getName()
                );
            }
        }

        fireDetected = true;
        setStatus("IN_PROGRESS");

        System.out.println("Fire detected in the laboratory.");
        System.out.println("Laboratory is now unavailable.");
        System.out.println("Oxygen consumed: " + oxygenConsumption);
    }

    public void finishEmergency(boolean fireControlled) {

        if (!fireDetected || !"IN_PROGRESS".equals(getStatus())) {
            System.out.println("Emergency is not in progress.");
            return;
        }

        for (Agent agent : responders) {
            if (agent != null && agent.getCurrentLocation() != null && agent.getCurrentLocation().equals(getAffectedFacility().getLocation()))
            {
                agent.setAvailable(true);
            }
        }

        if (fireControlled) {
            setStatus("RESOLVED");
            setReason("");
            System.out.println("Fire has been controlled.");
        } else {
            setStatus("UNRESOLVED");
            setReason("Fire could not be controlled.");
            System.out.println("Fire remains uncontrolled.");
        }
    }
}




