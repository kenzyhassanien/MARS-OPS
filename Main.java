/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mars.ops;
    import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 *
 * @author Lenovo
 */
public class Main {

    public static void main(String[] args) {

        System.out.println("====================================");
        System.out.println("       MARS-OPS TESTING START       ");
        System.out.println("====================================");

        testSuccessfulExploration();
        testMissionRejection();
        testResourceShortage();
        testOxygenLeakEmergency();
        testDustStormEffect();
        testSimulationTime();

        System.out.println("\n====================================");
        System.out.println("        MARS-OPS TESTING END        ");
        System.out.println("====================================");
    }

    // Scenario 1: Successful Exploration
    public static void testSuccessfulExploration() {

        System.out.println("\n--- SCENARIO 1: SUCCESSFUL EXPLORATION ---");

        MarsColony colony = new MarsColony();

        Scientist scientist = new Scientist(
                "Geology", new ArrayList<String>(),
                "Scientist-1", 1, "HEALTHY", "Habitat",
                Arrays.asList("Geology"), 10, true);

        ExplorationRover rover = new ExplorationRover(
                new ArrayList<String>(Arrays.asList("Camera", "Drill")),
                100, "Rover-1", 2, "HEALTHY", "Garage",
                Arrays.asList("Exploration"), 5, true);

        colony.addAgent(scientist);
        colony.addAgent(rover);

        ExplorationMission mission = new ExplorationMission(
                "North Region", "Geology", 50,
                Arrays.asList("Camera", "Drill"),
                "Explore Mars", "M-01", 2);

        System.out.println("Before execution:");
        System.out.println("Mission status: " + mission.getStatus());
        System.out.println("Scientist available: " + scientist.isAvailable());
        System.out.println("Rover battery: " + rover.getBatteryLevel());

        boolean submitted = colony.submitMission(
                mission, Arrays.<Agent>asList(scientist, rover));

        if (submitted && colony.startActiveMission()) {

            colony.advanceActiveMission();
            colony.advanceActiveMission();
        }

        System.out.println("After execution:");
        System.out.println("Mission status: " + mission.getStatus());
        System.out.println("Mission progress: " + mission.getProgress());
        System.out.println("Scientist available: " + scientist.isAvailable());
        System.out.println("Rover available: " + rover.isAvailable());

        if ("COMPLETED".equals(mission.getStatus())) {
            System.out.println("RESULT: PASS - Exploration completed successfully.");
        } else {
            System.out.println("RESULT: FAIL - " + mission.getFailureReason());
        }
    }

    // Scenario 2: Mission Rejection
    public static void testMissionRejection() {

        System.out.println("\n--- SCENARIO 2: MISSION REJECTION ---");

        MarsColony colony = new MarsColony();

        Scientist scientist = new Scientist(
                "Biology", new ArrayList<String>(),
                "Scientist-2", 3, "HEALTHY", "Habitat",
                Arrays.asList("Biology"), 10, true);

        RepairMission mission = new RepairMission(
                "Communication Unit", 0,
                "Repair Communication Unit", "M-02", 2);

        boolean submitted = colony.submitMission(
                mission, Arrays.<Agent>asList(scientist));

        System.out.println("Mission submitted: " + submitted);
        System.out.println("Mission status: " + mission.getStatus());
        System.out.println("Failure reason: " + mission.getFailureReason());
        System.out.println("Active mission: " + colony.getActiveMission());

        if (!submitted && "FAILED".equals(mission.getStatus())
                && colony.getActiveMission() == null) {

            System.out.println("RESULT: PASS - Unsuitable agent was rejected.");

        } else {
            System.out.println("RESULT: FAIL - Mission rejection did not work correctly.");
        }
    }

    // Scenario 3: Resource Shortage
    public static void testResourceShortage() {

        System.out.println("\n--- SCENARIO 3: RESOURCE SHORTAGE ---");

        MarsColony colony = new MarsColony();

        List<Resources> resources = new ArrayList<Resources>();

        resources.add(new SpareParts("Spare Parts", 2));

        Facility storage = new Facility(
                "Main Storage", 10, true,
                resources, new ArrayList<String>());

        colony.addFacility(storage);

        Engineer engineer = new Engineer(
                "Mechanical", 0, "Engineer-1",
                4, "HEALTHY", "Habitat",
                Arrays.asList("Repair"), 10, true);

        colony.addAgent(engineer);

        RepairMission mission = new RepairMission(
                "Rover Wheel", 5,
                "Repair Rover", "M-03", 2);

        mission.addResourceRequirement(
                new ResourceRequirement("Spare Parts", 5));

        int quantityBefore = colony.getResourceTotal("Spare Parts");

        boolean submitted = colony.submitMission(
                mission, Arrays.<Agent>asList(engineer));

        int quantityAfter = colony.getResourceTotal("Spare Parts");

        System.out.println("Required spare parts: 5");
        System.out.println("Available before: " + quantityBefore);
        System.out.println("Available after: " + quantityAfter);
        System.out.println("Mission submitted: " + submitted);
        System.out.println("Mission status: " + mission.getStatus());
        System.out.println("Failure reason: " + mission.getFailureReason());

        if (!submitted && quantityBefore == quantityAfter
                && quantityAfter >= 0
                && "FAILED".equals(mission.getStatus())) {

            System.out.println("RESULT: PASS - Mission rejected without consuming resources.");

        } else {
            System.out.println("RESULT: FAIL - Resource shortage handling is incorrect.");
        }
    }

