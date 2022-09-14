package com.github.kerrrusha.dataox_test_task.tool;

import java.util.concurrent.ThreadLocalRandom;

public class RandomisingTool {
    public int nextInt(int from, int to) {
        return ThreadLocalRandom.current().nextInt(from, to);
    }
    public int generateDestinationFloorNumber(int currentFloorNumber, int floorsAmount) {
        int result = generateFloorNumber(floorsAmount);
        while (result == currentFloorNumber) {
            result = generateFloorNumber(floorsAmount);
        }
        return result;
    }
    public int generateFloorNumber(int floorsAmount) {
        return nextInt(1, floorsAmount + 1);
    }
}
