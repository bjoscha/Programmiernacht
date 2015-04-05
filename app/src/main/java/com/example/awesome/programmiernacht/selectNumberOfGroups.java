package com.example.awesome.programmiernacht;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.awesome.programmiernacht.gameLogic.GameLogic;

import java.util.LinkedList;


public class selectNumberOfGroups extends ActionBarActivity {

    private int numberOfGroups = 0;
    private GameLogic gl;

    public void userSelectNumberOfGroups(View view) {
        Button button = (Button) view;
        LinkedList<Group> groups = new LinkedList<Group>();
        numberOfGroups = Integer.parseInt(button.getText().toString());

        for(int i=1; i<=numberOfGroups; i++) {
            groups.add(new Group(i));
        }

        gl.newGame(groups);

        Intent intent = new Intent(this, groupReady.class);
        startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_number_of_groups);


        gl  = GameLogic.getInstance(getResources().getXml(R.xml.cards));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_select_number_of_groups, menu);
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
