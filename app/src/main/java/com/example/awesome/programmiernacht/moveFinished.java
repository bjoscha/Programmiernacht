package com.example.awesome.programmiernacht;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.awesome.programmiernacht.gameLogic.GameLogic;


public class moveFinished extends ActionBarActivity {
    private GameLogic gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move_finished);

        Group curGroup = null; //todo

        ((TextView) findViewById(R.id.textView_totalPoints)).setText("Erreichte Punktzahl: " +
                curGroup.getTotalPoints());

        ((TextView) findViewById(R.id.textView_pointsCat1)).setText("Kategorie 1: " +
                curGroup.getPointsLastRound().get(0));

        ((TextView) findViewById(R.id.textView_pointsCat2)).setText("Kategorie 1: " +
                curGroup.getPointsLastRound().get(1));

        ((TextView) findViewById(R.id.textView_pointsCat3)).setText("Kategorie 1: " +
                curGroup.getPointsLastRound().get(2));

    }

    public void continueGame(View view) {
        Intent intent = null;

        gl.nextGroup();
        if (gl.getActiveGroup().getId() == 0) {
            intent = new Intent(this, roundFinished.class);
        } else {
            intent = new Intent(this, groupReady.class);//Group Ready Screen
        }

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_move_finished, menu);
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
