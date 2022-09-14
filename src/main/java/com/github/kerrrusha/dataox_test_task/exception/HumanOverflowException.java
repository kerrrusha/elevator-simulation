package com.github.kerrrusha.dataox_test_task.exception;

public class HumanOverflowException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "Impossible to add more humans - limit reached.";
    public HumanOverflowException() {
        super(ERROR_MESSAGE);
    }
}
