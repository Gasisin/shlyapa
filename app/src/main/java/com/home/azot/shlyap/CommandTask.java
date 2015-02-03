package com.home.azot.shlyap;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Женя on 23.01.2015.
 */
public class CommandTask {
    private ArrayList<TaskWord> _taskArray;

    public CommandTask(ArrayList<TaskWord> taskArray){
        _taskArray = taskArray;
    }
    public void setCommandTast(ArrayList<TaskWord> taskArray){
        _taskArray = taskArray;
    }

    public TaskWord getNextWord(){
        Iterator<TaskWord> itr = _taskArray.iterator();
        while(itr.hasNext()){
            TaskWord tw = itr.next();
            if (tw.isNotGuessed()){
                return tw;
            }
        }
        Iterator<TaskWord> itrMissed = _taskArray.iterator();
        while(itrMissed.hasNext()) {
            TaskWord tw = itrMissed.next();
            if (tw.isMissed()) {
                return tw;
            }
        }
           return null;
    }

    public ArrayList<TaskWord> getCommandTask(){
        return _taskArray;
    }
}
