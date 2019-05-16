package com.nettox.nettoxwapps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int currentId, watchBattery, lastPhoneSleep, lastPhoneUsage, lastBpm;
    TextView tvCurrentId, tvWatchBattery, tvLastPhoneSleep, tvLastPhoneUsage, tvLastBpm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCurrentId = (TextView) findViewById(R.id.textV_main_Id);
        tvWatchBattery = (TextView) findViewById(R.id.textV_main_watch);
        tvLastPhoneSleep = (TextView) findViewById(R.id.textV_main_lastSleep);
        tvLastPhoneUsage = (TextView) findViewById(R.id.textV_main_phoneUsage);
        tvLastBpm = (TextView) findViewById(R.id.textV_main_heartbeat);

        ////////////////////////////
        // test input sample text //
        ////////////////////////////

        // set value into variable
        currentId = 123456;
        watchBattery = 89;
        lastPhoneSleep = 34;
        lastPhoneUsage = 127;
        lastBpm = 89;

        // set value into textView
        tvCurrentId.setText(String.valueOf(currentId));
        tvWatchBattery.setText(String.valueOf(watchBattery));
        tvLastPhoneUsage.setText(String.valueOf(lastPhoneUsage));
        tvLastPhoneSleep.setText(String.valueOf(lastPhoneSleep));
        tvLastBpm.setText(String.valueOf(lastBpm));
    }
}
