package com.nettox.nettoxwapps;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nettox.nettoxwapps.DbHelper.DbHelper_HelpMenu;

import static com.nettox.nettoxwapps.StaticFieldVariables.TB_HELPMENU;

public class HelpContentActivity extends AppCompatActivity {

    private String dataId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_content);

        dataId = getIntent().getStringExtra("id");

        DbHelper_HelpMenu dbHelper = new DbHelper_HelpMenu(this);

        TextView TV_title = (TextView) findViewById(R.id.txtV_helpcontent_title);
        TextView TV_subtitle = (TextView) findViewById(R.id.txtV_helpcontent_subtitle);
        TextView TV_description = (TextView) findViewById(R.id.txtV_helpcontent_description);
        ImageView IV_image = (ImageView) findViewById(R.id.imgV_helpcontent_image);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] selectionArgs = {dataId};

        Cursor cursor = db.rawQuery("SELECT * FROM " + TB_HELPMENU + " WHERE id=?", selectionArgs);

        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);

            int getImageRef = cursor.getInt(3);

            TV_title.setText(cursor.getString(1));
            TV_subtitle.setText(cursor.getString(2));
            TV_description.setText(cursor.getString(4));

            IV_image.setImageResource(getImageRef);
        }
    }
}
