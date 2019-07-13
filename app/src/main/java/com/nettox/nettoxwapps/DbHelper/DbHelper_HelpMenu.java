package com.nettox.nettoxwapps.DbHelper;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.nettox.nettoxwapps.API.InitializeHelpMenu;
import com.nettox.nettoxwapps.DbModel.DbModel_HelpMenu;
import com.nettox.nettoxwapps.SharedPreferenceManager;

import java.util.ArrayList;

import static com.nettox.nettoxwapps.StaticFieldVariables.*;

public class DbHelper_HelpMenu extends SQLiteOpenHelper {

    private Context myContext;
    private String DB_PATH;

    public DbHelper_HelpMenu (Context context) {
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

    }

    public void createDatabase () {
        String checkCreate = SharedPreferenceManager.getFromPreference(myContext, TB_HELPMENU);
        if (checkCreate.isEmpty() || checkCreate.equals("") || checkCreate.equals("false")) {
            SQLiteDatabase db = this.getWritableDatabase();

            final String query = "CREATE TABLE IF NOT EXISTS " + TB_HELPMENU + " (" +
                    "" + RW_HELPMENU__ID +" INTEGER PRIMARY KEY, " +
                    "" + RW_HELPMENU_TITLE + " TEXT NOT NULL, " +
                    "" + RW_HELPMENU_IMAGE + " INTEGER NOT NULL, " +
                    "" + RW_HELPMENU_SUBTITLE + " TEXT NOT NULL, " +
                    "" + RW_HELPMENU_DESCRIPTION + " TEXT NOT NULL);";
            try {
                db.execSQL(query);
            } catch (SQLException e) {
                Log.e("Create Query", "cannot create new HELPMENU table!");
            }

            SharedPreferenceManager.saveIntoPreference(myContext, "true", TB_HELPMENU);
            InitializeHelpMenu.initialize(myContext);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

//    public int getHelpMenuSize () {
//        SQLiteDatabase db = this.getReadableDatabase();
//        final String query = "SELECT * FROM " + TB_HELPMENU + ";";
//        Cursor res = null;
//        try {
//            res = db.rawQuery(query, null);
//            res.moveToFirst();
//
//            int num = res.getCount();
//            res.close();
//
//            return num ;
//        } catch (SQLException e) {
//            Log.e("Get Size: ", "cannot get size of HrvData table!");
//            return 0;
//        } finally {
//            if (res != null) {
//                res.close();
//            }
//        }
//    }

    public ArrayList<DbModel_HelpMenu> getHelpMenuList () {
        ArrayList<DbModel_HelpMenu> product_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TB_HELPMENU + ";", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            DbModel_HelpMenu product = new DbModel_HelpMenu(
                    res.getInt(res.getColumnIndex(RW_HELPMENU__ID)),
                    res.getString(res.getColumnIndex(RW_HELPMENU_TITLE)),
                    res.getInt(res.getColumnIndex(RW_HELPMENU_IMAGE)),
                    res.getString(res.getColumnIndex(RW_HELPMENU_SUBTITLE)),
                    res.getString(res.getColumnIndex(RW_HELPMENU_DESCRIPTION))
            );
            product_list.add(product);
            res.moveToNext();
        }

        res.close();
        return product_list;
    }

    public DbModel_HelpMenu getHelp (int id) {
        DbModel_HelpMenu product = null;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + TB_HELPMENU + " WHERE " + RW_HELPMENU__ID + "=" + id, null);
        res.moveToFirst();

        if (res.getCount() > 0) {
            product = new DbModel_HelpMenu(
                    res.getInt(res.getColumnIndex(RW_HELPMENU__ID)),
                    res.getString(res.getColumnIndex(RW_HELPMENU_TITLE)),
                    res.getInt(res.getColumnIndex(RW_HELPMENU_IMAGE)),
                    res.getString(res.getColumnIndex(RW_HELPMENU_SUBTITLE)),
                    res.getString(res.getColumnIndex(RW_HELPMENU_DESCRIPTION))
            );
        } else {
            Log.e("Get Help", "no data on ID: " + id);
        }

        res.close();
        return product;
    }

    public void insertIntoHelpMenu (DbModel_HelpMenu model) {
        SQLiteDatabase db = this.getWritableDatabase();

        String[] args = {
                String.valueOf(model.getId()),
                model.getTitle(),
                String.valueOf(model.getImage()),
                model.getSubtitle(),
                model.getDescription()
        };
        final String query = "INSERT INTO " + TB_HELPMENU + " (" +
                "" + RW_HELPMENU__ID +", " +
                "'" + RW_HELPMENU_TITLE + "', " +
                "" + RW_HELPMENU_IMAGE + ", " +
                "'" + RW_HELPMENU_SUBTITLE + "', " +
                "'" + RW_HELPMENU_DESCRIPTION + "') VALUES (?, ?, ?, ?, ?);";

        try {
            db.execSQL(query, args);
        } catch (SQLException e) {
            Log.e("Insert data HelpMenu", "error while insert query!");
        }
    }
}
