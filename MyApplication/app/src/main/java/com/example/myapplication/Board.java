package com.example.myapplication;

public interface Board {
    int getNumRows();
    int getNumCols();
    boolean isBoardFull();
    void printBoard();
    Integer getPiece(int x, int y);
}