    package com.example.myapplication;

    import java.lang.reflect.Array;
    import java.util.ArrayList;
    import java.util.Stack;

    public class GoBoard implements Board{
        private int numRows;
        private int numCols;
        private GoStone board[][];
        //rows and cols corresponds to placeable areas
        //not grid, but rather the corners the stones can go on
        private GoStone KOboard1[][];
        private GoStone KOboard2[][];

        private int surroundLen;
        private int spacesLeft;

        private Stack<GoStone> delList = new Stack<>();

        private static GoBoard uniqueBoard;

        //default 9x1 board
        private GoBoard() {
            this.numRows = 9;
            this.numCols = 9;
            this.spacesLeft = numRows * numCols;
            this.board = new GoStone[numRows][numCols];
            for (int i = 0; i < numRows; i++) {
                for (int j = 0; j < numCols; j++) {
                    board[i][j] = new GoEmpty(i,j);
                }
            }
        }

        //customizable board dimensions and win condition
//        public GoBoard(int height, int width) {
//            this.numRows = height;
//            this.numCols = width;
//            this.spacesLeft = numRows * numCols;
//            this.board = new GoStone[numRows][numCols];
//            for (int i = 0; i < numRows; i++) {
//                for (int j = 0; j < numCols; j++) {
//                    board[i][j] = new GoEmpty(i,j);
//                }
//            }
//        }
        public void deletePiece(int row, int col){
            board[row][col] = new GoEmpty(row,col);
        }
        public ArrayList<Integer> placePiece(int row, int col, int playerNumber){
            if(board[row][col].getColor() != 0){
                return null;
                //a playerNumber is already there
            }
            GoStone Cur;
            if (playerNumber == 1) {
                Cur = new GoBlackStone(row,col);
            } else {
                Cur = new GoWhiteStone(row,col);
            }
            board[row][col] = Cur;
            ArrayList<Integer> ret = capture(row, col, playerNumber % 2 + 1);
            //compare with board1 or compare with board2 revert back board = board2

            if(libertyCount(row, col, playerNumber, true) == 0){
                board[row][col] = new GoEmpty(row,col);
                unCheck();
                delList.clear();
                return null;
                //illegal move
            }
            unCheck();
            delList.clear();
            spacesLeft--;
            //playerNumber successfully placed

            return ret;
        }

        public int libertyCount(int row, int col, int playerNumber, boolean puttingDown) {
            int liberty = 0;
            if (row < 0 || row == numRows || col < 0 || col == numCols) {
                return 0;
            }
            if (board[row][col].getColor() == 0 && !puttingDown) {
                return 1;
            }
            GoStone marked = board[row][col];
            int color = marked.getColor();
            if ((color != playerNumber && color!= 0) || marked.isChain()) {
                return 0;
            }
            marked.setChain(true);
            delList.push(marked);
            liberty += libertyCount(row - 1,col,playerNumber, false);
            liberty += libertyCount(row + 1,col,playerNumber, false);
            liberty += libertyCount(row,col - 1,playerNumber, false);
            liberty += libertyCount(row,col + 1,playerNumber, false);

            return liberty;
        }

        public ArrayList<Integer> capture(int row, int col, int playerNumber) {
            ArrayList<Integer> trueDel = new ArrayList<>();
            captureHelper(row - 1, col, playerNumber, trueDel);
            captureHelper(row + 1, col, playerNumber, trueDel);
            captureHelper(row, col - 1, playerNumber, trueDel);
            captureHelper(row, col + 1, playerNumber, trueDel);
            for (int i = 0; i < trueDel.size(); i++) {
                int index = trueDel.get(i);
                board[index / 9][index % 9] = new GoEmpty(row,col);
            }
            printBoard();
            return trueDel;
        }

        private void captureHelper(int row, int col, int playerNumber, ArrayList<Integer> trueDel) {
            if (libertyCount(row, col, playerNumber, false) == 0) {
                int size = delList.size();
                for (int i = 0; i < size; i++) {
                    GoStone Cur = delList.pop();
                    Cur.setChain(false);
                    trueDel.add(Cur.getIndex());
                }
            } else {
                unCheck();
            }
            delList.clear();
        }

        public boolean isBoardFull() {
            return (spacesLeft == 0);
        }

        public void printBoard() {
            //Loop through the board printing one character at a time
            for(int i=0; i<numRows; ++i) {
                for(int j=0; j<numCols; ++j) {
                    GoStone a = board[i][j];
                    System.out.print('[');
                    if (a.getColor() == 0) {
                        System.out.print(0);
                    } else {
                        System.out.print(board[i][j].getColor());
                    }
                    System.out.print("] ");
                }
                System.out.print('\n');
            }
        }

        private void unCheck() {
            for (GoStone i:
                 delList) {
                i.setChain(false);
            }
        }

        public static GoBoard getInstance() {
            if (uniqueBoard == null) {
                uniqueBoard = new GoBoard();
            }
            return uniqueBoard;
        }

        public int getNumRows() {
            return numRows;
        }

        public void setNumRows(int numRows) {
            this.numRows = numRows;
        }

        public int getNumCols() {
            return numCols;
        }

        public void setNumCols(int numCols) {
            this.numCols = numCols;
        }

        public Integer getPiece(int x, int y) {
            return board[x][y].getColor();
        }

        public int getSpacesLeft() {
            return spacesLeft;
        }
    }