package com.github.kerrrusha.dataox_test_task.model;

public class Identifier implements Identificable {
    private final int id;
    public Identifier(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }
}
