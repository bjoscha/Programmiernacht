package com.example.awesome.programmiernacht.gameLogic;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.widget.TextView;

import com.example.awesome.programmiernacht.Group;
import com.example.awesome.programmiernacht.R;
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
    private Activity myAct;

    private int targetPoints = 30;
    private int turnTime = 10;

    public GameLogic(XmlResourceParser xrp) {
        if (wcp == null)
            wcp = new WordCardProvider(xrp);
    }

    public static GameLogic getInstance(XmlResourceParser xrp) {
        if (INSTANCE == null) {
            INSTANCE = new GameLogic(xrp);
        }
        return INSTANCE;
    }

    public static GameLogic getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new GameLogic(null);
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
            activeGroup.addPoints(activeCard.GetDifficulty());
            activeCard = wcp.GetNextCard(min(activeCard.GetDifficulty() + 1, 3));
            return activeCard;
        } else {
            activeCard = wcp.GetNextCard(max(activeCard.GetDifficulty() - 1, 1));
            return activeCard;
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

    public WordCard start(Timeable timeable, Activity act) {

        this.timeable = timeable;
        this.myAct = act;
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

        Thread myThread = new Thread(timer);
        myThread.start();

        this.activeCard = wcp.GetNextCard(difficulty);
        return this.activeCard;
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
