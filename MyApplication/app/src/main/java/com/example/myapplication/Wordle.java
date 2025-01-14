package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import org.w3c.dom.Text;

public class Wordle extends AppCompatActivity{
    private TextView nameDisplay;
    private ImageView dynamicImageView;
    private char[] qwerty = {'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P',
            'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', '*',
            'Z', 'X', 'C', 'V', 'B', 'N', 'M','*','*','*'};
    private String curr = "";
    private int row = 0;
    private int col = -1;
    private static View[][] wordleViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.wordle);
        nameDisplay = findViewById(R.id.player1Name);
        nameDisplay.setText(InitialConfigWordle.userName);
        dynamicImageView = findViewById(R.id.player1Avatar);
        dynamicImageView.setImageDrawable(InitialConfigWordle.avatar.getDrawable());
        boardMake();
        /* change color to gray
        ChangeColorContext colorContext = new ChangeColorContext();
        ChangeColorStrategy grayStrategy = new ChangeGrayStrategy();
        colorContext.setChangeColorStrategy(grayStrategy);
        colorContext.changeColor(1, 4);
         */
    }

    private void boardMake(){
        GridLayout keyboard = (GridLayout) findViewById(R.id.KeyBoard);
        keyboard.removeAllViews();
        WordlePlayer.getInstance().resetLives();
        View count = (View) findViewById(R.id.numLives);
        TextView liveCount = (TextView) count;
        liveCount.setText(String.format("%d",WordlePlayer.getInstance().getLives()));
        WordleLogic.getInstance().newWord();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 10; j++) {
                View inflated = View.inflate(Wordle.this, R.layout.key_button, keyboard);
                View intersectionCur = (View) findViewById(R.id.button_x);
                intersectionCur.setId(10 * i + j);
                FrameLayout cur = (FrameLayout) intersectionCur;
                cur.getChildAt(0).setId(10 * i + j);
                if(10 * i + j == 19) {
                    cur.getChildAt(0).setOnClickListener(this::delete);
                    TextView key = (TextView) cur.getChildAt(1);
                    key.setText("<");
                }else if (10 * i + j >= 27) {
                        cur.getChildAt(0).setOnClickListener(this::submit);
                        TextView key = (TextView) cur.getChildAt(1);
                        key.setText("=");
                } else {
                    cur.getChildAt(0).setId(10 * i + j);
                    TextView key = (TextView) cur.getChildAt(1);
                    key.setText(Character.toString(qwerty[10 * i + j]));
                }
            }
        }

        GridLayout wordleBoard = (GridLayout) findViewById(R.id.wordleBoard);
        wordleBoard.removeAllViews();
        wordleViews = new View[6][5];

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                View boxInflated = View.inflate(Wordle.this, R.layout.wordleboard, wordleBoard);
                View intersectionCur1 = (View) findViewById(R.id.wordleBox);
                intersectionCur1.setId(5 * i + j + 100);

                FrameLayout cur1 = (FrameLayout) intersectionCur1;
                cur1.getChildAt(0).setId(5 * i + j + 100);

                wordleViews[i][j] = intersectionCur1;
            }
        }
    }
    //this builds the current word guess
    //if the current String is not already filled, it will append the character being pressed
    public void append(View key) {

        char letter = qwerty[key.getId()];
        if (curr.length() < 5) {
            col ++;
            System.out.println("appending letter" + row);
            //this is where you update the grid han/yaunning
            int index = 5 * row + col + 100;
            FrameLayout aaa = (FrameLayout) findViewById(index);
            TextView gridText = (TextView) aaa.getChildAt(1);
            gridText.setText(Character.toString(qwerty[key.getId()]));
            curr = curr + letter;
        }
        System.out.println(curr);
    }

    //this allows players to delete unwanted characters
    //if the current row is not empty, it will delete the last character
    public void delete(View key) {
        if (curr.length() > 0) {
            //this is where you update the grid han/yaunning
            int index = 5 * row + col + 100;
            FrameLayout aaa = (FrameLayout) findViewById(index);
            TextView gridText = (TextView) aaa.getChildAt(1);
            gridText.setText("");
            curr = curr.substring(0, curr.length() - 1);
            col --;
        }
        System.out.println(curr);
    }

    //this method communicates with game logic in order to evaluate the guess
    public void submit(View key) {
        if (curr.length() == 5) {
            //this is where you call for the logic Taiki/ hussein
            WordleLogic.checkWord(curr);
            col = -1;

            int[] color = WordleLogic.checkWord(curr);
            boolean isGreen = true;
            for (int i = 0; i < 5; i++) {
                if (color[i] == 0) {
                    ChangeColorContext colorContext = new ChangeColorContext();
                    ChangeColorStrategy grayStrategy = new ChangeGrayStrategy();
                    colorContext.setChangeColorStrategy(grayStrategy);
                    colorContext.changeColor(row, i);
                    isGreen = false;
                } else if (color[i] == 1) {
                    ChangeColorContext colorContext = new ChangeColorContext();
                    ChangeColorStrategy greenStrategy = new ChangeGreenStrategy();
                    colorContext.setChangeColorStrategy(greenStrategy);
                    colorContext.changeColor(row, i);

                } else if (color[i] == 2) {
                    ChangeColorContext colorContext = new ChangeColorContext();
                    ChangeColorStrategy yellowStrategy = new ChangeYellowStrategy();
                    colorContext.setChangeColorStrategy(yellowStrategy);
                    colorContext.changeColor(row, i);
                    isGreen = false;
                }
            }

            if (isGreen) {
                // go to end screen
                Intent intent = new Intent(Wordle.this, EndWordle.class);
                WordlePlayer.getInstance().addWin();
                int loss = WordlePlayer.getInstance().getLoss();
                int win = WordlePlayer.getInstance().getWins();
                WordlePlayer.getInstance().setWinLoss(win, loss);
                intent.putExtra("player1WinCounter", WordlePlayer.getInstance().getWins());
                intent.putExtra("player1LoseCounter", WordlePlayer.getInstance().getLoss());
                startActivity(intent);
            }

            row += 1;

            WordlePlayer.getInstance().decrementLives();
            View count = (View) findViewById(R.id.numLives);
            TextView liveCount = (TextView) count;
            liveCount.setText(String.format("%d",WordlePlayer.getInstance().getLives()));

            curr = "";
            if (WordlePlayer.getInstance().getLives() == 0) {
                boolean notGreen = false;
                for (int i = 0; i < color.length; i++) {
                    if (color[i] != 1) {
                        notGreen = true;
                    }
                }
                if (notGreen) {
                    //lose the game
                    WordlePlayer.getInstance().addLoss();
                    int loss = WordlePlayer.getInstance().getLoss();
                    int win = WordlePlayer.getInstance().getWins();
                    WordlePlayer.getInstance().setWinLoss(win, loss);
                    Intent intent = new Intent(Wordle.this, EndWordle.class);
                    intent.putExtra("player1WinCounter", WordlePlayer.getInstance().getWins());
                    intent.putExtra("player1LoseCounter", WordlePlayer.getInstance().getLoss());
                    startActivity(intent);

                }
//                else {
//                    WordlePlayer.getInstance().addWin();
//                    int loss = WordlePlayer.getInstance().getLoss();
//                    int win = WordlePlayer.getInstance().getWins();
//                    WordlePlayer.getInstance().setWinLoss(win, loss);
//                    Intent intent = new Intent(Wordle.this, EndWordle.class);
//                    intent.putExtra("player1WinCounter", WordlePlayer.getInstance().getWins());
//                    startActivity(intent);
//                }

                //game won
            }
        }
    }

    public static View[][] getWordleViews() {
        return wordleViews;
    }
}
