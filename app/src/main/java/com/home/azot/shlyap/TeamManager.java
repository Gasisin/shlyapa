package com.home.azot.shlyap;

import android.util.Log;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Женя on 24.01.2015.
 */
public class TeamManager{
    private ArrayList<GameUser> _totalUsers = new ArrayList<GameUser>();
    private Team[] _teams;
    private int _addedUserCount = 0;
    private ArrayList<GameUser> _addedUser = new ArrayList<>();

    private ArrayList<Team> _teamInGame = new ArrayList<>();

    private int MAX_TEAM_COUNT = 3;

    public TeamManager(){
        Log.d("MyTag", "teamManager");
        _totalUsers.add(new GameUser("Женя",  1));
        _totalUsers.add(new GameUser("Катя",  2));
        _totalUsers.add(new GameUser("Юра",  3));
        _totalUsers.add(new GameUser("Кристина",  4));
        _totalUsers.add(new GameUser("Вера",  5));
        _totalUsers.add(new GameUser("Слава",  6));

        _teams = new Team[MAX_TEAM_COUNT];
        for (int i=0; i<MAX_TEAM_COUNT; i++){
            _teams[i] = new Team();
        }
    }

    public boolean isFullTeam(){
        if (_addedUser.size()%2==0&&_addedUser.size()!=2){return true;}
        return false;
    }
    public void resetTeam(){
        _teams = new Team[MAX_TEAM_COUNT];
        for (int i=0; i<MAX_TEAM_COUNT; i++){
            _teams[i] = new Team();
        }
        _addedUser = new ArrayList<>();
        _addedUserCount = 0;
    }
    public boolean addUserInTeam(GameUser user){
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

    public boolean deleteUserFromTeam(GameUser user){
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

    public ArrayList<GameUser> getAddedUser(){
        return _addedUser;
    }

    public GameUser getUserById(int id){
        Iterator<GameUser> itr = _totalUsers.iterator();
        while (itr.hasNext()) {
            GameUser user = itr.next();
            if (user.getUserId()==id){
                return user;
            }
        }
        return null;
    }

    public ArrayList<Team> getTeamInGame(){
    if (_teamInGame.size()==0) {
        for (int i = 0; i < MAX_TEAM_COUNT; i++) {
            if (_teams[i].isFullTeam()) {
                _teamInGame.add(_teams[i]);
            }
        }
    }
    return _teamInGame;
    }


}
