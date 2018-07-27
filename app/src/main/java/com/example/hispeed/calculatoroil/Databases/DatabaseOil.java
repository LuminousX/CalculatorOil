package com.example.hispeed.calculatoroil.Databases;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hispeed on 22/6/2560.
 */

public class DatabaseOil extends SQLiteOpenHelper {

    private static final String DB_NAME = "Travel";
    private static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Information_Travel";
    public static final String COL_NAME = "Name";
    public static final String COL_TYPE_CAR = "type_car";
    public static final String COL_ORIGIN = "Origin";
    public static final String COL_DESTINATION = "Destination";
    public static final String COL_DISTANCE = "Distance";
    public static final String COL_DURATION = "Duration";
    public static final String COL_TYPE_OIL = "Type_Oil";
    public static final String COL_SPEND_OIL = "Spend_Oil";
    public static final String COL_MONEY = "Money";
    public static final String COL_DATE = "date";
    public static final String COL_AVERAGE_BAHT = "Average_baht";

    SQLiteDatabase sqLiteDatabase;

    public DatabaseOil(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_NAME + " TEXT, " + COL_TYPE_CAR + " TEXT, " + COL_ORIGIN + " TEXT, " + COL_DESTINATION + " TEXT, " + COL_DISTANCE + " TEXT, "
                + COL_DURATION + " TEXT, " + COL_TYPE_OIL + " TEXT, " + COL_SPEND_OIL + " TEXT, " + COL_MONEY + " TEXT, " + COL_DATE + " TEXT, " + COL_AVERAGE_BAHT + " TEXT);");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        if (newVersion > oldVersion) {
//            db.execSQL("ALTER TABLE " + TABLE_NAME + " ADD COLUMN " + COL_TYPE_CAR + " TEXT DEFAULT 0");
//        }
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public long insertData(String name, String type_car, String origin, String destination, String distance, String duration, String type_oil, String spend_oil, String money, String date, String Average_baht) {
        try {

            sqLiteDatabase = this.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(COL_NAME, name);
            values.put(COL_TYPE_CAR, type_car);
            values.put(COL_ORIGIN, origin);
            values.put(COL_DESTINATION, destination);
            values.put(COL_DISTANCE, distance);
            values.put(COL_DURATION, duration);
            values.put(COL_TYPE_OIL, type_oil);
            values.put(COL_SPEND_OIL, spend_oil);
            values.put(COL_MONEY, money);
            values.put(COL_DATE, date);
            values.put(COL_AVERAGE_BAHT, Average_baht);

            long rows = sqLiteDatabase.insert(TABLE_NAME, null, values);
            sqLiteDatabase.close();
            return rows;
        } catch (Exception e) {
            return -1;
        }
    }

    public Cursor getAllDetailRecyclerView() {
        SQLiteDatabase sq;
        sq = getWritableDatabase();
        String[] column = {COL_NAME, COL_TYPE_CAR, COL_ORIGIN, COL_DESTINATION, COL_DISTANCE, COL_DURATION, COL_TYPE_OIL, COL_SPEND_OIL, COL_MONEY, COL_DATE, COL_AVERAGE_BAHT};
        return sq.query(TABLE_NAME, column, null, null, null, null, null);
    }
}
