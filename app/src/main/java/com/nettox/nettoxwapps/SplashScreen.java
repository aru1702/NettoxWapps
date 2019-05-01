package com.nettox.nettoxwapps;

import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class SplashScreen extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        webView = (WebView) findViewById(R.id.WBV_SplashScreen_Gears);
        webView.loadUrl("file:///android_asset/gears_rolling.html");
        webView.getSettings();
        webView.setBackgroundColor(Color.TRANSPARENT);
    }

    @Override
    protected void onStart() {
        super.onStart();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, PreApp.class);
                startActivity(i);
            }
        }, 1000);
    }
}
