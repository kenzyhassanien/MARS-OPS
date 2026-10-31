/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;

/**
 *
 * @author Lenovo
 */
public class Simulation {
    
    private MarsColony colony;
    private int currentTime;

    public Simulation(MarsColony colony, int currentTime) {
        this.colony = colony;
        this.currentTime = 0;
    }

    public int getCurrentTime() {
        return currentTime;
    }
    

    public void advanceTime(int units) {
        if (units<=0) 
        {
            System.out.println("Invalid time units.");
            return;
        }

        for (int i=0;i<units;i++)
        {
            Mission mission=colony.getActiveMission();
            
            if (mission!=null && "IN_PROGRESS".equals(mission.getStatus())) {

                boolean advanced=colony.advanceActiveMission();

                if(advanced)
                {
                    for (Agent agent:mission.getAssignedAgents())
                    {
                        agent.setFatigueLevel(Math.min(100, agent.getFatigueLevel() + 5));

                        if (agent instanceof Robot)
                        {

                            Robot robot=(Robot)agent;

                            robot.setBatteryLevel(Math.max(0, robot.getBatteryLevel() - 5));
                        }
                    }
                }
            }

            currentTime++;
            System.out.println("Time: " + currentTime);
        }
    }
}
    
