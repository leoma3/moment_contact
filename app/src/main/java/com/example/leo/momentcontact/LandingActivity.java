package com.example.leo.momentcontact;


import android.app.Activity;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class LandingActivity extends Activity {

    EditText usernameEditText;
    EditText passwordEditText;
    public MyDatabase myDatabase;
    SQLiteDatabase sqLiteDatabase;
    String username;
    String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        usernameEditText = (EditText)findViewById(R.id.userEditText);
        passwordEditText = (EditText)findViewById(R.id.passEditText);
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

    }

    public void addSomething(View view) {
        long id = myDatabase.insertData("LEO", "123", "01001", "1241233");
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


        if (whoResult.moveToNext()){

            String pwDB = whoResult.getString(pwIndex);

            Toast.makeText(LandingActivity.this, pwDB+"", Toast.LENGTH_SHORT).show();

            if (pass.equals(pwDB)){

                Intent intent = new Intent(this, PlacesActivity.class);

                intent.putExtra("who", who);
                startActivity(intent);
            }
            else {
                Toast.makeText(this, "Password doesn't match, try again", Toast.LENGTH_SHORT).show();
            }

        }
        else{

            Toast.makeText(this, "DNS please register", Toast.LENGTH_SHORT).show();
        }

    }

    public void register(View v) {

        username = usernameEditText.getText().toString().toLowerCase();
        password = passwordEditText.getText().toString();

        Toast.makeText(this, username + " " + password, Toast.LENGTH_SHORT).show();
        long id = myDatabase.insertData(username, password, "[0,0,0,0,1]", "[0,0,0,0,0]");
        if (id < 0) {
            Toast.makeText(this, "register fail, duplicated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "register success", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PlacesActivity.class);
            intent.putExtra("who", username);
            startActivity(intent);
        }

    }
}
