package com.github.kerrrusha.dataox_test_task.exception;

public class IllegalFloorException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "Incorrect floor value.";
    public IllegalFloorException() {
        super(ERROR_MESSAGE);
    }
}
