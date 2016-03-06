package com.example.leo.momentcontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by gloriazhong on 2016-03-05.
 */
public class PlacesActivity extends Activity {

    int completePercent;
    TextView profileName;
    TextView stanleyPlace;
    TextView gastownPlace;
    String stanleyName;
    String gastownName;
    MyDatabase db;
    SimpleCursorAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        stanleyPlace = (TextView) findViewById(R.id.stanleyTextView);
        stanleyName = "Stanley Park";
        gastownName = "Gastown";
        gastownPlace = (TextView) findViewById(R.id.gasTextView);
        db = new MyDatabase(this);

        Bundle profileExtra = getIntent().getExtras();
        profileName = (TextView) findViewById(R.id.profileNameTextView);

        String pName = profileExtra.getString("who");
        profileName.setText(pName);

    }

    public void stanleyGallery(View v) {
        Intent intent = new Intent(this, GalleryActivity.class);
        intent.putExtra("PLACE_NAME", stanleyName);
        startActivity(intent);
    }

    public void gastownGallery(View v) {
        Intent intent = new Intent(this, GalleryActivity.class);
        intent.putExtra("PLACE_NAME", gastownName);
        startActivity(intent);
    }


}

