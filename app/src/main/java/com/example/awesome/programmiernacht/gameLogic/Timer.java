package com.example.awesome.programmiernacht.gameLogic;

import android.os.SystemClock;

public class Timer implements Runnable {

    private GameLogic gameLogic;
    private int turnTime;

    public Timer(GameLogic gameLogic, int turnTime) {
        this.turnTime = turnTime;
        this.gameLogic = gameLogic;
    }

    @Override
    public void run() {

        float startTime = SystemClock.elapsedRealtime();

        boolean running = true;
        while (running) {
            try {
                Thread.sleep(1000);
                float secondsSinceStart = (SystemClock.elapsedRealtime()-startTime)/1000;
                if(secondsSinceStart >= turnTime) {

                    gameLogic.timeUp();
                    running = false;
                } else {
                    gameLogic.updateTime((int)(turnTime - secondsSinceStart));
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
