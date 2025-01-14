package com.example.myapplication;

public class GoStone {
    private boolean chain = false;
    private boolean surrounded;
    private int color;
    private int row;
    private int col;
    private int index;

    public GoStone(int color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
        this.index = row * 9 + col;
    }

    public int getColor() {
        return this.color;
    }

    public boolean getSurrounded() {
        return this.surrounded;
    }

    public void setSurrounded(boolean surrounded) {
        this.surrounded = surrounded;
    }

    public boolean isChain() {
        return chain;
    }

    public void setChain(boolean chain) {
        this.chain = chain;
    }

    public int getIndex() {
        return index;
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
