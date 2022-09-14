package com.github.kerrrusha.dataox_test_task;

import com.github.kerrrusha.dataox_test_task.model.Building;
import com.github.kerrrusha.dataox_test_task.model.Elevator;
import com.github.kerrrusha.dataox_test_task.model.Human;
import com.github.kerrrusha.dataox_test_task.tool.BuildingPreparable;
import com.github.kerrrusha.dataox_test_task.tool.BuildingPreparingParameterizedTool;
import com.github.kerrrusha.dataox_test_task.view.ConsoleView;
import com.github.kerrrusha.dataox_test_task.view.View;
import com.github.kerrrusha.dataox_test_task.view_model.ElevatorBuildingViewModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ViewTest {
    @Test
    public void startTest() {
        final String ERROR_MESSAGE = "Incorrect string output.";
        final String expected = """


                ##### Step 1 #####

                Going to:\t4
                5|            | 4 4 4
                4|            | 5 5 5
                3|            | 4 4 4
                2|            | 3 3 3
                1|^ 2 3 4 1  ^| 2 2 2
                """;

        final int FLOORS_AMOUNT = 5;
        final int HUMAN_PER_FLOOR_AMOUNT = 3;

        BuildingPreparable preparingTool =
                new BuildingPreparingParameterizedTool(FLOORS_AMOUNT, HUMAN_PER_FLOOR_AMOUNT);
        Building building = preparingTool.getBuilding();
        Elevator elevator = prepareElevator();
        View<ElevatorBuildingViewModel> consoleView = new ConsoleView(
                new ElevatorBuildingViewModel(elevator, building)
        );

        assertEquals(expected, consoleView.representString(), ERROR_MESSAGE);
    }

    private Elevator prepareElevator() {
        final int ELEVATOR_CAPACITY = 5;
        final int HUMAN_IN_ELEVATOR_AMOUNT = 4;

        Elevator elevator = new Elevator(ELEVATOR_CAPACITY);
        for (int i = 0; i < HUMAN_IN_ELEVATOR_AMOUNT; i++) {
            elevator.add(new Human(i + 1));
        }
        return elevator;
    }
}
