/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kenzy Hassanen
 */
public class MedicalEmergency extends Emergencies {

    private Human patient;

    public MedicalEmergency(Human patient, Facility affectedFacility, String status, String reason) {
        super(affectedFacility, status, reason);
        this.patient = patient;
    }

    public Human getPatient() {
        return patient;
    }
    
    
    @Override
    public String getEmergencyType()
    {
        return "MEDICAL";
    }
}
