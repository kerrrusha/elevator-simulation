package com.github.kerrrusha.dataox_test_task.model;

import com.github.kerrrusha.dataox_test_task.ValidationTool;
import com.github.kerrrusha.dataox_test_task.exception.IllegalFloorException;

public class Building extends IdToObjectMap {
    private final int floorsNumber;

    public Building(int floorsNumber) {
        ValidationTool.checkFloorNumber(floorsNumber);
        this.floorsNumber = floorsNumber;
    }
    public Building(int floorsNumber, IdToObjectMap floors) {
        super(floors);
        ValidationTool.checkFloorNumber(floorsNumber);
        if (floors.size() > floorsNumber)
            throw new IllegalFloorException();
        this.floorsNumber = floorsNumber;
    }
}
