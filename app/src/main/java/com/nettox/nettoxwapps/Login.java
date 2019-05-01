package com.nettox.nettoxwapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * For imageView with onClick
     * @param view
     */
    public void btnLogin (View view) {
        // define here

    }

    public void recoverAccount (View view) {
        Intent i = new Intent(Login.this, ResetPassword.class);
        startActivity(i);
    }
}
