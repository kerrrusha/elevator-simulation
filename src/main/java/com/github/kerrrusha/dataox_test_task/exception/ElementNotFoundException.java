package com.github.kerrrusha.dataox_test_task.exception;

public class ElementNotFoundException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "There is no such human.";
    public ElementNotFoundException() {
        super(ERROR_MESSAGE);
    }
}
