package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import java.util.Random;
///**
// * Example local unit test, which will execute on the development machine (host).
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
public class GoUnitTest {
    //    //Jeongyeop Han Test1
//    //Test goStone Initialization
//    @Test
//    public void goStoneInitialization() {
//        GoStone stone = new GoStone(1, 2, 3);
//
//        assertEquals(stone.getColor(), 1);
//        assertEquals(stone.getRow(), 2);
//        assertEquals(stone.getCol(), 3);
//    }
//    //Jeongyeop Han Test2
//    //Test libertyCount method in GoBoard class
//    @Test
//    public void Test_libertyCount() {
//        GoBoard board = new GoBoard();
//        //Test libertyCount method without placing pieces
//        assertEquals(board.libertyCount(3, 4 , 2, true), 4);
//        //Test libertyCount method after placing pieces
//        board.placePiece(3, 3, 1);
//        assertEquals(board.libertyCount(3, 4 , 2, true), 3);
//
//        board.placePiece(3, 5, 1);
//        assertEquals(board.libertyCount(3, 4 , 2, true), 2);
//
//        board.placePiece(2, 4, 1);
//        assertEquals(board.libertyCount(3, 4 , 2, true), 1);
//    }
//
//    //Yuanning Test 1
//    @Test
//    public void testScoreKeeper1() {
//        GoBoard board = new GoBoard();
//        board.placePiece(4, 0, 1);
//        board.placePiece(3, 1, 1);
//        board.placePiece(2, 2, 1);
//        board.placePiece(1, 3, 1);
//        board.placePiece(0, 4, 1);
//        board.placePiece(8, 5, 2);
//        board.placePiece(7, 6, 2);
//        board.placePiece(6, 7, 2);
//        board.placePiece(5, 8, 2);
//        double[] scores = GoScoreKeeper.checkScore(board);
//        assertEquals(scores[0], 10.0, 1e-15);
//        assertEquals(scores[1], 6.0, 1e-15);
//    }
//
//    //Yuanning Test 2
//    @Test
//    public void testScoreKeeper2() {
//        GoBoard board = new GoBoard();
//        board.placePiece(4, 0, 1);
//        board.placePiece(3, 1, 1);
//        board.placePiece(2, 2, 1);
//        board.placePiece(1, 3, 1);
//        board.placePiece(0, 4, 1);
//        board.placePiece(1, 5, 1);
//        board.placePiece(2, 6, 1);
//        board.placePiece(3, 7, 1);
//        board.placePiece(4, 8, 1);
//
//        board.placePiece(5, 0, 2);
//        board.placePiece(5, 1, 2);
//        board.placePiece(5, 2, 2);
//        board.placePiece(6, 3, 2);
//        board.placePiece(7, 4, 2);
//        board.placePiece(8, 5, 2);
//        board.placePiece(7, 6, 2);
//        board.placePiece(6, 7, 2);
//        board.placePiece(5, 8, 2);
//
//        double[] scores = GoScoreKeeper.checkScore(board);
//        assertEquals(scores[0], 20.0, 1e-15);
//        assertEquals(scores[1], 18.0, 1e-15);
//    }
    //Zaid Test1
    //Test get num rows GoBoard
    @Test
    public void getRows() {
        GoBoard board1 = GoBoard.getInstance();
        assertEquals(board1.getNumRows(), 9);

        GoBoard board2 = GoBoard.getInstance();
        board2.setNumRows(5);
        assertEquals(board2.getNumRows(), 5);
    }

