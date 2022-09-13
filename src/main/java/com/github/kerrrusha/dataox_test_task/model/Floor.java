package com.github.kerrrusha.dataox_test_task.model;

import com.github.kerrrusha.dataox_test_task.ValidationTool;
import com.github.kerrrusha.dataox_test_task.exception.HumanAlreadyExistsException;
import com.github.kerrrusha.dataox_test_task.exception.HumanNotFoundException;

import java.util.List;
import java.util.stream.Collectors;

public class Floor extends IdToObjectMap implements Identifier {
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
    public Floor(int floorNumber, IdToObjectMap people) {
        super(objectsCreated);
        ValidationTool.checkFloorNumber(floorNumber);
        this.number = floorNumber;
        this.people = people;
        objectsCreated++;
    }

    public int getPeopleCount() {
        return people.size();
    }
    public void add(Human humanToAdd) {
        if (contains(humanToAdd))
            throw new HumanAlreadyExistsException();
        people.add(humanToAdd);
    }
    public boolean contains(Human human) {
        return people.contains(human);
    }
    public void remove(Human humanToRemove) {
        if (people.isEmpty() || contains(humanToRemove))
            throw new HumanNotFoundException();
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
