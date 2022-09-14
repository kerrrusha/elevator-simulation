package com.github.kerrrusha.dataox_test_task.exception;

public class ElementAlreadyExistsException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "There is no such human.";
    public ElementAlreadyExistsException() {
        super(ERROR_MESSAGE);
    }
}
