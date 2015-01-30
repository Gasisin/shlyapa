package app.src.main.java.com.home.azot.shlyap;

import android.os.Handler;
import android.util.Log;

import java.util.concurrent.TimeUnit;

/**
 * Created by Женя on 23.01.2015.
 */
public class MyTimer {
    private boolean _isStarted;
    private int _time = 0;
    private Handler _handler = null;

    public void start(){
        _isStarted = true;
        Log.d("MyTag", "new timer");
        startCount();

    }

    public void setHandler(Handler handler){
        _handler = handler;
    }
    private void startCount(){
        Log.d("MyTag", "timerBeforeCount");
        Thread t = new Thread(new Runnable() {
            public void run() {
                Log.d("MyTag", "timerbewforeWhile");
                while(_isStarted){
                    try {
                        Log.d("MyTag", "timer");
                        Thread.sleep(1000);
//                        TimeUnit.SECONDS.sleep(1);
                        _time++;
                        if (_handler!=null){
                            _handler.sendEmptyMessage(_time);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    public void stop(){
        _isStarted = false;
    }

    public int getTime(){
        return _time;
    }
}
