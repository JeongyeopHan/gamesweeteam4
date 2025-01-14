package com.example.myapplication;

public class WordleLogic {
    private volatile static WordleLogic logic;
    private static String word;
    private WordleLogic(){};

    public static WordleLogic getInstance(){
        if (logic == null){
            synchronized (WordleLogic.class) {
                if (logic == null) {
                    logic = new WordleLogic();
                }
            }
        }
        return logic;
    }

    //enter/submit button should call this method, it returns the color for each letter
    public static int[] checkWord(String guess) {
        System.out.println(word);
        guess = guess.toUpperCase();
        int[] color = {0,0,0,0,0};
        boolean[] linked = {false,false,false,false,false};
        for (int j = 0; j < word.length(); j++) {
            if (word.charAt(j) == guess.charAt(j)) {
                color[j] = 1;
                linked[j] = true;
            }
        }
        //i is the guess' index
        for (int i = 0; i < guess.length(); i++) {
            //j is the actual word's index
            for (int j = 0; j < word.length(); j++) {
                 if (color[i] != 1 && !linked[j] && word.charAt(j) == guess.charAt(i)) {
                    color[i] = 2;
                    linked[j] = true;
                    break;
                }
            }
        }
        return color;
    }

    public void newWord() {
        word = WordleWords.getInstance().randomWord();
    }
    //for testing purposes
    public void setWord(String word) {
        this.word = word.toUpperCase();
    }
}
