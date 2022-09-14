package com.github.kerrrusha.dataox_test_task.model;

import com.github.kerrrusha.dataox_test_task.exception.IllegalFloorException;

import java.util.Collection;

import static com.github.kerrrusha.dataox_test_task.tool.ValidationTool.checkFloorNumber;

public class Building {
    private final int floorsNumber;
    private final IdToObjectMap floors;

    public Building(int floorsNumber) {
        checkFloorNumber(floorsNumber);
        this.floorsNumber = floorsNumber;
        this.floors = new IdToObjectMap();
    }
    public Building(int floorsNumber, IdToObjectMap floors) {
        checkFloorNumber(floorsNumber);
        if (floors.size() > floorsNumber)
            throw new IllegalFloorException();
        this.floorsNumber = floorsNumber;
        this.floors = floors;
    }

    public int getFloorsNumber() {
        return floorsNumber;
    }
    public void add(Floor floor) {
        floors.checkIfPresent(floor);
        floors.add(floor);
    }
    public Floor getFloor(int floorNumber) {
        checkFloorNumber(floorNumber);
        if (floorNumber > floorsNumber) {
            throw new IllegalFloorException();
        }
        return (Floor)floors.get(floorNumber);
    }
    public Collection<Identificable> floorsToCollection() {
        return floors.toCollection();
    }
    public boolean contains(Floor floor) {
        return floors.contains(floor);
    }
}
