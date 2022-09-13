package com.github.kerrrusha.dataox_test_task;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public final class Config {
    private static Config instance;

    private static final String KEY_BUILDING_FLOOR_MIN = "BUILDING_FLOOR_MIN";
    private static final String KEY_BUILDING_FLOOR_MAX = "BUILDING_FLOOR_MAX";
    private static final String KEY_PASSENGERS_PER_FLOOR_MIN = "PASSENGERS_PER_FLOOR_MIN";
    private static final String KEY_PASSENGERS_PER_FLOOR_MAX = "PASSENGERS_PER_FLOOR_MAX";
    private static final String KEY_ELEVATOR_CAPACITY = "ELEVATOR_CAPACITY";

    private Map<String, Integer> data;

    private Config() {
        data = new HashMap<>();
        initialiseDefault();
    }
    private Config(String jsonPath) {
        Optional<JSONObject> jsonOptional = readJsonObject(jsonPath);
        data = new HashMap<>();
        initialiseDefault();
        if (jsonOptional.isEmpty()) {
            return;
        }
        setJsonValues(jsonOptional.get());
    }
    private void setJsonValues(JSONObject json) {
        data.keySet().forEach(parameter -> data.put(parameter, Integer.parseInt(
                String.valueOf((long) json.get(parameter)))));
    }
    private void initialiseDefault() {
        data.put(KEY_BUILDING_FLOOR_MIN, 5);
        data.put(KEY_BUILDING_FLOOR_MAX, 20);
        data.put(KEY_PASSENGERS_PER_FLOOR_MIN, 0);
        data.put(KEY_PASSENGERS_PER_FLOOR_MAX, 10);
        data.put(KEY_ELEVATOR_CAPACITY, 5);
    }

    public static Config getInstanceDefaultValues() {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }
    public static Config getInstance(String jsonPath) {
        if (instance == null) {
            instance = new Config(jsonPath);
        }
        return instance;
    }

    public int getBuildingFloorMin() {
        return data.get(KEY_BUILDING_FLOOR_MIN);
    }
    public int getBuildingFloorMax() {
        return data.get(KEY_BUILDING_FLOOR_MAX);
    }
    public int getPassengersPerFloorMin() {
        return data.get(KEY_PASSENGERS_PER_FLOOR_MIN);
    }
    public int getPassengersPerFloorMax() {
        return data.get(KEY_PASSENGERS_PER_FLOOR_MAX);
    }
    public int getElevatorCapacity() {
        return data.get(KEY_ELEVATOR_CAPACITY);
    }

    private Optional<JSONObject> readJsonObject(String jsonPath) {
        try(FileReader fr = new FileReader(jsonPath)) {
            return Optional.of((JSONObject) new JSONParser().parse(fr));
        } catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
