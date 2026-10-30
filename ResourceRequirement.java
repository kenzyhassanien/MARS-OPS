/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kenzy Hassanen
 */
public class ResourceRequirement {
    private String resourceName;
    private int requiredQuantity;

    public ResourceRequirement(String resourceName, int requiredQuantity) {
        if (resourceName != null && !resourceName.isBlank() && requiredQuantity > 0)
        {
            this.resourceName = resourceName;
            this.requiredQuantity = requiredQuantity;
        }
        else
        {
            this.resourceName= "";
            this.requiredQuantity = 0;
            System.out.println("Invalid resource name or required quantity");
        }
        
    }

    public String getResourceName() {
        return resourceName;
    }

    public int getRequiredQuantity() {
        return requiredQuantity;
    }
    
    
}
