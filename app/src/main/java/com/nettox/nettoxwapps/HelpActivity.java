package com.nettox.nettoxwapps;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.nettox.nettoxwapps.DbAdapter.DbAdapter_HelpMenu;
import com.nettox.nettoxwapps.DbHelper.DbHelper_HelpMenu;
import com.nettox.nettoxwapps.DbModel.DbModel_HelpMenu;

import java.util.List;

import static com.nettox.nettoxwapps.StaticFieldVariables.TB_HELPMENU;

public class HelpActivity extends AppCompatActivity {

    private ListView listView;
    private DbAdapter_HelpMenu dbAdapter_helpMenu;
    private List<DbModel_HelpMenu> adapterList;
    private DbHelper_HelpMenu dbHelper_helpMenu;

    private String dataId[] = null;
    private int countData = 0;

    private ImageView goBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        goBack = (ImageView) findViewById(R.id.imgV_helpmenu_goback);
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        listView = (ListView) findViewById(R.id.listV_helpmenu_helplist);
        dbHelper_helpMenu = new DbHelper_HelpMenu(this);

        /**
         * Bagian di bawah ini mengambil data dari myDBHelper dengan membuka database
         * secara Readable, yang berarti read only databas. Gunakan Writeable untuk
         * menggunakan database dan bisa mengubahnya
         *
         * Database yang digunakan dipanggil dengan menggunakan variabel String yang sudah
         * didefinisikan sebelumnya pada class dbHelper masing-masing database
         */

        SQLiteDatabase myDB = dbHelper_helpMenu.getReadableDatabase();
        Cursor myCursor = myDB.rawQuery("SELECT * FROM " + TB_HELPMENU, null);
        myCursor.moveToFirst();

        countData = myCursor.getCount();
        dataId = new String[countData];

        for (int i = 0 ; i < countData ; i++) {
            myCursor.moveToPosition(i);
            dataId[i] = myCursor.getString(0).toString();
        }

        /**
         * Hasilnya untuk mendapatkan seluruh data dari database lalu dimasukkan ke dalam
         * variabel array untuk memuat suatu data.
         *
         * -> myCursor mendapatkan hasil tabel dari query yang ada
         * -> countData berisi banyaknya row
         * -> dataID[] berisi isi "id" dari database
         * -> dataTitle1[] berisi isi "judul" dari data, lihat bagian FOR STATEMENT
         * -> dataTitle2[] berisi isi "banyakselesai" dari data, lihat bagian FOR STATEMENT
         */

        adapterList = dbHelper_helpMenu.getListProduct();
        dbAdapter_helpMenu = new DbAdapter_HelpMenu(this, adapterList);
        listView.setAdapter(dbAdapter_helpMenu);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            /**
             * @params parent       : ...
             * @params view         : ...
             * @params position     : posisi dari data ke berapa per yang ditampilkan, dari 0
             * @params id           : id dari tiap-tiap link yang dibuat, berawal dari 1
             *
             */

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectionID = dataId[position];

                /**
                 * Bagian di bawah ini mengambil data pada masing-masing variabel array dataTitle(1/2)[]
                 * untuk di passing ke class java selanjutnya.
                 *
                 * Adapun pemakaian switch case untuk mengarahkan berdasarkan ID_ENTITY apakah masuk ke
                 * class java ke-1, ke-2, dst.
                 */

                Intent myIntent = new Intent(HelpActivity.this, HelpContentActivity.class);
                myIntent.putExtra("id", selectionID);
                startActivity(myIntent);
            }
        });
    }
}
