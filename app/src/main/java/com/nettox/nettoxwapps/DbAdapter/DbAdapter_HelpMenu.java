package com.nettox.nettoxwapps.DbAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nettox.nettoxwapps.DbModel.DbModel_HelpMenu;
import com.nettox.nettoxwapps.R;

import java.util.ArrayList;

public class DbAdapter_HelpMenu extends BaseAdapter {

    private Context context;
    private ArrayList<DbModel_HelpMenu> dbModel_helpMenuList;

    public DbAdapter_HelpMenu(Context context, ArrayList<DbModel_HelpMenu> dbModel_helpMenuList) {
        this.context = context;
        this.dbModel_helpMenuList = dbModel_helpMenuList;
    }

    @Override
    public int getCount() {
        return dbModel_helpMenuList.size();
    }

    @Override
    public Object getItem(int position) {
        return dbModel_helpMenuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return dbModel_helpMenuList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.layout_help, null);
        TextView TV_title = (TextView) v.findViewById(R.id.layout_help_textV_title);
        TV_title.setText(dbModel_helpMenuList.get(position).getTitle());

        return v;
    }
}
