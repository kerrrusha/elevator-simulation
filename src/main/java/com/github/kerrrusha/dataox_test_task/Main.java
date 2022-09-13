package com.github.kerrrusha.dataox_test_task;

import com.github.kerrrusha.dataox_test_task.model.Building;

import java.util.Objects;

public class Main {
    final static String configJsonPath = Objects.requireNonNull(Main.class.
            getClassLoader().getResource("config.json")).getPath();

    public static void main(String[] args) {
        BuildingPreparingTool preparingTool = new BuildingPreparingTool(Config.getInstance(configJsonPath));
        Building building = preparingTool.getBuilding();
    }
}
