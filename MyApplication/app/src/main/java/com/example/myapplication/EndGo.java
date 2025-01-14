package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EndGo extends AppCompatActivity{
    private TextView player1Name;
    private ImageView player1Avatar;
    private TextView player2Name;
    private ImageView player2Avatar;

    private Button playAgain, quitGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_go);

        TextView winMessage = findViewById(R.id.winMessage);
        winMessage.setText(Go.winMessage);

        player1Name = findViewById(R.id.player1Name);
        player1Name.setText(InitialConfigGo.userName1);
        player2Name = findViewById(R.id.player2Name);
        player2Name.setText(InitialConfigGo.userName2);

        player1Avatar = findViewById(R.id.player1Avatar);
        player1Avatar.setImageDrawable(InitialConfigGo.player_avatar1.getDrawable());
        player2Avatar = findViewById(R.id.player2Avatar);
        player2Avatar.setImageDrawable(InitialConfigGo.player_avatar2.getDrawable());

        TextView player1WinCount = findViewById(R.id.player1WinCount);
        int player1Win = getIntent().getIntExtra("player1WinCounter", 0);
        player1WinCount.setText(Integer.toString(player1Win));

        TextView player2WinCount = findViewById(R.id.player2WinCount);
        int player2Win = getIntent().getIntExtra("player2WinCounter", 0);
        player2WinCount.setText(Integer.toString(player2Win));

        TextView drawCount = findViewById(R.id.player1LoseCount);
        int draw = getIntent().getIntExtra("drawCounter", 0);
        drawCount.setText(Integer.toString(draw));

        TextView player1Score = findViewById(R.id.player1Score);
        double player1score = getIntent().getDoubleExtra("player1score", 0);
        player1Score.setText(Double.toString(player1score));

        TextView player2Score = findViewById(R.id.player2Score);
        double player2score = getIntent().getDoubleExtra("player2score", 0) + 6.5;
        player2Score.setText(Double.toString(player2score));

        playAgain = findViewById(R.id.playAgain);
        quitGame = findViewById(R.id.quitGame);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndGo.this, Go.class);
                startActivity(intent);
            }
        });
        quitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndGo.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}