/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kenzy Hassanen
 */
public class Environment {
    private String condition;

    public Environment(String condition) {
        this.condition="Normal";
        setCondition(condition);
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        if(condition != null)
        {
        if(condition.equalsIgnoreCase("Normal") || condition.equalsIgnoreCase("Dust storm") || condition.equalsIgnoreCase("Communication disruption") )
        this.condition = condition;
        else
        {
            System.out.println("Invalid enviromental condition");
            return;
        }
        }
        else
        {
            System.out.println("Enter a valid condition");
            return;
        }
        
    }   
    public boolean isDustStorm()
    {
        if(condition==null)
        {
            System.out.println("Invalid condition");
            return false;
        }
        else
        {
            return condition.equalsIgnoreCase("Dust storm");
              
        }
    }
    
     public boolean isCommunicationDisruption()
    {
        if(condition==null)
        {
            System.out.println("Invalid condition");
            return false;
        }
        else
        {
            return condition.equalsIgnoreCase("Communication disruption");
                
        }
    }

    
}
