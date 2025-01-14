package com.example.myapplication;

//import android.content.Intent;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;

public class InitialConfigGomoku extends AppCompatActivity{
    private EditText inputName1;
    private EditText inputName2;
    private Button storeButton;
    private Button chooseAvatar1;
    private Button chooseAvatar2;
    private TextView displayTextView;
    private ImageView avatar1;
    private ImageView avatar2;
    private ImageView avatar3;
    private ImageView avatar4;
    public static String userName1;
    public static String userName2;
    public static ImageView player_avatar1;
    public static ImageView player_avatar2;
    public static GomokuPlayer player1 = new GomokuPlayer(1);
    public static GomokuPlayer player2 = new GomokuPlayer(2);
    //public int drawCount, player1WinCount, player2WinCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.initial_config_gomoku);
        EditText winCon = findViewById(R.id.wincondition);
        winCon.setText("5");

        player1.setWinCounter(0);
        player2.setWinCounter(0);
        player1.setDrawCounter(0);
        player2.setDrawCounter(0);
        inputName1 = findViewById(R.id.inputName1);
        inputName2 = findViewById(R.id.inputName2);
        chooseAvatar1 = findViewById(R.id.chooseAvatar1);
        chooseAvatar2 = findViewById(R.id.chooseAvatar2);
        storeButton = findViewById(R.id.storeButton);
        displayTextView = findViewById(R.id.textView2);
        //int[] temp = new int[] {drawCount, player1WinCount, player2WinCount};
        //displayTextView.setText(Arrays.toString(temp));
        avatar1 = findViewById(R.id.avatar1);
        avatar2 = findViewById(R.id.avatar2);
        avatar3 = findViewById(R.id.avatar3);
        avatar4 = findViewById(R.id.avatar4);

        chooseAvatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avatar1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        player_avatar1 = avatar1;
                        clearAllFilters();
                        avatar1.setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_ATOP);

                    }

                });
                avatar2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        player_avatar1 = avatar2;
                        clearAllFilters();
                        avatar2.setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_ATOP);}
                });
                avatar3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        player_avatar1 = avatar3;
                        clearAllFilters();
                        avatar3.setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_ATOP);
                    }
                });

                avatar4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        player_avatar1 = avatar4;
                        clearAllFilters();
                        avatar4.setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_ATOP);
                    }
                });
            }
        });
        chooseAvatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearAllFilters();
                avatar1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        player_avatar2 = avatar1;
                        clearAllFilters();
                        avatar1.setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_ATOP);
                    }
                });
                avatar2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        player_avatar2 = avatar2;
                        clearAllFilters();
                        avatar2.setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_ATOP);}
                });
                avatar3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        player_avatar2 = avatar3;
                        clearAllFilters();
                        avatar3.setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_ATOP);
                    }
                });

                avatar4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        player_avatar2 = avatar4;
                        clearAllFilters();
                        avatar4.setColorFilter(Color.parseColor("#80FFFFFF"), PorterDuff.Mode.SRC_ATOP);
                    }
                });
            }
        });

        storeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userName1 = inputName1.getText().toString();
                userName2 = inputName2.getText().toString();
                if(player_avatar1 != null && player_avatar2 != null) {
                    player_avatar1.clearColorFilter();
                    player_avatar2.clearColorFilter();
                }
                boolean invalid1 = true;
                boolean invalid2 = true;
                boolean invalid3 = false;
                for (int i = 0; i < userName1.length(); i++){
                    if (userName1.charAt(i) != ' '){
                        invalid1 = false;
                    }
                }
                for (int i = 0; i < userName2.length(); i++){
                    if (userName2.charAt(i) != ' '){
                        invalid2 = false;
                    }
                }

                GomokuBoard board = GomokuBoard.getInstance();
                EditText winCon = findViewById(R.id.wincondition);
                if (winCon.getText().toString().equals("")) {
                    invalid3 = true;
                } else {
                    board.setWinLength(Integer.parseInt(winCon.getText().toString()));
                    if (board.getWinLength() < 4 || board.getWinLength() > 9) {
                        invalid3 = true;
                    }
                }

                if (invalid1) {
                    inputName1.setError("Invalid name");
                } else if (invalid2) {
                    inputName2.setError("Invalid name");
                } else if (player_avatar1 == null) {
                    inputName1.setError("Choose an avatar");
                } else if (player_avatar2 == null) {
                    inputName2.setError("Choose an avatar");
                }else if (invalid3) {
                    winCon.setError("Please pick a number between 4-9");
                    winCon.setText("");
                } else {
                    Intent intent = new Intent(InitialConfigGomoku.this, Gomoku.class);
                    startActivity(intent);
                }
            }
        });
    }
    public void clearAllFilters() {
        avatar1.clearColorFilter();
        avatar2.clearColorFilter();
        avatar3.clearColorFilter();
        avatar4.clearColorFilter();
    }
}


