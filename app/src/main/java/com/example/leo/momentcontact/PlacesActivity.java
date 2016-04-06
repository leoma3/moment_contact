package com.example.leo.momentcontact;

import android.Manifest;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
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

    LocationManager lm;

    Location surreyStation = new Location("");//provider name is unecessary

    double longitude;
    double latitude;

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            float distanceInMeters = surreyStation.distanceTo(location);
            Toast.makeText(PlacesActivity.this, "location changed " + distanceInMeters + " meters", Toast.LENGTH_SHORT).show();

            ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 50);
            toneG.startTone(ToneGenerator.TONE_PROP_BEEP, 400);


            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1500);


            if (distanceInMeters < 200){
                String[] newProgress = new String[]{"1","1","1","1","1"};
                String progessInString = Constants.convertArrayToString(newProgress);
                int num = db.updateRow(dbUserName, Constants.STANLEY_PARK, progessInString);
                Toast.makeText(PlacesActivity.this, "stanley udpate : " + num, Toast.LENGTH_SHORT).show();
            }

            getGalleryProgress(dbUserName);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        surreyStation.setLatitude(49.189473d);//your coords of course
        surreyStation.setLongitude(-122.847834d);

        registerListener();

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

    public void locateStanley(View v) {
        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("lat", 49.3017049f);
        i.putExtra("lng", -123.1438943f);
        i.putExtra("placeName", "Stanley Park");
        i.putExtra("zoom", 14);
        startActivity(i);
    }

    public void locateGastown(View v) {
        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("lat", 49.283034f);
        i.putExtra("lng", -123.106315f);
        i.putExtra("placeName", "Gastown");
        i.putExtra("zoom", 16);
        startActivity(i);
    }



    @Override
    protected void onPause() {
        super.onPause();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        lm.removeUpdates(locationListener);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // To make sure there is an update with the latest data in the database when coming back to this activity
        getGalleryProgress(dbUserName);
        registerListener();
    }

    public void registerListener(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

//        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 5, locationListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 5, locationListener);
    }
}

