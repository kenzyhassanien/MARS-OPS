/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kenzy Hassanen
 */
public class RescueMission extends Mission {
    private String strandedEntity;
    private String location;
    

    public RescueMission(String strandedEntity, String location, String name, String ID, int duration) {
        super(name, ID, duration);
        this.strandedEntity = strandedEntity;
        this.location = location;
    }

    public String getStrandedEntity() {
        return strandedEntity;
    }

    public String getLocation() {
        return location;
    }
    
    
    
}
