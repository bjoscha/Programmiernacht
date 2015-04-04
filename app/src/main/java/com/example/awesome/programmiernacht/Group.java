package com.example.awesome.programmiernacht;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private int id;
    private int totalPoints;
    private List<Integer> pointsLastRound;

    public Group(int id) {
        this.id = id;
        this.totalPoints = 0;
        this.pointsLastRound = new ArrayList<>();
    }

    public void addPoints(int difficulty) {
        pointsLastRound.add(difficulty-1, difficulty);
        totalPoints += difficulty;
    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getId() {
        return id;
    }

    public List<Integer> getPointsLastRound() {
        return pointsLastRound;
    }

    public void clearPointsLastRound() {
        pointsLastRound.clear();
    }

}
