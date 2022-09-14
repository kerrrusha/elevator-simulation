package com.github.kerrrusha.dataox_test_task.view;

public abstract class View<T> {
    protected T viewModel;
    public View(T viewModel) {
        this.viewModel = viewModel;
    }
    public abstract void show();
    public abstract void show(String content);
    public abstract String representString();
}
