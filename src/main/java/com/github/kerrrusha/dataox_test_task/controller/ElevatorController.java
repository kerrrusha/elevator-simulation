package com.github.kerrrusha.dataox_test_task.controller;

import com.github.kerrrusha.dataox_test_task.model.Building;
import com.github.kerrrusha.dataox_test_task.model.Elevator;
import com.github.kerrrusha.dataox_test_task.view.ConsoleView;
import com.github.kerrrusha.dataox_test_task.view_model.ElevatorBuildingViewModel;

public class ElevatorController {
    private final Elevator elevator;
    private final Building building;
    private final ConsoleView view;

    public ElevatorController(Elevator elevator, Building building) {
        this.elevator = elevator;
        this.building = building;
        this.view = new ConsoleView();
    }
    public void run() {
        int stepCount = 0;
        ElevatorBuildingViewModel viewModel = new ElevatorBuildingViewModel(elevator, building, stepCount);
        loadPeople();
        while (elevator.getDestinationFloor().isPresent()) {
            stepCount++;
            moveElevator();
            loadPeople();

            viewModel.setCurrentStep(stepCount);
            view.show(viewModel);
        }
    }
    private void loadPeople() {

    }
    private void moveElevator() {

    }
}
