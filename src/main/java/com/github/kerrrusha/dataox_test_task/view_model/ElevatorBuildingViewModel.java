package com.github.kerrrusha.dataox_test_task.view_model;

import com.github.kerrrusha.dataox_test_task.model.Building;
import com.github.kerrrusha.dataox_test_task.model.Elevator;

public class ElevatorBuildingViewModel {
    private int currentStep;
    private final Elevator elevator;
    private final Building building;

    public ElevatorBuildingViewModel(Elevator elevator, Building building) {
        this.elevator = elevator;
        this.building = building;
        this.currentStep = 1;
    }
    public Elevator getElevator() {
        return elevator;
    }
    public Building getBuilding() {
        return building;
    }
    public int getCurrentStep() {
        return currentStep;
    }
    public void updateCurrentStep() {
        currentStep++;
    }
}
