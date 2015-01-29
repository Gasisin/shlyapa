package com.home.azot.shlyap;

/**
 * Created by Женя on 24.01.2015.
 */
public class GameUser{

    private String _userName;
    private int _imageId;
    private int _userId;

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
