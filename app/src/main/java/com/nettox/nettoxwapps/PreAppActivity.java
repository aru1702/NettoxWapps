package com.nettox.nettoxwapps;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class PreAppActivity extends AppCompatActivity {

    private ImageView pressStart;
    private TextView registered;

    private final String REGISTERED = "Registered";
    private final String UNREGISTERED = "Unregistered";

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_app);

//        ImageView login = (ImageView) findViewById(R.id.imgV_preapp_btn_login);
//        ImageView register = (ImageView) findViewById(R.id.imgV_preapp_btn_register);
//
//        login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(PreApp.this, Login.class));
//            }
//        });
//
//        register.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(PreApp.this, Register.class));
//            }
//        });

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
                progressDialog.dismiss();

                pressStart.setVisibility(View.VISIBLE);
                registered.setText(REGISTERED);
            }
        }, 3000);
    }

    @Override
    protected void onResume() {
        super.onResume();

        /**
         * TODO:
         * in here by using SharedPreference it will like this:
         * -> if ID is present then go to MainActivity
         * -> if ID is absence then go to RegisterActivity
         *
         * this is method to one-time register for user
         * basically this will generate like login session
         */

        pressStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog = new ProgressDialog();
                progressDialog.show(getSupportFragmentManager(), "");

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        progressDialog.dismiss();

                        // go to main menu or dashboard
                        startActivity(new Intent(PreAppActivity.this, MainActivity.class));
                        finish();
                    }
                }, 3000);
            }
        });
    }
}
