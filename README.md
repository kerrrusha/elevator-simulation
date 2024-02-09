# Elevator working model

## Guidelines
Run of the application will simulate work of the Elevator and will print step-by-step results of his work after every action.

## Simulation

![](https://reedfree.sirv.com/video_2022-09-14_16-24-50.gif)

## Source Code Review

This part of code in the Main class runs the application:

```java
    public static void main(String[] args) {
        Config config = Config.getInstance(configJsonPath);
        BuildingPreparingRandomTool preparingTool = new BuildingPreparingRandomTool(config);

        Elevator elevator = new Elevator(config.getElevatorCapacity());
        Building building = preparingTool.getBuilding();

        new ElevatorController(elevator, building).run();
    }
```

Make sure that you have correctly specified the path to the config.json file and also make sure that it exists and that the data is correct.
It is assumed that the config.json file is located in a relative path:
```
/src/main/resources/config.json
```

# Solution class diagram:

![](https://reedfree.sirv.com/dataox_test_task.png)

### The project is divided into the corresponding packages:
    
<ol>
  <li>
    <p><strong>controller</strong> - contains ElevatorController class, used to combine ConsoleView and simulating of Elevator work</p>
  </li>
  <li>
    <p><strong>exception</strong> - containt custom exceptions, written in order to make debug process easier</p>
  </li>
  <li>
    <p><strong>model</strong> - contains main model classes, such as:</p>
    <ol>
      <li>
        <strong>Building</strong> - class-container that describes building with floors. Doesn't have to extend Identifier class because there is no need to create Building collections and maps 
      </li>
        <li>
        <p><strong>Direction</strong> - enum class, describes possible elevator directions</p>
      </li>
        <li>
        <p><strong>Elevator</strong> - class-container, that describes elevator with people on some floor.</p>
      </li>
        <li>
        <p><strong>Floor</strong> - class-container of Human. Extends Identifier class, because this class objects are used to create Collections in Building class</p>
      </li>
        <li>
        <p><strong>Human</strong> - this class describes human, that wants to lift up to some floor via elevator. Extends Identifier class, because Human objects are collected in Floor and Elevator classes.</p>
      </li>
        <li>
        <p><strong>Identificable</strong> - interface with getId() method.</p>
      </li>
        <li>
        <p><strong>Identifier</strong> - class, that implements identificable interface. Used to abstract while collecting such objects.</p>
      </li>
        <li>
        <p><strong>IdToObjectMap</strong> - superstructure on HashMap<Integer, Identificable>, that holds Identificable objects by their id values, so we can quickly get them out.</p>
      </li>
    </ol>
  </li>
    <li>
    <p><strong>tool</strong> - contains different tools, such as:</p>
        <ol>
            <li>
        <p><strong>BuildingPreparable</strong> - interface, used to create custom BuildingPreparators.</p>
      </li>
        <li>
        <p><strong>BuildingPreparingParameterizedTool</strong> - class that creates strongly parameterized building.</p>
      </li>
        <li>
        <p><strong>BuildingPreparingRandomTool</strong> - class that randomly creates building with entry parameters.</p>
      </li>
            <li>
        <p><strong>RandomisingTool</strong>- class with different generating random values methods.</p>
      </li>
            <li>
        <p><strong>ValidationTool</strong> - utility class with different static validating methods.</p>
      </li>
    </ol>
  </li>
    <li>
    <p><strong>view</strong> - contains views:</p>
        <ol>
            <li>
        <p><strong>View<T></strong> - parameterised abstract class, that holds ViewModel and provides general method signatures for implemeting.</p>
      </li>
        <li>
        <p><strong>ConsoleView</strong> - class that extends View<ElevatorBuildingViewModel> and provides all required methods to show app data in the console.</p>
      </li>
    </ol>
  </li>
    <li>
    <p><strong>view_model</strong> - contains view models:</p>
        <ol>
            <li>
        <p><strong>ElevatorBuildingViewModel</strong> - contains Elevator, Building and Elevator simulation current step.</p>
      </li>
    </ol>
  </li>
</ol>


# Project structure:

![](https://reedfree.sirv.com/projstruct.png)

# Algorithm:

For the first time, the elevator is loaded with people from the first floor, and goes to the largest of those that passengers need.

On the way, the elevator stops at those floors where passengers need, dropping them off and picking up people who need to go in the same direction as the elevator is moving.

When boarding new passengers, the target floor is recalculated.

In the case when the elevator is empty, and there are still people waiting, the elevator will first go to the top floor, picking up the rest along the way.

While the elevator is empty, it will pick up people waiting for it along the way.
