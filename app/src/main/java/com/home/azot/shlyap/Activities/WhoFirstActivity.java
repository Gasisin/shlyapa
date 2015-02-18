package com.home.azot.shlyap.Activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.home.azot.shlyap.GameManager;
import com.home.azot.shlyap.Helpers.GUIHelper;
import com.home.azot.shlyap.Helpers.StaticHolder;
import com.home.azot.shlyap.R;
import com.home.azot.shlyap.TaskManager;

import java.io.IOException;

/**
 * Created by Женя on 19.02.2015.
 */
public class WhoFirstActivity extends ActionBarActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.who_first_screen);

        GUIHelper.configureWindowEnterExitExplodeTransition(getWindow());


    }
}
