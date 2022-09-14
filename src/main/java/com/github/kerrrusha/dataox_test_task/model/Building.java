package com.github.kerrrusha.dataox_test_task.model;

import com.github.kerrrusha.dataox_test_task.exception.IllegalFloorException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.github.kerrrusha.dataox_test_task.tool.ValidationTool.checkFloorNumber;

public class Building {
    private final int floorsNumber;
    private final IdToObjectMap floors;

    public Building(int floorsNumber) {
        checkFloorNumber(floorsNumber);
        this.floorsNumber = floorsNumber;
        this.floors = new IdToObjectMap();
    }

    public int getFloorsNumber() {
        return floorsNumber;
    }
    public void add(Floor floor) {
        floors.checkIfNotPresent(floor);
        floors.add(floor);
    }
    public boolean isEmpty() {
        for(Floor floor : getFloorList()) {
            if (floor.notEmpty())
                return false;
        }
        return true;
    }
    public List<Floor> getFloorList() {
        return floors.toCollection().stream().
                map(elem -> (Floor)elem).
                collect(Collectors.toList());
    }
    public Floor getFloor(int floorNumber) {
        checkFloorNumber(floorNumber);
        if (floorNumber > floorsNumber) {
            throw new IllegalFloorException();
        }
        return (Floor)floors.get(floorNumber);
    }
    public Optional<Integer> getNextNonEmptyFloor(int currentFloor, Direction direction) {
        if (isEmpty()) {
            return Optional.empty();
        }
        Optional<Integer> nextUp = getNextNonEmptyFloorUp(currentFloor);
        Optional<Integer> nextDown = getNextNonEmptyFloorDown(currentFloor);
        if (direction.equals(Direction.UP)) {
            return nextUp;
        }
        if (direction.equals(Direction.DOWN)) {
            return nextDown;
        }
        if (nextDown.isPresent()) {
            return nextDown;
        }
        return nextUp;
    }
    private Optional<Integer> getNextNonEmptyFloorUp(int currentFloor) {
        if (currentFloor + 1 > getFloorsNumber())
            return Optional.empty();
        for(int floorNumber = currentFloor + 1; floorNumber <= getFloorsNumber(); floorNumber++) {
            if (get(floorNumber).notEmpty())
                return Optional.of(floorNumber);
        }
        return Optional.empty();
    }
    private Optional<Integer> getNextNonEmptyFloorDown(int currentFloor) {
        if (currentFloor - 1 < 1)
            return Optional.empty();
        for(int floorNumber = currentFloor - 1; floorNumber >= 1; floorNumber--) {
            if (get(floorNumber).notEmpty())
                return Optional.of(floorNumber);
        }
        return Optional.empty();
    }
    public Floor get(int floorNumber) {
        return (Floor)floors.get(floorNumber);
    }
}
