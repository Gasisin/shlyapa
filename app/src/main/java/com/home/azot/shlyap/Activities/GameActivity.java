package com.home.azot.shlyap.Activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.home.azot.shlyap.Helpers.GUIHelper;
import com.home.azot.shlyap.Helpers.StaticHolder;
import com.home.azot.shlyap.MyTimer;
import com.home.azot.shlyap.R;
import com.home.azot.shlyap.TaskWord;

/**
 * Created by Женя on 19.02.2015.
 */
public class GameActivity extends ActionBarActivity {

    MyTimer myTimer;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        GUIHelper.configureWindowEnterExitExplodeTransition(getWindow());

        startGuestWord();

    }

    private void startGuestWord(){
        myTimer = new MyTimer();

        Handler handler = new Handler(){
            public void handleMessage(android.os.Message msg) {
                // обновляем TextView
                Log.d("MyTag", "Handler mes - " + msg.what);
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
        TextView word = (TextView) findViewById(R.id.word);
        final TaskWord tWord = StaticHolder.gameManager.getCurrentTask().getNextWord();
        if (tWord==null){
            myTimer.stop();
            StaticHolder.gameManager.getCurrentUser().setComplete();
            StaticHolder.gameManager.getCurrentUser().getUserTask().setTotalTime(myTimer.getTime());
            showRoundResult();
        }
        else {
            tWord.startGuess();

            word.setText(tWord.getWord());

            View guesedBtn = findViewById(R.id.guessed_btn);
            View missedBtn = findViewById(R.id.missed);

            View.OnClickListener someWordAction = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    switch (v.getId()) {
                        case R.id.guessed_btn:
                            tWord.setGuessed();
                            break;
                        case R.id.missed:
                            tWord.miss();
                            break;
                    }
                    setNextWord();
                }
            };
            guesedBtn.setOnClickListener(someWordAction);
            missedBtn.setOnClickListener(someWordAction);
        }
    }

    private void showRoundResult() {
    }
}
