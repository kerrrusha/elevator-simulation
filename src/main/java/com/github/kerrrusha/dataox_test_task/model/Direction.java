package com.github.kerrrusha.dataox_test_task.model;

public enum Direction {
    NONE, UP, DOWN;

    public static Direction getDirection(int currentFloor, int destinationFloor) {
        if (currentFloor > destinationFloor)
            return DOWN;
        if (currentFloor < destinationFloor)
            return UP;
        return NONE;
    }
}
