package com.byegor.edrive;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;


import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;



import com.byegor.edrive.localDB.DBHelper;
import com.byegor.edrive.repository.MachineRepository;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FloatingActionButton btnAdd;
    DBHelper dbHelper;
    SimpleCursorAdapter simpleCursorAdapter;
    MachineRepository machineRepository;
    String[] projections = {"_id", "name", "fuel"};
    String [] from = new String[] {"name" , "fuel"};
    int [] to = new int[] {R.id.tvMachineName, R.id.tvMachineFuel, R.id.tvMachineId};
    ListView machineList;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        machineList = (ListView) findViewById(R.id.lvMachineList);
        dbHelper = new DBHelper(this);
        db = dbHelper.getWritableDatabase();


        btnAdd = (FloatingActionButton) findViewById(R.id.btnAddNewMachine);
        btnAdd.setOnClickListener(this);

        Cursor cursor = db.query("machine", null, null, null, null, null, null);
        startManagingCursor(cursor);

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.machine_point, cursor, from, to);
        machineList = (ListView) findViewById(R.id.lvMachineList);
        machineList.setAdapter(adapter);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnAddNewMachine:
                Intent intent = new Intent(this, NewMachineActivity.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    //@Override
//    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
//        return new MyCursorLoader(this, db);
//    }

//    @Override
//    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
//
//    }
//
//    @Override
//    public void onLoaderReset(Loader<Cursor> loader) {
//
//    }

//    static class MyCursorLoader extends CursorLoader {
//
//        DB db;
//
//        public MyCursorLoader(Context context, DB db) {
//            super(context);
//            this.db = db;
//        }
//
//        @Override
//        public Cursor loadInBackground() {
//            Cursor cursor = db.getAllData();
//            try {
//                TimeUnit.SECONDS.sleep(3);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            return cursor;
//        }
//
//    }

}