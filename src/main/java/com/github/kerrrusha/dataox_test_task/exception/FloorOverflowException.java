package com.github.kerrrusha.dataox_test_task.exception;

public class FloorOverflowException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "Impossible to add more floors - floor limit exceeded.";
    public FloorOverflowException() {
        super(ERROR_MESSAGE);
    }
}
