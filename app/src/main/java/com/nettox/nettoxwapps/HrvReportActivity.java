package com.nettox.nettoxwapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.nettox.nettoxwapps.API.HrvReport;
import com.nettox.nettoxwapps.Adapter.HrvReportAdapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class HrvReportActivity extends AppCompatActivity {

    private ImageView goBack;

    // a list to store all the products
    List<HrvReport> hrvReportList;

    // the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrv_report);

        goBack = (ImageView) findViewById(R.id.imageV_report_goBack);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // initializing the productlist
        hrvReportList = new ArrayList<>();

        // adding some items to our list
        // furthermore using LOOP to get data from DATABASE
        hrvReportList.add(new HrvReport(
                1,
                new Date(),
                89,
                R.drawable.good
        ));
        hrvReportList.add(new HrvReport(
                2,
                new Date(),
                82,
                R.drawable.good
        ));
        hrvReportList.add(new HrvReport(
                3,
                new Date(),
                75,
                R.drawable.average
        ));

        // creating recyclerview adapter
        HrvReportAdapter adapter = new HrvReportAdapter(this, hrvReportList);

        // setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
