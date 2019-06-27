package com.nettox.nettoxwapps;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.nettox.nettoxwapps.API.HrvReport;
import com.nettox.nettoxwapps.Adapter.HrvReportAdapter;
import com.nettox.nettoxwapps.DbAdapter.DbAdapter_HrvData;
import com.nettox.nettoxwapps.DbHelper.DbHelper_HrvData;
import com.nettox.nettoxwapps.DbModel.DbModel_HrvData;

import java.io.IOException;
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
    private ArrayList<DbModel_HrvData> myProductList;
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

        // getting database
        myDBHelper = new DbHelper_HrvData(this);

        // dummy data
        myDBHelper.insertIntoHrvData(
            80,
            96,
            "Very good health, sir!",
            1,
            "Wednesday, 26 June 2019 on 22:50:00"
        );
        myDBHelper.insertIntoHrvData(
            88,
            99,
            "Very good health, sir!",
            1,
            "Wednesday, 26 June 2019 on 22:58:00"
        );

        myProductList = myDBHelper.getListProduct();

        countData = myDBHelper.getHrvDataSize();
        id = new String[countData]; 
        hrv_result = new String[countData];
        bpm_avg = new String[countData];
        hrv_time = new String[countData];
        comment = new String[countData];
        emot = new String[countData];
        int counter = 0;

        for (DbModel_HrvData item : myProductList) {
            id[counter] = String.valueOf(item.getId());
            hrv_result[counter] = String.valueOf(item.getHrv_result());
            bpm_avg[counter] = String.valueOf(item.getBpm_avg());
            comment[counter] = item.getComment();
            emot[counter] = String.valueOf(item.getEmot());
            hrv_time[counter] = item.getHrv_time();
            counter++;
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

        // creating recyclerview adapter
        HrvReportAdapter adapter = new HrvReportAdapter(this, hrvReportList);

        // setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
        adapter.setItemClickListener(onItemClickListener);
    }

    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();

            HrvReport item = hrvReportList.get(position);
            int itemId = item.getId();
            int counter = 0;

            while (itemId != Integer.valueOf(id[counter])) {
                counter++;
            }

            Intent i = new Intent(HrvReportActivity.this, HrvScanResultActivity.class);
            i.putExtra("hrv_result", hrv_result[counter]);
            i.putExtra("bpm_avg", bpm_avg[counter]);
            i.putExtra("hrv_time", hrv_time[counter]);
            i.putExtra("comment", comment[counter]);
            startActivity(i);
        }
    };
}
