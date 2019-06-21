package com.nettox.nettoxwapps.DbAdapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nettox.nettoxwapps.DbModel.DbModel_HrvData;
import com.nettox.nettoxwapps.R;

import java.util.List;

public class DbAdapter_HrvData extends BaseAdapter {

    /**
     * Mendefinisikan variabel Context dan List yang digunakan.
     * Di sini menggunakan List dari Product_Challenge.java
     */
    private Context context;
    private List<DbModel_HrvData> dbModel_hrvDataList;

    public DbAdapter_HrvData(Context context, List<DbModel_HrvData> dbModel_hrvDataList) {
        this.context = context;
        this.dbModel_hrvDataList = dbModel_hrvDataList;
    }

    public int getCount() {
        return dbModel_hrvDataList.size();
    }


    public Object getItem(int position) {
        return dbModel_hrvDataList.get(position);
    }


    public long getItemId(int position) {
        return dbModel_hrvDataList.get(position).getId();
    }

    /**
     * Bagian di bawah ini akan membuat tampilan pada listview yang digunakan dengan:
     * @param position      : posisi dari tiap-tiap list pada listview
     * @param convertView   : ...
     * @param parent        : ...
     *
     * Definisikan setiap TextView pada listview yang ada.
     * Import data untuk TextView berdasarkan ProductList yang mengambil dari List<ProductList>
     * Data ditampilkan pada setText.
     *
     * @return : nilai dari hasil TextView masing-masing
     */


    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.activity_hrv_scan_result, null);

//        TextView TV_Judul = (TextView)v.findViewById(R.id.listview_challenge_level_title);
//        TV_Judul.setText(Adapter_ProductList_Challenges.get(position).getJudul());
//
//        ImageView IV_Medal = (ImageView)v.findViewById(R.id.listview_challenge_icon);
//        Integer myID = Adapter_ProductList_Challenges.get(position).getId();
//        if (myID == 1) {
//            IV_Medal.setImageResource(R.drawable.medal_bronze);
//        } else if (myID == 2) {
//            IV_Medal.setImageResource(R.drawable.medal_silver);
//        } else if (myID == 3) {
//            IV_Medal.setImageResource(R.drawable.medal_gold);
//        }

        return v;
    }
}
