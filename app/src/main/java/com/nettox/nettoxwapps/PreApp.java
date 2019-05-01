package com.nettox.nettoxwapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class PreApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_app);
    }

    /**
     * These method is for onClick imageView
     * - btn_login
     * - btn_register
     * @param view
     */

    public void btnLoginToLoginActivity (View view) {
        // define here
        Intent i = new Intent(PreApp.this, Login.class);
        startActivity(i);
    }

    public void btnRegisterToRegisterActivity (View view) {
        // define here
        Intent i = new Intent(PreApp.this, Register.class);
        startActivity(i);
    }
}
