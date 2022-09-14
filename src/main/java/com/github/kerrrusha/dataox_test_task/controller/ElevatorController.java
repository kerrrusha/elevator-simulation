package com.github.kerrrusha.dataox_test_task.controller;

import com.github.kerrrusha.dataox_test_task.model.*;
import com.github.kerrrusha.dataox_test_task.view.ConsoleView;
import com.github.kerrrusha.dataox_test_task.view_model.ElevatorBuildingViewModel;

import java.util.List;
import java.util.Optional;

public class ElevatorController {
    private final Elevator elevator;
    private final Building building;
    private final ConsoleView view;
    private final ElevatorBuildingViewModel viewModel;

    public ElevatorController(Elevator elevator, Building building) {
        this.elevator = elevator;
        this.building = building;
        viewModel = new ElevatorBuildingViewModel(elevator, building);
        this.view = new ConsoleView(viewModel);
    }
    public void run() {
        view.show();
        loadPeople();
        viewModel.updateCurrentStep();
        view.show();
        while (getNextFloorNumber().isPresent()) {
            if (!moveElevator())
                return;
            unloadPeople();
            loadPeople();

            viewModel.updateCurrentStep();
            view.show();
        }
    }
    private void unloadPeople() {
        if (elevator.isEmpty())
            return;
        elevator.getPeopleIn().forEach(human -> {
            if (human.getDestinationFloor() == elevator.getCurrentFloorNumber()) {
                elevator.remove(human);
            }
        });
    }
    private void loadPeople() {
        Floor elevatorFloor = building.getFloor(elevator.getCurrentFloorNumber());
        if (elevatorFloor.isEmpty()) {
            return;
        }

        List<Human> peopleToLoad = elevator.isEmpty() ?
                elevatorFloor.getPeopleGoingTheSameWay() :
                elevatorFloor.getPeopleGoingInSuchWay(elevator.getDirection());

        for (Human human : peopleToLoad) {
            if (elevator.isFull())
                return;
            elevator.add(human);
            elevatorFloor.remove(human);
        }
    }
    private boolean moveElevator() {
        Optional<Integer> nextFloorOptional = getNextFloorNumber();
        if (nextFloorOptional.isEmpty())
            return false;
        elevator.setCurrentFloor(nextFloorOptional.get());
        return true;
    }
    private Optional<Integer> getNextFloorNumber() {
        if (building.isEmpty() && elevator.isEmpty())
            return Optional.empty();
        Direction elevatorDirection = elevator.getDirection();

        Optional<Integer> elevatorNextFloorNumberOptional = elevator.getNextFloor();
        Optional<Integer> buildingNextFloorNumberOptional = building.
                getNextNonEmptyFloor(elevator.getCurrentFloorNumber(), elevatorDirection);
        if (elevatorNextFloorNumberOptional.isEmpty() && buildingNextFloorNumberOptional.isEmpty()) {
            return Optional.empty();
        }
        if (elevator.isFull() && elevatorNextFloorNumberOptional.isPresent()) {
            return elevatorNextFloorNumberOptional;
        }
        if (elevatorNextFloorNumberOptional.isPresent() && buildingNextFloorNumberOptional.isPresent()) {
            if (elevatorDirection.equals(Direction.UP)) {
                int min = Math.min(elevatorNextFloorNumberOptional.get(),
                        buildingNextFloorNumberOptional.get());
                return Optional.of(min);
            } else {
                int max = Math.max(elevatorNextFloorNumberOptional.get(),
                        buildingNextFloorNumberOptional.get());
                return Optional.of(max);
            }
        }
        if (elevatorNextFloorNumberOptional.isPresent()) {
            return elevatorNextFloorNumberOptional;
        }
        return buildingNextFloorNumberOptional;
    }
}
