package com.example.leo.momentcontact;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;

/**
 * Created by minsukmoon on 2016-03-07.
 */
public class GalSubActivity extends ActionBarActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subgallery);

        Intent intent = getIntent();
        int imgnum = intent.getIntExtra("imgnum", 0);

        ImageView bigimg = (ImageView) findViewById(R.id.bigimg);
        if(imgnum == 0)
            bigimg.setImageResource(R.drawable.pic1);
        else if(imgnum == 1)
            bigimg.setImageResource(R.drawable.pic2);
        else if(imgnum == 2)
            bigimg.setImageResource(R.drawable.pic3);
        else if(imgnum == 3)
            bigimg.setImageResource(R.drawable.pic4);
        else if(imgnum == 4)
            bigimg.setImageResource(R.drawable.pic5);
        else if(imgnum == 5)
            bigimg.setImageResource(R.drawable.pic6);
        else if(imgnum == 6)
            bigimg.setImageResource(R.drawable.pic7);
        else if(imgnum == 7)
            bigimg.setImageResource(R.drawable.pic8);
        else if(imgnum == 8)
            bigimg.setImageResource(R.drawable.pic9);
        else if(imgnum == 9)
            bigimg.setImageResource(R.drawable.pic1);

    }
}
