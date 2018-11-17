package com.example.senzerroom;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "RoomData.db";
    public static final String TABLE_NAME = "Room";
    public static final String COL_1 = "Time";
    public static final String COL_2 = "Vacent";
    public static final String COL_3 = "Temperature";
    public static final String COL_4 = "Lights";

    public DBHelper(Context context) {
        super(context, DB_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // for(int i = 1; i<= numRooms; i++) {
        //db.execSQL("CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COL_1 + " TEXT," + COL_2 + " TEXT," + COL_3 + "INTEGER" + COL_4 + "TEXT)");
        //}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void  initDB(int numRooms){
        SQLiteDatabase db = this.getWritableDatabase();
        for(int i = 1; i<= numRooms; i++) {
            db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + i + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," + COL_1 + " TEXT," + COL_2 + " TEXT," + COL_3 + "INTEGER" + COL_4 + "TEXT)");
        }

    }
}
