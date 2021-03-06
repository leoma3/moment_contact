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
import android.media.AudioManager;
import android.media.ToneGenerator;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LandingActivity extends Activity {

    EditText usernameEditText;
    EditText passwordEditText;
    public MyDatabase myDatabase;
    String username;
    String profileName;
    String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        usernameEditText = (EditText) findViewById(R.id.userEditText);
        passwordEditText = (EditText) findViewById(R.id.passEditText);
        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        myDatabase = new MyDatabase(this);


    }


    public void viewResults(View view) {

        Intent intent = new Intent(this, AllRowActivity.class);
        startActivity(intent);
    }


    public void login(View v) {

        String userName = usernameEditText.getText().toString().toLowerCase();
        profileName = usernameEditText.getText().toString();
        String pass = passwordEditText.getText().toString();

        Cursor whoResult = myDatabase.getUser(userName);
        int pwIndex = whoResult.getColumnIndex(Constants.PASSWORD);


        if (whoResult.moveToNext()) {

            String pwDB = whoResult.getString(pwIndex);

            Toast.makeText(getApplicationContext(), pwDB + "", Toast.LENGTH_SHORT).show();

            if (pass.equals(pwDB)) {

                Intent intent = new Intent(this, PlacesActivity.class);

                intent.putExtra("userName", userName);
                intent.putExtra("profileName", profileName);
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
        profileName = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();

        Toast.makeText(this, username + " " + password, Toast.LENGTH_SHORT).show();
        long id = myDatabase.insertData(username, password, "0,1,0,0,1", "1,0,1,0,1");
        if (id < 0) {
            Toast.makeText(this, "register fail, duplicated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "register success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PlacesActivity.class);
            intent.putExtra("userName", username);
            intent.putExtra("profileName", profileName);
            startActivity(intent);
        }

    }


    @Override
    protected void onPause() {
        super.onPause();


    }


}