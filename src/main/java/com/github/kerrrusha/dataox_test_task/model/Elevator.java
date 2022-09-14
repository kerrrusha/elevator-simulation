package com.github.kerrrusha.dataox_test_task.model;

import com.github.kerrrusha.dataox_test_task.exception.ElementNotFoundException;
import com.github.kerrrusha.dataox_test_task.exception.HumanOverflowException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Elevator {
    public final int MAX_CAPACITY;
    private final IdToObjectMap peopleIn;
    private int currentFloorNumber;

    public Elevator(int maxCapacity) {
        MAX_CAPACITY = maxCapacity;
        peopleIn = new IdToObjectMap();
        currentFloorNumber = 1;
    }
    public Optional<Integer> getNextFloor() {
        if (peopleIn.isEmpty()) {
            return Optional.empty();
        }
        Optional<Human> maxFloorHuman = getDirection().equals(Direction.UP) ?
                getPeopleIn().stream().min(Comparator.comparingInt(Human::getDestinationFloor)) :
                getPeopleIn().stream().max(Comparator.comparingInt(Human::getDestinationFloor));
        if (maxFloorHuman.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(maxFloorHuman.get().getDestinationFloor());
    }
    public void setCurrentFloor(int floorNumber) {
        currentFloorNumber = floorNumber;
    }
    public Optional<Integer> getDestinationFloor() {
        if (peopleIn.isEmpty()) {
            return Optional.empty();
        }
        Optional<Human> maxFloorHuman = getDirection().equals(Direction.UP) ?
                getPeopleIn().stream().max(Comparator.comparingInt(Human::getDestinationFloor)) :
                getPeopleIn().stream().min(Comparator.comparingInt(Human::getDestinationFloor));
        if (maxFloorHuman.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(maxFloorHuman.get().getDestinationFloor());
    }
    public Direction getDirection() {
        if (isEmpty())
            return Direction.NONE;
        return peopleAreGoingUp() ? Direction.UP : Direction.DOWN;
    }
    private boolean peopleAreGoingUp() {
        if (isEmpty())
            throw new ElementNotFoundException();
        return getPeopleIn().get(0).getDestinationFloor() > currentFloorNumber;
    }
    public int getCurrentCapacity() {
        return peopleIn.size();
    }
    public void add(Human human) {
        checkIfOverflow();
        peopleIn.checkIfNotPresent(human);
        peopleIn.add(human);
    }
    public int getCurrentFloorNumber() {
        return currentFloorNumber;
    }
    public boolean isFull() {
        return getCurrentCapacity() >= MAX_CAPACITY;
    }
    public void remove(Human human) {
        peopleIn.checkIfPresent(human);
        peopleIn.remove(human);
    }
    public boolean isEmpty() {
        return getCurrentCapacity() == 0;
    }
    public List<Human> getPeopleIn() {
        return peopleIn.toCollection().stream().
                map(elem -> (Human)elem).
                collect(Collectors.toList());
    }

    private void checkIfOverflow() {
        if (peopleIn.size() >= MAX_CAPACITY) {
            throw new HumanOverflowException();
        }
    }
}