    // Scenario 4: Oxygen Leak Emergency
    public static void testOxygenLeakEmergency() {

        System.out.println("\n--- SCENARIO 4: OXYGEN LEAK EMERGENCY ---");

        MarsColony colony = new MarsColony();

        List<Resources> resources = new ArrayList<Resources>();

        resources.add(new Oxygen("Oxygen", 100));
        resources.add(new SpareParts("Spare Parts", 10));
        resources.add(new BatteryPower("Battery Power", 20));

        Habitat habitat = new Habitat(
                "Habitat-A", 20, true,
                resources, new ArrayList<String>());

        colony.addFacility(habitat);

        Engineer engineer = new Engineer(
                "Maintenance", 0, "Engineer-2",
                5, "HEALTHY", "Main Base",
                Arrays.asList("Repair"), 10, true);

        RepairRobots repairRobot = new RepairRobots(
                0, 90, "Repair-Robot-1",
                6, "HEALTHY", "Garage",
                Arrays.asList("Repair"), 5, true);

        colony.addAgent(engineer);
        colony.addAgent(repairRobot);

        OxygenLeak leak = new OxygenLeak(
                10, habitat, "PENDING",
                "Oxygen pressure dropped");

        colony.addEmergency(leak);

        System.out.println("Oxygen before leak: "
                + colony.getResourceTotal("Oxygen"));

        boolean handled = colony.handleOxygenLeak(leak);

        System.out.println("Emergency handled: " + handled);
        System.out.println("Emergency status: " + leak.getStatus());
        System.out.println("Emergency reason: " + leak.getReason());

        System.out.println("Habitat operational: "
                + habitat.isOperationalStatus());

        System.out.println("Oxygen after response: "
                + colony.getResourceTotal("Oxygen"));

        System.out.println("Spare parts remaining: "
                + colony.getResourceTotal("Spare Parts"));

        if (handled && "RESOLVED".equals(leak.getStatus())
                && habitat.isOperationalStatus()) {

            System.out.println("RESULT: PASS - Oxygen leak was repaired.");

        } else {
            System.out.println("RESULT: FAIL - Oxygen leak response was not completed.");
        }
    }

    // Scenario 5: Environmental Effect
    public static void testDustStormEffect() {

        System.out.println("\n--- SCENARIO 5: DUST STORM EFFECT ---");

        MarsColony colony = new MarsColony();

        colony.setEnvironmentCondition("Dust storm");

        Scientist scientist = new Scientist(
                "Geology", new ArrayList<String>(),
                "Scientist-3", 7, "HEALTHY", "Habitat",
                Arrays.asList("Geology"), 10, true);

        ExplorationRover rover = new ExplorationRover(
                new ArrayList<String>(Arrays.asList("Camera")),
                100, "Rover-2", 8, "HEALTHY", "Garage",
                Arrays.asList("Exploration"), 5, true);

        colony.addAgent(scientist);
        colony.addAgent(rover);

        ExplorationMission mission = new ExplorationMission(
                "South Region", "Geology", 50,
                Arrays.asList("Camera"),
                "Dust Storm Exploration", "M-05", 2);

        boolean submitted = colony.submitMission(
                mission, Arrays.<Agent>asList(scientist, rover));

        System.out.println("Environment: "
                + colony.getEnvironment().getCondition());

        System.out.println("Mission submitted: " + submitted);
        System.out.println("Mission status: " + mission.getStatus());
        System.out.println("Failure reason: " + mission.getFailureReason());
        System.out.println("Active mission: " + colony.getActiveMission());

        if (!submitted && "FAILED".equals(mission.getStatus())
                && colony.getActiveMission() == null) {

            System.out.println("RESULT: PASS - Dust storm prevented exploration.");

        } else {
            System.out.println("RESULT: FAIL - Dust storm did not prevent exploration.");
        }
    }

    // Additional Test: Simulation Time
    public static void testSimulationTime() {

        System.out.println("\n--- ADDITIONAL TEST: SIMULATION TIME ---");

        MarsColony colony = new MarsColony();

        List<Resources> resources = new ArrayList<Resources>();

        resources.add(new Oxygen("Oxygen", 100));
        resources.add(new Water("Water", 100));
        resources.add(new Food("Food", 100));

        Facility storage = new Facility(
                "Life Support Storage", 20, true,
                resources, new ArrayList<String>());

        colony.addFacility(storage);

        Simulation simulation = new Simulation(colony, 0);

        System.out.println("Time before: " + simulation.getCurrentTime());

        simulation.advanceTime(1);

        System.out.println("Time after: " + simulation.getCurrentTime());

        System.out.println("Oxygen remaining: "
                + colony.getResourceTotal("Oxygen"));

        System.out.println("Water remaining: "
                + colony.getResourceTotal("Water"));

        System.out.println("Food remaining: "
                + colony.getResourceTotal("Food"));

        if (simulation.getCurrentTime() == 1) {
            System.out.println("RESULT: PASS - Simulation time advanced.");
        } else {
            System.out.println("RESULT: FAIL - Simulation time did not advance.");
        }
    }
}
    

