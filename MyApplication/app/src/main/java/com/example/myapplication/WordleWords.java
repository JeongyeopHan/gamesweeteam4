package com.example.myapplication;
import java.util.ArrayList;
public class WordleWords {
    private volatile static WordleWords words;
    private ArrayList<String> wordList;
    private boolean defaultInitialized = false;
    private WordleWords(){};

    public static WordleWords getInstance(){
        if (words == null){
            synchronized (WordleWords.class) {
                if (words == null) {
                    words = new WordleWords();
                }
            }
        }
        return words;
    }

    public void addWords(String words){
        defaultWords();
        wordList.add(wordList.size(), words.toUpperCase());
    }
    public void addWords(ArrayList<String> words) {
        defaultWords();
        for (int i = 0; i < words.size(); i++) {
            wordList.add(wordList.size(), words.get(i).toUpperCase());
        }
    }

    private void defaultWords() {
        if (defaultInitialized) {
            return;
        }
        defaultInitialized = true;
        ArrayList<String> words = new ArrayList<String>();
        words.add("Phone".toUpperCase());
        words.add("Lunch".toUpperCase());
        words.add("Arrow".toUpperCase());
        words.add("Whirl".toUpperCase());

        wordList = words;
    }

    // Getter for the wordList
    public ArrayList<String> getWordList() {
        return wordList;
    }

    public void resetWordList() {
        defaultInitialized = false;
        defaultWords();
    }


    public String randomWord() {
        defaultWords();
        int idx = (int) (Math.random() * wordList.size());
        return wordList.get(idx);
    }
}