package com.nettox.nettoxwapps;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferenceManager {

    private Context context;
    private SharedPreferences sharedPreferences;

    public SharedPreferenceManager(Context context) {
        this.context = context;
        getSharedPreference();
    }

    private void getSharedPreference () {
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.PRF_MAIN_KEY), Context.MODE_PRIVATE);
    }

    public void writePreference () {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.PRF_TUTORIAL), "true");
        editor.commit();
    }

    public boolean checkPreference () {
        boolean status = false;

        if (sharedPreferences.getString(context.getString(R.string.PRF_TUTORIAL), "null").equals("null")) {
            status = false;
        } else {
            status = true;
        }

        return status;
    }

    public void clearPreference () {
        sharedPreferences.edit().clear().commit();
    }
}
