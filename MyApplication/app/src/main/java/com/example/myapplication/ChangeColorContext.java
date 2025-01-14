package com.example.myapplication;

public class ChangeColorContext {
    private ChangeColorStrategy changeColorStrategy;

    public void setChangeColorStrategy(ChangeColorStrategy changeColorStrategy) {
        this.changeColorStrategy = changeColorStrategy;
    }

    public void changeColor(int row, int col) {
        changeColorStrategy.changeColor(row, col);
    }
}
