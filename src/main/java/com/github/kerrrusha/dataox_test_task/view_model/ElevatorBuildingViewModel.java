package com.github.kerrrusha.dataox_test_task.view_model;

import com.github.kerrrusha.dataox_test_task.model.Building;
import com.github.kerrrusha.dataox_test_task.model.Elevator;

public class ElevatorBuildingViewModel extends ViewModel {
    int currentStep;
    private final Elevator elevator;
    private final Building building;

    public ElevatorBuildingViewModel(Elevator elevator, Building building, int currentStep) {
        this.elevator = elevator;
        this.building = building;
        this.currentStep = currentStep;
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
    public void setCurrentStep(int step) {
        currentStep = step;
    }
}
