package com.example.leo.momentcontact;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gloriazhong on 2016-03-05.
 */
public class PlacesActivity extends Activity{

    int completePercent;
    TextView profileName;
    TextView stanleyPlace;
    TextView gastownPlace;
    String stanleyName;
    String gastownName;
    MyDatabase db;
    SimpleCursorAdapter myAdapter;


    private static final int PROGRESS = 0x1;

    private ProgressBar mProgress;
    private int mProgressStatus = 0;

    private Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);



        profileName = (TextView)findViewById(R.id.profileNameTextView);
        stanleyPlace = (TextView)findViewById(R.id.stanleyTextView);
        stanleyName = "Stanley Park";
        gastownName = "Gastown";
        gastownPlace = (TextView)findViewById(R.id.gasTextView);
        db = new MyDatabase(this);




//        mProgress = (ProgressBar) findViewById(R.id.stanleyProgressBar);
//
//        // Start lengthy operation in a background thread
//        new Thread(new Runnable() {
//            public void run() {
//                while (mProgressStatus < 100) {
////                    mProgressStatus = doWork();
//                    Toast.makeText(getApplicationContext(), mProgressStatus +"", Toast.LENGTH_SHORT).show();
//
//                    // Update the progress bar
//                    mHandler.post(new Runnable() {
//                        public void run() {
//                            mProgress.setProgress(mProgressStatus);
//                        }
//                    });
//                }
//            }
//        }).start();

    }

    public void stanleyGallery(View v){
        Intent intent = new Intent(this, GalleryActivity.class);
        intent.putExtra("PLACE_NAME", stanleyName);
        startActivity(intent);
    }

    public void gastownGallery(View v){
        Intent intent = new Intent(this, GalleryActivity.class);
        intent.putExtra("PLACE_NAME", gastownName);
        startActivity(intent);
    }



}

