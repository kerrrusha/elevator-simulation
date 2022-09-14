package com.github.kerrrusha.dataox_test_task.model;

import com.github.kerrrusha.dataox_test_task.exception.HumanOverflowException;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;

public class Elevator {
    public final int MAX_CAPACITY;
    private final IdToObjectMap peopleIn;
    private int currentFloorNumber;

    public Elevator(int maxCapacity) {
        MAX_CAPACITY = maxCapacity;
        peopleIn = new IdToObjectMap();
        currentFloorNumber = 1;
    }
    public Optional<Integer> getDestinationFloor() {
        if (peopleIn.isEmpty()) {
            return Optional.empty();
        }
        Optional<Human> maxFloorHuman = peopleIn.toCollection().stream().
                map(identificable -> (Human)identificable).
                max(Comparator.comparingInt(Human::getDestinationFloor));
        if (maxFloorHuman.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(maxFloorHuman.get().getDestinationFloor());
    }
    public Direction getDirection() {
        Optional<Integer> destinationFloorOptional = getDestinationFloor();
        if (destinationFloorOptional.isEmpty())
            return Direction.NONE;
        int destinationFloor = destinationFloorOptional.get();
        if (destinationFloor > currentFloorNumber)
            return Direction.UP;
        return Direction.DOWN;
    }
    public int getCurrentCapacity() {
        return peopleIn.size();
    }
    public void add(Human human) {
        checkIfOverflow();
        peopleIn.checkIfPresent(human);
    }
    public int getCurrentFloorNumber() {
        return currentFloorNumber;
    }
    public Collection<Identificable> getPeopleIn() {
        return peopleIn.toCollection();
    }

    private void checkIfOverflow() {
        if (peopleIn.size() >= MAX_CAPACITY) {
            throw new HumanOverflowException();
        }
    }
}
