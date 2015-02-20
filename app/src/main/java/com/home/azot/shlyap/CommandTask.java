package com.home.azot.shlyap;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Женя on 23.01.2015.
 */
public class CommandTask {
    private ArrayList<TaskWord> _taskArray;

    private int _totalTime = 0;
    private TaskWord missedWord = new TaskWord("");

    public CommandTask(ArrayList<TaskWord> taskArray){
        _taskArray = taskArray;
    }
    public void setCommandTast(ArrayList<TaskWord> taskArray){
        _taskArray = taskArray;
    }

    public TaskWord getNextWord(){
        int missedCount = 0;
        Iterator<TaskWord> itr = _taskArray.iterator();
        while(itr.hasNext()){
            TaskWord tw = itr.next();
            if (tw.isNotGuessed()){
                return tw;
            }
        }
        Iterator<TaskWord> itrMissedForCount = _taskArray.iterator();  //TODO продумать проход по повторным словам
        while(itrMissedForCount.hasNext()) {
            TaskWord tw = itrMissedForCount.next();
            if (tw.isMissed()) {
                missedCount++;
            }
        }
        Iterator<TaskWord> itrMissed = _taskArray.iterator();
        while(itrMissed.hasNext()) {
            TaskWord tw = itrMissed.next();
            if ((tw.isMissed()&&missedCount==1)||tw.isMissed()&&tw!=missedWord) {
                missedWord = tw;
                return tw;
            }
        }
           return null;
    }

    public void setTotalTime(int totalTime){
        _totalTime = totalTime;
    }

    public int getTotalTime(){
        return _totalTime;
    }

    public ArrayList<TaskWord> getCommandTask(){
        return _taskArray;
    }
}
