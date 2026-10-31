/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Kenzy Hassanen
 */

import java.util.Random;

public class Environment {
    private String condition;
    private Random random = new Random();

    public Environment(String condition) {
        this.condition="Normal";
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

    public void changeEnvironment()
    {
        int environment = random.nextInt(3);
        
        if(environment == 0)
            setCondition("NORMAL");
        else if(environment == 1)
            setCondition("DUST STORM");
        else
            setCondition("COMMUNICATION DISRUPTION");
    }
    
}
