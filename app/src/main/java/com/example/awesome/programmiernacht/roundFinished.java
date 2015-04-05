package com.example.awesome.programmiernacht;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.awesome.programmiernacht.gameLogic.GameLogic;

import java.util.LinkedList;
import java.util.List;


public class roundFinished extends ActionBarActivity {
    private GameLogic gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_finished);

        gl = GameLogic.getInstance();

        List<Group> groups = gl.getAllGroups();

        for (Group curGroup : groups) {
            LinearLayout curLayout = (LinearLayout) findViewById(R.id.roundFinished);
            TextView tv = new TextView(this);
            int sumLastRound = 0;
            for (int i : curGroup.getPointsLastRound())
                sumLastRound += i;
            tv.setText("Gruppe " + curGroup.getId() + ": " + curGroup.getTotalPoints() +
                        " + " + sumLastRound);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(lp);
            curLayout.addView(tv,2+curGroup.getId());
        }
    }

    public void continueGame(View view) {
        Intent intent = null;

        if (gl.isGameOver()) {
            //intent = new Intent(this, ) todo gameOver-Screen
        }
        else {
            intent = new Intent(this, groupReady.class);
        }

        startActivity(intent);
    }

    public void endGame(View view) {
        Intent intent = null;

        //TODO EndGameScreen

        gl.nextGroup();
        if (gl.getActiveGroup().getId() == 0) {
            intent = new Intent(this, selectNumberOfGroups.class);
        } else {
            intent = new Intent(this, groupReady.class);//Group Ready Screen
        }

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_round_finished, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
