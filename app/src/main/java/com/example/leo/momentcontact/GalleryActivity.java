package com.example.leo.momentcontact;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gloriazhong on 2016-03-05.
 */
public class GalleryActivity extends Activity {


    String[] progress;

    String where; // "aslkdja"



    TextView title;


    ImageButton imageButtons[] = new ImageButton[5];

    String userName;

    int[] sp = new int[5];
    int[] gt = new int[5];
    int[] spImages = {
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};
    int[] gtImages={
            R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9, R.drawable.pic10};


    LocationManager lm;

    Location surreyStation = new Location("");//provider name is unecessary

    double longitude;
    double latitude;

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            float distanceInMeters = surreyStation.distanceTo(location);
            Toast.makeText(GalleryActivity.this, "location changed " + distanceInMeters + " meters", Toast.LENGTH_SHORT).show();

            ToneGenerator toneG = new ToneGenerator(AudioManager.STREAM_ALARM, 50);
            toneG.startTone(ToneGenerator.TONE_PROP_BEEP, 400);


            Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
            vibrator.vibrate(1500);

            if (distanceInMeters < 200){
                updateGallery();
            }

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


    MyDatabase myDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        surreyStation.setLatitude(49.189473d);//your coords of course
        surreyStation.setLongitude(-122.847834d);

        registerListener();

        setContentView(R.layout.activity_gallery);
        imageButtons[0] = (ImageButton) findViewById(R.id.kksbtn1);
        imageButtons[1] = (ImageButton) findViewById(R.id.kksbtn2);
        imageButtons[2] = (ImageButton) findViewById(R.id.kksbtn3);
        imageButtons[3] = (ImageButton) findViewById(R.id.kksbtn4);
        imageButtons[4] = (ImageButton) findViewById(R.id.kksbtn5);


        title= (TextView) findViewById(R.id.title);

        Bundle placesExtra = getIntent().getExtras();

        if (placesExtra != null) {
            where = placesExtra.getString("galleryName");

            userName = placesExtra.getString("userName");

            String str = placesExtra.getString("progress");

            progress = Constants.convertStringToArray(str);

            galleryMainActivity(progress);
        }
        myDatabase = new MyDatabase(this);

    }


    public void galleryMainActivity(String[] strArr) {
        if (where.equals("Stanley Park")) {
            title.setText("Stanley Park");
            for (int i = 0; i < 5; i++) {

                sp[i] = Integer.parseInt(progress[i]);


                imageButtons[i].setBackgroundResource(spImages[i]);
                if (sp[i] == 1){
                    final int finalI = i;
                    imageButtons[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
                            intent.putExtra("imgnum", finalI);
                            startActivityForResult(intent, 0);
                        }
                    });
                    imageButtons[i].setVisibility(View.VISIBLE);
                }
                else{
                    imageButtons[i].setVisibility(View.INVISIBLE);
                }

            }
        } else {
            title.setText("Gas Town");
            for (int i = 0; i < 5; i++) {
                gt[i] = Integer.parseInt(progress[i]);
                imageButtons[i].setBackgroundResource(gtImages[i]);
                if (gt[i] == 1){
                    final int finalI = i;
                    imageButtons[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
                            intent.putExtra("imgnum", finalI+5);
                            startActivityForResult(intent, 0);
                        }
                    });

                    imageButtons[i].setVisibility(View.VISIBLE);

                }
                else{
                    imageButtons[i].setVisibility(View.INVISIBLE);
                }

            }

        }
    }

    public void updateGallery() {
        progress = new String[]{"1","1","1","1","1"};
        String progessInString = Constants.convertArrayToString(progress);
        if (where.equals("Stanley Park")) {
            int num = myDatabase.updateRow(userName, Constants.STANLEY_PARK, progessInString);
            Toast.makeText(GalleryActivity.this, "stanly udpate : " + num, Toast.LENGTH_SHORT).show();
        }

        galleryMainActivity(progress);


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

        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 5, locationListener);
        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 5, locationListener);

    }
}
