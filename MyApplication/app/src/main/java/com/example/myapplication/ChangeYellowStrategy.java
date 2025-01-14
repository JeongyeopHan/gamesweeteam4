package com.example.myapplication;

import android.graphics.Color;
import android.view.View;

public class ChangeYellowStrategy implements ChangeColorStrategy{
    @Override
    public void changeColor(int row, int col) {
        View[][] wordleViews = Wordle.getWordleViews();
        View view = wordleViews[row][col];
        int yellow = Color.parseColor("#EFCD65");
        view.setBackgroundColor(yellow);
    }
}
