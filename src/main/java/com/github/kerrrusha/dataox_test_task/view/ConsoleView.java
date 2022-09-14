package com.github.kerrrusha.dataox_test_task.view;

import com.github.kerrrusha.dataox_test_task.model.Building;
import com.github.kerrrusha.dataox_test_task.model.Elevator;
import com.github.kerrrusha.dataox_test_task.view_model.ElevatorBuildingViewModel;

import java.util.Optional;

public class ConsoleView extends View<ElevatorBuildingViewModel> {
    static final String UP_SYMBOL = "^";
    static final String STAYING_SYMBOL = "=";
    static final String DOWN_SYMBOL = "v";
    static final String WALL_SYMBOL = "|";

    public ConsoleView(ElevatorBuildingViewModel viewModel) {
        super(viewModel);
    }

    @Override
    public void show() {
        System.out.println(representString());
    }
    @Override
    public void show(String content) {
        System.out.println(content);
    }
    @Override
    public String representString() {
        return getHeader(viewModel.getCurrentStep()) + representViewModelString(viewModel);
    }

    private String getHeader(int stepCount) {
        return "\n\n" + getHeaderSymbols() + " Step " + stepCount + " " + getHeaderSymbols() + "\n\n";
    }
    private String getHeaderSymbols() {
        final String HEADER_SYMBOL = "#";
        final int HEADER_SYMBOL_REPEAT = 5;
        return HEADER_SYMBOL.repeat(HEADER_SYMBOL_REPEAT);
    }
    private String representViewModelString(ElevatorBuildingViewModel viewModel) {
        StringBuilder result = new StringBuilder();

        Optional<Integer> goingToOptional = viewModel.getElevator().getDestinationFloor();
        result.append("Going to:\t")
                .append(goingToOptional.isEmpty() ? "WAITING" : goingToOptional.get()).
                append("\n");

        for (int floorNumber = viewModel.getBuilding().getFloorsNumber(); floorNumber >= 1; floorNumber--) {
            result.append(floorNumber).append(representElevatorOnFloor(viewModel.getElevator(), floorNumber)).
                    append(representPeopleWaitingOnFloor(viewModel.getBuilding(), floorNumber));
        }

        return result.toString();
    }
    private String representElevatorOnFloor(Elevator elevator, int floorNumber) {
        final String elevatorDirectionSymbol = elevator.getCurrentFloorNumber() == floorNumber ?
                getElevatorDirectionSymbol(elevator) :
                " ";
        return WALL_SYMBOL +
                elevatorDirectionSymbol +
                getElevatorInsideView(elevator, floorNumber) +
                elevatorDirectionSymbol +
                WALL_SYMBOL;
    }
    private String getElevatorInsideView(Elevator elevator, int floorNumber) {
        final String BLANK = "  ";
        if (elevator.getCurrentFloorNumber() != floorNumber) {
            return BLANK.repeat(elevator.MAX_CAPACITY);
        }
        StringBuilder result = new StringBuilder();
        elevator.getPeopleIn().forEach(human -> result.append(" ").append(human.getDestinationFloor()));
        int freePlaceInElevator = elevator.MAX_CAPACITY - elevator.getCurrentCapacity();
        result.append(BLANK.repeat(freePlaceInElevator));
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
                human -> result.append(" ").append(human.getDestinationFloor()));
        result.append("\n");
        return result.toString();
    }
}
