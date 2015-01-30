package app.src.main.java.com.home.azot.shlyap;

import android.util.Log;

import com.home.azot.shlyap.*;
import com.home.azot.shlyap.GameUser;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Женя on 24.01.2015.
 */
public class TeamManager{
    private ArrayList<com.home.azot.shlyap.GameUser> _totalUsers = new ArrayList<com.home.azot.shlyap.GameUser>();
    private com.home.azot.shlyap.Team[] _teams;
    private int _addedUserCount = 0;
    private ArrayList<com.home.azot.shlyap.GameUser> _addedUser = new ArrayList<>();

    private int MAX_TEAM_COUNT = 3;

    public TeamManager(){
        Log.d("MyTag", "teamManager");
        _totalUsers.add(new com.home.azot.shlyap.GameUser("Женя",  1));
        _totalUsers.add(new com.home.azot.shlyap.GameUser("Катя",  2));
        _totalUsers.add(new com.home.azot.shlyap.GameUser("Юра",  3));
        _totalUsers.add(new com.home.azot.shlyap.GameUser("Кристина",  4));
        _totalUsers.add(new com.home.azot.shlyap.GameUser("Вера",  5));
        _totalUsers.add(new com.home.azot.shlyap.GameUser("Слава",  6));

        _teams = new com.home.azot.shlyap.Team[MAX_TEAM_COUNT];
        for (int i=0; i<MAX_TEAM_COUNT; i++){
            _teams[i] = new com.home.azot.shlyap.Team();
        }
    }

    public boolean isFullTeam(){
        if (_addedUser.size()%2==0&&_addedUser.size()!=2){return true;}
        return false;
    }
    public void resetTeam(){
        _teams = new com.home.azot.shlyap.Team[MAX_TEAM_COUNT];
        for (int i=0; i<MAX_TEAM_COUNT; i++){
            _teams[i] = new com.home.azot.shlyap.Team();
        }
        _addedUser = new ArrayList<>();
        _addedUserCount = 0;
    }
    public boolean addUserInTeam(com.home.azot.shlyap.GameUser user){
        for (int i=0; i<MAX_TEAM_COUNT; i++){
            Log.d("MyTag", "TryToAddUser - "+user.toString());
            if (_teams[i].addUser(user)){
                _addedUserCount++;
                _addedUser.add(user);
                Log.d("MyTag", "SuccsesAdded - "+user.toString());
                return true;
            }
        }
        Log.d("MyTag", "FailAdded - "+user.toString());
        return false;
    }

    public boolean deleteUserFromTeam(com.home.azot.shlyap.GameUser user){
        for (int i=0; i<MAX_TEAM_COUNT; i++){
            if (_teams[i].deleteUser(user)){
                _addedUserCount--;
                return true;
            }
        }
        return false;
    }

    public int getAddedUserCount(){
        return _addedUserCount;
    }

    public ArrayList<com.home.azot.shlyap.GameUser> getAddedUser(){
        return _addedUser;
    }

    public com.home.azot.shlyap.GameUser getUserById(int id){
        Iterator<com.home.azot.shlyap.GameUser> itr = _totalUsers.iterator();
        while (itr.hasNext()) {
            GameUser user = itr.next();
            if (user.getUserId()==id){
                return user;
            }
        }
        return null;
    }


}
