package com.example.awesome.programmiernacht.gameLogic;

import com.example.awesome.programmiernacht.Group;
import com.example.awesome.programmiernacht.Timeable;
import com.example.awesome.programmiernacht.WordCard;
import com.example.awesome.programmiernacht.WordCardProvider;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class GameLogic {

    private List<Group> groups;
    private Group activeGroup;
    private WordCard activeCard;
    private boolean gameOver;
    private WordCardProvider wcp;
    private static GameLogic INSTANCE;
    private Timeable timeable;

    private int targetPoints = 30;
    private int turnTime = 60;

    public static GameLogic getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameLogic();
        }
        return INSTANCE;
    }

    public void newGame(List<Group> groups) {
        this.groups = groups;
        this.activeGroup = groups.get(0);
        gameOver = false;
    }

    public WordCard next(boolean correct) {

        if(correct) {
            return wcp.GetNextCard(min(activeCard.GetDifficulty() + 1, 3));
        } else {
            return wcp.GetNextCard(max(activeCard.GetDifficulty() - 1, 1));
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
        Group g0 = new Group(0);
        g0.addPoints(1);
        g0.addPoints(2);
        g0.addPoints(2);

        Group g1 = new Group(1);
        g1.addPoints(2);
        g1.addPoints(3);
        g1.addPoints(3);

        this.groups = new ArrayList<Group>();

        groups.clear();

        groups.add(g0);
        groups.add(g1);

        return groups;

    }

    public WordCard start(Timeable timeable) {

        this.timeable = timeable;

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

        Timer timer = new Timer(this, turnTime);
        timer.run();

        return wcp.GetNextCard(difficulty);
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void updateTime(int remainingTime) {
        timeable.setRemainingTime(remainingTime);
    }

    public void timeUp() {
        timeable.timeOver();
    }
}
