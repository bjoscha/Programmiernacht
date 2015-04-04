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
        for(int i=0;i<3;i++) {
            pointsLastRound.add(0);
        }
    }

    public void addPoints(int difficulty) {
        int i = difficulty-1;
        pointsLastRound.set(i, pointsLastRound.get(i)+difficulty);
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

    public int getTurnPoints() {
        int sum = 0;
        for (int i : pointsLastRound) {
            sum += i;
        }
        return sum;
    }

    public void clearPointsLastRound() {
        pointsLastRound.clear();
    }

}
