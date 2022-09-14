package com.github.kerrrusha.dataox_test_task.model;

public class Human extends Identifier {
    private static int objectsCreated = 0;

    private final int destinationFloor;
    public Human(int destinationFloor) {
        super(objectsCreated);
        this.destinationFloor = destinationFloor;
        objectsCreated++;
    }

    public int getDestinationFloor() {
        return destinationFloor;
    }

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Human other = (Human) obj;
        return getId() == other.getId();
    }
}
