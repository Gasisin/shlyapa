package com.home.azot.shlyap;

/**
 * Created by Женя on 24.01.2015.
 */
public class GameUser{

    private String _userName;
    private int _imageId;
    private int _userId;

    private boolean _isComplete;

    private CommandTask userTast;

    private final int NOT_ANSWER = 1;
    private final int ANSWER = 2;

    public GameUser(String userName, int imageId, int userId){
        _userName = userName;
        _imageId = imageId;
        _userId = userId;
    }

    public GameUser(String userName, int userId){
        _userName = userName;
        _imageId = 0;
        _userId = userId;
    }

    public void setUserTask(CommandTask tast){
        userTast = tast;
    }

    public CommandTask getUserTask(){
        return userTast;
    }

    public void setComplete(){
        _isComplete = true;
    }

    public boolean IsComplete(){
        return _isComplete;
    }

    public String getUserName(){
        return _userName;
    }

    public int getImageId(){
        return _imageId;
    }

    public int getUserId(){
        return _userId;
    }

//    public boolean equals(GameUser obj)
//    {
//        if(obj == this)
//            return true;
//        if(!(getClass() == obj.getClass()))
//            return false;
//        if (_userId == obj.getUserId()){
//            return true;
//        }
//        return false;
//    }

}
