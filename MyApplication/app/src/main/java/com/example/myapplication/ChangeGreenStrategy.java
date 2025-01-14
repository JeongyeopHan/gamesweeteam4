package com.example.myapplication;

import android.graphics.Color;
import android.view.View;

public class ChangeGreenStrategy implements ChangeColorStrategy {
    @Override
    public void changeColor(int row, int col) {
        View[][] wordleViews = Wordle.getWordleViews();
        View view = wordleViews[row][col];
        int green = Color.parseColor("#FF57D65C");
        view.setBackgroundColor(green);
    }
}
