package com.github.kerrrusha.dataox_test_task.exception;

public class ElementNotFoundException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "Such human already exists.";
    public ElementNotFoundException() {
        super(ERROR_MESSAGE);
    }
}
