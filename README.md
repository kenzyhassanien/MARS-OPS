# MARS-OPS --- Mars Colony Operations Simulation

## 1. Project Overview

MARS-OPS is a Java-based simulation of a colony operating on Mars. The
application models the colony's personnel, robots, facilities,
resources, missions, environmental conditions, emergencies, and the
passage of simulation time.

The project demonstrates object-oriented programming concepts including
inheritance, abstraction, encapsulation, polymorphism, and relationships
between objects.

## 2. Main System Components

### 2.1 Colony Management

`MarsColony` is the central class. It stores and manages: - Agents
(human personnel and robots). - Facilities. - Missions and the currently
active mission. - Emergencies. - The colony's environment.

It also provides operations for mission submission, mission starting and
progress, resource totals and allocation, time-based resource
consumption, and emergency handling.

### 2.2 Personnel and Robotic Units

-   `Agent` is the abstract base class for colony agents.
-   `Human` is an abstract subclass of `Agent`.
-   Human subclasses include `Scientist`, `Engineer`, and
    `MedicalOfficer`.
-   `Robot` is an abstract subclass of `Agent`.
-   Robot subclasses include `ExplorationRover`, `RepairRobots`, and
    `Drone`.

Agents have information such as name, ID, health status, current
location, skills, fatigue level, and availability. Robots also have a
battery level.

### 2.3 Facilities

`Facility` represents a location or operational unit in the colony. It
stores its location, capacity, operational status, resources, and
equipment.

Facility subclasses in the project are: - `Habitat` - `Laboratories` -
`StorageFacilities` - `PowerStations` - `CommunicationStations`

### 2.4 Resources

`Resources` is the abstract parent class for the colony's resource
types: - `Oxygen` - `Water` - `Food` - `Fuel` - `BatteryPower` -
`SpareParts`

Resources have a name and quantity. The resource class validates
quantities and supports adding and consuming quantities. Resource
consumption is designed to prevent quantities from becoming negative.

### 2.5 Missions

`Mission` is the abstract base class for: - `ExplorationMission` -
`RepairMission` - `ResearchMission` - `RescueMission`

A mission stores its name, ID, duration, progress, status, failure
reason, assigned agents, and resource requirements.

The mission lifecycle used by the application includes: - `ASSIGNED` -
`IN_PROGRESS` - `COMPLETED` - `FAILED`

A mission is submitted with suitable available agents. The colony checks
agent capability, environmental restrictions, and resource availability
before allowing the mission to proceed.

### 2.6 Emergencies

`Emergencies` is the abstract parent class for: - `OxygenLeak` -
`Fire` - `RoverBreakdown` - `MedicalEmergency`

Emergency objects store the affected facility, status, and reason.
Specific emergency subclasses implement their own detection and response
behavior.

### 2.7 Environment

`Environment` stores the current environmental condition. The supported
conditions are: - `Normal` - `Dust storm` - `Communication disruption`

Environmental conditions affect supported operations. For example, an
exploration mission is rejected during a dust storm, and a rescue
mission is restricted during communication disruption.

The `changeEnvironment()` method randomly selects one of the supported
conditions when called.

### 2.8 Simulation

`Simulation` manages the passage of time through
`advanceTime(int units)`.

For each time unit, the current implementation: - Consumes 2 units of
Oxygen. - Consumes 2 units of Water. - Consumes 1 unit of Food. -
Changes the environmental condition randomly. - Advances the active
mission when one is in progress. - Increases the fatigue of agents
assigned to an advancing mission by 5, up to the allowed maximum. -
Decreases the battery level of assigned robots by 5, without going below
zero. - Increments and prints the simulation time.

## 3. Resource Management Rules

1.  The colony calculates a resource total across operational
    facilities.
2.  A mission's resource requirements are checked before the mission is
    accepted.
3.  When a mission starts, the colony plans the required resource
    allocation across operational facilities.
4.  The mission is rejected if the available quantity is insufficient.
5.  Resource quantities must not become negative.
6.  If a resource-consumption operation fails after partial consumption,
    the implementation attempts to restore the quantities already
    consumed.
7.  During each simulation time unit, the colony attempts to consume
    Oxygen, Water, and Food according to the rates described in Section
    2.8.

## 4. Mission Management Rules

1.  A mission is submitted with a list of agents.
2.  The colony rejects a mission if another mission is already active.
3.  The colony checks that the supplied agents are valid, available, and
    capable of performing the mission.
4.  The colony checks environmental restrictions and resource
    availability.
5.  If submission succeeds, the mission becomes assigned and is stored
    as the active mission.
6.  `startActiveMission()` allocates and consumes the required resources
    and changes the mission status to `IN_PROGRESS`.
7.  `advanceActiveMission()` increases mission progress.
8.  When a mission is completed or fails, the assigned agents are
    released and become available again.

## 5. Environmental Effects

The application supports three environmental conditions:

-   **Normal:** No special environmental restriction is applied by the
    colony's mission checks.
