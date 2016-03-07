package com.example.leo.momentcontact;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by gloriazhong on 2016-03-05.
 */
public class GalleryActivity extends Activity{

    TextView placeName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        placeName = (TextView)findViewById(R.id.galleryTextView);

        Bundle placesExtra = getIntent().getExtras();
        if(placesExtra != null) {
            String pName = placesExtra.getString("galleryName");
            placeName.setText(pName +" Gallery");

        }



    }
}
