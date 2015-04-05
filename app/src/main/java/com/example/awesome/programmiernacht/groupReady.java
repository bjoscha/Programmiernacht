package com.example.awesome.programmiernacht;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.awesome.programmiernacht.gameLogic.GameLogic;


public class groupReady extends ActionBarActivity {
    private GameLogic gl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_ready);
        gl = GameLogic.getInstance();
        TextView textGroup = (TextView) findViewById(R.id.textViewGr);
        int activeGroup = gl.getActiveGroup().getId();
        textGroup.setText("Gruppe "+activeGroup +" bereit?");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_group_ready, menu);
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

    public void groupIsReady(View view) {
        Intent intent = new Intent(this, gameScreen.class);
        startActivity(intent);
    }
}
