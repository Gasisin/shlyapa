package com.home.azot.shlyap.Activities;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.home.azot.shlyap.Adapters.CommandListAdapter;
import com.home.azot.shlyap.GameManager;
import com.home.azot.shlyap.GameUser;
import com.home.azot.shlyap.Helpers.GUIHelper;
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
    View startBtn;
    UserListAdapter mAdapter;
    ArrayList<GameUser> gameUsers;
    RecyclerView allUsersRecycleView;



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
        configStartBtn();
        resetBtnSet();
        recyclerSet();

    }

    private void configStartBtn() {
        GUIHelper.configureWindowEnterExitExplodeTransition(getWindow());
        startBtn = findViewById(R.id.fab_button);
        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent (UserSelectActivity.this, WhoFirstActivity.class);

                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(UserSelectActivity.this,
                        Pair.create(startBtn, "fab"));

                startActivity(i, transitionActivityOptions.toBundle());
            }
        });
        GUIHelper.configureFab(startBtn);
    }

    private void resetBtnSet() {
        View resetBtn = findViewById(R.id.repeat);
        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();

            }
        });
    }

    private void recyclerSet(){
        allUsersRecycleView = (RecyclerView) findViewById(R.id.user_list_in_raw);
        allUsersRecycleView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        allUsersRecycleView.setLayoutManager(layoutManager);
        RecyclerView.ItemAnimator animator = new DefaultItemAnimator();
        animator.setRemoveDuration(1000);
        allUsersRecycleView.setItemAnimator(animator);
        gameUsers = new ArrayList<>(teamManager.getTotalUsers());
        Log.d("MyTag", String.valueOf(gameUsers.size()));
        mAdapter = new UserListAdapter(gameUsers);
        allUsersRecycleView.setAdapter(mAdapter);
    }

    public void removeItem(View view){
        int position = allUsersRecycleView.getChildPosition(view);
        TextView tv = (TextView)allUsersRecycleView.findViewHolderForPosition(position).itemView.findViewById(R.id.user_name_in_row);
        Log.d("MyTag", tv.getTag().toString());
        gameUsers.remove(position);
        mAdapter.notifyItemRemoved(position);

        teamManager.addUserInTeam(teamManager.getUserById((int)tv.getTag()));

        showAddedUser();

        checkCanStart();
    }

    private void checkCanStart() {
        if(teamManager.isFullTeam()){
            View startBtn = findViewById(R.id.fab_button);
            startBtn.animate().setStartDelay(300)
                    .scaleX(1).scaleY(1);
        }
        else {
            View startBtn = findViewById(R.id.fab_button);
            startBtn.animate().setStartDelay(300)
                    .scaleX(0).scaleY(0);
        }
    }

    private void reset(){
        LinearLayout linMain = (LinearLayout) findViewById(R.id.holder_view);
        linMain.removeAllViews();
        ObjectAnimator objectAnimator = ObjectAnimator.ofInt(linMain, "minimumHeight", 0);
        objectAnimator.setDuration(700);
        objectAnimator.start();
        teamManager.resetTeam();

        recyclerSet();
        checkCanStart();
    }
    private void showAddedUser() {
        if (teamManager.getAddedUserCount()%2==1) {
            final View linl = findViewById(R.id.holder_view);
            int minSize = 250 * (teamManager.getAddedUserCount()/2+1);
            ObjectAnimator objectAnimator = ObjectAnimator.ofInt(linl, "minimumHeight", minSize);
            objectAnimator.setDuration(700);
            objectAnimator.start();

            objectAnimator.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) { }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.d("MyTag", "after animation");
                    addAddedUser();
                }

                private void addAddedUser() {
                    LayoutInflater ltInflater = getLayoutInflater();
                    View viewForAdd = ltInflater.inflate(R.layout.command_raw, null, false);

                    AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
                    animation1.setDuration(700);
                    LinearLayout ll = (LinearLayout) linl;
                    View clearTag = ll.findViewWithTag("lastAdded");
                    if (clearTag!=null) clearTag.setTag("");
                    viewForAdd.setTag("lastAdded");
                    ll.addView(viewForAdd);
                    viewForAdd.startAnimation(animation1);
                    TextView commandTitle = (TextView) viewForAdd.findViewById(R.id.title_command);
                    commandTitle.setText("Комманда №"+String.valueOf(teamManager.getAddedUserCount()/2+1));
                    TextView user1Title = (TextView) viewForAdd.findViewById(R.id.user_one);
                    user1Title.setText(teamManager.getLastAddedUser().getUserName());
                }

                @Override
                public void onAnimationCancel(Animator animation) { }
                @Override
                public void onAnimationRepeat(Animator animation) { }

            });
        }
        else {
            LinearLayout holder = (LinearLayout) findViewById(R.id.holder_view);
            AlphaAnimation animation2 = new AlphaAnimation(0.0f, 1.0f);
            animation2.setDuration(1000);
            TextView userTwo = (TextView) holder.findViewWithTag("lastAdded").findViewById(R.id.user_two);
            userTwo.startAnimation(animation2);
            userTwo.setText(teamManager.getLastAddedUser().getUserName());}
    }


}