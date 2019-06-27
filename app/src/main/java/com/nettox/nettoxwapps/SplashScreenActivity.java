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

    private DbHelper_HrvData dbHelper_hrvData;
    File database;

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

        dbHelper_hrvData = new DbHelper_HrvData(this);
//        database = getApplicationContext().getDatabasePath(DB_NAME);
//        if (!database.exists()){
//            dbHelper_hrvData.getReadableDatabase();
//            if (copyDatabase(this)){
//                Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
//            }
//        }

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
