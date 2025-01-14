package com.example.myapplication;

public class GomokuBoard implements Board{
    private int numRows;
    private int numCols;
    private int board[][];
    //rows and cols corresponds to placeable areas
    //not grid, but rather the corners the stones can go on
    private int winLength;
    private int spacesLeft;

    private static GomokuBoard uniqueInstance;

    //default 19x19 board with 5 in a row win condition
    private GomokuBoard() {
        resetGomokuBoard();
    }

    public void resetGomokuBoard() {
        this.numRows = 19;
        this.numCols = 19;
        this.spacesLeft = numRows * numCols;
        this.board = new int[numRows][numCols];
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                board[i][j] = 0;
            }
        }
    }

    //customizable board dimensions and win condition
//    public GomokuBoard(int height, int width, int winLength) {
//        this.numRows = height;
//        this.numCols = width;
//        this.winLength = winLength;
//        this.spacesLeft = numRows * numCols;
//        this.board = new int[numRows][numCols];
//        for (int i = 0; i < numRows; i++) {
//            for (int j = 0; j < numCols; j++) {
//                board[i][j] = 0;
//            }
//        }
//    }

    public int placePiece(int row, int col, int playerNumber){
        if(col < 0 || col >= numCols){
            return -1;
            //out of bounds
        }
        if(row < 0 || row >= numRows){
            return -1;
            //out of bounds
        }
        if(board[row][col] != 0){
            return -1;
            //a playerNumber is already there
        }
        board[row][col] = playerNumber;
        spacesLeft--;
        //playerNumber successfully placed

        return checkWin(row, col, playerNumber);
    }

    public int checkWin(int row, int col, int playerNumber) {
        int boundCheck = winLength - 1;
        int colMax = col + boundCheck;
        int colMin = col - boundCheck;
        int rowMax = row + boundCheck;
        int rowMin = row - boundCheck;
        int counter = 0;

        if (colMin < 0) {
            colMin = 0;
        }
        if (colMax >= numCols) {
            colMax = numCols - 1;
        }

        if (rowMin < 0) {
            rowMin = 0;
        }
        if (rowMax >= numRows) {
            rowMax = numRows - 1;
        }

        //vertical check
        for (int i = rowMin; i <= rowMax; i++) {
            if (board[i][col] == playerNumber) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == winLength) {
                return playerNumber;
            }
        }
        counter = 0;
        //horizontal check
        for (int i = colMin; i <= colMax; i++) {
            if (board[row][i] == playerNumber) {
                counter++;
            } else {
                counter = 0;
            }
            if (counter == winLength) {
                return playerNumber;
            }
        }
        int diagRetVal =  diagonalChecks(row, col, playerNumber);

        //if the return value is 0, that means no win was found.
        //if it's a positive integer, that player won on a diagonal.
        if(diagRetVal > 0) {
            return diagRetVal;
        }

        //if code has made it this far, no win was found
        if(spacesLeft == 0){
            //return draw (board is full)
            return -2;
        } else {
            //no win or draw, game continues
            return 0;
        }
    }

    private int diagonalChecks(int row, int col, int playerNumber){
        int boundCheck = winLength - 1;
        int colMax = col + boundCheck;
        int colMin = col - boundCheck;
        int rowMax = row + boundCheck;
        int rowMin = row - boundCheck;
        int counter = 0;
        int diff = 0;

        if (colMin < 0) {
            diff = -colMin;
            colMin += diff;
            rowMin += diff;
        }
        if (colMax >= numCols) {
            diff = colMax - numCols + 1;
            colMax -= diff;
            rowMax -= diff;
        }

        if (rowMin < 0) {
            diff = -rowMin;
            colMin += diff;
            rowMin += diff;
        }
        if (rowMax >= numRows) {
            diff = rowMax - numRows + 1;
            colMax -= diff;
            rowMax -= diff;
        }

        //("\") diagonal check
        int a = colMin;
        for (int i = rowMin; i <= rowMax; i++) {
            if (a <= colMax) {
                if (board[i][a] == playerNumber) {
                    counter++;
                } else {
                    counter = 0;
                }
                if (counter == winLength) {
                    return playerNumber;
                }
            } else {
                break;
            }
            a++;
        }


        //variables are being reinitialized because its necessary for new calculations
        colMax = col + boundCheck;
        colMin = col - boundCheck;
        rowMax = row + boundCheck;
        rowMin = row - boundCheck;
        counter = 0;

        if (colMin < 0) {
            diff = -colMin;
            colMin += diff;
            rowMax -= diff;
        }
        if (colMax >= numCols) {
            diff = colMax - numCols + 1;
            colMax -= diff;
            rowMin += diff;
        }

        if (rowMin < 0) {
            diff = -rowMin;
            colMax -= diff;
            rowMin += diff;
        }
        if (rowMax >= numRows) {
            diff = rowMax - numRows + 1;
            colMin += diff;
            rowMax -= diff;
        }

        //("/") diagonal check
        int b = colMin;
        for (int i = rowMax; i >= rowMin; i--) {
            if (b <= colMax) {
                if (board[i][b] == playerNumber) {
                    counter++;
                } else {
                    counter = 0;
                }
                if (counter == winLength) {
                    return playerNumber;
                }
            } else {
                break;
            }
            b++;
        }


        //no win found yet
        return  0;
    }

    public boolean isBoardFull() {
        return (spacesLeft == 0);
    }

    public void printBoard() {

        //Loop through the board printing one character at a time
        for(int i=0; i<numRows; ++i) {
            for(int j=0; j<numCols; ++j) {
                System.out.print('[');
                System.out.print(board[i][j]);
                System.out.print("] ");
            }
            System.out.print('\n');
        }
    }

    @Override
    public Integer getPiece(int x, int y) {
        return null;
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public int getWinLength() {
        return winLength;
    }

    public int getSpacesLeft() {
        return spacesLeft;
    }

    public void setWinLength(int winLength) {
            this.winLength = winLength;
    }

    public static GomokuBoard getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new GomokuBoard();
        }
        return uniqueInstance;
    }
}