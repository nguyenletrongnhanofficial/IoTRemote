package com.example.iotremote.chart_database;


import static android.icu.text.MessagePattern.ArgType.SELECT;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.iotremote.assetclass.AssetCs;
import com.example.iotremote.assetdetail_database.AssetDetail;
import com.example.iotremote.chart_database.chart.AssetDailyData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class ChartDBHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "chartdbManager.db";
    private static final String TABLE_CONTACTS = "chartdb";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATA_1 = "id_asset";
    private static final String KEY_DATA_2 = "humidity";
    private static final String KEY_DATA_3 = "temperature";
    private static final String KEY_DATA_4 = "wind_direction";
    private static final String KEY_DATA_5 = "wind_speed";
    private static final String KEY_DATA_6 = "day";
    private static final String KEY_DATA_7 = "month";
    private static final String KEY_DATA_8 = "year";
    public ChartDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_CONTACTS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_DATA_1 + " TEXT," + KEY_DATA_2 + " TEXT,"
                + KEY_DATA_3 + " TEXT," + KEY_DATA_4 + " TEXT,"
                + KEY_DATA_5 + " TEXT," + KEY_DATA_6 + " TEXT,"
                +KEY_DATA_7 + " TEXT," +KEY_DATA_8 + " TEXT" + ")";
        db.execSQL(createTable);
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, "Not null name");
        values.put(KEY_DATA_1, "Not null id");
        values.put(KEY_DATA_2, "Not null humidity");
        values.put(KEY_DATA_3, "Not null temperature");
        values.put(KEY_DATA_4, "Not null wind_direction");
        values.put(KEY_DATA_5, "Not null wind_speed");
        values.put(KEY_DATA_6, "31");
        values.put(KEY_DATA_7, "12");
        values.put(KEY_DATA_8, "2001");
        db.insert(TABLE_CONTACTS, null, values);
        Log.d("Data", createTable);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
//        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
//        onCreate(db);
    }

    // code to add the new contact
    public void addAssetDailyData(AssetCs asset) {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH)+1;
        int year = c.get(Calendar.YEAR);
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, asset.getName());
        values.put(KEY_DATA_1, asset.getId());
        values.put(KEY_DATA_2, ""+(asset.getAttributes().getHumidity().getValue().doubleValue()));
        values.put(KEY_DATA_3, ""+(asset.getAttributes().getTemperature().getValue().doubleValue()));
        values.put(KEY_DATA_4, ""+(asset.getAttributes().getWindDirection().getValue().doubleValue()));
        values.put(KEY_DATA_5, ""+(asset.getAttributes().getWindSpeed().getValue().doubleValue()));
        values.put(KEY_DATA_6, ""+ day);
        values.put(KEY_DATA_7, ""+ month);
        values.put(KEY_DATA_8, ""+ year);
        db.insert(TABLE_CONTACTS, null, values);
    }
//    private String check_null_data(double a){
//        String f = ""+a;
//        if (a != -0.1)
//            return f;
//        else
//            return "No data";
//    }
    @SuppressLint("Range")
    public List<AssetDailyData> getAllDailyData()
    {
        List<AssetDailyData> AssetDDList = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);
        cursor.moveToNext();
        while(cursor.moveToNext())
        {
            int id = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
            String id_asset = cursor.getString(cursor.getColumnIndex(KEY_DATA_1));
            String humi = cursor.getString(cursor.getColumnIndex(KEY_DATA_2));
            String temp = cursor.getString(cursor.getColumnIndex(KEY_DATA_3));
            String winddir = cursor.getString(cursor.getColumnIndex(KEY_DATA_4));
            String windsp = cursor.getString(cursor.getColumnIndex(KEY_DATA_5));
            String day = cursor.getString(cursor.getColumnIndex(KEY_DATA_6));
            String month = cursor.getString(cursor.getColumnIndex(KEY_DATA_7));
            String year = cursor.getString(cursor.getColumnIndex(KEY_DATA_8));
            AssetDailyData AssetDD = new AssetDailyData(name, id_asset, humi, temp, winddir, windsp, day, month, year);
            AssetDD.setId_(id);
            AssetDDList.add(AssetDD);
        }
        return AssetDDList;
    }
    public void deleteTable()
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        onUpgrade(sqLiteDatabase, 1, 1);
    }
    public int checkDate (int day, int month, int year){
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);
        cursor.moveToPosition(cursor.getCount() - 1);
        @SuppressLint("Range") String DB_day = cursor.getString(cursor.getColumnIndex(KEY_DATA_6));
        @SuppressLint("Range") String DB_month = cursor.getString(cursor.getColumnIndex(KEY_DATA_7));
        @SuppressLint("Range") String DB_year = cursor.getString(cursor.getColumnIndex(KEY_DATA_8));
        if (day == Integer.parseInt(DB_day) && month == Integer.parseInt(DB_month)&& year == Integer.parseInt(DB_year)) {
            return 1;
        }
        return 0;
    }
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        return cursor.getCount();
    }

}