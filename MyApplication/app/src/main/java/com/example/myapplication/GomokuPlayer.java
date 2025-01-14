package com.example.myapplication;

public class GomokuPlayer {
    private int color;
//    private String name;
//    private ImageView sprite;
    private int winCounter;
    private int drawCounter;

    public GomokuPlayer(int color) {
        this.color = color;
//        this.name = name;
//        this.sprite = sprite;
        this.winCounter = 0;
    }

    public int getColor() {
        return this.color;
    }


//    public String getName() {
//        return this.name;
//    }
//
//    public ImageView getSprite() {
//        return this.sprite;
//    }

    public int getWinCounter() {
        return this.winCounter;
    }

    public void setColor(int c) {
        this.color = c;
    }

    public void setWinCounter(int n) {
        this.winCounter = n;
    }

    public void addWin() {
        this.winCounter += 1;
    }
    public void setDrawCounter(int n) { this.drawCounter = n; }
    public int getDrawCounter() { return this.drawCounter; }
}
