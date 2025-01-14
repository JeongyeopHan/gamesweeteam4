package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

public class WordleUnitTest {
    @Test
    public void correctWordComparison() {
        WordleLogic logic = WordleLogic.getInstance();
        logic.setWord("arrow");
        int[] colors = logic.checkWord("rarer");
        assert (colors[0] == 2);
        assert (colors[1] == 2);
        assert (colors[2] == 1);
        assert (colors[3] == 0);
        assert (colors[4] == 0);

        logic.setWord("phone");
        colors = logic.checkWord("whirl");
        assert (colors[0] == 0);
        assert (colors[1] == 1);
        assert (colors[2] == 0);
        assert (colors[3] == 0);
        assert (colors[4] == 0);

        logic.setWord("pearl");
        colors = logic.checkWord("trees");
        assert (colors[0] == 0);
        assert (colors[1] == 2);
        assert (colors[2] == 2);
        assert (colors[3] == 0);
        assert (colors[4] == 0);

        logic.setWord("carts");
        colors = logic.checkWord("whole");
        assert (colors[0] == 0);
        assert (colors[1] == 0);
        assert (colors[2] == 0);
        assert (colors[3] == 0);
        assert (colors[4] == 0);
    }

    @Test
    public void caseIndependent() {
        WordleLogic logic = WordleLogic.getInstance();
        logic.setWord("ARROW");
        int[] colors = logic.checkWord("arrow");
        assert (colors[0] == 1);
        assert (colors[1] == 1);
        assert (colors[2] == 1);
        assert (colors[3] == 1);
        assert (colors[4] == 1);

        logic.setWord("arrow");
        colors = logic.checkWord("ARROW");
        assert (colors[0] == 1);
        assert (colors[1] == 1);
        assert (colors[2] == 1);
        assert (colors[3] == 1);
        assert (colors[4] == 1);
    }

    // test the singleton method, making sure that they are actually creating only 1 instance
    @Test
    public void testPlayerSingletonInstance() {
        WordlePlayer player1 = WordlePlayer.getInstance();
        WordlePlayer player2 = WordlePlayer.getInstance();

        // Assert that both player1 and player2 refer to the same instance
        assertSame(player1, player2);
    }

    @Test
    public void testLogicSingletonInstance() {
        WordleLogic logic1 = WordleLogic.getInstance();
        WordleLogic logic2 = WordleLogic.getInstance();

        // Assert that both player1 and player2 refer to the same instance
        assertSame(logic1, logic2);
    }

    // test the addWords method with String parameter
    // Taiki test 1
    @Test
    public void testAddWordsString() {
        WordleWords words1 = WordleWords.getInstance();
        words1.resetWordList();
        words1.addWords("three");
        assertSame(words1.getWordList().size(), 5);
        words1.addWords("throw");
        assertSame(words1.getWordList().size(), 6);
        words1.addWords("twirl");
        assertSame(words1.getWordList().size(), 7);
        words1.addWords("trips");
        assertSame(words1.getWordList().size(), 8);
        words1.addWords("tulip");
        assertSame(words1.getWordList().size(), 9);
    }

    // test the addWords method with ArrayList parameter
    // Taiki test 2
    @Test
    public void testAddWordsArrayList() {
        WordleWords words1 = WordleWords.getInstance();
        words1.resetWordList();
        ArrayList<String> list1 = new ArrayList<>();
        list1.add("three");
        list1.add("throw");
        list1.add("twirl");
        words1.addWords(list1);
        assertSame(words1.getWordList().size(), 7);
        ArrayList<String> list2 = new ArrayList<>();
        list2.add("trips");
        list2.add("tulip");
        words1.addWords(list2);
        assertSame(words1.getWordList().size(), 9);
    }
    @Test
    //Zaid Akkawi test getLive()
    public void getLivesChecker() {
        WordlePlayer player1 = WordlePlayer.getInstance();
        assertSame(player1.getLives(), 0);
        player1.setLives(-1);
        assertSame(player1.getLives(), -1);
        player1.setLives(6);
        assertSame(player1.getLives(), 6);
    }

    //Zaid Akkawi checking or setter for wins/losses
    @Test
    public void checkWinsLosses() {
        WordlePlayer player1 = WordlePlayer.getInstance();
        assertSame(player1.getWins(), 0);
        assertSame(player1.getLoss(), 0);
        player1.setWinLoss(4, 3);
        assertSame(player1.getWins(), 4);
        assertSame(player1.getLoss(), 3);
    }

    //Jeongyeop Han checking wordle default method in WordleWords class
    @Test
    public void checkDefault() {
        WordleWords testWords = WordleWords.getInstance();
        testWords.resetWordList();
        ArrayList<String> testList = new ArrayList<>();
        testList.add("first");
        testList.add("seven");
        testList.add("eight");
        testWords.addWords(testList);
        ArrayList<String> expectedList = new ArrayList<>(Arrays.asList("PHONE","LUNCH","ARROW","WHIRL","FIRST", "SEVEN", "EIGHT"));
        assertEquals(testWords.getWordList(), expectedList);
    }

    //Jeongyeop Han testing resetLives in wordlePlayer class
    @Test
    public void checkCheckLives() {
        WordlePlayer playerTest = WordlePlayer.getInstance();
        playerTest.setLives(4);
        playerTest.resetLives();
        assertEquals(playerTest.getLives(), 6);
    }
    // Tate Johnson testing edge case of the logic if the number of yellows is right
    @Test
    public void yellowCheck() {
        WordleLogic logic = WordleLogic.getInstance();
        logic.setWord("start");
        int[] colors = logic.checkWord("aaiaa");
        assert (colors[0] == 2);
        assert (colors[1] == 0);
        assert (colors[2] == 0);
        assert (colors[3] == 0);
        assert (colors[4] == 0);

        logic.setWord("staat");
        colors = logic.checkWord("aaiia");
        assert (colors[0] == 2);
        assert (colors[1] == 2);
        assert (colors[2] == 0);
        assert (colors[3] == 0);
        assert (colors[4] == 0);

        logic.setWord("staaa");
        colors = logic.checkWord("aaiia");
        assert (colors[0] == 2);
        assert (colors[1] == 2);
        assert (colors[2] == 0);
        assert (colors[3] == 0);
        assert (colors[4] == 1);
    }
    @Test
    public void testGreen() {
        WordleLogic logic = WordleLogic.getInstance();
        logic.setWord("idddd");
        int[] colors = logic.checkWord("iiiii");
        assert (colors[0] == 1);
        assert (colors[1] == 0);
        assert (colors[2] == 0);
        assert (colors[3] == 0);
        assert (colors[4] == 0);
    }
}
