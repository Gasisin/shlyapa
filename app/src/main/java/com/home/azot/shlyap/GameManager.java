package com.home.azot.shlyap;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Женя on 30.01.2015.
 */
public class GameManager {
    private int _state;

    public static GameManager instance;
    public static TeamManager teamManager;
//    public static TaskManager taskManager;

    private int _teamCount = 0;

    private GameUser _currentUser = null;
    private Context _context;

    public final static int TEAM_SELECT = 1;
    public final static int FIRST_SELECT = 2;
    public final static int GAME_STAGE = 3;

    private int _currentStage = 0;

    private CommandTask _currentTask = null;

    public static GameManager getInstance()
    {
        if (instance == null)
        {
            instance = new GameManager();
        }
        return instance;
    }

    public CommandTask getCurrentTask(){
        if (_currentTask==null&&_currentUser!=null){
            _currentTask = getCurrentTeam().getTaskForUser(_currentUser);
        }
        if (_currentUser==null)
            return null;
        return _currentTask;
    }

    private GameManager(){
        _state = TEAM_SELECT;
        teamManager = new TeamManager();
    }

    public void setTeamCount(){
        _teamCount = teamManager.getAddedUserCount()/2;
    }
    public TeamManager getTeamManager(){
        return teamManager;
    }
    public int getGameState(){
        return _state;
    }
    public Team getCurrentTeam(){
        if (_teamCount==0)setTeamCount();
            return teamManager.getTeamInGame().get(_currentStage%_teamCount);
    }

    public void setCurrentUser(GameUser currentUser){
        _currentUser = currentUser;
    }

    public GameUser getCurrentUser(){
        return _currentUser;
    }
    public void setGameState(int state){
        _state = state;
    }



}
