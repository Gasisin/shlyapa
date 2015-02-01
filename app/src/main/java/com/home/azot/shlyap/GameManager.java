package com.home.azot.shlyap;

/**
 * Created by Женя on 30.01.2015.
 */
public class GameManager {
    private int _state;

    public static GameManager instance;
    public static TeamManager teamManager;

    private int _teamCount = 0;

    private GameUser _currentUser = null;


    public final static int TEAM_SELECT = 1;
    public final static int FIRST_SELECT = 2;
    public final static int GAME_STAGE = 3;

    private int _currentStage = 0;

    public static GameManager getInstance()
    {
        if (instance == null)
        {
            instance = new GameManager();
        }
        return instance;
    }

    private GameManager(){
        _state = TEAM_SELECT;
        teamManager = new TeamManager();
    }

    public TeamManager getTeamManager(){
        return teamManager;
    }
    public int getGameState(){
        return _state;
    }
    public Team getCurrentTeam(){
            return teamManager.getTeamInGame().get(_currentStage%2);
    }

    public void setCurrentUser(GameUser currentUser){
        _currentUser = currentUser;
    }
    public void setGameState(int state){
        _state = state;
    }



}
