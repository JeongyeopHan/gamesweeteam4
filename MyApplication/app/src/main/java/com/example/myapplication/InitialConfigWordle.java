package com.example.myapplication;

//import android.content.Intent;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class InitialConfigWordle extends AppCompatActivity{
    private EditText inputEditText;
    private Button storeButton;
    private TextView displayTextView;
    private ImageView avatar1;
    private ImageView avatar2;
    public static String userName;
    public static ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_config_wordle);
        WordlePlayer.getInstance().setWinLoss(0,0);
        inputEditText = findViewById(R.id.inputName1);
        storeButton = findViewById(R.id.storeButton);
        displayTextView = findViewById(R.id.displayTextView);
        avatar1 = findViewById(R.id.avatar1);
        avatar2 = findViewById(R.id.avatar2);

        avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar = avatar1;
                clearAllFilters();
                avatar1.setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_ATOP);
            }
        });

        avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar = avatar2;
                if (avatar != null) {
                    clearAllFilters();
                }
                avatar2.setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_ATOP);
            }
        });

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName = inputEditText.getText().toString();
                if (avatar != null) {
                    avatar.clearColorFilter();
                }
                boolean invalid = true;
                for (int i = 0; i < userName.length(); i++){
                    if (userName.charAt(i) != ' '){
                        invalid = false;
                    }
                }
                if (invalid) {
                    inputEditText.setError("Invalid name");
                } else if (avatar == null) {
                    inputEditText.setError("Choose an avatar");
                } else {
                    Intent intent = new Intent(InitialConfigWordle.this, Wordle.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void clearAllFilters() {
        avatar1.clearColorFilter();
        avatar2.clearColorFilter();
    }

    public static class GomokuPlayer {

    }
}
