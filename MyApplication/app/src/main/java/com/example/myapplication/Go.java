package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.graphics.Color;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Go extends AppCompatActivity{
    public static String winMessage;
    final static int bSize = 9;
    private LinearLayout goBoard;
    private TextView player1Name;
    private ImageView player1Avatar;
    private TextView player2Name;
    private ImageView player2Avatar;
    private GoPlayer player1;
    private GoPlayer player2;
    private GoBoard board;
    private int row;
    private int col;
    private int piece;
    private int turn;
    private int lastPass = -1;
    private boolean gameEnded = false;
    private int gameState = -1;
    //private int drawCount = InitialConfigGo.drawCount;
    GoPlayer p1 = InitialConfigGo.player1;
    GoPlayer p2 = InitialConfigGo.player2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        int player1WinCounter = p1.getWinCounter();
        int player2WinCounter = p2.getWinCounter();
        int drawCount = p1.getDrawCounter();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.go);

        TextView player1Name = findViewById(R.id.player1Name);
        player1Name.setText(InitialConfigGo.userName1);
        TextView player2Name = findViewById(R.id.player2Name);
        player2Name.setText(InitialConfigGo.userName2);

        ImageView player1Avatar = findViewById(R.id.player1Avatar);
        player1Avatar.setImageDrawable(InitialConfigGo.player_avatar1.getDrawable());
        ImageView player2Avatar = findViewById(R.id.player2Avatar);
        player2Avatar.setImageDrawable(InitialConfigGo.player_avatar2.getDrawable());

        TextView player1WinCount = findViewById(R.id.player1WinCount);
        player1WinCount.setText(Integer.toString(player1WinCounter));
        TextView player2WinCount = findViewById(R.id.player2WinCount);
        player2WinCount.setText(Integer.toString(player2WinCounter));
        TextView draw = findViewById(R.id.player1LoseCount);
        draw.setText(Integer.toString(drawCount));

        boardMake();

        board = GoBoard.getInstance();
        turn = 0;
        Log.d("Go", "game state is " + gameState);
    }
    private void boardMake(){
        GridLayout griddy = (GridLayout) findViewById(R.id.griddy);
        griddy.removeAllViews();
        for (int i = 0; i < bSize; i++) {
            for (int j = 0; j < bSize; j++) {
                View inflated = View.inflate(Go.this, R.layout.intersection_button,griddy);
                View intersectionCur = (View) findViewById(R.id.empty_intersection);
                intersectionCur.setId(i * bSize + j);
                ImageButton buttonCur = (ImageButton) intersectionCur;
                boardHelp(i, j, buttonCur);
            }
        }
    }
    public void place (View button) {
        ImageButton buttonCur = (ImageButton) button;
        ImageView turnbox = (ImageView) findViewById(R.id.turnbox);
        piece = buttonCur.getId();
        row = piece / bSize;
        col = piece % bSize;

        Button endGame = findViewById(R.id.endGameButton);
        endGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double[] scores = GoScoreKeeper.checkScore(board);
                Log.d("Go", "scores are" + scores[0] + " and " + scores[1]);
                // scores [blackSpace, whiteSpace], 6.5 is given to white to compensate going second
                if (scores[0] - scores[1] - 6.5 == 0) {
                    // draw
                    p1.setDrawCounter(p1.getDrawCounter() + 1);
                    p2.setDrawCounter(p2.getDrawCounter() + 1);
                    winMessage = "It's a draw!";
                    Log.d("Go", "draw count: " + p1.getDrawCounter());
                    Intent intent = new Intent(Go.this, EndGo.class);
                    intent.putExtra("player1score", scores[0]);
                    intent.putExtra("player2score", scores[1]);
                    intent.putExtra("player1WinCounter", p1.getWinCounter());
                    intent.putExtra("player2WinCounter", p2.getWinCounter());
                    intent.putExtra("drawCounter", p1.getDrawCounter());
                    startActivity(intent);
                } else if (scores[0] - scores[1] - 6.5 > 0) {
                    //player 1 wins, black wins
                    p1.setWinCounter(p1.getWinCounter() + 1);
                    winMessage = "Player 1 wins!";
                    Log.d("Go", "Player 1 win count: " + p1.getWinCounter());
                    Intent intent = new Intent(Go.this, EndGo.class);
                    intent.putExtra("player1score", scores[0]);
                    intent.putExtra("player2score", scores[1]);
                    intent.putExtra("player1WinCounter", p1.getWinCounter());
                    intent.putExtra("player2WinCounter", p2.getWinCounter());
                    intent.putExtra("drawCounter", p1.getDrawCounter());
                    startActivity(intent);
                } else if (scores[0] - scores[1] - 6.5 < 0) {
                    //player 2 win, white wins
                    p2.setWinCounter(p2.getWinCounter() + 1);
                    winMessage = "Player 2 wins!";
                    Log.d("Gomoku", "Player 2 win count: " + p2.getWinCounter());
                    Intent intent = new Intent(Go.this, EndGo.class);
                    intent.putExtra("player1score", scores[0]);
                    intent.putExtra("player2score", scores[1]);
                    intent.putExtra("player1WinCounter", p1.getWinCounter());
                    intent.putExtra("player2WinCounter", p2.getWinCounter());
                    intent.putExtra("drawCounter", p1.getDrawCounter());
                    startActivity(intent);
                }
            }
        });

        if (gameEnded) {
            board.deletePiece(row, col);
            buttonCur.setImageResource(R.drawable.blank_intersection);
            boardHelp(row, col, buttonCur);
            return;
        }
        ArrayList<Integer> delist = board.placePiece(row, col, turn % 2 + 1);
        if (delist != null) {
            //0 means piece was placed and game continues
            // add piece, swap turns
            if (turn % 2 == 0) {
                buttonCur.setImageResource(R.drawable.black);
                turnbox.setBackgroundColor(Color.WHITE);
            } else {
                buttonCur.setImageResource(R.drawable.white);
                turnbox.setBackgroundColor(Color.BLACK);
            }
            if (delist.size() > 0) {
                for (int index:
                     delist) {
                    View viewThing = findViewById(index);
                    ImageButton buttonNo = (ImageButton) viewThing;
                    boardHelp(index / 9, index % 9, buttonNo);
                }
            }
            turn++;
        }
    }

    private void boardHelp (int i, int j, ImageButton buttonCur) {
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
        } else {
            buttonCur.setImageResource(R.drawable.blank_intersection);
        }
    }
    public void pass(View button) {
        Log.d("Go", "Pass");
        if (lastPass >= 0 && lastPass == turn - 1) {
            gameEnded = true;
            Button passBut = findViewById(R.id.passButton);
            passBut.setVisibility(View.GONE);
            Button endGame = findViewById(R.id.endGameButton);
            endGame.setVisibility(View.VISIBLE);
            return;
        }
        lastPass = turn;
        ImageView turnbox = (ImageView) findViewById(R.id.turnbox);
        if (turn % 2 == 0) {
            turnbox.setBackgroundColor(Color.WHITE);
        } else {
            turnbox.setBackgroundColor(Color.BLACK);
        }
        turn++;
    }
}