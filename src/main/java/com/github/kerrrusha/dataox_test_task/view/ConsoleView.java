package com.github.kerrrusha.dataox_test_task.view;

import com.github.kerrrusha.dataox_test_task.model.Building;
import com.github.kerrrusha.dataox_test_task.model.Elevator;
import com.github.kerrrusha.dataox_test_task.model.Human;
import com.github.kerrrusha.dataox_test_task.view_model.ElevatorBuildingViewModel;

public class ConsoleView implements View<ElevatorBuildingViewModel> {
    static final String UP_SYMBOL = "^";
    static final String STAYING_SYMBOL = "=";
    static final String DOWN_SYMBOL = "v";
    static final String WALL_SYMBOL = "|";

    @Override
    public void show(ElevatorBuildingViewModel viewModel) {
        String result = getHeader(viewModel.getCurrentStep()) +
                representString(viewModel);

        System.out.println(result);
    }
    private String getHeader(int stepCount) {
        return "\n\n" + getHeaderSymbols() + " Step " + stepCount + " " + getHeaderSymbols() + "\n\n";
    }
    private String getHeaderSymbols() {
        final String HEADER_SYMBOL = "#";
        final int HEADER_SYMBOL_REPEAT = 5;
        return HEADER_SYMBOL.repeat(HEADER_SYMBOL_REPEAT);
    }
    private String representString(ElevatorBuildingViewModel viewModel) {
        StringBuilder result = new StringBuilder();

        result.append("Going to:\t")
                .append(viewModel.getElevator().getDestinationFloor())
                .append("\n");
        for (int floorNumber = 1; floorNumber < viewModel.getBuilding().getFloorsNumber(); floorNumber++) {
            result.append(representElevatorOnFloor(viewModel.getElevator(), floorNumber)).
                    append(representPeopleWaitingOnFloor(viewModel.getBuilding(), floorNumber));
        }

        return result.toString();
    }
    private String representElevatorOnFloor(Elevator elevator, int floorNumber) {
        return WALL_SYMBOL +
                getElevatorDirectionSymbol(elevator) +
                getElevatorInsideView(elevator, floorNumber) +
                getElevatorDirectionSymbol(elevator) +
                WALL_SYMBOL;
    }
    private String getElevatorInsideView(Elevator elevator, int floorNumber) {
        if (elevator.getCurrentFloorNumber() != floorNumber) {
            return " ".repeat(elevator.MAX_CAPACITY);
        }
        StringBuilder result = new StringBuilder();
        elevator.getPeopleIn().stream().
                map(identificable -> (Human)identificable).
                forEach(human -> result.append(human.getDestinationFloor()));
        return result.toString();
    }
    private String getElevatorDirectionSymbol(Elevator elevator) {
        switch (elevator.getDirection()) {
            case UP -> {
                return UP_SYMBOL;
            }
            case DOWN -> {
                return DOWN_SYMBOL;
            }
            default -> {
                return STAYING_SYMBOL;
            }
        }
    }
    private String representPeopleWaitingOnFloor(Building building, int floorNumber) {
        StringBuilder result = new StringBuilder();
        building.getFloor(floorNumber).getPeopleList().forEach(
                human -> result.append(human.getDestinationFloor()).append(" "));
        result.append("\n");
        return result.toString();
    }
}