-   **Dust storm:** Exploration missions are rejected at submission or
    start. An active mission involving an exploration rover can also
    fail if a dust storm is encountered during progress.
-   **Communication disruption:** Rescue missions are restricted. Drone
    operation is also prevented while communication is disrupted.

The environment can be set directly through
`MarsColony.setEnvironmentCondition(String)` or changed randomly by
`Environment.changeEnvironment()`.

## 6. Emergency Handling

The project includes four emergency types:

-   **Oxygen Leak:** Detects an oxygen leak in a habitat, consumes the
    configured oxygen loss, and can finish as resolved or unresolved.
    The colony also contains a response method that searches for an
    available suitable human and repair robot and checks repair
    resources.
-   **Fire:** Models a fire affecting a facility and stores responder
    and personnel information.
-   **Rover Breakdown:** Models a damaged exploration rover, its
    mission, breakdown location, and repair requirements.
-   **Medical Emergency:** Models a patient who needs medical assistance
    and is handled through the colony's medical-emergency operation.

Emergency handling depends on the specific emergency type and the
availability of suitable agents, facilities, and resources.

## 7. Testing and Demonstration

Run the `Main` class to execute the demonstration tests included with
this project.

  -----------------------------------------------------------------------
  Test                                Purpose
  ----------------------------------- -----------------------------------
  Scenario 1 --- Successful           Creates a scientist, an exploration
  Exploration                         rover, and an exploration mission;
                                      submits and starts the mission;
                                      advances it to completion; displays
                                      the state before and after
                                      execution.

  Scenario 2 --- Mission Rejection    Submits an exploration mission with
                                      an unsuitable engineer and displays
                                      the rejection status and reason.

  Scenario 3 --- Resource Shortage    Attempts a mission requiring more
                                      Oxygen than the colony has; checks
                                      that the mission is rejected and
                                      the Oxygen quantity remains
                                      unchanged and non-negative.

  Scenario 4 --- Oxygen Leak          Detects an oxygen leak in a
  Emergency                           habitat, applies the oxygen loss,
                                      finishes the emergency as repaired,
                                      and displays the final emergency
                                      and facility state.

  Scenario 5 --- Environmental Effect Sets the environment to a dust
                                      storm and demonstrates that an
                                      exploration mission is rejected.

  Additional --- Simulation Time      Advances the simulation by two time
                                      units and displays the resulting
                                      time, resource quantities, and
                                      environmental condition.
  -----------------------------------------------------------------------

### Important testing note

The current `Main` demonstration for Scenario 4 directly calls
`detectLeak()` and `finishEmergency(true)`. It demonstrates leak
detection, oxygen loss, and emergency resolution. The separate
`MarsColony.handleOxygenLeak()` method contains the responder-selection
and repair-resource workflow; that full responder workflow is not
exercised by this particular `Main` test.

The environment changes randomly during simulation time advancement.
Therefore, the final environmental condition in the additional
simulation test may differ between runs.

## 8. How to Compile and Run

### Option A --- NetBeans

1.  Open the MARS-OPS project in NetBeans.
2.  Make sure all `.java` files are included in the same source package
    used by the project.
3.  Set `Main` as the Main Class if NetBeans does not select it
    automatically.
4.  Select **Run Project**.

### Option B --- Command Line

If all Java source files are in the same directory and no external
libraries are required:

``` bash
javac *.java
java Main
```

The program prints each scenario's output to the console.

## 9. UML Design

The project UML deliverables are provided separately in
`MARS-OPS-UML.drawio`. The file is intended to be opened and edited
using diagrams.net (draw.io).

The required diagram types are: 1. Class Diagram 2. Object Diagram 3.
State Machine Diagram 4. Sequence Diagram 5. Use Case Diagram

The diagrams should be kept consistent with the final Java source code.

## 10. Project File Structure

The project contains Java source files for: - Colony and simulation
management. - Agents, human personnel, and robots. - Facilities and
resources. - Missions and resource requirements. - Emergency types. -
Environment management. - `Main.java` for testing and demonstration.

## 11. Design Assumptions and Notes

-   The colony maintains one active mission at a time.
-   Only operational facilities contribute to the colony's available
    resource totals.
-   Mission resources are consumed when the mission starts, not on every
    mission-progress step.
-   Time-based Oxygen, Water, and Food consumption occurs once per
    simulation time unit.
-   Environmental changes are random when `changeEnvironment()` is
    invoked.
-   The rates and random environmental behavior are implementation
    choices in this project.

## 12. How to Run the Required Demonstrations

Run `Main` and review the console output in order. Each scenario is
printed with a heading so that its behavior can be identified.

For a repeatable successful exploration demonstration, Scenario 1
advances the mission directly through the colony's mission-progress
method rather than using the random environment-change operation in
`Simulation`. The additional time test uses `Simulation.advanceTime(2)`
and may therefore produce a different environmental condition on each
run.
