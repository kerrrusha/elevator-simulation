package com.github.kerrrusha.dataox_test_task.exception;

public class HumanNotFoundException extends IllegalArgumentException {
    private static final String ERROR_MESSAGE = "Such human already exists.";
    public HumanNotFoundException() {
        super(ERROR_MESSAGE);
    }
}
