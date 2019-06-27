package com.nettox.nettoxwapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.nettox.nettoxwapps.DbAdapter.DbAdapter_HelpMenu;
import com.nettox.nettoxwapps.DbHelper.DbHelper_HelpMenu;
import com.nettox.nettoxwapps.DbModel.DbModel_HelpMenu;

import java.util.ArrayList;

public class HelpActivity extends AppCompatActivity {

    private ArrayList<DbModel_HelpMenu> adapterList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        ImageView goBack = (ImageView) findViewById(R.id.imgV_helpmenu_goback);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ListView listView = (ListView) findViewById(R.id.listV_helpmenu_helplist);
        DbHelper_HelpMenu dbHelper_helpMenu = new DbHelper_HelpMenu(this);

        adapterList = dbHelper_helpMenu.getHelpMenuList();

        DbAdapter_HelpMenu dbAdapter_helpMenu = new DbAdapter_HelpMenu(this, adapterList);
        listView.setAdapter(dbAdapter_helpMenu);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(HelpActivity.this, HelpContentActivity.class);
                myIntent.putExtra("id", String.valueOf(adapterList.get(position).getId()));
                startActivity(myIntent);
            }
        });
    }
}
