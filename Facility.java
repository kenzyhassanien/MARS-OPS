/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lenovo
 */
public class Facility {
    
    private String location;
    private int capacity;
    private boolean operationalStatus;
    private List<Resources> resources;
    private List<String> equipment;
    
    public Facility(String location, int capacity, boolean operationalStatus, List<Resources> resources, List<String> equipment) {
       this.location = location;
       this.capacity = capacity;
       this.operationalStatus = operationalStatus;
       if (resources == null)
       {
           this.resources = new ArrayList<>();
       }
       else
       {
           this.resources = new ArrayList<>(resources);
       }
       if (equipment == null)
       {
           this.equipment = new ArrayList<>(); 
       }
       else
       {
           this.equipment = new ArrayList<>(equipment);
       }
    }

    public String getLocation() {
        return location;
    }

    public int getCapacity() {
        return capacity;
    }

    public boolean isOperationalStatus() {
        return operationalStatus;
    }

    public List<Resources> getResources() {
        return resources;
    }

    public List<String> getEquipment() {
        return equipment;
    }
    
    public void setLocation(String location) {
        if (location == null || location.isBlank()) {
            System.out.println("Location cannot be empty."); 
        }
        else
        this.location = location;
    }

    public void setCapacity(int capacity) {
        if (capacity <= 0) {
         System.out.println("Capacity cannot be negative."); 
        }
        else
        this.capacity = capacity;
    }

    public void setOperationalStatus(boolean operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public void setEquipment(List<String> equipment)
    {
        if (equipment == null)
        {
            System.out.println("Equipment list cannot be null.");
        }
        else
        {
            this.equipment = new ArrayList<>(equipment);
        }
    }  public void addResource(Resources resource)
    {
        if (resource == null)
        {
            System.out.println("Resource cannot be null.");
        }
        else
        {
            resources.add(resource);
        }
    }  
    public void addEquipment(String item)
    {
        if (item == null || item.isBlank())
        {
            System.out.println("Equipment name cannot be empty.");
        }
        else
        {
            equipment.add(item);
        }
    }  public Resources findResource(String resourceName)
    {
        if (resourceName == null || resourceName.isBlank())
        {
            return null;
        }
        for (Resources resource : resources)
        { 
            if (resource.getName().equalsIgnoreCase(resourceName))
            {
                return resource;
            }
        }
        return null;
    } 
    public boolean addResourceQuantity(String resourceName, int amount)
    {
        if (amount <= 0)
        {
            return false;
        }
        Resources resource = findResource(resourceName);
        if (resource == null)
        {
            return false;
        }
        resource.addQuantity(amount);
        return true;
    }
    public boolean consumeResource(String resourceName, int amount)
    {
        if (resourceName == null || amount <= 0)
        {
            return false;
        }
        Resources resource = findResource(resourceName);
        if (resource == null)
        {
            return false;
        }
        return resource.consumeQuantity(amount);
    }


}



      

