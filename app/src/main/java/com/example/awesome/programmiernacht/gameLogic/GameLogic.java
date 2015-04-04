package com.example.awesome.programmiernacht.gameLogic;

import com.example.awesome.programmiernacht.Group;
import com.example.awesome.programmiernacht.WordCard;
import com.example.awesome.programmiernacht.WordCardProvider;

import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class GameLogic {

    private List<Group> groups;
    private Group activeGroup;
    private WordCard activeCard;
    private boolean gameOver;
    private WordCardProvider wcp;
    private int targetPoints = 30;

    public void newGame(List<Group> groups) {
        this.groups = groups;
        this.activeGroup = groups.get(0);
        gameOver = false;
    }

    public WordCard next(boolean correct) {

        if(correct) {
            return wcp.GetNextCard(min(activeCard.difficulty + 1, 3));
        } else {
            return wcp.GetNextCard(max(activeCard.difficulty - 1, 1));
        }
    }

    public void nextGroup() {
        if(activeGroup.getTotalPoints() >= targetPoints) {
            gameOver = true;
        } else {
            activeGroup = groups.get((activeGroup.getId()+1) % groups.size());
        }
    }

    public Group getActiveGroup() {
        return activeGroup;
    }

    public List<Group> getAllGroups() {
        return groups;
    }

    public WordCard start() {

        int difficulty = 1;
        int maxCards = 0;
        for(int i=1; i<=activeGroup.getPointsLastRound().size(); i++) {
            int cards = activeGroup.getPointsLastRound().get(i-1) / i;
            if(cards > maxCards) {
                difficulty = i;
                maxCards = cards;
            }
        }

        activeGroup.clearPointsLastRound();

        Timer timer = new Timer(this);
        timer.run();

        return wcp.GetNextCard(difficulty);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void updateTime(long secondsSinceStart) {

        // TODO call GUI
    }

    public void timeUp() {

        // TODO call GUI
    }

}
