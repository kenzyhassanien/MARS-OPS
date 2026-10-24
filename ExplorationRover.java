/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

import java.util.List;


public class ExplorationRover extends Robot {
    private List<String> carriedEquipment;

    public ExplorationRover(List carriedEquipment, int batteryLevel, String name, int id, String healthStatus, String currentLocation, List<String> skills, int fatigueLevel, boolean available) {
        super(batteryLevel, name, id, healthStatus, currentLocation, skills, fatigueLevel, available);
        this.carriedEquipment = carriedEquipment;
    }

    public List<String> getCarriedEquipment() {
        return carriedEquipment;
    }
    
    public void carryEquipment(String equipment) {
        if (equipment!=null&&!equipment.isBlank()) {
            carriedEquipment.add(equipment);
        } else {
            System.out.println("Invalid equipment.");
        }
    }
    
    @Override
    public void performTask()
    {
        System.out.println("Exploration Rover is exploring the Martian surface.");
    }
    
    
}
