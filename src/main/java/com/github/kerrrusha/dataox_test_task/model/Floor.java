package com.github.kerrrusha.dataox_test_task.model;

import com.github.kerrrusha.dataox_test_task.exception.ElementNotFoundException;
import com.github.kerrrusha.dataox_test_task.tool.ValidationTool;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Floor extends Identifier {
    private static int objectsCreated = 0;

    private final IdToObjectMap people;
    private final int number;

    public Floor(int floorNumber) {
        super(objectsCreated);
        ValidationTool.checkFloorNumber(floorNumber);
        number = floorNumber;
        people = new IdToObjectMap();
        objectsCreated++;
    }

    public void add(Human humanToAdd) {
        people.checkIfNotPresent(humanToAdd);
        people.add(humanToAdd);
    }
    public List<Human> getPeopleGoingTheSameWay() {
        long goingUp = getPeopleList().stream().filter(human ->
                getHumanDirection(human).equals(Direction.UP)).count();
        long goingDown = getPeopleList().stream().filter(human ->
                getHumanDirection(human).equals(Direction.DOWN)).count();

        Direction resultDirection = goingUp > goingDown ? Direction.UP : Direction.DOWN;
        return getPeopleList().stream().filter(human ->
                getHumanDirection(human).equals(resultDirection)).collect(Collectors.toList());
    }
    public Direction getHumanDirection(Human human) {
        if (!people.contains(human)) {
            throw new ElementNotFoundException();
        }
        if (human.getDestinationFloor() > getFloorNumber())
            return Direction.UP;
        if (human.getDestinationFloor() < getFloorNumber())
            return Direction.DOWN;
        return Direction.NONE;
    }
    public List<Human> getPeopleGoingInSuchWay(Direction direction) {
        List<Human> result = new ArrayList<>();
        for(Human human : getPeopleList()) {
            if ( (direction.equals(Direction.UP) && human.getDestinationFloor() > getFloorNumber()) ||
                    (direction.equals(Direction.DOWN) && human.getDestinationFloor() < getFloorNumber())) {
                result.add(human);
            }
        }
        return result;

    }
    public boolean isEmpty() {
        return people.isEmpty();
    }
    public boolean notEmpty() {
        return !people.isEmpty();
    }
    public void remove(Human humanToRemove) {
        people.checkIfPresent(humanToRemove);
        people.remove(humanToRemove);
    }
    public List<Human> getPeopleList() {
        return people.toCollection().stream().
                map(identificable -> (Human) identificable).
                collect(Collectors.toList());
    }
    public int getFloorNumber() {
        return number;
    }
    @Override
    public int getId() {
        return getFloorNumber();
    }

    @Override
    public int hashCode() {
        return getId() + number + people.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Floor other = (Floor) obj;
        return number == other.number && people.hashCode() == other.people.hashCode();
    }
}
