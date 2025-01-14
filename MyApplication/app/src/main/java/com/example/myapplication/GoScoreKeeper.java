package com.example.myapplication;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
public class GoScoreKeeper {
    public static double[] checkScore(GoBoard board) {
        int blackSpace = 0;
        int whiteSpace = 0;
        boolean[][] visited = new boolean[board.getNumRows()][board.getNumCols()];
        for (int i = 0; i < board.getNumRows(); i++) {
            for (int j = 0; j < board.getNumCols(); j++) {
                //Log.d("GoScoreKeeper", "trying to check piece at location" + i + " and " + j);
                if (board.getPiece(i, j) == 0 && !visited[i][j]) {

                    ArrayList<int[]> cluster = findCluster(board, i, j);
                    //System.out.println("starting position" + i + j);
                    Set<Integer> colorList = new HashSet<Integer>();
                    for (int[] position : cluster) {
                        // print x, y coordinates in the cluster for debug and set them as visited
                        int x = position[0];
                        int y = position[1];
                        visited[x][y] = true;
                        //System.out.println("x: " + x + ", y: " + y);
                        // check neighbors for color
                        int localColor = checkNeighbor(board, x, y);
                        //System.out.println("local color at this place is" + localColor);
                        if (localColor != 0) {
                            colorList.add(localColor);
                        }

//                        if (localColor != -1) { // means this piece has same color neighbors
//                            colorList.add(localColor);
//                        }
                    }
                    if (colorList.size() == 1) { // if this cluster is surrounded by the same color
                        Integer[] colorArray = colorList.toArray(new Integer[0]);
                        if (colorArray[0] == 1) {
                            //System.out.println("black space");
                            for (int[] position : cluster) {
                                int x = position[0];
                                int y = position[1];
                                //System.out.println("x: " + x + ", y: " + y);
                            }

                            blackSpace += cluster.size();
                        } else if (colorArray[0] == 2) {
                            //System.out.println("white space");
                            for (int[] position : cluster) {
                                int x = position[0];
                                int y = position[1];
                                //System.out.println("x: " + x + ", y: " + y);
                            }
                            whiteSpace += cluster.size();
                        }
                    }
                }
            }
        }
        //System.out.println("black has " + blackSpace);
        //System.out.println("white has " + whiteSpace);
        double[] scores = new double[2];
        scores[0] = blackSpace;
        scores[1] = whiteSpace;

        return scores;
    }

    public static int checkNeighbor(GoBoard board, int X, int Y) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        //ArrayList<Integer> colorList = new ArrayList<>();
        Set<Integer> colorList = new HashSet<Integer>();
        for (int[] direction : directions) {
            int neighborX = X + direction[0];
            int neighborY = Y + direction[1];
            if (neighborX >= 0 && neighborX < board.getNumRows() && neighborY >= 0 && neighborY < board.getNumCols()
                    && board.getPiece(neighborX, neighborY) != 0) {
                colorList.add(board.getPiece(neighborX, neighborY));
                //System.out.println("the color at position " + neighborX + ", " + neighborY + " is " + board.getPiece(neighborX, neighborY));
            }
        }
        boolean allSame = colorList.size() == 1;
        if (allSame) { // means all neighbor is black or white
            Integer[] colorArray = colorList.toArray(new Integer[0]);
            return colorArray[0]; // return the color of that stone
        } else if (colorList.size() == 0){ // means all neighbors are empty
            return 0;
        }
        return -1; // means neighbor with both black and white
    }
    public static ArrayList<int[]> findCluster(GoBoard board, int startX, int startY) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        boolean[][] visited = new boolean[board.getNumRows()][board.getNumCols()];
        ArrayList<int[]> cluster = new ArrayList<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startX, startY});

        while (!queue.isEmpty()) {
            int[] currentPosition = queue.poll();
            int currentX = currentPosition[0];
            int currentY = currentPosition[1];

            // Check if the current position has the target value
            if (!visited[currentX][currentY]) {
                cluster.add(new int[]{currentX, currentY});
                visited[currentX][currentY] = true;
            }


            for (int[] direction : directions) {
                int dx = direction[0];
                int dy = direction[1];

                int neighborX = currentX + dx;
                int neighborY = currentY + dy;
                //System.out.println("place checking is x" + neighborX + "y"+ neighborY);
                // Check if the neighbor position is within the array bounds and has the value 0
                if (neighborX >= 0 && neighborX < board.getNumRows() && neighborY >= 0 && neighborY < board.getNumCols()
                        && !visited[neighborX][neighborY] && board.getPiece(neighborX, neighborY) == 0) {
                    //System.out.println("at position" + neighborX + " " + neighborY + "visited is" + visited[neighborX][neighborY]);
                    cluster.add(new int[]{neighborX, neighborY});
                    queue.offer(new int[]{neighborX, neighborY});
                    visited[neighborX][neighborY] = true;
                }
            }
            queue.remove(currentPosition);
        }
        return cluster;
    }
    public static int[][] generateBoard(int rows, int columns) {
        int[][] board = new int[rows][columns];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                int piece = random.nextInt(3); // Generate random piece value: 0, 1, or 2
                board[i][j] = piece;
            }
        }
        return board;
    }


}
