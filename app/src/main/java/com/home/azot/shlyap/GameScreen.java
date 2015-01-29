package com.home.azot.shlyap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class GameScreen extends ActionBarActivity {

    public static TaskManager taskManager;
    TeamManager teamManager;
    MyDBHelper dbHelper;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        try {
            taskManager = new TaskManager(this.getApplicationContext());
            teamManager = new TeamManager();
            dbHelper = new MyDBHelper(this);
            setStartScreen();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        text = (TextView)findViewById(R.id.textView1);
//        text.setText(taskManager.getOneWord().toString());


    }


    @Override
    public void onBackPressed() {
        Log.d("MyTag", "Back");
    }

    private void setStartScreen() {

        Button btnAzoT = (Button) findViewById(R.id.Azot);
        Button btnKate = (Button) findViewById(R.id.Kate);
        Button btnUrii = (Button) findViewById(R.id.Urii);
        Button btnKristina = (Button) findViewById(R.id.Kris);
        Button btnVera = (Button) findViewById(R.id.Vera);
        Button btnSlava = (Button) findViewById(R.id.Slava);

        Button startBtn = (Button) findViewById(R.id.StartGameBtn);


        final Button resetBtn = (Button) findViewById(R.id.resetBtn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });
        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.Azot:
                        teamManager.addUserInTeam(teamManager.getUserById(1));
                        setTextAboutCommand();
                        break;
                    case R.id.Kate:
                        teamManager.addUserInTeam(teamManager.getUserById(2));
                        setTextAboutCommand();
                        break;
                    case R.id.Urii:
                        teamManager.addUserInTeam(teamManager.getUserById(3));
                        setTextAboutCommand();
                        break;
                    case R.id.Kris:
                        teamManager.addUserInTeam(teamManager.getUserById(4));
                        setTextAboutCommand();
                        break;
                    case R.id.Vera:
                        teamManager.addUserInTeam(teamManager.getUserById(5));
                        setTextAboutCommand();
                        break;
                    case R.id.Slava:
                        teamManager.addUserInTeam(teamManager.getUserById(6));
                        setTextAboutCommand();
                        break;
                }
                setButtonInactive(v);
            }
        };

        btnAzoT.setOnClickListener(oclBtn);
        btnKate.setOnClickListener(oclBtn);
        btnUrii.setOnClickListener(oclBtn);
        btnKristina.setOnClickListener(oclBtn);
        btnVera.setOnClickListener(oclBtn);
        btnSlava.setOnClickListener(oclBtn);

    }

    private void startGame() {
        if (teamManager.isFullTeam()){
        setContentView(R.layout.game_screen);
        MyTimer myTimer = new MyTimer();

        Handler handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                // обновляем TextView
                Log.d("MyTag", "Handler mes - "+msg.what);
                TextView timer = (TextView) findViewById(R.id.timer);
                timer.setText(String.valueOf(msg.what));
            };
        };
        myTimer.setHandler(handler);
        myTimer.start();
        Log.d("MyTag", "GameStart");}
    else{
            Context context = getApplicationContext();
            CharSequence text = "Неверное колличество игроков в командах";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();}
    }

    private void setButtonInactive(View view){
        view.setEnabled(false);
        view.setClickable(false);
    }

    private void setButtonActive(View view){
        view.setEnabled(true);
        view.setClickable(true);
    }

    private void setTextAboutCommand(){
        int _count = 0;
        LinearLayout llMain = (LinearLayout) findViewById(R.id.CommanDescription);
        int userCount = teamManager.getAddedUserCount();
        ArrayList<GameUser> addedUsers = teamManager.getAddedUser();
        llMain.removeAllViews();
        Iterator<GameUser> itr = addedUsers.iterator();
        while (itr.hasNext()) {
            if (_count%2==0){
                TextView tef = new TextView(this);
                tef.setText("Команда:");
                llMain.addView(tef);
             }
            _count++;
            TextView tef = new TextView(this);
            tef.setText(itr.next().getUserName().toString());
            llMain.addView(tef);
//            taskWordArrayList.add(new TaskWord(allWord.get(itr.next())));
        }


    }

    private void reset(){
        LinearLayout linMain = (LinearLayout) findViewById(R.id.CommanDescription);
        linMain.removeAllViews();
        teamManager.resetTeam();
        GridLayout llMain = (GridLayout) findViewById(R.id.gridLayout);
        for (int i = 0; i < llMain.getChildCount(); i++) {
            View view = llMain.getChildAt(i);
            setButtonActive(view);
        }
    }


}
