package app.src.main.java.com.home.azot.shlyap;

import com.home.azot.shlyap.CommandTask;
import com.home.azot.shlyap.GameScreen;
import com.home.azot.shlyap.GameUser;

/**
 * Created by Женя on 24.01.2015.
 */
public class Team{
    private GameUser[] users;
    private int USER_IN_TEAM_COUNT = 2;
    private int TASK_IN_TEAM_COUNT = 2;
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
