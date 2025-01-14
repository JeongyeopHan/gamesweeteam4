package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.graphics.Color;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Gomoku extends AppCompatActivity{

    public static String winMessage;
    final static int bSize = 19;
    private LinearLayout goBoard;
    private TextView player1Name;
    private ImageView player1Avatar;
    private TextView player2Name;
    private ImageView player2Avatar;
    private GomokuPlayer player1;
    private GomokuPlayer player2;
    private GomokuBoard board;
    private int row;
    private int col;
    private int piece;
    private int turn;
    private int gameState = -1;
    //private int drawCount = InitialConfigGomoku.drawCount;
    GomokuPlayer p1 = InitialConfigGomoku.player1;
    GomokuPlayer p2 = InitialConfigGomoku.player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        int player1WinCounter = p1.getWinCounter();
        int player2WinCounter = p2.getWinCounter();
        int drawCount = p1.getDrawCounter();
        Log.d("Gomoku", "Player 1 win count: " + p1.getWinCounter());
        Log.d("Gomoku", "Player 2 win count: " + p2.getWinCounter());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.gomoku);

        TextView player1Name = findViewById(R.id.player1Name);
        player1Name.setText(InitialConfigGomoku.userName1);
        TextView player2Name = findViewById(R.id.player2Name);
        player2Name.setText(InitialConfigGomoku.userName2);

        ImageView player1Avatar = findViewById(R.id.player1Avatar);
        player1Avatar.setImageDrawable(InitialConfigGomoku.player_avatar1.getDrawable());
        ImageView player2Avatar = findViewById(R.id.player2Avatar);
        player2Avatar.setImageDrawable(InitialConfigGomoku.player_avatar2.getDrawable());

        TextView player1WinCount = findViewById(R.id.player1WinCount);
        player1WinCount.setText(Integer.toString(player1WinCounter));
        TextView player2WinCount = findViewById(R.id.player2WinCount);
        player2WinCount.setText(Integer.toString(player2WinCounter));
        TextView draw = findViewById(R.id.player1LoseCount);
        draw.setText(Integer.toString(drawCount));

        boardMake();

        board = GomokuBoard.getInstance();
        board.resetGomokuBoard();
        turn = 0;
        Log.d("Gomoku", "game state is " + gameState);


    }
    private void boardMake(){
        GridLayout griddy = (GridLayout) findViewById(R.id.griddy);
        griddy.removeAllViews();
        for (int i = 0; i < bSize; i++) {
            for (int j = 0; j < bSize; j++) {
                View inflated = View.inflate(Gomoku.this, R.layout.intersection_button,griddy);
                View intersectionCur = (View) findViewById(R.id.empty_intersection);
                intersectionCur.setId(i * bSize + j);
                ImageButton buttonCur = (ImageButton) intersectionCur;
                if (i == 0) {
                    if (j == 0) {
                        buttonCur.setImageResource(R.drawable.wall_topleft);
                    } else if (j == bSize - 1) {
                        buttonCur.setImageResource(R.drawable.wall_topright);
                    } else {
                        buttonCur.setImageResource(R.drawable.wall_top);
                    }
                } else if (j == 0) {
                    if (i == bSize - 1) {
                        buttonCur.setImageResource(R.drawable.wall_bottomleft);
                    } else {
                        buttonCur.setImageResource(R.drawable.wall_left);
                    }
                } else if (i == bSize - 1) {
                    if (j == bSize - 1) {
                        buttonCur.setImageResource(R.drawable.wall_bottomright);
                    } else {
                        buttonCur.setImageResource(R.drawable.wall_bottom);
                    }
                } else if (j == bSize - 1) {
                    buttonCur.setImageResource(R.drawable.wall_right);
                }
            }
        }
    }
    public void place (View button) {
        if (gameState == 1) {
            //player 1 win
            play1Win();
            return;
        } else if (gameState == 2) {
            //player 2 win
            play2Win();
            return;
        }
        ImageButton buttonCur = (ImageButton) button;
        ImageView turnbox = (ImageView) findViewById(R.id.turnbox);
        piece = buttonCur.getId();
        row = piece / bSize;
        col = piece % bSize;
        gameState = board.placePiece(row, col, turn % 2 + 1);
        if (gameState != -1) {
            timeKeeper();
        }

        if (gameState == 0) {
            //0 means piece was placed and game continues
            // add piece, swap turns
            if(turn % 2 == 0) {
                buttonCur.setImageResource(R.drawable.black);
                turnbox.setBackgroundColor(Color.WHITE);
            } else {
                buttonCur.setImageResource(R.drawable.white);
                turnbox.setBackgroundColor(Color.BLACK);
            }
            turn++;
        } else if (gameState == -1) {
            //-1 means piece is out of bounds or on an occupied space, was not placed
            return;
        } else if (gameState == -2) {
            //-2 means board is full and no win is present, so it's a draw
            p1.setDrawCounter(p1.getDrawCounter() + 1);
            p2.setDrawCounter(p2.getDrawCounter() + 1);
            winMessage = "It's a draw!";
            Log.d("Gomoku", "draw count: " + p1.getDrawCounter());
            Intent intent = new Intent(Gomoku.this, EndGomoku.class);
            intent.putExtra("player1WinCounter", p1.getWinCounter());
            intent.putExtra("player2WinCounter", p2.getWinCounter());
            intent.putExtra("drawCounter", p1.getDrawCounter());
            startActivity(intent);
        } else if (gameState == 1) {
            //player 1 win
            play1Win();
        } else if (gameState == 2) {
            //player 2 win
            play2Win();
        } else if (gameState == -2) {
            //something went wrong if it makes it here
            return;
        }
    }

    private void play2Win() {
        p2.setWinCounter(p2.getWinCounter() + 1);
        winMessage = "Player 2 wins!";
        Log.d("Gomoku", "Player 2 win count: " + p2.getWinCounter());
        Intent intent = new Intent(Gomoku.this, EndGomoku.class);
        intent.putExtra("player1WinCounter", p1.getWinCounter());
        intent.putExtra("player2WinCounter", p2.getWinCounter());
        intent.putExtra("drawCounter", p1.getDrawCounter());
        startActivity(intent);
    }

    private void play1Win() {
        p1.setWinCounter(p1.getWinCounter() + 1);
        winMessage = "Player 1 wins!";
        Log.d("Gomoku", "Player 1 win count: " + p1.getWinCounter());
        Intent intent = new Intent(Gomoku.this, EndGomoku.class);
        intent.putExtra("player1WinCounter", p1.getWinCounter());
        intent.putExtra("player2WinCounter", p2.getWinCounter());
        intent.putExtra("drawCounter", p1.getDrawCounter());
        startActivity(intent);
    }

    private void timeKeeper() {
        if (turn != 0) {
            Timer.getInstance().stop();

        }
        TextView timeBox = findViewById(R.id.timer);
        Timer.getInstance().time(findViewById(timeBox.getId()), turn);
        if (timeBox.getText().equals("Player 2 ran out of time")) {
            gameState = 1;
        }
        if (timeBox.getText().equals("Player 1 ran out of time")) {
            gameState = 2;
        }
    }

}