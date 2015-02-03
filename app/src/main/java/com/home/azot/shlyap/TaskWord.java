package com.home.azot.shlyap;

import android.util.Log;

/**
 * Created by Женя on 23.01.2015.
 */
public class TaskWord {
    private String _word;
    private MyTimer _timer;
    private boolean _isStarted;
    private int _state;

    public final int GUESSED = 1;
    public final int NOT_GUESSED = 2;
    public final int MISSED = 3;

    public TaskWord(String word){
        _word = word;
        _timer = new MyTimer();
        _isStarted = false;
        _state = NOT_GUESSED;
    }

    public String getWord(){
        return _word;
    }
    public void startGuess(){
        if (_state==NOT_GUESSED||_state==MISSED){
            _isStarted = true;
            Log.d("MyTag", "startTimerTaskWord");
            _timer.start();
        }
    }

    public boolean isNotGuessed(){
        if (_state==NOT_GUESSED){return true;}
        return false;
    }
    public boolean isMissed(){
        if (_state==MISSED){return true;}
        return false;
    }

    public int getTime(){
        return _timer.getTime();
    }

    public void setGuessed(){
        _state = GUESSED;
        _timer.stop();
    }

    public void miss(){
        _state = MISSED;
        _timer.stop();
    }
}
