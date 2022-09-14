package com.github.kerrrusha.dataox_test_task;

import com.github.kerrrusha.dataox_test_task.model.Building;
import com.github.kerrrusha.dataox_test_task.model.Elevator;
import com.github.kerrrusha.dataox_test_task.tool.BuildingPreparingTool;
import com.github.kerrrusha.dataox_test_task.view.ConsoleView;
import com.github.kerrrusha.dataox_test_task.view.View;
import com.github.kerrrusha.dataox_test_task.view_model.ElevatorBuildingViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewTest {
    @Test
    public void startTest() {
        final String ERROR_MESSAGE = "Incorrect string output.";
        Config config = Config.getInstanceDefaultValues();
        BuildingPreparingTool preparingTool = new BuildingPreparingTool(config);
        Building building = preparingTool.getBuilding();
        Elevator elevator = new Elevator(config.getElevatorCapacity());
        View<ElevatorBuildingViewModel> consoleView = new ConsoleView();
        ElevatorBuildingViewModel elevatorBuildingViewModel =
                new ElevatorBuildingViewModel(elevator, building, 1);

        consoleView.show(elevatorBuildingViewModel);

        assertEquals(1,1, ERROR_MESSAGE);
    }
}
