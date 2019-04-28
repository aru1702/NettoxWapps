package com.nettox.nettoxwapps;

import android.graphics.Color;
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
}
