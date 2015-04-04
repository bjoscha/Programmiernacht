package com.example.awesome.programmiernacht;

import java.lang.String;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by j on 04.04.2015.
 */
public class WordCard {
    private String word;
    private List<String> forbiddenWords;
    private int difficulty;

    public WordCard(String word, List<String> forbiddenWords, int difficulty) {
        this.word = word;
        this.forbiddenWords = forbiddenWords;
        this.difficulty = difficulty;
    }

    public WordCard() {
        this.forbiddenWords = new LinkedList<>();
        this.word = "";
    }

    public void SetWord(String word)
    {
        this.word = word;
    }

    public String GetWord()
    {
        return this.word;
    }

    public void AddForbiddenWord(String forbiddenWord)
    {
        this.forbiddenWords.add(forbiddenWord);
    }

    public void SetDifficulty(int difficulty)
    {
        this.difficulty = difficulty;
    }

    public int GetDifficulty()
    {
        return this.difficulty;
    }

    public List<String> GetForbiddenWords() {
        return this.forbiddenWords;
    }

}
