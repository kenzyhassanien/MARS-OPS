/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

/**
 *
 * @author Lenovo
 */
public abstract class Emergencies {
    
    protected Facility affectedFacility;
    private String status;
    private String reason;

    public Emergencies(Facility affectedFacility, String status, String reason) {
        this.affectedFacility = affectedFacility;
        this.status = status;
        this.reason = reason;
    }

    public Facility getAffectedFacility() {
        return affectedFacility;
    }

    public String getStatus() {
        return status;
    }

    public String getReason() {
        return reason;
    }


    public void setStatus(String status) {
        if (status == null || status.isBlank())
        {
            System.out.println("Emergency status cannot be empty.");
        }
        else
        {
            this.status = status;
        }
    }

    public void setReason(String reason) {
       if (reason == null)
       {
           System.out.println("Emergency reason cannot be null.");
       }
       else
       {
           this.reason = reason;
       }
    }
}

