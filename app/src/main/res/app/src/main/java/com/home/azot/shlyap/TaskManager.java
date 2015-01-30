package app.src.main.java.com.home.azot.shlyap;

import android.content.Context;
import android.util.Log;

import com.home.azot.shlyap.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * Created by Женя on 23.01.2015.
 */
public class TaskManager {
    private Context _context;
    final String TAG = "logShlyapa";
    private ArrayList<String> allWord;

    public TaskManager(Context context) throws IOException {
        _context = context;
        allWord = getMassiv();
    }


    public ArrayList<String> getMassiv() throws FileNotFoundException, IOException {
        InputStream ini = _context.getAssets().open("easy.txt");
        InputStreamReader in = new InputStreamReader(ini);
        BufferedReader reader = new BufferedReader(in);
        String line;
        ArrayList<String> list = new ArrayList<String>();
        while ((line = reader.readLine()) !=null) {
            list.add(line.trim());
        }
        list.remove("");
        in.close();
        reader.close();
        return  list;
    }


    public ArrayList<com.home.azot.shlyap.TaskWord> getTask(){
        int WORDCOUNT = 12;
        ArrayList<com.home.azot.shlyap.TaskWord> taskWordArrayList = new ArrayList<com.home.azot.shlyap.TaskWord>();

        Random random = new Random();
        HashSet<Integer> intset = new HashSet<Integer>();
        Log.d(TAG, "коллекция случайных элементов массива создана");
//
        while(intset.size() < WORDCOUNT){
            intset.add(random.nextInt(allWord.size()));
        }
        Iterator<Integer> itr = intset.iterator();
        while (itr.hasNext()) {
            taskWordArrayList.add(new com.home.azot.shlyap.TaskWord(allWord.get(itr.next())));
        }
        return taskWordArrayList;
    }

    public String getOneWord(){
        return allWord.get(new Random().nextInt(allWord.size()));
    }
}
