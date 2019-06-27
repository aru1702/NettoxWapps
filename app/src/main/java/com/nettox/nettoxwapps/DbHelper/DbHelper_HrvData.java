package com.nettox.nettoxwapps.DbHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.nettox.nettoxwapps.DbModel.DbModel_HrvData;
import com.nettox.nettoxwapps.SharedPreferenceManager;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import static com.nettox.nettoxwapps.StaticFieldVariables.*;

public class DbHelper_HrvData extends SQLiteOpenHelper {

    private Context myContext;
    private String DB_PATH;
    
    public DbHelper_HrvData (Context context) {
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
        String checkCreate = SharedPreferenceManager.getFromPreference(myContext, TB_HRVDATA);
        if (checkCreate.isEmpty() || checkCreate.equals("") || checkCreate.equals("false")) {
            createDatabase(db);
            SharedPreferenceManager.saveIntoPreference(myContext, "true", TB_HRVDATA);
        }
    }

    private void createDatabase (SQLiteDatabase db) {
        final String query = "CREATE TABLE IF NOT EXISTS " + TB_HRVDATA + " (" +
                "" + RW_HRVDATA__ID +" INTEGER PRIMARY KEY, " +
                "" + RW_HRVDATA_HRVRESULT + " INTEGER NOT NULL, " +
                "" + RW_HRVDATA_BPMAVG + " INTEGER NOT NULL, " +
                "" + RW_HRVDATA_COMMENT + " TEXT NOT NULL, " +
                "" + RW_HRVDATA_EMOT + " INTEGER NOT NULL, " +
                "" + RW_HRVDATA_LASTUPDATE + " TEXT NOT NULL);";
        try {
            db.execSQL(query);
        } catch (SQLException e) {
            Log.e("Create Query: ", "cannot create new HRVDATA table!");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        
    }

    public int getHrvDataSize () {
        SQLiteDatabase db = this.getReadableDatabase();
        final String query = "SELECT * FROM " + TB_HRVDATA + ";";
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
            res.close();
        }
    }

    public ArrayList<DbModel_HrvData> getListProduct () {
        ArrayList<DbModel_HrvData> product_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TB_HRVDATA + ";", null);
        res.moveToFirst();
        
        while (!res.isAfterLast()) {
            DbModel_HrvData product = new DbModel_HrvData(
                    res.getInt(res.getColumnIndex(RW_HRVDATA__ID)),
                    res.getInt(res.getColumnIndex(RW_HRVDATA_HRVRESULT)),
                    res.getInt(res.getColumnIndex(RW_HRVDATA_BPMAVG)),
                    res.getString(res.getColumnIndex(RW_HRVDATA_LASTUPDATE)),
                    res.getString(res.getColumnIndex(RW_HRVDATA_COMMENT)),
                    res.getInt(res.getColumnIndex(RW_HRVDATA_EMOT))
            );
            product_list.add(product);
            res.moveToNext();
        }

        res.close();
        return product_list;
    }

    public void insertIntoHrvData (int hrv_result, int bpm_avg, String comment, int emot, String last_update) {
        SQLiteDatabase db = this.getWritableDatabase();

        DbHelper_LastId lastIdDbHelper = new DbHelper_LastId(myContext);
        int lastId = lastIdDbHelper.getHrvDataLastId();

        if (lastId == -1) {
            Log.e("Insert data: ", "getting -1 from lastId");
            Toast.makeText(myContext, "Cannot insert data, try again later!", Toast.LENGTH_SHORT).show();
            return;
        }
        lastId++;
        lastIdDbHelper.updateHrvDataLastId(lastId);
        Log.d("Last HrvData id: ", String.valueOf(lastId));

        String[] args = {String.valueOf(lastId), String.valueOf(hrv_result), String.valueOf(bpm_avg), comment, String.valueOf(emot), last_update};
        final String query = "INSERT INTO " + TB_HRVDATA + " (" +
                "" + RW_HRVDATA__ID +", " +
                "" + RW_HRVDATA_HRVRESULT + ", " +
                "" + RW_HRVDATA_BPMAVG + ", " +
                "'" + RW_HRVDATA_COMMENT + "', " +
                "" + RW_HRVDATA_EMOT + ", " +
                "'" + RW_HRVDATA_LASTUPDATE + "') VALUES (?, ?, ?, ?, ?, ?);";

        try {
            db.execSQL(query, args);
        } catch (SQLException e) {
            Log.e("Insert data: ", "error while insert query!");
        }
    }

    public void closeDatabase () {
    }
}
