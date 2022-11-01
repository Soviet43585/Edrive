package com.byegor.edrive.localDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.byegor.edrive.dao.ConsumptionDAO;
import com.byegor.edrive.dao.MachineDAO;
import com.byegor.edrive.model.Consumption;
import com.byegor.edrive.model.Machine;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.Connection;
import java.sql.SQLException;


public class DBHelper extends OrmLiteSqliteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "machines";
    private MachineDAO machineDAO = null;
    private ConsumptionDAO consumptionDAO = null;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String TAG = DBHelper.class.getSimpleName();


    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
//        db.execSQL("CREATE TABLE machine (" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                "name TEXT NOT NULL, " +
//                "fuel INTEGER NOT NULL); ");
//        db.execSQL("CREATE TABLE consumption (" +
//                "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
//                "name TEXT NOT NULL, " +
//                "fuel_consumption FLOAT NOT NULL, " +
//                "conventional_unit FLOAT NOT NULL, " +
//                "machine_id INTEGER NOT NULL, " +
//                "FOREIGN KEY(machine_id) REFERENCES machine(id));");
//        db.execSQL("CREATE TABLE job ( " +
//                "machine_id INTEGER NOT NULL, " +
//                "consumption_id INTEGER NOT NULL, " +
//                "date DATE NOT NULL, " +
//                "job INTEGER NOT NULL, " +
//                "FOREIGN KEY(machine_id) REFERENCES machine(id), " +
//                "FOREIGN KEY(consumption_id) REFERENCES consumption(id));");
        try {
            TableUtils.createTable(connectionSource, Machine.class);
            TableUtils.createTable(connectionSource, Consumption.class);
        } catch (SQLException e) {
            Log.e(TAG, "error creating DB " + DATABASE_NAME);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int i, int i1) {
//        db.execSQL("DROP TABLE IF EXISTS machine; ");
//        db.execSQL("DROP TABLE IF EXISTS consumption;");
//        db.execSQL("DROP TABLE IF EXISTS job; ");
//        onCreate(db);
        try{
            //Так делают ленивые, гораздо предпочтительнее не удаляя БД аккуратно вносить изменения
            TableUtils.dropTable(connectionSource, Machine.class, true);
            TableUtils.dropTable(connectionSource, Consumption.class, true);
            onCreate(db, connectionSource);
        }
        catch (SQLException e){
            Log.e(TAG,"error upgrading db " + DATABASE_NAME+"from ver " + DATABASE_VERSION);
            throw new RuntimeException(e);
        }
    }

    public MachineDAO getMachineDAO() throws SQLException {
        if(machineDAO == null) {
            machineDAO = new MachineDAO(getConnectionSource(), Machine.class);
        }
        return machineDAO;
    }

    public ConsumptionDAO getConsumptionDAO() throws  SQLException {
        if(consumptionDAO == null) {
            consumptionDAO = new ConsumptionDAO(getConnectionSource(), Consumption.class);
        }
        return consumptionDAO;
    }

    @Override
    public void close() {
        super.close();
        machineDAO = null;
        consumptionDAO = null;
    }
}
