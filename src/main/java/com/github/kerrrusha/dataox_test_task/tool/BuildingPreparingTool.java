package com.github.kerrrusha.dataox_test_task.tool;

import com.github.kerrrusha.dataox_test_task.Config;
import com.github.kerrrusha.dataox_test_task.model.Building;
import com.github.kerrrusha.dataox_test_task.model.Floor;
import com.github.kerrrusha.dataox_test_task.model.Human;

public class BuildingPreparingTool {
    Config config;
    RandomisingTool rand;
    public BuildingPreparingTool(Config config) {
        this.config = config;
        this.rand = new RandomisingTool();
    }

    public Building getBuilding() {
        int floorsAmount = rand.nextInt(
                config.getBuildingFloorMin(),
                config.getPassengersPerFloorMax() + 1
        );
        Building building = new Building(floorsAmount);

        for (int floorNumber = 1; floorNumber < floorsAmount; floorNumber++) {
            building.add(getFloor(floorNumber, floorsAmount));
        }

        return building;
    }
    private Floor getFloor(int floorNumber, int floorsAmount) {
        Floor floor = new Floor(floorNumber);

        int peopleAmount = rand.nextInt(
                config.getPassengersPerFloorMin(),
                config.getPassengersPerFloorMax() + 1
        );
        for (int i = 0; i < peopleAmount; i++) {
            floor.add(getHuman(floorNumber, floorsAmount));
        }

        return floor;
    }
    private Human getHuman(int floorNumber, int floorsAmount) {
        int destinationFloor = rand.generateDestinationFloorNumber(floorNumber, floorsAmount);
        return new Human(destinationFloor);
    }
}
