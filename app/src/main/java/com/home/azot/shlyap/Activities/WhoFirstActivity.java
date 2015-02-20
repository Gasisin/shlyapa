package com.home.azot.shlyap.Activities;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Pair;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.home.azot.shlyap.GameManager;
import com.home.azot.shlyap.Helpers.GUIHelper;
import com.home.azot.shlyap.Helpers.StaticHolder;
import com.home.azot.shlyap.R;
import com.home.azot.shlyap.TaskManager;
import com.home.azot.shlyap.Team;

import java.io.IOException;

/**
 * Created by Женя on 19.02.2015.
 */
public class WhoFirstActivity extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.who_first_screen);

        GUIHelper.configureWindowEnterExitExplodeTransition(getWindow());


        setScreen();
    }

    private void setScreen() {

        Team currentTeam  = StaticHolder.gameManager.getCurrentTeam();
//
        final View firstBtn = findViewById(R.id.user1_radio);
        final View secondBtn = findViewById(R.id.user2_radio);

        TextView user1Text = (TextView) firstBtn.findViewById(R.id.user_name_in_radio);
        TextView user2Text = (TextView) secondBtn.findViewById(R.id.user_name_in_radio);

        user1Text.setText(String.valueOf(currentTeam.getGameUser(Team.FIRST_USER).getUserName()));
        user2Text.setText(String.valueOf(currentTeam.getGameUser(Team.SECOND_USER).getUserName()));

        View.OnClickListener choseFirstn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioButton rb = (RadioButton) firstBtn.findViewById(R.id.user_switch);
                RadioButton rb2 = (RadioButton) secondBtn.findViewById(R.id.user_switch);
                switch (v.getId()) {
                    case R.id.user1_radio:
                        StaticHolder.gameManager.setCurrentUser(StaticHolder.gameManager.getCurrentTeam().getGameUser(Team.FIRST_USER));
                        rb.setChecked(true);
                        rb2.setChecked(false);
                        break;
                    case R.id.user2_radio:
                        StaticHolder.gameManager.setCurrentUser(StaticHolder.gameManager.getCurrentTeam().getGameUser(Team.SECOND_USER));
                        rb.setChecked(false);
                        rb2.setChecked(true);
                        break;
                }
            }
        };
        firstBtn.setOnClickListener(choseFirstn);
        secondBtn.setOnClickListener(choseFirstn);

        findViewById(R.id.startGameButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityOptions transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(WhoFirstActivity.this,
                        Pair.create(v, "guessedAnim"));
                Intent i  = new Intent (WhoFirstActivity.this, GameActivity.class);
                startActivity(i, transitionActivityOptions.toBundle());
            }
        });
    }
}
