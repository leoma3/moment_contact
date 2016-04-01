package com.example.leo.momentcontact;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LandingActivity extends Activity {

    EditText usernameEditText;
    EditText passwordEditText;
    public MyDatabase myDatabase;
    String username;
    String password;

    LocationManager lm;

    double longitude;
    double latitude;

    LocationListener locationListener = new LocationListener() {
        public void onLocationChanged(Location location) {
            longitude = location.getLongitude();
            latitude = location.getLatitude();
            Toast.makeText(LandingActivity.this, "location changed", Toast.LENGTH_SHORT).show();
//            double distance =
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Toast.makeText(LandingActivity.this, "status changed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderEnabled(String provider) {
            Toast.makeText(LandingActivity.this, "provider enable", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onProviderDisabled(String provider) {
            Toast.makeText(LandingActivity.this, " provide disable", Toast.LENGTH_SHORT).show();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        usernameEditText = (EditText) findViewById(R.id.userEditText);
        passwordEditText = (EditText) findViewById(R.id.passEditText);
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        myDatabase = new MyDatabase(this);

//        String[] strArr = {"1","2","3"};
//
//        String strResult = Constants.convertArrayToString(strArr);
//
//        String[] newArr = Constants.convertStringToArray(strResult);
//
//
//        Toast.makeText(this, newArr[2], Toast.LENGTH_LONG).show();


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

        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 2000, 10, locationListener);

    }

    public void addSomething(View view) {
        long id = myDatabase.insertData("LEO", "123", "0,1,0,0,1", "1,1,1,1,1");
        if (id < 0) {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewResults(View view) {

        Intent intent = new Intent(this, AllRowActivity.class);
        startActivity(intent);
    }


    public void login(View v) {

        String who = usernameEditText.getText().toString().toLowerCase();
        String pass = passwordEditText.getText().toString();

        Cursor whoResult = myDatabase.getUser(who);
        int pwIndex = whoResult.getColumnIndex(Constants.PASSWORD);

//        whoResult.moveToNext();


        if (whoResult.moveToNext()) {

            String pwDB = whoResult.getString(pwIndex);

            Toast.makeText(getApplicationContext(), pwDB + "", Toast.LENGTH_SHORT).show();

            if (pass.equals(pwDB)) {

                Intent intent = new Intent(this, PlacesActivity.class);

                intent.putExtra("who", who);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Password doesn't match, try again", Toast.LENGTH_SHORT).show();
                passwordEditText.setText("");
            }

        } else {

            Toast.makeText(this, "DNS please register", Toast.LENGTH_SHORT).show();
        }

    }

    public void register(View v) {

        username = usernameEditText.getText().toString().toLowerCase();
        password = passwordEditText.getText().toString();

        Toast.makeText(this, username + " " + password, Toast.LENGTH_SHORT).show();
        long id = myDatabase.insertData(username, password, "0,0,0,0,1", "1,0,1,0,0");
        if (id < 0) {
            Toast.makeText(this, "register fail, duplicated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "register success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PlacesActivity.class);
            intent.putExtra("who", username);
            startActivity(intent);
        }

    }

    public void updateButton(View view) {
        username = usernameEditText.getText().toString().toLowerCase();
        int num = myDatabase.updateRow(username, Constants.STANLEY_PARK, "1,1,1,1,1");
        Toast.makeText(LandingActivity.this, "" + num, Toast.LENGTH_SHORT).show();
    }

    public void locationButton(View view) {
        Toast.makeText(LandingActivity.this, latitude + " " + longitude, Toast.LENGTH_SHORT).show();
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
}