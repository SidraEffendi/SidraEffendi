package com.example.h.sidraeffendi;

/**
 * Created by h on 6/24/2015.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseOperations extends SQLiteOpenHelper {
    public DatabaseOperations(Context context) {
        super(context, TableData.DB_NAME, null, TableData.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqlDB) {
        String sqlQuery =
                String.format("CREATE TABLE "+TableData.TABLE1+" ("  +TableData.Columns._ID+
                                " INTEGER PRIMARY KEY AUTOINCREMENT," +
                                TableData.Columns.COURSE+" text,"+TableData.Columns.PROFESSOR+" text,"+
                        TableData.Columns.VENUE+" text,"+TableData.Columns.CODE+" text);");

        Log.d("DatabaseOperations","Query to form table: "+sqlQuery);
        sqlDB.execSQL(sqlQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqlDB, int i, int i2) {
        sqlDB.execSQL("DROP TABLE IF EXISTS "+TableData.TABLE1);
        onCreate(sqlDB);
    }
}