package com.example.leo.momentcontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by gloriazhong on 2016-03-05.
 */
public class GalleryActivity extends Activity{

    String given = "1,0,1,1,0";
    String given2= "1,0,0,0,1";

    String[] givenArray = {"1", "0", "1", "1", "0"};
    String[] givenArray2= {"1", "0", "0", "0", "1"};

    String where = "stanle"; // "aslkdja"



    int sp1=Integer.parseInt(givenArray[0]);
    int sp2=Integer.parseInt(givenArray[1]);
    int sp3=Integer.parseInt(givenArray[2]);
    int sp4=Integer.parseInt(givenArray[3]);
    int sp5=Integer.parseInt(givenArray[4]);

    int ps1=Integer.parseInt(givenArray2[0]);
    int ps2=Integer.parseInt(givenArray2[1]);
    int ps3=Integer.parseInt(givenArray2[2]);
    int ps4=Integer.parseInt(givenArray2[3]);
    int ps5=Integer.parseInt(givenArray2[4]);



    int scount=0;//stanleypark
    int stotal=5;

    int pcount=0;
    int ptotal=5;

    TextView textView;

    ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        ImageButton kksbtn1 = (ImageButton) findViewById(R.id.kksbtn1);
        ImageButton kksbtn2 = (ImageButton) findViewById(R.id.kksbtn2);
        ImageButton kksbtn3 = (ImageButton) findViewById(R.id.kksbtn3);
        ImageButton kksbtn4 = (ImageButton) findViewById(R.id.kksbtn4);
        ImageButton kksbtn5 = (ImageButton) findViewById(R.id.kksbtn5);




        textView=(TextView) findViewById(R.id.textView);

        Bundle placesExtra = getIntent().getExtras();
//        if(placesExtra != null) {
//            String pName = placesExtra.getString("galleryName");
//            placeName.setText(pName +" Gallery");






        if(where.equals("stanleypark")) {
            kksbtn1.setBackgroundResource(R.drawable.pic1);
            kksbtn2.setBackgroundResource(R.drawable.pic2);
            kksbtn3.setBackgroundResource(R.drawable.pic3);
            kksbtn4.setBackgroundResource(R.drawable.pic4);
            kksbtn5.setBackgroundResource(R.drawable.pic5);
            scount=sp1+sp2+sp3+sp4+sp5;

            textView.setText(scount+"/"+stotal);
            progressbar=(ProgressBar)findViewById(R.id.progressBar);

            progressbar.setProgress(100 * scount / stotal);

//            if (sp1 == 1) {
//
//                kksbtn1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
//                        intent.putExtra("imgnum", 1);
//                        startActivityForResult(intent, 0);
//                    }
//                });
//            } else {
//                kksbtn1.setVisibility(View.INVISIBLE);
//            }
//
//            if (sp2 == 1) {
//                kksbtn2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
//                        intent.putExtra("imgnum", 2);
//                        startActivityForResult(intent, 0);
//                    }
//                });
//            } else {
//                kksbtn2.setVisibility(View.INVISIBLE);
//            }
//            if (sp3 == 1) {
//                kksbtn3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
//                        intent.putExtra("imgnum", 3);
//                        startActivityForResult(intent, 0);
//                    }
//                });
//            } else {
//                kksbtn3.setVisibility(View.INVISIBLE);
//            }
//
//            if (sp4 == 1) {
//                kksbtn4.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
//                        intent.putExtra("imgnum", 2);
//                        startActivityForResult(intent, 0);
//                    }
//                });
//            } else {
//                kksbtn4.setVisibility(View.INVISIBLE);
//            }
//
//            if (sp5 == 1) {
//                kksbtn5.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
//                        intent.putExtra("imgnum", 2);
//                        startActivityForResult(intent, 0);
//                    }
//                });
//            } else {
//                kksbtn5.setVisibility(View.INVISIBLE);
//            }
        }



        //police st
        else{
            kksbtn1.setBackgroundResource(R.drawable.pic6);
            kksbtn2.setBackgroundResource(R.drawable.pic7);
            kksbtn3.setBackgroundResource(R.drawable.pic8);
            kksbtn4.setBackgroundResource(R.drawable.pic9);
            kksbtn5.setBackgroundResource(R.drawable.pic1);
            pcount=ps1+ps2+ps3+ps4+ps5;

            textView.setText(pcount + "/" + ptotal);
            progressbar=(ProgressBar)findViewById(R.id.progressBar);

            progressbar.setProgress(100 * pcount / ptotal);

//            if (ps1 == 1) {
//
//                kksbtn1.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
//                        intent.putExtra("imgnum", 1);
//                        startActivityForResult(intent, 0);
//                    }
//                });
//            } else {
//                kksbtn1.setVisibility(View.INVISIBLE);
//            }
//
//            if (ps2 == 1) {
//                kksbtn2.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
//                        intent.putExtra("imgnum", 2);
//                        startActivityForResult(intent, 0);
//                    }
//                });
//            } else {
//                kksbtn2.setVisibility(View.INVISIBLE);
//            }
//            if (ps3 == 1) {
//                kksbtn3.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
//                        intent.putExtra("imgnum", 3);
//                        startActivityForResult(intent, 0);
//                    }
//                });
//            } else {
//                kksbtn3.setVisibility(View.INVISIBLE);
//            }
//
//            if (ps4 == 1) {
//                kksbtn4.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
//                        intent.putExtra("imgnum", 2);
//                        startActivityForResult(intent, 0);
//                    }
//                });
//            } else {
//                kksbtn4.setVisibility(View.INVISIBLE);
//            }
//
//            if (ps5 == 1) {
//                kksbtn5.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
//                        intent.putExtra("imgnum", 2);
//                        startActivityForResult(intent, 0);
//                    }
//                });
//            } else {
//                kksbtn5.setVisibility(View.INVISIBLE);
//            }

        }

    }
}