    //Zaid Test2
    //Test get num cols GoBoard
    @Test
    public void getCols() {
        GoBoard board1 = GoBoard.getInstance();
        assertEquals(board1.getNumRows(), 9);

        GoBoard board2 = GoBoard.getInstance();
        board2.setNumCols(6);
        assertEquals(board2.getNumCols(), 6);
    }
//
//    // Taiki Test 1
//    // Test getSpacesLeft GoBoard
//    @Test
//    public void testGetSpacesLeft() {
//        GoBoard board1 = new GoBoard();
//        assertEquals(board1.getSpacesLeft(), 81);
//        board1.placePiece(4, 0, 1);
//        board1.placePiece(3, 1, 1);
//        board1.placePiece(2, 2, 1);
//        board1.placePiece(1, 3, 1);
//        board1.placePiece(0, 4, 1);
//        assertEquals(board1.getSpacesLeft(), 76);
//
//        GoBoard board2 = new GoBoard(5, 6);
//        assertEquals(board2.getSpacesLeft(), 30);
//        board2.placePiece(4, 0, 2);
//        board2.placePiece(3, 1, 2);
//        board2.placePiece(2, 2, 2);
//        board2.placePiece(1, 3, 2);
//        board2.placePiece(0, 4, 2);
//        assertEquals(board2.getSpacesLeft(), 25);
//    }
//
//    // Taiki Test 2
//    // Test getPiece GoBoard
//    @Test
//    public void testGetPiece() {
//        GoBoard board = new GoBoard();
//        board.placePiece(4, 0, 1);
//        board.placePiece(3, 1, 2);
//        board.placePiece(2, 2, 1);
//        board.placePiece(1, 3, 2);
//        board.placePiece(0, 4, 1);
//        assertEquals(board.getPiece(4, 0), (Integer) 1);
//        assertEquals(board.getPiece(3, 1), (Integer) 2);
//        assertEquals(board.getPiece(2, 2), (Integer) 1);
//        assertEquals(board.getPiece(1, 3), (Integer) 2);
//        assertEquals(board.getPiece(0, 4), (Integer) 1);
//    }
//
//    //Tate Unit 1
//    //test capture method
//    @Test
//    public void testCapture() {
//        GoBoard board = new GoBoard();
//        board.placePiece(1, 1, 1);
//        board.placePiece(1, 2, 1);
//        board.placePiece(2, 1, 1);
//        board.placePiece(2, 2, 1);
//        assertEquals(board.getPiece(1, 1), (Integer) 1);
//        board.placePiece(0, 1, 2);
//        board.placePiece(0, 2, 2);
//        board.placePiece(3, 1, 2);
//        board.placePiece(3, 2, 2);
//        board.placePiece(1, 0, 2);
//        board.placePiece(2, 0, 2);
//        board.placePiece(1, 3, 2);
//        board.placePiece(2, 3, 2);
//        assertEquals(board.getPiece(1, 1), (Integer) 0);
//
//
//    }
//    //Tate Unit 1
//    //test capture on every side
//    @Test
//    public void testCaptureComp() {
//        GoBoard board = new GoBoard();
//        board.placePiece(0, 2, 1);
//        board.placePiece(4, 2, 1);
//        board.placePiece(2, 0, 1);
//        board.placePiece(2, 4, 1);
//        board.placePiece(1, 1, 1);
//        board.placePiece(3, 1, 1);
//        board.placePiece(1, 3, 1);
//        board.placePiece(3, 3, 1);
//        board.placePiece(1, 2, 2);
//        board.placePiece(2, 1, 2);
//        board.placePiece(3, 2, 2);
//        board.placePiece(2, 3, 2);
//        board.placePiece(3, 2, 2);
//        assertEquals(board.getPiece(1, 2), (Integer) 2);
//        assertEquals(board.getPiece(2, 1), (Integer) 2);
//        assertEquals(board.getPiece(3, 2), (Integer) 2);
//        assertEquals(board.getPiece(2, 3), (Integer) 2);
//        board.placePiece(2, 2, 1);
//        assertEquals(board.getPiece(1, 2), (Integer) 0);
//        assertEquals(board.getPiece(2, 1), (Integer) 0);
//        assertEquals(board.getPiece(3, 2), (Integer) 0);
//        assertEquals(board.getPiece(2, 3), (Integer) 0);
//
//
//    }
//
//
//    //Hussein Test 1
//    //testing what happens when a piece is actually present
//    @Test
//    public void testDeletePiece1(){
//        GoBoard board = new GoBoard();
//
//        board.placePiece(0,0,1);
//        assertEquals(1,(int) board.getPiece(0,0));
//        board.deletePiece(0,0);
//        assertEquals(0, (int) board.getPiece(0,0));
//
//        board.placePiece(7,7,2);
//        assertEquals(2,(int) board.getPiece(7,7));
//        board.deletePiece(7,7);
//        assertEquals(0, (int) board.getPiece(7,7));
//    }
//
//    //Hussein test 2
//    //testing if deletePiece errors when called on cells with nothing to delete
//    @Test
//    public void testDeletePiece2(){
//        GoBoard board = new GoBoard();
//
//        assertEquals(0, (int) board.getPiece(4,5));
//        board.deletePiece(4,5);
//        assertEquals(0, (int) board.getPiece(4,5));
//
//        assertEquals(0, (int) board.getPiece(8,8));
//        board.deletePiece(8,8);
//        assertEquals(0, (int) board.getPiece(8,8));
//    }
}