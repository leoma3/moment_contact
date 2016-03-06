package com.example.leo.momentcontact;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class LandingActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
    }

    public void login(View v){
        Intent intent = new Intent(this, PlacesActivity.class);
        startActivity(intent);
    }

    public void register(View v){
        Intent intent = new Intent(this, PlacesActivity.class);
        startActivity(intent);
    }
}
