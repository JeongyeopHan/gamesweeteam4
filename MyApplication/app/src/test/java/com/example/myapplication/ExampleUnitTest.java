package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    //Hussein unit test 1
    //tests default constructor and custom constructor for board
    public void boardInitialization() {
        //checking if default constructor sets board properly
        GomokuBoard board1 = GomokuBoard.getInstance();

        assertEquals(board1.getNumRows(),19);
        assertEquals(board1.getNumCols(),19);
        assertEquals(board1.getSpacesLeft(),19*19);
        assertEquals(board1.getWinLength(),5);

        //checking if customizable constructor sets board properly
        GomokuBoard board2 = GomokuBoard.getInstance();

        assertEquals(board2.getNumRows(),4);
        assertEquals(board2.getNumCols(),8);
        assertEquals(board2.getSpacesLeft(),4*8);
        assertEquals(board2.getWinLength(),7);

        GomokuBoard board3 = GomokuBoard.getInstance();

        assertEquals(board3.getNumRows(),30);
        assertEquals(board3.getNumCols(),21);
        assertEquals(board3.getSpacesLeft(),30*21);
        assertEquals(board3.getWinLength(),2);

        GomokuBoard board4 = GomokuBoard.getInstance();

        assertEquals(board4.getNumRows(),15);
        assertEquals(board4.getNumCols(),15);
        assertEquals(board4.getSpacesLeft(),15*15);
        assertEquals(board4.getWinLength(),6);
    }

    @Test
    //Hussein unit test 2
    //tests horizontal wins and vertical wins
    public void test_horizontal_and_vertical() {
        GomokuBoard board = GomokuBoard.getInstance();
        //vertical check
        assertEquals(board.placePiece(0,0,2),0);
        assertEquals(board.placePiece(1,0,2),0);
        assertEquals(board.placePiece(2,0,2),0);
        assertEquals(board.placePiece(3,0,2),0);
        assertEquals(board.placePiece(4,0,2),2);
        //horizontal check
        assertEquals(board.placePiece(18,14,1),0);
        assertEquals(board.placePiece(18,15,1),0);
        assertEquals(board.placePiece(18,16,1),0);
        assertEquals(board.placePiece(18,17,2),0);
        assertEquals(board.placePiece(18,18,1),0);
        assertEquals(board.placePiece(18,12,1),0);
        assertEquals(board.placePiece(18,11,2),0);
        assertEquals(board.placePiece(18,13,1),1);
    }

    @Test
    //Zaid unit test 1
    //Checks player initialization
    public void playerInitialization() {
        //checking if  constructor sets player properly
        GomokuPlayer player1 = new GomokuPlayer(1);

        assertEquals(player1.getColor(),1);
        assertEquals(player1.getWinCounter(),0);
    }

    @Test
    //Zaid unit test 2
    //Checks win incrementer
    public void playerAddWin() {
        //checking if  constructor sets player properly
        GomokuPlayer player1 = new GomokuPlayer(1);
        assertEquals(player1.getWinCounter(),0);
        player1.addWin();
        assertEquals(player1.getWinCounter(),1);
        player1.addWin();
        assertEquals(player1.getWinCounter(),2);
    }

    @Test
    //Taiki unit test 1
    public void test_placePiece() {
        GomokuBoard board = GomokuBoard.getInstance();
        assertEquals(board.placePiece(0,2,1), -1);
        assertEquals(board.placePiece(0,-1,1), -1);
        assertEquals(board.placePiece(0,0,1), 0);
        assertEquals(board.placePiece(0,0,1), -1);
        assertEquals(board.getSpacesLeft(), 3);
    }

    @Test
    //Taiki unit test 2
    public void test_isBoardFull() {
        GomokuBoard board1 = GomokuBoard.getInstance();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(board1.isBoardFull(), false);
                board1.placePiece(i,j,1);
                assertEquals(board1.placePiece(i, j, 1), -1);
            }
        }
        assertEquals(board1.getSpacesLeft(), 0);
        assertEquals(board1.isBoardFull(), true);

        GomokuBoard board2 = GomokuBoard.getInstance();
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertEquals(board2.isBoardFull(), false);
                board2.placePiece(i,j,1);
                assertEquals(board2.placePiece(i, j, 1), -1);
            }
        }
        assertEquals(board2.getSpacesLeft(), 0);
        assertEquals(board2.isBoardFull(), true);
    }

    //Yuanning unit test 1
    @Test
    public void test_repeated_piece() {
        GomokuBoard board = GomokuBoard.getInstance();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                assertEquals(board.placePiece(i,j,1), 0);
                assertEquals(board.placePiece(i,j,1), -1);
                assertEquals(board.placePiece(i,j,2), -1);
            }
        }
    }

    //Yuanning unit test 2
    @Test
    public void test_draw() {
        GomokuBoard board = GomokuBoard.getInstance();
        assertEquals(board.placePiece(0,0,1), 0);
        assertEquals(board.placePiece(0,1,2), 0);
        assertEquals(board.placePiece(0,2,1), 0);
        assertEquals(board.placePiece(1,1,2), 0);
        assertEquals(board.placePiece(1,0,1), 0);
        assertEquals(board.placePiece(2,0,2), 0);
        assertEquals(board.placePiece(1,2,1), 0);
        assertEquals(board.placePiece(2,2,2), 0);
        assertEquals(board.placePiece(2,1,1), -2);
    }

    //Tate unit test 1
    @Test
    public void test_notWin() {
        GomokuBoard boardA = GomokuBoard.getInstance();
        for (int i = 0; i < 4; i++) {
            assertEquals(boardA.placePiece(i,4,1), 0);
            assertEquals(boardA.placePiece(i,i,1), 0);
            assertEquals(boardA.placePiece(4,i,1), 0);
            assertEquals(boardA.placePiece(i + 5,i + 5,1), 0);
            assertEquals(boardA.placePiece(i + 5,4,1), 0);
            assertEquals(boardA.placePiece(4,i + 5,1), 0);
            assertEquals(boardA.placePiece(8-i,i,1), 0);
            assertEquals(boardA.placePiece(3-i,i+5,1), 0);
        }
        boardA.printBoard();
        assertEquals(boardA.placePiece(4,4,1), 1);
    }

    //Tate unit test 2
    @Test
    public void test_diagonal() {

        //"\" diagonal
        GomokuBoard boardA = GomokuBoard.getInstance();
        for (int i = 0; i < 4; i++) {
            assertEquals(boardA.placePiece(i,i,1), 0);
        }
        assertEquals(boardA.placePiece(4,4,1), 1);

        //"/" diagonal
        GomokuBoard boardB = GomokuBoard.getInstance();
        for (int i = 0; i < 4; i++) {
            assertEquals(boardB.placePiece(4 - i,i,2), 0);
        }
        assertEquals(boardB.placePiece(0,4,2), 2);
    }

    @Test
    public void GoScoreTest() {
        //int[][] board1 = GoScoreKeeper.generateBoard(9, 9);
        GoBoard board1 = GoBoard.getInstance();
        for (int i = 0; i < board1.getNumRows(); i++) {
            // Iterate over each column
            for (int j = 0; j < board1.getNumCols(); j++) {
                System.out.print(board1.getPiece(i,j) + " ");
            }
            System.out.println(); // Move to the next line after each row
        }
        GoScoreKeeper.checkScore(board1);
    }
}