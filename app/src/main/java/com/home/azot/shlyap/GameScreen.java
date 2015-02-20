package com.home.azot.shlyap;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    GameManager gameManager;
    TeamManager teamManager;
    MyDBHelper dbHelper;
    MyTimer myTimer;
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_screen);

        try {
            taskManager = new TaskManager(this.getApplicationContext());
            gameManager = GameManager.getInstance();
            teamManager = gameManager.getTeamManager();
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
            choiseWhoFirst();
        }
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

    private void choiseWhoFirst(){
//        gameManager.setTeamCount();  //получает колличество команд в игре
//
//        setContentView(R.layout.who_first_screen);
//
//        Team currentTeam  = gameManager.getCurrentTeam();
//
//        Button firstBtn = (Button) findViewById(R.id.first);
//        Button secondBtn = (Button) findViewById(R.id.second);
//
//        firstBtn.setText(String.valueOf(currentTeam.getGameUser(Team.FIRST_USER).getUserName()));
//        secondBtn.setText(String.valueOf(currentTeam.getGameUser(Team.SECOND_USER).getUserName()));
//
//        View.OnClickListener choseFirstn = new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()) {
//                    case R.id.first:
//                        gameManager.setCurrentUser(gameManager.getCurrentTeam().getGameUser(Team.FIRST_USER));
//                        setButtonActive(findViewById(R.id.second));
//                        break;
//                    case R.id.second:
//                        gameManager.setCurrentUser(gameManager.getCurrentTeam().getGameUser(Team.SECOND_USER));
//                        setButtonActive(findViewById(R.id.first));
//                        break;
//                }
//                setButtonInactive(v);
//            }
//        };
//        firstBtn.setOnClickListener(choseFirstn);
//        secondBtn.setOnClickListener(choseFirstn);
//
//        Button startBtn = (Button) findViewById(R.id.chekedwhofirstbtn);
//        startBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startGuestWord();
//            }
//        });
    }
    private void setButtonActive(View view){
        view.setEnabled(true);
        view.setClickable(true);
    }

    private void startGuestWord(){
        setContentView(R.layout.game_screen);
        myTimer = new MyTimer();

        Handler handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                // обновляем TextView
                Log.d("MyTag", "Handler mes - "+msg.what);
                TextView timer = (TextView) findViewById(R.id.timer);
                if (timer != null)
                timer.setText(String.valueOf(msg.what));
            };
        };
        myTimer.setHandler(handler);
        myTimer.start();

        setNextWord();
//        Log.d("MyTag", "GameStart");
    }



    private void setNextWord() {
//        TextView word = (TextView) findViewById(R.id.taskWord);
//        final TaskWord tWord = gameManager.getCurrentTask().getNextWord();
//        if (tWord==null){
//            myTimer.stop();
//            showRoundResult();
//        }
//        else {
//            tWord.startGuess();
//
//            word.setText(tWord.getWord());
//
//            Button guesedBtn = (Button) findViewById(R.id.checkedBtn);
//            Button missedBtn = (Button) findViewById(R.id.missedBtn);
//
//            View.OnClickListener someWordAction = new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    switch (v.getId()) {
//                        case R.id.checkedBtn:
//                            tWord.setGuessed();
//                            break;
//                        case R.id.missedBtn:
//                            tWord.miss();
//                            break;
//                    }
//                    setNextWord();
//                }
//            };
//            guesedBtn.setOnClickListener(someWordAction);
//            missedBtn.setOnClickListener(someWordAction);
//        }
    }

    private void showRoundResult() {
//        Context context = getApplicationContext();
//        CharSequence text = "Все слова угаданы";
//        int duration = Toast.LENGTH_SHORT;
//
//        Toast toast = Toast.makeText(context, text, duration);
//        toast.show();
//        setContentView(R.layout.result_screen);
//        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
//        recyclerView.setHasFixedSize(true);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
//
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//
//        ResultAdapter mAdapter = new ResultAdapter(gameManager.getCurrentTask().getCommandTask());
//        recyclerView.setAdapter(mAdapter);
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
