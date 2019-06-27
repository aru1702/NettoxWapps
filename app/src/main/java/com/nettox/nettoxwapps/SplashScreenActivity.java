package com.nettox.nettoxwapps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Toast;

import com.nettox.nettoxwapps.DbHelper.DbHelper_HrvData;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import static com.nettox.nettoxwapps.StaticFieldVariables.*;

public class SplashScreenActivity extends AppCompatActivity {

    // create WebView field
    WebView webView;

    // make WebView for gears.gif
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        webView = (WebView) findViewById(R.id.WBV_SplashScreen_Gears);
        webView.loadUrl("file:///android_asset/gears_rolling.html");
        webView.getSettings();
        webView.setBackgroundColor(Color.TRANSPARENT);
    }

    // after several second, automatic go to Intent
    @Override
    protected void onStart() {
        super.onStart();

        // DUMMY DATA FOR TESTING ONLY
        if (SharedPreferenceManager.getFromPreference(this, "dummy1") == "") {
            DbHelper_HrvData dbHelper1 = new DbHelper_HrvData(this);
            dbHelper1.insertIntoHrvData(
                    80,
                    96,
                    "Very good health, sir!",
                    1,
                    "Wednesday, 26 June 2019 on 22:50:00"
            );
            dbHelper1.insertIntoHrvData(
                    88,
                    99,
                    "Very good health, sir!",
                    1,
                    "Wednesday, 26 June 2019 on 22:58:00"
            );
            SharedPreferenceManager.saveIntoPreference(this, "true", "dummy1");
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreenActivity.this, PreAppActivity.class);
                startActivity(i);
                finish();
            }
        }, 1500);
    }
}
