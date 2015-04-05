package com.example.awesome.programmiernacht;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.awesome.programmiernacht.gameLogic.GameLogic;

import java.util.List;


public class gameFinished extends ActionBarActivity {
    private GameLogic gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_finished);

        gl = GameLogic.getInstance();

        //Display winning group
        TextView tvWinnerGroup = (TextView) findViewById(R.id.textViewWinnerGroup);
        List<Group> groups = gl.getAllGroups();
        Group winner = groups.get(0);
        boolean multipleWinners = false;
        for (int i = 1; i < groups.size(); i++) {
            Group cg = groups.get(i);
            if (cg.getTotalPoints() == winner.getTotalPoints()) {
                multipleWinners = true;
            }
            if (cg.getTotalPoints() > winner.getTotalPoints()) {
                winner = cg;
                multipleWinners = false;
            }

        }

        if (!multipleWinners)
            tvWinnerGroup.setText("Gruppe " + winner.getId() + " gewinnt");
        else
            tvWinnerGroup.setText("Gleichstand");

        //Display overall scores
        LinearLayout curLayout = (LinearLayout) findViewById(R.id.gameFinished);
        for (Group curGroup : groups) {

            TextView tv = new TextView(this);
            tv.setText("Gruppe " + curGroup.getId() + ": " + curGroup.getTotalPoints());
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(lp);
            curLayout.addView(tv,1+curGroup.getId());
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_finished, menu);
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

    public void playAgain(View view) {
        gl.restartGame();
        Intent intent = new Intent(this, groupReady.class);
        startActivity(intent);
    }

    public void mainMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
