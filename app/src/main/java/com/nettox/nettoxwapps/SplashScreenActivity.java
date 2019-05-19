package com.nettox.nettoxwapps;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

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
