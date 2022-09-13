package com.github.kerrrusha.dataox_test_task;

import com.github.kerrrusha.dataox_test_task.exception.IllegalFloorException;

public class ValidationTool {
    public static void checkFloorNumber(int number) {
        if (numberIsNegative(number))
            throw new IllegalFloorException();
    }
    public static boolean numberIsNegative(int number) {
        return number < 0;
    }
}
