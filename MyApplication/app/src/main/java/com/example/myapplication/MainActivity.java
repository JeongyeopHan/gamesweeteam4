package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button game1Button, game2Button, game3Button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        game1Button = findViewById(R.id.game1Button);
        game2Button = findViewById(R.id.game2Button);
        game3Button = findViewById(R.id.game3Button);

        game1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartScreenGomoku.class);
                startActivity(intent);
            }
        });

        game2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartScreenGo.class);
                startActivity(intent);
            }
        });

        game3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, StartScreenWordle.class);
                startActivity(intent);
            }
        });
        // tate was here
    }
    //this is a version control test
}