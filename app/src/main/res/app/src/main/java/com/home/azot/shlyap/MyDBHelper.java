package app.src.main.java.com.home.azot.shlyap;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.home.azot.shlyap.*;
import com.home.azot.shlyap.GameUser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;

public class MyDBHelper extends SQLiteOpenHelper {


    private final String DATABASE_NAME = "shlyapa.db";
    private final int DATABASE_VERSION = 1;

    private final String UID = "_id";
    private final String USER_NAME = "name";
    private final String PHOTO = "photo";
    private final String WORD_VALUE = "value";
    private final String USER1_ID = "user1_id";
    private final String USER2_ID = "user2_id";
    private final String OFTEN_COUNT = "often_count";
    private final String GAME_TIME = "date_time";
    private final String WINNER_TEAM_ID = "winner_id";
    private final String GAME_ID = "game_id";
    private final String TEAM_ID = "team_id";
    private final String ANSWER_ID = "answer_id";
    private final String WORD_ID = "word_id";
    private final String TOTAL_TIME = "total_time";
    private final String WORD_TIME = "time";
    private final String IS_MISSED= "is_missed";
    private final String IS_REPLACED= "is_replaced";

    private final String USERS= "users";
    private final String WORDS= "words";
    private final String TEAMS= "teams";
    private final String GAMES= "games";
    private final String ROUNDS= "rounds";
    private final String WORDS_IN_ROUND= "words_id_round";

    private final String SQL_CREATE_USERS = "CREATE TABLE "
            + USERS + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER_NAME + " VARCHAR(255),"
            + PHOTO + " VARCHAR(255));";

    private final String SQL_CREATE_WORDS = "CREATE TABLE "
            + WORDS + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + WORD_VALUE + " VARCHAR(255));";

    private final String SQL_CREATE_TEAMS = "CREATE TABLE "
            + TEAMS + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + USER1_ID + "INTEGER,"
            + USER2_ID + "INTEGER,"
            + OFTEN_COUNT + " INTEGER);";

    private final String SQL_CREATE_GAMES = "CREATE TABLE "
            + GAMES + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + GAME_TIME + "INTEGER,"
            + WINNER_TEAM_ID + " INTEGER);";

    private final String SQL_CREATE_ROUND = "CREATE TABLE "
            + ROUNDS + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + GAME_ID + "INTEGER,"
            + TEAM_ID + "INTEGER,"
            + ANSWER_ID + "INTEGER,"
            + TOTAL_TIME + " INTEGER);";

    private final String SQL_CREATE_WORDS_IN_ROUND = "CREATE TABLE "
            + WORDS_IN_ROUND + " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + WORD_ID + "INTEGER,"
            + WORD_TIME + "INTEGER,"
            + IS_MISSED + "INTEGER,"
            + IS_REPLACED + " INTEGER);";



    public MyDBHelper(Context context) {
        super(context, "shlyapa.db", null, 1);
        Log.d("MyTag", "CREATE DB constructor");

        try {
            insertWords(getWritableDatabase());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("MyTag", "CREATE DB");
        // TODO Auto-generated method stub
        db.execSQL(SQL_CREATE_USERS);
        db.execSQL(SQL_CREATE_WORDS);
        db.execSQL(SQL_CREATE_TEAMS);
        db.execSQL(SQL_CREATE_GAMES);
        db.execSQL(SQL_CREATE_ROUND);
        db.execSQL(SQL_CREATE_WORDS_IN_ROUND);

        try {
            insertWords(db);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void insertWords(SQLiteDatabase db) throws IOException {
        ArrayList<String> allWords = com.home.azot.shlyap.GameScreen.taskManager.getMassiv();
        Iterator<String> itr = allWords.iterator();
        while(itr.hasNext()){
            ContentValues cv = new ContentValues();
            cv.put(WORD_VALUE, itr.next());
            db.insert(WORDS, null, cv);
        }
    }

    public long saveTeam(com.home.azot.shlyap.GameUser gu1, GameUser gu2){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(USER1_ID, gu1.getUserId());
        cv.put(USER1_ID, gu1.getUserId());
        cv.put(OFTEN_COUNT, 1);
        return db.insert(TEAMS, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
        // TODO Auto-generated method stub

    }

}