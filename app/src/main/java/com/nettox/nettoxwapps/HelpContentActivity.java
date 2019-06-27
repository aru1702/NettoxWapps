package com.nettox.nettoxwapps;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.nettox.nettoxwapps.DbHelper.DbHelper_HelpMenu;
import com.nettox.nettoxwapps.DbModel.DbModel_HelpMenu;

public class HelpContentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_content);

        String dataId = getIntent().getStringExtra("id");

        DbHelper_HelpMenu dbHelper = new DbHelper_HelpMenu(this);

        TextView TV_title = (TextView) findViewById(R.id.txtV_helpcontent_title);
        TextView TV_subtitle = (TextView) findViewById(R.id.txtV_helpcontent_subtitle);
        TextView TV_description = (TextView) findViewById(R.id.txtV_helpcontent_description);
        ImageView IV_image = (ImageView) findViewById(R.id.imgV_helpcontent_image);

        DbModel_HelpMenu item = dbHelper.getHelp(Integer.valueOf(dataId));

        TV_title.setText(item.getTitle());
        TV_subtitle.setText(item.getSubtitle());
        TV_description.setText(item.getDescription());

        int imageResource = item.getImage();

        // dummy image
        IV_image.setImageResource(R.drawable.good);
    }
}
