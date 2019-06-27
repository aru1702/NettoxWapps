package com.nettox.nettoxwapps;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.nettox.nettoxwapps.DbHelper.DbHelper_HelpMenu;
import com.nettox.nettoxwapps.DbHelper.DbHelper_HrvData;
import com.nettox.nettoxwapps.DbHelper.DbHelper_LastId;
import com.nettox.nettoxwapps.DbHelper.DbHelper_TimeUsage;

public class SplashScreenActivity extends AppCompatActivity {

    // create WebView field
    WebView webView;
    SQLiteDatabase db;

    // make WebView for gears.gif
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        webView = (WebView) findViewById(R.id.WBV_SplashScreen_Gears);
        webView.loadUrl("file:///android_asset/gears_rolling.html");
        webView.getSettings();
        webView.setBackgroundColor(Color.TRANSPARENT);

        // create database
        DbHelper_LastId dbh1 = new DbHelper_LastId(this);
        db = dbh1.getWritableDatabase();
        dbh1.onCreate(db);

        DbHelper_HrvData dbh2 = new DbHelper_HrvData(this);
        db = dbh2.getWritableDatabase();
        dbh2.onCreate(db);

        DbHelper_TimeUsage dbh3 = new DbHelper_TimeUsage(this);
        db = dbh3.getWritableDatabase();
        dbh3.onCreate(db);

        DbHelper_HelpMenu dbh4 = new DbHelper_HelpMenu(this);
        db = dbh4.getWritableDatabase();
        dbh4.onCreate(db);

        db.close();
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
        // ENDS HERE

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
