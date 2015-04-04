package com.example.awesome.programmiernacht.gameLogic;

import android.os.SystemClock;

public class Timer implements Runnable {

    private GameLogic gameLogic;

    public Timer(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }

    @Override
    public void run() {

        long startTime = SystemClock.currentThreadTimeMillis();

        boolean running = true;
        while (running) {
            try {
                Thread.sleep(1000);
                long secondsSinceStart = (SystemClock.currentThreadTimeMillis()-startTime)/1000;
                if(secondsSinceStart >= 60) {
                    gameLogic.timeUp();
                } else {
                    gameLogic.updateTime(secondsSinceStart);
                }
                gameLogic.updateTime(secondsSinceStart);
            } catch (InterruptedException e) {

            }
        }
    }
}
