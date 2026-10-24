/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

import java.util.List;


public class MedicalOfficer extends Human {
    
    private String medicalSpecialization;
    private int patientsTreated;

    public MedicalOfficer(String medicalSpecialization, int patientsTreated, String name, int id, String healthStatus, String currentLocation, List<String> skills, int fatigueLevel, boolean available) {
        super(name, id, healthStatus, currentLocation, skills, fatigueLevel, available);
        this.medicalSpecialization = medicalSpecialization;
        this.patientsTreated = patientsTreated;
    }

    


    

    public String getMedicalSpecialization() {
        return medicalSpecialization;
    }

    public int getPatientsTreated() {
        return patientsTreated;
    }

    public void setMedicalSpecialization(String medicalSpecialization) {
        if (medicalSpecialization==null||medicalSpecialization.isBlank()) {
              System.out.println("Error");
    }
        else
        this.medicalSpecialization = medicalSpecialization;
    }

    public void setPatientsTreated(int patientsTreated) {
        if (patientsTreated>=0){
        this.patientsTreated = patientsTreated;   
        }
        else
            System.out.println("Error: patientsTreated cannot be negative.");
        
    }
    
    @Override
    public void performDuty()
    {
        System.out.println("Responding to medical emergencies.");  
    }
    
    public void respondToEmergency() {
    if (!isAvailable())
    {
        System.out.println("Medical Officer is not available.");
        return;
    }

    if (!getHealthStatus().equals("HEALTHY")) {
        System.out.println("Medical Officer is not fit for duty.");
        return;
    }
    
     if (getFatigueLevel()>=75) {
        System.out.println("Medical Officer is too tired.");
        return;
    }


    System.out.println("Responding to a medical emergency.");
    patientsTreated++;
    System.out.println("Medical emergency handled.");
}
    
}
