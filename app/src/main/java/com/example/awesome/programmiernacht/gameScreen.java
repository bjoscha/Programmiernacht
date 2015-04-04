package com.example.awesome.programmiernacht;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class gameScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);
        TextView textTime = (TextView) findViewById(R.id.textViewTime);
        int currentTime = 50; //todo
        textTime.setText("Zeit:" +currentTime);
        TextView textPoints = (TextView) findViewById(R.id.textViewPoints);
        int currentPoints = 10; //todo
        textPoints.setText("Punkte:" +currentPoints);
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
        Intent intent = new Intent(this, gameScreen.class);
        startActivity(intent);
    }

    public void nextWord(View view) {
        Intent intent = new Intent(this, gameScreen.class);
        startActivity(intent);
    }
}
