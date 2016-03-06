package com.example.leo.momentcontact;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class LandingActivity extends Activity {



    public MyDatabase myDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        myDatabase = new MyDatabase(this);

    }

    public void addSomething(View view){
        long id = myDatabase.insertData("LEO", "123", "01001", "1241233");
        if (id < 0)
        {
            Toast.makeText(this, "fail", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this, "success", Toast.LENGTH_SHORT).show();
        }
    }

    public void viewResults(View view)
    {

        Intent intent = new Intent(this, AllRowActivity.class);
        startActivity(intent);
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
