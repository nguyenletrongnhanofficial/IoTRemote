package com.example.iotremote.assetdetail_database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.iotremote.assetclass.AssetCs;

import java.util.Calendar;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "assetManager.db";
    private static final String TABLE_CONTACTS = "assets";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_DATA_1 = "id_db";
    private static final String KEY_DATA_2 = "humidity";
    private static final String KEY_DATA_3 = "rainfall";
    private static final String KEY_DATA_4 = "sun_altitude";
    private static final String KEY_DATA_5 = "sun_azimuth";
    private static final String KEY_DATA_6 = "sun_irradiance";
    private static final String KEY_DATA_7 = "sun_zenith";
    private static final String KEY_DATA_8 = "temperature";
    private static final String KEY_DATA_9 = "uv_index";
    private static final String KEY_DATA_10 = "wind_direction";
    private static final String KEY_DATA_11 = "wind_speed";
    private static final String KEY_DATA_12 = "date";

    public DatabaseHandler(Context context) {
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
                + KEY_DATA_7 + " TEXT," + KEY_DATA_8 + " TEXT,"
                + KEY_DATA_9 + " TEXT," + KEY_DATA_10 + " TEXT,"
                + KEY_DATA_11 + " TEXT," + KEY_DATA_12 + " TEXT" + ")";
        db.execSQL(createTable);
        Log.d("Data", createTable);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        // Create tables again
        onCreate(db);

    }

    // code to add the new contact
    public void addAsset(AssetCs asset) {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        String date = day + "/" + (month+1) + "/" + year;
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, asset.getName());
        values.put(KEY_DATA_1, asset.getId());
        values.put(KEY_DATA_2, ""+check_null_data(asset.getAttributes().getHumidity().getValue().doubleValue()));
        values.put(KEY_DATA_3, ""+check_null_data(asset.getAttributes().getRainfall().getValue().doubleValue()));
        values.put(KEY_DATA_4, ""+check_null_data(asset.getAttributes().getSunAltitude().getValue().doubleValue()));
        values.put(KEY_DATA_5, ""+check_null_data(asset.getAttributes().getSunAzimuth().getValue().doubleValue()));
        values.put(KEY_DATA_6, ""+check_null_data(asset.getAttributes().getSunIrradiance().getValue().doubleValue()));
        values.put(KEY_DATA_7, ""+check_null_data(asset.getAttributes().getSunZenith().getValue().doubleValue()));
        values.put(KEY_DATA_8, ""+check_null_data(asset.getAttributes().getTemperature().getValue().doubleValue()));
        values.put(KEY_DATA_9, ""+check_null_data(asset.getAttributes().getUVIndex().getValue().doubleValue()));
        values.put(KEY_DATA_10, ""+check_null_data(asset.getAttributes().getWindDirection().getValue().doubleValue()));
        values.put(KEY_DATA_11, ""+check_null_data(asset.getAttributes().getWindSpeed().getValue().doubleValue()));
        values.put(KEY_DATA_12, date);
        db.insert(TABLE_CONTACTS, null, values);
    }
    private String check_null_data(double a){
        String f = ""+a;
        if (a != -0.1)
            return f;
        else
            return "No data";
    }
    @SuppressLint("Range")
    public AssetDetail getAsset(int id) {
        AssetDetail asd = new AssetDetail(""+1,""+1,""+1,""+1,""+1,""+1,""+1,""+1,""+1,""+1,""+1,""+1,""+1);
        int idRoot;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);
        while(cursor.moveToNext())
        {
            idRoot = cursor.getInt(cursor.getColumnIndex(KEY_ID));
            if(id == idRoot) {
                String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
                String id_ =cursor.getString(cursor.getColumnIndex(KEY_DATA_1));
                String Humidity = cursor.getString(cursor.getColumnIndex(KEY_DATA_2));
                String Rainfall = cursor.getString(cursor.getColumnIndex(KEY_DATA_3));
                String Sun_altitude = cursor.getString(cursor.getColumnIndex(KEY_DATA_4));
                String Sun_azimuth = cursor.getString(cursor.getColumnIndex(KEY_DATA_5));
                String Sun_irradiance = cursor.getString(cursor.getColumnIndex(KEY_DATA_6));
                String Sun_zenith = cursor.getString(cursor.getColumnIndex(KEY_DATA_7));
                String Temperature = cursor.getString(cursor.getColumnIndex(KEY_DATA_8));
                String UV_index = cursor.getString(cursor.getColumnIndex(KEY_DATA_9));
                String Wind_direction = cursor.getString(cursor.getColumnIndex(KEY_DATA_10));
                String Wind_speed = cursor.getString(cursor.getColumnIndex(KEY_DATA_11));
                String date = cursor.getString(cursor.getColumnIndex(KEY_DATA_12));
                asd = new AssetDetail(name, id_,Humidity,Rainfall,Sun_altitude,Sun_azimuth,Sun_irradiance,Sun_zenith,Temperature,UV_index,Wind_direction,Wind_speed,date);
                return asd;
            }
        }
        return asd;
    }
    public void deleteTable()
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        onUpgrade(sqLiteDatabase, 1, 1);
    }
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        return cursor.getCount();
    }
    public int getDBCount(){
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        int count = cursor.getCount();
        return count;
    }

}