package com.example.awesome.programmiernacht.gameLogic;

import com.example.awesome.programmiernacht.Group;

import java.util.List;

public class GameLogic {

    private List<Group> groups;
    private Group activeGroup;
    private WordCard activeCard;
    private boolean gameOver = false;

    public void newGame(List<Group> groups) {
        this.groups = groups;
    }

    public int getPoints() {

        // TODO

        return 0;
    }

    public WordCard next(boolean correct) {

        // TODO

        return null;
    }

    public void nextGroup() {
        activeGroup = groups.get((activeGroup.getId()+1) % groups.size());
    }

    public Group getActiveGroup() {
        return activeGroup;
    }

    public List<Group> getAllGroups() {
        return groups;
    }

    public WordCard start() {

        int difficulty = 0;
        int maxCards = 0;
        for(int i=1; i<=activeGroup.getPointsLastRound().size(); i++) {
            int cards = activeGroup.getPointsLastRound().get(i-1) / i;
            if(cards > maxCards) {
                difficulty = i;
                maxCards = cards;
            }
        }

        activeGroup.clearPointsLastRound();

        // TODO timer

        return WordCard.getCardForDifficulty(difficulty);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void updateTime(long secondsSinceStart) {
        // TODO call GUI interface
    }

    public void timeUp() {
        gameOver = true;
    }

    // TODO: dummy interfaces
    private static class WordCard {
        public static WordCard getCardForDifficulty(int diff) {
            return null;
        }
    }

}
