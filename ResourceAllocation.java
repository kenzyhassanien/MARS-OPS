/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kenzy Hassanen
 */
public class ResourceAllocation {
    private Facility facility;
    private String resourceName;
    private int quantityToConsume;

    public ResourceAllocation(Facility facility, String resourceName, int quantityToConsume) {
        this.facility = facility;
        this.resourceName = resourceName;
        this.quantityToConsume = quantityToConsume;
    }

    public Facility getFacility() {
        return facility;
    }

    public String getResourceName() {
        return resourceName;
    }

    public int getQuantityToConsume() {
        return quantityToConsume;
    }
    
    
    
}
