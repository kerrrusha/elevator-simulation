package com.github.kerrrusha.dataox_test_task;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ConfigTest {
    @Test
    public void defaultTest() {
        final String ERROR_MESSAGE = "Incorrect default value.";
        Config config = Config.getInstanceDefaultValues();

        assertEquals(5, config.getBuildingFloorMin(), ERROR_MESSAGE);
        assertEquals(20, config.getBuildingFloorMax(), ERROR_MESSAGE);
        assertEquals(0, config.getPassengersPerFloorMin(), ERROR_MESSAGE);
        assertEquals(10, config.getPassengersPerFloorMax(), ERROR_MESSAGE);
        assertEquals(5, config.getElevatorCapacity(), ERROR_MESSAGE);
    }

    @Test
    public void jsonFileTest() {
        final String ERROR_MESSAGE = "Incorrect default value.";
        final String jsonPath = Objects.requireNonNull(getClass().
                getClassLoader().getResource("configTest.json")).getPath();

        assertDoesNotThrow(() -> {
            Config.getInstance(jsonPath);
        });

        Config config = Config.getInstance(jsonPath);

        assertEquals(1, config.getBuildingFloorMin(), ERROR_MESSAGE);
        assertEquals(2, config.getBuildingFloorMax(), ERROR_MESSAGE);
        assertEquals(3, config.getPassengersPerFloorMin(), ERROR_MESSAGE);
        assertEquals(4, config.getPassengersPerFloorMax(), ERROR_MESSAGE);
        assertEquals(5, config.getElevatorCapacity(), ERROR_MESSAGE);
    }
}
