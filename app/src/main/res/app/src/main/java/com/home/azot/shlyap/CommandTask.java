package app.src.main.java.com.home.azot.shlyap;

import com.home.azot.shlyap.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Женя on 23.01.2015.
 */
public class CommandTask {
    private ArrayList<com.home.azot.shlyap.TaskWord> _taskArray;

    public CommandTask(ArrayList<com.home.azot.shlyap.TaskWord> taskArray){
        _taskArray = taskArray;
    }
    public void setCommandTast(ArrayList<com.home.azot.shlyap.TaskWord> taskArray){
        _taskArray = taskArray;
    }

    public com.home.azot.shlyap.TaskWord getNextWord(){
        Iterator<com.home.azot.shlyap.TaskWord> itr = _taskArray.iterator();
        while(itr.hasNext()){
            com.home.azot.shlyap.TaskWord tw = itr.next();
            if (tw.isNotGuessed()){
                return tw;
            }
        }
           return null;
    }
}
