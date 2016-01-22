package com.example.researcher.heatmap;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Display;

public class MainActivity extends AppCompatActivity {



    SharedPreferences prefs;
    public static final String MYPREFS = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new MyView(this));

        Intent intentSensorService = new Intent(this, SensorsService.class);
        startService(intentSensorService);
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
        prefs = getApplicationContext().getSharedPreferences(MYPREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences(MYPREFS, MODE_PRIVATE).edit();
        editor.putFloat("maxX", maxX);
        editor.putFloat("maxY", maxY);

        editor.commit();
    }

    @Override
    protected void onDestroy() {
        Log.d("TAG", "onDestroy");
        Intent intentSensorService = new Intent(this, SensorsService.class);
        stopService(intentSensorService);
        super.onDestroy();
    }


}
