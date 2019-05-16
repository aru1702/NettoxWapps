package com.nettox.nettoxwapps;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        TextView recover = (TextView) findViewById(R.id.textV_login_recover);
        ImageView login = (ImageView) findViewById(R.id.imgV_register_btnregisterlong);

        // when recover being clicked
        recover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, ResetPassword.class));
            }
        });

        // when login button being clicked
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // test into the main menu
                startActivity(new Intent(Login.this, MainActivity.class));

            }
        });
    }
}
