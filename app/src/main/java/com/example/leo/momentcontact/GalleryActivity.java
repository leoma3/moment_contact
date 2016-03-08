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
public class GalleryActivity extends Activity {


    String[] progress;

    String where; // "aslkdja"


    int scount = 0;//stanleypark
    int stotal = 5;

    int gcount = 0;//gastown
    int gtotal = 5;

    TextView textView;

    ProgressBar progressbar;

    ImageButton imageButtons[] = new ImageButton[5];

    int[] sp = new int[5];
    int[] gt = new int[5];
    int[] spImages = {
            R.drawable.pic1, R.drawable.pic2, R.drawable.pic3, R.drawable.pic4, R.drawable.pic5};
    int[] gtImages={
            R.drawable.pic6, R.drawable.pic7, R.drawable.pic8, R.drawable.pic9, R.drawable.pic1};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        imageButtons[0] = (ImageButton) findViewById(R.id.kksbtn1);
        imageButtons[1] = (ImageButton) findViewById(R.id.kksbtn2);
        imageButtons[2] = (ImageButton) findViewById(R.id.kksbtn3);
        imageButtons[3] = (ImageButton) findViewById(R.id.kksbtn4);
        imageButtons[4] = (ImageButton) findViewById(R.id.kksbtn5);

        textView = (TextView) findViewById(R.id.textView);

        Bundle placesExtra = getIntent().getExtras();

        if (placesExtra != null) {
            where = placesExtra.getString("galleryName");

            String str = placesExtra.getString("progress");

            progress = Constants.convertStringToArray(str);

            galleryMainActivity(progress);
        }

    }


    public void galleryMainActivity(String[] strArr) {
        if (where.equals("Stanley Park")) {
            for (int i = 0; i < 5; i++) {

                sp[i] = Integer.parseInt(progress[i]);


                imageButtons[i].setBackgroundResource(spImages[i]);
                if (sp[i] == 1){
                    final int finalI = i;
                    imageButtons[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
                            intent.putExtra("imgnum", finalI);
                            startActivityForResult(intent, 0);
                        }
                    });
                    scount = sp[0] + sp[1] + sp[2] + sp[3] + sp[4];

                    textView.setText(scount + "/" + stotal);
                    progressbar = (ProgressBar) findViewById(R.id.progressBar);

                    progressbar.setProgress(100 * scount / stotal);

                }
                else{
                    imageButtons[i].setVisibility(View.INVISIBLE);
                }

            }
        } else {
            for (int i = 0; i < 5; i++) {
                gt[i] = Integer.parseInt(progress[i]);
                imageButtons[i].setBackgroundResource(gtImages[i]);
                if (gt[i] == 1){
                    final int finalI = i;
                    imageButtons[i].setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(getApplicationContext(), GalSubActivity.class);
                            intent.putExtra("imgnum", finalI+5);
                            startActivityForResult(intent, 0);
                        }
                    });

                }
                else{
                    imageButtons[i].setVisibility(View.INVISIBLE);
                }

            }
            gcount = gt[0] + gt[1] + gt[2] + gt[3] + gt[4];

            textView.setText(gcount + "/" + gtotal);
            progressbar = (ProgressBar) findViewById(R.id.progressBar);

            progressbar.setProgress(100 * gcount / gtotal);
        }
    }
}
