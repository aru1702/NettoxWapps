package com.nettox.nettoxwapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HrvScanResultActivity extends AppCompatActivity {

    private TextView hrvResult, bpmAverage, timestamp, commentJarvis;
    private ImageView goBack;

    private final SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy - HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrv_scan_result);

        hrvResult = (TextView) findViewById(R.id.textV_result_hrv);
        bpmAverage = (TextView) findViewById(R.id.textV_result_bpmAvg);
        timestamp = (TextView) findViewById(R.id.textV_result_time);
        commentJarvis = (TextView) findViewById(R.id.textV_result_comment);

        goBack = (ImageView) findViewById(R.id.imageV_result_goBack);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(HrvScanResultActivity.this, MainActivity.class));
                finish();
            }
        });

        final String S_hrv_result = getIntent().getStringExtra("hrv_result");
        final String S_bpm_avg = getIntent().getStringExtra("bpm_avg");
        final String S_hrv_time = getIntent().getStringExtra("hrv_time");
        final String S_comment = getIntent().getStringExtra("comment");

        // test input data
//        int hrv = 120;
//        int bpm = 80;
//        Date date = new Date();
//        date.getTime();
//        System.out.println("Ini waktu: " + date);
//        String comment = getResources().getString(R.string.result1);

        // set value to layout
        hrvResult.setText(S_hrv_result);
        bpmAverage.setText(S_bpm_avg);
        timestamp.setText(S_hrv_time);
        commentJarvis.setText(S_comment);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
    }
}
