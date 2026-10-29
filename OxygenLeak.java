/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

/**
 *
 * @author Lenovo
 */
public class OxygenLeak extends Emergencies{
    private int oxygenLoss;
    private boolean leakDetected;

    public OxygenLeak(int oxygenLeak, boolean leakDetected, Facility affectedFacility, String status, String reason) {
        super(affectedFacility, status, reason);
        if (affectedFacility == null) {
            System.out.println("Affected facility cannot be null.");
            return;
        }

        if (!(affectedFacility instanceof Habitat)) {
            System.out.println("Affected facility must be a habitat.");
            return;
        }

        if (oxygenLoss <= 0) {
            System.out.println("Oxygen loss must be positive.");
            return;
        }

        this.oxygenLoss = oxygenLoss;
        this.leakDetected = false;
    }

    public int getOxygenLoss() {
        return oxygenLoss;
    }

    public boolean isLeakDetected() {
        return leakDetected;
    }
    

   
    public void setOxygenLoss(int oxygenLoss) {
        if (oxygenLoss <= 0) {
            System.out.println("Oxygen loss must be positive.");
        } else {
            this.oxygenLoss = oxygenLoss;
        }
    }

    public boolean detectLeak() {

        if (leakDetected) {
            System.out.println("Leak has already been detected.");
            return false;
        }

        Facility affectedFacility = getAffectedFacility();

        if (!(affectedFacility instanceof Habitat)) {
            System.out.println("Affected facility must be a habitat.");
            return false;
        }

        if (!affectedFacility.isOperationalStatus()) {
            System.out.println("Habitat is not operational.");
            return false;
        }

        Resources oxygen = affectedFacility.findResource("Oxygen");

        if (oxygen == null) {
            System.out.println("Oxygen resource not found.");
            return false;
        }

        int actualLoss;

        if (oxygen.getQuantity() < oxygenLoss) {
            actualLoss = oxygen.getQuantity();
        } 
        else
        {
            actualLoss = oxygenLoss;
        }

        if (actualLoss > 0) {
            oxygen.consumeQuantity(actualLoss);
        }

        leakDetected = true;
        setStatus("IN_PROGRESS");

        System.out.println("Oxygen leak detected.");
        System.out.println("Oxygen lost: " + actualLoss);

        return true;
    }

    public boolean finishEmergency(boolean repaired) {

        if (!leakDetected || !"IN_PROGRESS".equals(getStatus())) {
            System.out.println(
                "Emergency must be in progress before finishing."
            );
            return false;
        }

        if (repaired) {
            setStatus("RESOLVED");
            setReason("");

            System.out.println("Oxygen leak repaired.");
            return true;
        }

        Facility affectedFacility = getAffectedFacility();
        Resources oxygen = affectedFacility.findResource("Oxygen");

        if (oxygen != null) {
            int actualLoss;

            if (oxygen.getQuantity() < oxygenLoss) {
                actualLoss = oxygen.getQuantity();
            } else {
                actualLoss = oxygenLoss;
            }

            if (actualLoss > 0) {
                oxygen.consumeQuantity(actualLoss);
            }

            System.out.println("Additional oxygen lost: " + actualLoss);
        }

        setStatus("UNRESOLVED");
        setReason("Oxygen leak could not be repaired.");

        System.out.println("Oxygen leak remains unresolved.");

        return true;
    }
}
    
    
    
    



