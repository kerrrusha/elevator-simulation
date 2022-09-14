package com.github.kerrrusha.dataox_test_task.tool;

import com.github.kerrrusha.dataox_test_task.model.Building;
import com.github.kerrrusha.dataox_test_task.model.Floor;
import com.github.kerrrusha.dataox_test_task.model.Human;

public class BuildingPreparingParameterizedTool implements BuildingPreparable {
    int floorsAmount, peoplePerFloorAmount;

    public BuildingPreparingParameterizedTool(int floorsAmount, int peoplePerFloorAmount) {
        this.floorsAmount = floorsAmount;
        this.peoplePerFloorAmount = peoplePerFloorAmount;
    }

    @Override
    public Building getBuilding() {
        Building building = new Building(floorsAmount);

        for (int floorNumber = 1; floorNumber <= floorsAmount; floorNumber++) {
            building.add(getFloor(floorNumber, floorsAmount));
        }

        return building;
    }
    private Floor getFloor(int floorNumber, int floorsAmount) {
        Floor floor = new Floor(floorNumber);

        for (int i = 0; i < peoplePerFloorAmount; i++) {
            floor.add(getHuman(floorNumber, floorsAmount));
        }

        return floor;
    }
    private Human getHuman(int floorNumber, int floorsAmount) {
        int destinationFloor = getDestinationFloorNumber(floorNumber, floorsAmount);
        return new Human(destinationFloor);
    }
    private int getDestinationFloorNumber(int floorNumber, int floorsAmount) {
        int result;
        if ((result = floorNumber + 1) > floorsAmount)
            result = floorNumber - 1;
        return result;
    }
}
