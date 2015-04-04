package com.example.awesome.programmiernacht;

import java.lang.String;

public class Group {

    private int id;
    private int points;
    private String name;

    public void Group(int id, int points, String name) {
        this.id = id;
        this.points = points;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
