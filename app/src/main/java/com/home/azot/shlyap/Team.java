package com.home.azot.shlyap;

/**
 * Created by Женя on 24.01.2015.
 */
public class Team{
    private GameUser[] users;
    private int USER_IN_TEAM_COUNT = 2;
    private int TASK_IN_TEAM_COUNT = 2;

    public final static int FIRST_USER = 1;
    public final static int SECOND_USER = 2;

    private CommandTask[] _tasks;

    public Team(){
        users = new GameUser[USER_IN_TEAM_COUNT];
        for (int i=0; i<USER_IN_TEAM_COUNT; i++){
            users[i] = null;
        }
        _tasks = new CommandTask[TASK_IN_TEAM_COUNT];
        for (int i=0; i<TASK_IN_TEAM_COUNT; i++){
            _tasks[i] = new CommandTask(GameScreen.taskManager.getTask());
        }
    }

    public boolean addUser(GameUser user){
        for (int i=0; i<USER_IN_TEAM_COUNT;i++){  // изменить на длинну массива
            if (users[i]==null){
                users[i]=user;
                return true;}
        }
        return false;
    }

    public GameUser getGameUser(int index){
        return users[index-1];
    }
    public boolean isFullTeam(){
        for (int i=0; i<USER_IN_TEAM_COUNT; i++){
            if (users[i] == null){
                return false;
            }
        }
        return true;
    }

    public void setCommandTasks(){

    }
    public boolean deleteUser(GameUser user){
        for (int i=0; i<USER_IN_TEAM_COUNT;i++){  // изменить на длинну массива
            if (users[i].equals(user)){
                users[i]=null;
                return true;}
        }
        return false;
    }
}
