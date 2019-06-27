package com.nettox.nettoxwapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import static com.nettox.nettoxwapps.StaticFieldVariables.STATIC_PREFERENCE;

public class SharedPreferenceManager {

    public static SharedPreferences sharedPreferences;

    public static boolean saveIntoPreference (Context context, String saveData, String saveKey) {

        // open shared preference
        sharedPreferences = context.getSharedPreferences(STATIC_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // put the value into the preference
        editor.putString(saveKey, saveData);

        // commit the value (WRITE)
        return editor.commit();
    }

    public static String getFromPreference (Context context, String savedKey) {

        // call and open the shared preference
        sharedPreferences = context.getSharedPreferences(STATIC_PREFERENCE, Context.MODE_PRIVATE);

        // store value from preference into field
        if (sharedPreferences.contains(savedKey)) {
            return sharedPreferences.getString(savedKey, "");
        } else {
            Log.e("Getting preference: ", "No data on" + savedKey + " is saved!");
        }

        return "";
    }

    public boolean removeKeyPreference (Context context, String savedKey) {

        // open shared preference
        sharedPreferences = context.getSharedPreferences(STATIC_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // remove the value into the preference
        editor.remove(savedKey);

        return editor.commit();
    }
}
