package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EndWordle extends AppCompatActivity{
    private TextView player1Name;
    private ImageView player1Avatar;
    private Button playAgain, quitGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.end_wordle);

        TextView winMessage = findViewById(R.id.winMessage);
        winMessage.setText(Gomoku.winMessage);

        player1Name = findViewById(R.id.player1Name);
        player1Name.setText(InitialConfigWordle.userName);

        player1Avatar = findViewById(R.id.player1Avatar);
        player1Avatar.setImageDrawable(InitialConfigWordle.avatar.getDrawable());

        TextView player1WinCount = findViewById(R.id.player1WinCount);
        TextView player1LoseCount = findViewById(R.id.player1LoseCount);
        int player1Win = getIntent().getIntExtra("player1WinCounter", 0);
        player1WinCount.setText(Integer.toString(player1Win));
        int player1Loss = getIntent().getIntExtra("player1LoseCounter", 0);
        player1LoseCount.setText(Integer.toString(player1Loss));

        playAgain = findViewById(R.id.playAgain);
        quitGame = findViewById(R.id.quitGame);

        playAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndWordle.this, Wordle.class);
                startActivity(intent);
            }
        });
        quitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EndWordle.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}


