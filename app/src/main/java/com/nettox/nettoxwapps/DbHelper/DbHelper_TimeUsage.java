package com.nettox.nettoxwapps.DbHelper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nettox.nettoxwapps.DbModel.DbModel_TimeUsage;
import com.nettox.nettoxwapps.SharedPreferenceManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.nettox.nettoxwapps.StaticFieldVariables.*;

public class DbHelper_TimeUsage extends SQLiteOpenHelper {

    private Context myContext;
    private String DB_PATH;

    public DbHelper_TimeUsage (Context context) {
        super(context, DB_NAME, null, DB_VER);

        if(android.os.Build.VERSION.SDK_INT >= 4.2){
            DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
        }

        this.myContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String checkCreate = SharedPreferenceManager.getFromPreference(myContext, TB_TIMEUSAGE);
        if (checkCreate.isEmpty() || checkCreate.equals("") || checkCreate.equals("false")) {
            createDatabase(db);
            SharedPreferenceManager.saveIntoPreference(myContext, "true", TB_TIMEUSAGE);
        }
    }

    private void createDatabase (SQLiteDatabase db) {
        final String query = "CREATE TABLE IF NOT EXISTS " + TB_TIMEUSAGE + " (" +
                "" + RW_TIMEUSAGE__ID +" INTEGER PRIMARY KEY, " +
                "" + RW_TIMEUSAGE_LASTPHONESLEEP + " INTEGER NOT NULL, " +
                "" + RW_TIMEUSAGE_PHONETIMEUSAGE + " INTEGER NOT NULL, " +
                "" + RW_TIMEUSAGE_LASTUPDATE + " TEXT NOT NULL);";
        try {
            db.execSQL(query);
        } catch (SQLException e) {
            Log.e("Create Query", "cannot create new TIMEUSAGE table!");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public int getTimeUsageSize () {
        SQLiteDatabase db = this.getReadableDatabase();
        final String query = "SELECT * FROM " + TB_TIMEUSAGE + ";";
        Cursor res = null;
        try {
            res = db.rawQuery(query, null);
            res.moveToFirst();

            int num = res.getCount();
            res.close();

            return num ;
        } catch (SQLException e) {
            Log.e("Get Size: ", "cannot get size of HrvData table!");
            return 0;
        } finally {
            if (res != null) {
                res.close();
            }
        }
    }

    public ArrayList<DbModel_TimeUsage> getTimeUsageList () {
        ArrayList<DbModel_TimeUsage> product_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TB_HRVDATA + ";", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            DbModel_TimeUsage product = new DbModel_TimeUsage(
                    res.getInt(res.getColumnIndex(RW_TIMEUSAGE__ID)),
                    res.getInt(res.getColumnIndex(RW_TIMEUSAGE_LASTPHONESLEEP)),
                    res.getInt(res.getColumnIndex(RW_TIMEUSAGE_PHONETIMEUSAGE)),
                    res.getString(res.getColumnIndex(RW_TIMEUSAGE_LASTUPDATE))
                    );
            product_list.add(product);
            res.moveToNext();
        }

        res.close();
        return product_list;
    }

    public DbModel_TimeUsage getTimeUsage (int id) {
        DbModel_TimeUsage product = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TB_HRVDATA + " WHERE " + RW_TIMEUSAGE__ID + "=" + id, null);
        res.moveToFirst();

        if (res.getCount() > 0) {
            product = new DbModel_TimeUsage(
                    res.getInt(res.getColumnIndex(RW_TIMEUSAGE__ID)),
                    res.getInt(res.getColumnIndex(RW_TIMEUSAGE_LASTPHONESLEEP)),
                    res.getInt(res.getColumnIndex(RW_TIMEUSAGE_PHONETIMEUSAGE)),
                    res.getString(res.getColumnIndex(RW_TIMEUSAGE_LASTUPDATE))
            );
        } else {
            Log.e("Get TimeUsage", "no data on ID: " + id);
        }

        res.close();
        return product;
    }

    public void insertIntoTimeUsage (int lastPhoneSleep, int phoneTimeUsage, String last_update) {
        SQLiteDatabase db = this.getWritableDatabase();

        DbHelper_LastId lastIdDbHelper = new DbHelper_LastId(myContext);
        int lastId = lastIdDbHelper.getTimeUsageLastId();

        if (lastId == -1) {
            Log.e("Insert data TimeUsage", "getting -1 from lastId");
            return;
        }
        lastId++;

        lastIdDbHelper.updateTimeUsageLastId(lastId);
        Log.d("Last TimeUsage id: ", String.valueOf(lastId));

        String[] args = {
                String.valueOf(lastId),
                String.valueOf(lastPhoneSleep),
                String.valueOf(phoneTimeUsage),
                last_update
        };
        final String query = "INSERT INTO " + TB_TIMEUSAGE + " (" +
                "" + RW_TIMEUSAGE__ID +", " +
                "" + RW_TIMEUSAGE_LASTPHONESLEEP + ", " +
                "" + RW_TIMEUSAGE_PHONETIMEUSAGE + ", " +
                "'" + RW_TIMEUSAGE_LASTUPDATE + "') VALUES (?, ?, ?, ?);";

        try {
            db.execSQL(query, args);
        } catch (SQLException e) {
            Log.e("Insert data TimeUsage", "error while insert query!");
        }
    }

    @SuppressLint("SimpleDateFormat")
    public void updateLastPhoneSleep (int id, int lastPhoneSleep) {
        SQLiteDatabase db = this.getWritableDatabase();
        Date date = new Date();
        SimpleDateFormat sdf1, sdf2;

        sdf1 = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        sdf2 = new SimpleDateFormat("HH:mm:ss");

        final String lastUpdate = sdf1.format(date) + " on " + sdf2.format(date);

        final String query = "UPDATE " + TB_LASTID + " SET "
                + RW_TIMEUSAGE_LASTPHONESLEEP + "=" + lastPhoneSleep + ", "
                + RW_TIMEUSAGE_LASTUPDATE + "='" + lastUpdate + "'"
                + " WHERE " + RW_TIMEUSAGE__ID + "=" + id + ";";

        try {
            db.execSQL(query);
        } catch (SQLException e) {
            Log.e("Update TimeUsage", "cannot update LastPhoneSleep on ID: " + id);
        }
    }

    @SuppressLint("SimpleDateFormat")
    public void updatePhoneTimeUsage (int id, int phoneTimeUsage) {
        SQLiteDatabase db = this.getWritableDatabase();
        Date date = new Date();
        SimpleDateFormat sdf1, sdf2;

        sdf1 = new SimpleDateFormat("EEEE, dd MMMM yyyy");
        sdf2 = new SimpleDateFormat("HH:mm:ss");

        final String lastUpdate = sdf1.format(date) + " on " + sdf2.format(date);

        final String query = "UPDATE " + TB_LASTID + " SET "
                + RW_TIMEUSAGE_PHONETIMEUSAGE + "=" + phoneTimeUsage + ", "
                + RW_TIMEUSAGE_LASTUPDATE + "='" + lastUpdate + "'"
                + " WHERE " + RW_TIMEUSAGE__ID + "=" + id + ";";

        try {
            db.execSQL(query);
        } catch (SQLException e) {
            Log.e("Update TimeUsage", "cannot update PhoneTimeUsage on ID: " + id);
        }
    }
}
