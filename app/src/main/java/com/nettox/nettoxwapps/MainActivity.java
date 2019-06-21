package com.nettox.nettoxwapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nettox.nettoxwapps.API.HrvReport;

public class MainActivity extends AppCompatActivity {

    private int watchBattery, lastPhoneSleep, lastPhoneUsage, lastBpm;
    TextView tvWatchBattery, tvLastPhoneSleep, tvLastPhoneUsage, tvLastBpm;
    ImageView ivHrvReport, ivHrvScan, ivConfig, ivHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set TextView components
        tvWatchBattery = (TextView) findViewById(R.id.textV_main_watch);
        tvLastPhoneSleep = (TextView) findViewById(R.id.textV_main_lastSleep);
        tvLastPhoneUsage = (TextView) findViewById(R.id.textV_main_phoneUsage);
        tvLastBpm = (TextView) findViewById(R.id.textV_main_heartbeat);

        // set ImageView components
        ivHrvReport = (ImageView) findViewById(R.id.imageV_main_monitoringresult);
        ivHrvScan = (ImageView) findViewById(R.id.imageV_main_analyze);
        ivConfig = (ImageView) findViewById(R.id.imageV_main_settings_config);
        ivHelp = (ImageView) findViewById(R.id.imageV_main_helpmenu);

        // set routing from ImageView
        ivHrvReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HrvReportActivity.class));
            }
        });
        ivHrvScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HrvScanActivity.class));
            }
        });
        ivHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, HelpActivity.class));
            }
        });
        ivConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            }
        });

        ////////////////////////////
        // test input sample text //
        ////////////////////////////

        // set value into variable
        watchBattery = 89;
        lastPhoneSleep = 34;
        lastPhoneUsage = 127;
        lastBpm = 89;

        // set value into textView
        tvWatchBattery.setText(String.valueOf(watchBattery));
        tvLastPhoneUsage.setText(String.valueOf(lastPhoneUsage));
        tvLastPhoneSleep.setText(String.valueOf(lastPhoneSleep));
        tvLastBpm.setText(String.valueOf(lastBpm));
    }
}
