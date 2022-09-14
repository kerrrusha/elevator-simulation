package com.github.kerrrusha.dataox_test_task.model;

import com.github.kerrrusha.dataox_test_task.exception.ElementAlreadyExistsException;

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
    public void add(Identificable element) {
        data.put(element.getId(), element);
    }
    public Identificable get(Integer id) {
        return data.get(id);
    }
    public boolean contains(Identificable element) {
        return data.containsKey(element.getId());
    }
    public int size() {
        return data.size();
    }
    public void remove(Identificable element) {
        data.remove(element.getId());
    }
    public Collection<Identificable> toCollection() {
        return data.values();
    }
    public void checkIfPresent(Identificable element) {
        if (contains(element)) {
            throw new ElementAlreadyExistsException();
        }
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }
}
