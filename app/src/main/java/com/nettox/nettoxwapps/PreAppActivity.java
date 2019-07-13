package com.nettox.nettoxwapps;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nettox.nettoxwapps.DbHelper.DbHelper_HelpMenu;
import com.nettox.nettoxwapps.DbHelper.DbHelper_HrvData;
import com.nettox.nettoxwapps.DbHelper.DbHelper_LastId;
import com.nettox.nettoxwapps.DbHelper.DbHelper_TimeUsage;

import static com.nettox.nettoxwapps.StaticFieldVariables.CONFIG_ALERT_TIMEUSAGE;
import static com.nettox.nettoxwapps.StaticFieldVariables.TUTORIALKEY;

public class PreAppActivity extends AppCompatActivity {

    private ImageView pressStart;
    private TextView registered;

    private final String REGISTERED = "Registered";
    private final String UNREGISTERED = "Unregistered";

    private static boolean alreadyTutorial = false;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_app);

        pressStart = (ImageView) findViewById(R.id.imgV_preapp_pressStart);
        registered = (TextView) findViewById(R.id.textV_preapp_registered);

        pressStart.setVisibility(View.INVISIBLE);

        progressDialog = new ProgressDialog();
        progressDialog.show(getSupportFragmentManager(), "Progress Dialog");
        progressDialog.setCancelable(false);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                String getTutorialCondition = SharedPreferenceManager.getFromPreference(PreAppActivity.this, TUTORIALKEY);
                if (getTutorialCondition.isEmpty() || getTutorialCondition.equals("") || getTutorialCondition.equals("false")) {
                    alreadyTutorial = false;
                    registered.setText(UNREGISTERED);

                    // create database
                    DbHelper_LastId dbh1 = new DbHelper_LastId(PreAppActivity.this);
                    dbh1.createDatabase();

                    DbHelper_HrvData dbh2 = new DbHelper_HrvData(PreAppActivity.this);
                    dbh2.createDatabase();

                    DbHelper_TimeUsage dbh3 = new DbHelper_TimeUsage(PreAppActivity.this);
                    dbh3.createDatabase();

                    DbHelper_HelpMenu dbh4 = new DbHelper_HelpMenu(PreAppActivity.this);
                    dbh4.createDatabase();

                    // initialize set alert
                    SharedPreferenceManager.saveIntoPreference(PreAppActivity.this, "60", CONFIG_ALERT_TIMEUSAGE);

                    // DUMMY DATA FOR TESTING ONLY
                    if (SharedPreferenceManager.getFromPreference(PreAppActivity.this, "dummy1") == "") {
                        DbHelper_HrvData dbHelper1 = new DbHelper_HrvData(PreAppActivity.this);
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
                        SharedPreferenceManager.saveIntoPreference(PreAppActivity.this, "true", "dummy1");
                    }
                    // ENDS HERE

                } else {
                    alreadyTutorial = true;
                    registered.setText(REGISTERED);
                }

                pressStart.setVisibility(View.VISIBLE);
                progressDialog.dismiss();
            }
        }, 3000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        pressStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alreadyTutorial) {
                    startActivity(new Intent(PreAppActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(PreAppActivity.this, TutorialActivity.class));
                }

                finish();
            }
        });
    }
}
