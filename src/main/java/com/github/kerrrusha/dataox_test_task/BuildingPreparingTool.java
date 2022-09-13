package com.github.kerrrusha.dataox_test_task;

import com.github.kerrrusha.dataox_test_task.model.Building;
import com.github.kerrrusha.dataox_test_task.model.Floor;
import com.github.kerrrusha.dataox_test_task.model.Human;

public class BuildingPreparingTool {
    Config config;
    Randomizer rand;
    public BuildingPreparingTool(Config config) {
        this.config = config;
        this.rand = new Randomizer();
    }

    public Building getBuilding() {
        int floorsAmount = rand.nextInt(
                config.getBuildingFloorMin(),
                config.getPassengersPerFloorMax() + 1
        );
        Building building = new Building(floorsAmount);

        for (int floorNumber = 1; floorNumber < floorsAmount; floorNumber++) {
            Floor floor = new Floor(floorNumber);

            int peopleAmount = rand.nextInt(
                    config.getPassengersPerFloorMin(),
                    config.getPassengersPerFloorMax() + 1
            );
            for (int i = 0; i < peopleAmount; i++) {
                int destinationFloor = rand.generateDestinationFloorNumber(floorNumber, floorsAmount);
                floor.add(new Human(destinationFloor));
            }

            building.add(floor);
        }
    }
}
