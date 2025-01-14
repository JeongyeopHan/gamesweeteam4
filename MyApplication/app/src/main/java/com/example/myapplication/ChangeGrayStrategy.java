package com.example.myapplication;

import android.graphics.Color;
import android.view.View;
public class ChangeGrayStrategy implements ChangeColorStrategy {
    public void changeColor(int row, int col) {
        View[][] wordleViews = Wordle.getWordleViews();
        View view = wordleViews[row][col];
        int gray = Color.parseColor("#888888");
        view.setBackgroundColor(gray);
    }
}
