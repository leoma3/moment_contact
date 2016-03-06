package com.example.leo.momentcontact;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class LandingActivity extends AppCompatActivity {

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
}
