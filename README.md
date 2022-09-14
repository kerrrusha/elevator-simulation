# dataox-test-task
Test task for Junior Java Developer position in the Dataox co.

# Elevator working model

## Overview
The following repo contains source code for test task in the Dataox co.
It's Java Console application, that involved MVC design model to process actions with model and showing the results to the user side.

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

#Solution class diagram:

![](https://reedfree.sirv.com/dataox_test_task.png)

The project is divided into the corresponding packages:

1. **controller** - contains ElevatorController class, used to combine ConsoleView and simulating of Elevator work
2. **exception** - containt custom exceptions, written in order to make debug process easier
3. **model** - contains main model classes, such as: 
  3.1 **Building** - class-container that describes building with floors. Doesn't have to extend Identifier class because there is no need to create Building collections and maps 
  3.2 **Direction** - enum class, describes possible elevator directions
  3.3 **Elevator** - class-container, that describes elevator with people on some floor.
  3.4 **Floor** - class-container of Human. Extends Identifier class, because this class objects are used to create Collections in Building class
  3.5 **Human** - this class describes human, that wants to lift up to some floor via elevator. Extends Identifier class, because Human objects are collected in Floor and Elevator classes.
  3.6 **Identificable** - interface with getId() method.
  3.7 **Identifier** - class, that implements identificable interface. Used to abstract while collecting such objects.
  3.8 **IdToObjectMap** - superstructure on HashMap<Integer, Identificable>, that holds Identificable objects by their id values, so we can quickly get them out.
4. **tool** - contains different tools, such as:
  4.1 **BuildingPreparable** - interface, used to create custom BuildingPreparators.
  4.2 **BuildingPreparingParameterizedTool** - class that creates strongly parameterized building.
  4.3 **BuildingPreparingRandomTool** - class that randomly creates building with entry parameters.
  4.4 **RandomisingTool** - class with different generating random values methods
  4.5 **ValidationTool** - utility class with different static validating methods
5. **view** - contains views:
  5.1 **View<T>** - parameterised abstract class, that holds ViewModel and provides general method signatures for implemeting.
  5.2 **ConsoleView** - class that extends View<ElevatorBuildingViewModel> and provides all required methods to show app data in the console.
6. #####view_model##### - contains view models:
  6.1. ######ElevatorBuildingViewModel###### - contains Elevator, Building and Elevator simulation current step.

# Project structure:

![](https://reedfree.sirv.com/projstruct.png)
