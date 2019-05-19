package com.nettox.nettoxwapps;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

public class HrvScanActivity extends AppCompatActivity {

    private ImageView btnScan, staticLine;
    private TextView textOrder;

    private String[] staticWord = {
            "Initialize your wearable device...",
            "Start connection to your wearable device...",
            "Wearable device connected!",

            "Please wait until your device finish scanning...",
            "Data collected!",

            "Generate analyze...",
            "Analyze completed!",

            "Wearable device not connected, please try again!",
            "Data not collected, please try again!",
            "Analyze error, please try again!"
    };

    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrv_scan);

        btnScan = (ImageView) findViewById(R.id.imageV_scan_btnAnalyze);
        staticLine = (ImageView) findViewById(R.id.imageV_scan_line);
        textOrder = (TextView) findViewById(R.id.textV_scan_attention);

        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getFading();

                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        initialize();
                    }
                }, 1100);
            }
        });
    }

    private void initialize () {

        // TODO:
        // initialize the wearable device
        // get the ID
        // make the connection to the wearable device
        // connection established

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textOrder.setText(staticWord[0]);
            }
        }, 2000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textOrder.setText(staticWord[1]);
            }
        }, 6000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textOrder.setText(staticWord[2]);
            }
        }, 9000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getData();
            }
        }, 10000);
    }

    private void getData () {

        // TODO:
        // scan using wearable device
        // using Listener to get the data every stream (maybe 0.3 sec.)
        // set data into field Array

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textOrder.setText(staticWord[3]);
            }
        }, 5000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textOrder.setText(staticWord[4]);
            }
        }, 8000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                takeAnalyze();
            }
        }, 9000);

    }

    private void takeAnalyze () {

        // TODO:
        // use data to create HRV graph
        // use data to create HRV value
        // use data to create analyze

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textOrder.setText(staticWord[5]);
            }
        }, 5000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                textOrder.setText(staticWord[6]);
            }
        }, 8000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                hasDone();
            }
        }, 9000);

    }

    private void hasDone () {

        // TODO:
        // data stored into SQLiteDatabase
        // data displayed into HrvScanResultActivity

        startActivity(new Intent(HrvScanActivity.this, HrvScanResultActivity.class));
        finish();

    }

    private void getFading() {

        Animation fadeOut = new AlphaAnimation(1, 0);
        fadeOut.setInterpolator(new AccelerateInterpolator());
        fadeOut.setDuration(1000);

        fadeOut.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationEnd(Animation animation)
            {
                btnScan.setVisibility(View.GONE);
                staticLine.setVisibility(View.GONE);
            }
            public void onAnimationRepeat(Animation animation) {}
            public void onAnimationStart(Animation animation) {}
        });

        btnScan.startAnimation(fadeOut);
        staticLine.startAnimation(fadeOut);
    }
}
