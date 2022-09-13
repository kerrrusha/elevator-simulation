package com.github.kerrrusha.dataox_test_task.exception;

public class HumanAlreadyExistsException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "There is no such human.";
    public HumanAlreadyExistsException() {
        super(ERROR_MESSAGE);
    }
}
