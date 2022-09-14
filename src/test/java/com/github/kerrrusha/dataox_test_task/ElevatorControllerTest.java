package com.github.kerrrusha.dataox_test_task;

import com.github.kerrrusha.dataox_test_task.controller.ElevatorController;
import com.github.kerrrusha.dataox_test_task.model.Building;
import com.github.kerrrusha.dataox_test_task.model.Elevator;
import com.github.kerrrusha.dataox_test_task.tool.BuildingPreparingRandomTool;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ElevatorControllerTest {
    @Test
    public void CompletenessTest() {
        final String configJsonPath = Objects.requireNonNull(Main.class.
                getClassLoader().getResource("config.json")).getPath();
        Config config = Config.getInstance(configJsonPath);
        BuildingPreparingRandomTool preparingTool = new BuildingPreparingRandomTool(config);

        int testsCount = 100;
        for (int i = 0; i < testsCount; i++) {
            Elevator elevator = new Elevator(config.getElevatorCapacity());
            Building building = preparingTool.getBuilding();

            new ElevatorController(elevator, building).run();

            assertTrue(elevator.isEmpty() && building.isEmpty());
        }
    }
}
