package com.home.azot.shlyap.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.home.azot.shlyap.GameManager;
import com.home.azot.shlyap.GameUser;
import com.home.azot.shlyap.Helpers.StaticHolder;
import com.home.azot.shlyap.MyDBHelper;
import com.home.azot.shlyap.MyTimer;
import com.home.azot.shlyap.R;
import com.home.azot.shlyap.TaskManager;
import com.home.azot.shlyap.TeamManager;
import com.home.azot.shlyap.Adapters.UserListAdapter;

import java.io.IOException;
import java.util.ArrayList;

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
    UserListAdapter mAdapter;
    ArrayList<GameUser> gameUsers;
    RecyclerView recyclerView;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_select_screen);
        StaticHolder.userSelectActivityInstance = this;
    try{
        taskManager = new TaskManager(this.getApplicationContext());
        gameManager = GameManager.getInstance();
        teamManager = gameManager.getTeamManager();
    } catch (IOException e) {
        e.printStackTrace();
    }


        recyclerView = (RecyclerView) findViewById(R.id.user_list_in_raw);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        animator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(animator);
        gameUsers = new ArrayList<>(teamManager.getTotalUsers());
        mAdapter = new UserListAdapter(gameUsers);
        recyclerView.setAdapter(mAdapter);
    }

    public void removeItem(View view){
        int position = recyclerView.getChildPosition(view);
        TextView tv = (TextView)recyclerView.findViewHolderForPosition(position).itemView.findViewById(R.id.user_name_in_row);
        Log.d("MyTag", tv.getText().toString());
        gameUsers.remove(position);
        mAdapter.notifyItemRemoved(position);
    }
}
