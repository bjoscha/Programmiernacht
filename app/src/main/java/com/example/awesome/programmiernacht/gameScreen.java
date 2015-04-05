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

import java.util.LinkedList;
import java.util.List;


public class gameScreen extends ActionBarActivity implements Timeable{
    private GameLogic gl;
    private int numberOfPreviousForbiddenWords;

    @Override
    public void setRemainingTime(int remainingTime) {
        TextView textTime = (TextView) findViewById(R.id.textViewTime);
        textTime.setText("" + remainingTime);

    }

    @Override
    public void timeOver() {
        Intent intent = new Intent(this, moveFinished.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        gl = GameLogic.getInstance();

        TextView textPoints = (TextView) findViewById(R.id.textViewPoints);
        int currentPoints = 0;
        textPoints.setText("Punkte:" +currentPoints);

        WordCard wc = gl.start(this,this);

        showNextWord(wc);
    }

    private void showNextWord(WordCard wc) {
        ((TextView) findViewById(R.id.textView_Word)).setText(wc.GetWord());

        List<String> forbiddenWords = wc.GetForbiddenWords();

        LinearLayout curLayout = (LinearLayout) findViewById(R.id.gameScreen);

        for (int i = 0; i < forbiddenWords.size(); i++) {
            String curForbWord = forbiddenWords.get(i);
            TextView tv = new TextView(this);

            tv.setText(curForbWord);
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            tv.setLayoutParams(lp);
            curLayout.addView(tv, 2 + i);

        }
        numberOfPreviousForbiddenWords = forbiddenWords.size();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_game_screen, menu);
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


    public void skipWord(View view) {

        removePreviousForbiddenWords();
        showNextWord(gl.next(false));
    }

    public void nextWord(View view) {

        removePreviousForbiddenWords();
        showNextWord(gl.next(true));
        Group g = gl.getActiveGroup();
        TextView tv = (TextView) findViewById(R.id.textViewPoints);
        tv.setText("" + g.getTurnPoints());
    }

    private void removePreviousForbiddenWords() {
        LinearLayout curLayout = (LinearLayout) findViewById(R.id.gameScreen);
        curLayout.removeViews(2,numberOfPreviousForbiddenWords);
    }
}
