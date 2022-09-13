package com.github.kerrrusha.dataox_test_task.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class IdToObjectMap implements Emptyable {
    private final Map<Integer, Identificable> data;

    public IdToObjectMap() {
        data = new HashMap<>();
    }
    public IdToObjectMap(IdToObjectMap other) {
        this.data = other.data;
    }
    public void add(Identificable toAdd) {
        data.put(toAdd.getId(), toAdd);
    }
    public boolean contains(Human human) {
        return data.containsKey(human.getId());
    }
    public int size() {
        return data.size();
    }
    public void remove(Human humanToRemove) {
        data.remove(humanToRemove.getId());
    }
    public Collection<Identificable> toCollection() {
        return data.values();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
