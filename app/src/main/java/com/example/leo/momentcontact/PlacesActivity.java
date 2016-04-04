package com.example.leo.momentcontact;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.annotation.Target;

/**
 * Created by gloriazhong on 2016-03-05.
 */
public class PlacesActivity extends Activity {

    String sPercentage;
    String gPercentage;
    TextView profileName;
    TextView stanleyPlace;
    TextView gastownPlace;
    TextView stanleyPercent;
    TextView gastownPercent;
    String stanleyName;
    String gastownName;
    String sArrayDB;
    String gArrayDB;
    String dbUserName;
    MyDatabase db;
    SimpleCursorAdapter myAdapter;

    private ProgressBar stanleyBar = null;
    private ProgressBar gastownBar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        stanleyPlace = (TextView) findViewById(R.id.stanleyTextView);
        gastownPlace = (TextView) findViewById(R.id.gasTextView);
        stanleyPercent = (TextView) findViewById(R.id.stanleyPercentTextView);
        gastownPercent = (TextView) findViewById(R.id.gasPercentTextView);
        stanleyName = "Stanley Park";
        gastownName = "Gastown";
        db = new MyDatabase(this);

        Bundle profileExtra = getIntent().getExtras();
        profileName = (TextView) findViewById(R.id.profileNameTextView);

        String pName = profileExtra.getString("profileName");
        dbUserName = profileExtra.getString("userName");
        profileName.setText(pName);

        stanleyBar = (ProgressBar) findViewById(R.id.stanleyBar);
        gastownBar = (ProgressBar) findViewById(R.id.gastownBar);

        getGalleryProgress(dbUserName);

    }

    public void getGalleryProgress(String dbUserName) {

        Cursor whoResult = db.getUser(dbUserName);
        int sPercIndex = whoResult.getColumnIndex(Constants.STANLEY_PARK);
        int gPercIndex = whoResult.getColumnIndex(Constants.POLICE_STATION);

        if (whoResult.moveToNext()) {

            sArrayDB = whoResult.getString(sPercIndex);  // "0,0,0,0,0"
            gArrayDB = whoResult.getString(gPercIndex);

//            Toast.makeText(this, sPercDB+ " " +gPercDB, Toast.LENGTH_SHORT).show();

            String[] stanleyArray = Constants.convertStringToArray(sArrayDB);

            float sum = 0;

            for (int i = 0; i < stanleyArray.length; i++) {

                if (stanleyArray[i].equals("1")) {
                    sum++;
//                    Toast.makeText(PlacesActivity.this, i + " " + sum, Toast.LENGTH_SHORT).show();
                }
            }

            float stanleyResult = sum / stanleyArray.length;
            sPercentage = Integer.toString(Math.round(stanleyResult * 100));

            stanleyBar.setVisibility(View.VISIBLE);
            stanleyBar.setMax(100);
            stanleyBar.setProgress(Math.round(stanleyResult * 100));
            //Set the second progress bar value

            stanleyPercent.setText(sPercentage + " %");

            String[] gasArray = Constants.convertStringToArray(gArrayDB);
            sum = 0;
            for (int i = 0; i < gasArray.length; i++) {

                if (gasArray[i].equals("1")) {
                    sum++;
//                    Toast.makeText(PlacesActivity.this, i + " " + sum, Toast.LENGTH_SHORT).show();
                }
            }

            float gasResult = sum / gasArray.length;
//            Toast.makeText(PlacesActivity.this, sum + " " +gasResult +" "+ gasArray.length, Toast.LENGTH_SHORT).show();
            gPercentage = Integer.toString(Math.round(gasResult * 100));

            gastownBar.setVisibility(View.VISIBLE);
            gastownBar.setMax(100);
            gastownBar.setProgress(Math.round(gasResult * 100));
            gastownPercent.setText(gPercentage + " %");


        } else {
            Toast.makeText(this, "Cannot get array of places", Toast.LENGTH_SHORT).show();
        }
    }

    public void stanleyGallery(View v) {
        Intent intent = new Intent(this, GalleryActivity.class);
        intent.putExtra("galleryName", stanleyName);
        intent.putExtra("userName", dbUserName);
        intent.putExtra("progress", sArrayDB);
        startActivity(intent);
    }

    public void gastownGallery(View v) {
        Intent intent = new Intent(this, GalleryActivity.class);
        intent.putExtra("galleryName", gastownName);
        intent.putExtra("userName", dbUserName);
        intent.putExtra("progress", gArrayDB);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // To make sure there is an update with the latest data in the database when coming back to this activity
        getGalleryProgress(dbUserName);
    }
}

