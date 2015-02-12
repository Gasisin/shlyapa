package com.home.azot.shlyap;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by Женя on 13.02.2015.
 */
public class UserSelectActivity extends ActionBarActivity {

    public static TaskManager taskManager;
    GameManager gameManager;
    TeamManager teamManager;
    MyDBHelper dbHelper;
    MyTimer myTimer;
    TextView text;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_select_screen);

    try{
        taskManager = new TaskManager(this.getApplicationContext());
        gameManager = GameManager.getInstance();
        teamManager = gameManager.getTeamManager();
    } catch (IOException e) {
        e.printStackTrace();
    }


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.user_list_in_raw);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        UserListAdapter mAdapter = new UserListAdapter(teamManager.getTotalUsers());
        recyclerView.setAdapter(mAdapter);
    }
}
