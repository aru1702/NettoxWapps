package com.nettox.nettoxwapps.DbHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nettox.nettoxwapps.SharedPreferenceManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.nettox.nettoxwapps.StaticFieldVariables.*;

public class DbHelper_LastId extends SQLiteOpenHelper {

    private Context myContext;
    private String DB_PATH;
    
    public DbHelper_LastId (Context context) {
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
        String checkCreate = SharedPreferenceManager.getFromPreference(this.myContext, TB_LASTID);
        if (checkCreate.isEmpty() || checkCreate.equals("") || checkCreate.equals("false")) {
            createDatabase(db);
            setInitialValueId(db);
            SharedPreferenceManager.saveIntoPreference(myContext, "true", TB_LASTID);
        }
    }

    private void createDatabase (SQLiteDatabase db) {
        final String query = "CREATE TABLE IF NOT EXISTS " + TB_LASTID + " (" +
                "" + RW_LASTID_NAME +" TEXT NOT NULL, " +
                "" + RW_LASTID_LASTINPUT + " INTEGER NOT NULL);";
        try {
            db.execSQL(query);
        } catch (SQLException e) {
            Log.e("Create Query: ", "cannot create new LASTID table!");
        }
    }

    private boolean setInitialValueId (SQLiteDatabase db) {
        final String[] tableName = {TB_HRVDATA, TB_TIMEUSAGE};
        for (int i = 0 ; i < tableName.length ; i++) {
            final String[] args = {tableName[i], String.valueOf(0)};
            final String query = "INSERT INTO " + TB_LASTID + " (" +
                "" + RW_LASTID_NAME +", " +
                "" + RW_LASTID_LASTINPUT + ") VALUES (?, ?);";
            try {
                db.execSQL(query, args);
            } catch (SQLException e) {
                Log.e("Insert Query: ","cannot insert new InitialValueId to TB_LASTID!");
                return false;
            }
        }
        return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }

    public int getHrvDataLastId () {
        SQLiteDatabase db = this.getReadableDatabase();
        final String query = "SELECT " + RW_LASTID_LASTINPUT + " FROM " + TB_LASTID +
                " WHERE " + RW_LASTID_NAME + "=" + TB_HRVDATA + ";";
        Cursor res = null;
        try {
            res = db.rawQuery(query, null);
            res.moveToFirst();

            int tempInt = res.getInt(res.getColumnIndex(RW_LASTID_LASTINPUT));
            return tempInt;
        } catch (SQLException e) {
            Log.e("Get Id: ", "error while query to get HrvData last id!");
            return -1;
        } finally {
            res.close();
        }
    }

    public int getLastTimeUsageId () {
        SQLiteDatabase db = this.getReadableDatabase();
        final String query = "SELECT " + RW_LASTID_LASTINPUT + " FROM " + TB_LASTID +
                " WHERE " + RW_LASTID_NAME + "=" + TB_TIMEUSAGE + ";";
        Cursor res = null;
        try {
            res = db.rawQuery(query, null);
            res.moveToFirst();

            int tempInt = res.getInt(res.getColumnIndex(RW_LASTID_LASTINPUT));
            return tempInt;
        } catch (SQLException e) {
            Log.e("Get Id: ", "error while query to get HrvData last id!");
            return -1;
        } finally {
            res.close();
        }
    }
}