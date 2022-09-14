package com.github.kerrrusha.dataox_test_task;

import com.github.kerrrusha.dataox_test_task.controller.ElevatorController;
import com.github.kerrrusha.dataox_test_task.model.Building;
import com.github.kerrrusha.dataox_test_task.model.Elevator;
import com.github.kerrrusha.dataox_test_task.tool.BuildingPreparingRandomTool;

import java.util.Objects;

public class Main {
    final static String configJsonPath = Objects.requireNonNull(Main.class.
            getClassLoader().getResource("config.json")).getPath();

    public static void main(String[] args) {
        Config config = Config.getInstance(configJsonPath);
        BuildingPreparingRandomTool preparingTool = new BuildingPreparingRandomTool(config);

        Elevator elevator = new Elevator(config.getElevatorCapacity());
        Building building = preparingTool.getBuilding();

        new ElevatorController(elevator, building).run();
    }
}
