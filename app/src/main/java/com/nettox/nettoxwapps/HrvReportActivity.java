package com.nettox.nettoxwapps;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import com.nettox.nettoxwapps.API.HrvReport;
import com.nettox.nettoxwapps.Adapter.HrvReportAdapter;
import com.nettox.nettoxwapps.DbAdapter.DbAdapter_HrvData;
import com.nettox.nettoxwapps.DbHelper.DbHelper_HrvData;
import com.nettox.nettoxwapps.DbModel.DbModel_HrvData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.nettox.nettoxwapps.StaticFieldVariables.TB_HRVDATA;

public class HrvReportActivity extends AppCompatActivity {

    private ImageView goBack;

    // a list to store all the products
    List<HrvReport> hrvReportList;

    // the recyclerview
    RecyclerView recyclerView;

    private DbAdapter_HrvData myAdapter;
    private List<DbModel_HrvData> myProductList;
    private DbHelper_HrvData myDBHelper;

    private String id[] = null;
    private String hrv_result[] = null;
    private String bpm_avg[] = null;
    private String hrv_time[] = null;
    private String comment[] = null;
    private String emot[] = null;

    private int countData = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrv_report);
    }

    @Override
    protected void onResume() {
        super.onResume();

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

        myDBHelper = new DbHelper_HrvData(this);

        SQLiteDatabase myDB = myDBHelper.getReadableDatabase();
        Cursor myCursor = myDB.rawQuery("SELECT * FROM " + TB_HRVDATA, null);
        myCursor.moveToFirst();

        countData = myCursor.getCount();
        id = new String[countData];
        hrv_result = new String[countData];
        bpm_avg = new String[countData];
        hrv_time = new String[countData];
        comment = new String[countData];
        emot = new String[countData];

        for (int i = 0 ; i < countData ; i++) {
            myCursor.moveToPosition(i);
            id[i] = myCursor.getString(0).toString();
            hrv_result[i] = myCursor.getString(1).toString();
            bpm_avg[i] = myCursor.getString(2).toString();
            hrv_time[i] = myCursor.getString(3).toString();
            comment[i] = myCursor.getString(4).toString();
            emot[i] = myCursor.getString(5).toString();
        }

        // initializing the productlist
        hrvReportList = new ArrayList<>();
        for (int i = 0 ; i < countData ; i++) {
            int myEmot = 0;

            if (emot[i].equals("1")) {
                myEmot = R.drawable.good;
            } else if (emot[i].equals("2")) {
                myEmot = R.drawable.average;
            } else if (emot[i].equals("3")){
                myEmot = R.drawable.bad;
            }

            hrvReportList.add(new HrvReport(
                    Integer.valueOf(id[i]),
                    hrv_time[i],
                    Integer.valueOf(hrv_result[i]),
                    myEmot
            ));
        }

        // adding some items to our list
        // furthermore using LOOP to get data from DATABASE
//        hrvReportList.add(new HrvReport(
//                1,
//                new Date(),
//                89,
//                R.drawable.good
//        ));
//        hrvReportList.add(new HrvReport(
//                2,
//                new Date(),
//                82,
//                R.drawable.good
//        ));
//        hrvReportList.add(new HrvReport(
//                3,
//                new Date(),
//                75,
//                R.drawable.average
//        ));

        // creating recyclerview adapter
        HrvReportAdapter adapter = new HrvReportAdapter(this, hrvReportList);

        // setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
