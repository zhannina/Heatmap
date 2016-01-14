package com.example.researcher.heatmap;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;

public class MainActivity extends AppCompatActivity {

    SharedPreferences prefs;
    public static final String MYPREFS = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     //   setContentView(R.layout.activity_main);
        setContentView(new MyView(this));



    }

    @Override
    protected void onResume(){
        super.onResume();
        getDisplayContentSize();
    }

    public void getDisplayContentSize(){
        float result;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        result = getResources().getDimensionPixelSize(resourceId);

        Display mdisp = getWindowManager().getDefaultDisplay();
        Point mdispSize = new Point();
        mdisp.getSize(mdispSize);
        float maxX = mdispSize.x;
        float maxY = mdispSize.y-result;
        float radius = mdispSize.x/8;
        Log.d("screen1", "y: " + mdispSize.y +" maxY: " + maxY);
        prefs = getApplicationContext().getSharedPreferences(MYPREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences(MYPREFS, MODE_PRIVATE).edit();
        editor.putFloat("maxX", maxX);
        editor.putFloat("maxY", maxY);
        editor.putFloat("radius", radius);
        editor.commit();
    }



}
