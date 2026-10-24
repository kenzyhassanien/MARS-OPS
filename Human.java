/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

import java.util.List;

public abstract class Human extends Agent{

    public Human(String name, int id, String healthStatus, String currentLocation, List<String> skills, int fatigueLevel, boolean available) {
        super(name, id, healthStatus, currentLocation, skills, fatigueLevel, available);
    }
    
   public abstract void performDuty();
   
}
