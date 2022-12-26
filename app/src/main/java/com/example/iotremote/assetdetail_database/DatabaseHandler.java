package com.example.iotremote.assetdetail_database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.iotremote.assetclass.AssetCs;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "assetManager";
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
                + KEY_DATA_11 + " TEXT" + ")";
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
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        //values.put(KEY_ID, asset.getdb_id());
        values.put(KEY_NAME, asset.getName()); // Contact Name
        values.put(KEY_DATA_1, asset.getId());
        values.put(KEY_DATA_2, ""+asset.getAttributes().getHumidity().getValue().doubleValue());
        values.put(KEY_DATA_3, ""+asset.getAttributes().getRainfall().getValue().doubleValue());
        values.put(KEY_DATA_4, ""+asset.getAttributes().getSunAltitude().getValue().doubleValue());
        values.put(KEY_DATA_5, ""+asset.getAttributes().getSunAzimuth().getValue().doubleValue());
        values.put(KEY_DATA_6, ""+asset.getAttributes().getSunIrradiance().getValue().doubleValue());
        values.put(KEY_DATA_7, ""+asset.getAttributes().getSunZenith().getValue().doubleValue());
        values.put(KEY_DATA_8, ""+asset.getAttributes().getTemperature().getValue().doubleValue());
        values.put(KEY_DATA_9, ""+asset.getAttributes().getUVIndex().getValue().doubleValue());
        values.put(KEY_DATA_10, ""+asset.getAttributes().getWindDirection().getValue().doubleValue());
        values.put(KEY_DATA_11, ""+asset.getAttributes().getWindSpeed().getValue().doubleValue());

        // Inserting Row
        db.insert(TABLE_CONTACTS, null, values);
        //2nd argument is String containing nullColumnHack
        //db.close(); // Closing database connection
    }

    // code to get the single contact
//    public AssetDetail getAsset(int id) {
//        SQLiteDatabase db = this.getReadableDatabase();
//
//        Cursor cursor = db.query(TABLE_CONTACTS, new String[] { KEY_ID,
//                        KEY_NAME, KEY_DATA_1, KEY_DATA_2, KEY_DATA_3, KEY_DATA_4, KEY_DATA_5, KEY_DATA_6,
//                        KEY_DATA_7, KEY_DATA_8, KEY_DATA_9, KEY_DATA_10, KEY_DATA_11}, KEY_ID + "=?",
//                new String[] { String.valueOf(id) }, null, null, null, null);
//        if (cursor != null)
//            cursor.moveToFirst();
//
//        AssetDetail assetDetail = new AssetDetail(Integer.parseInt(cursor.getString(0)),
//                cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),
//                cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getString(8),
//                cursor.getString(9), cursor.getString(10), cursor.getString(11), cursor.getString(12));
//        // return contact
//        return assetDetail;
//    }
    @SuppressLint("Range")
    public AssetDetail getAsset(int id) {
        AssetDetail asd = null;
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
                asd = new AssetDetail(name, id_,Humidity,Rainfall,Sun_altitude,Sun_azimuth,Sun_irradiance,Sun_zenith,Temperature,UV_index,Wind_direction,Wind_speed);
                return asd;
            }
        }
        return asd;
    }
//    @SuppressLint("Range")
//    public AssetDetail getAssetUName(String name) {
//        AssetDetail asd = null;
//        String nameRoot;
//        SQLiteDatabase db = getReadableDatabase();
//        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS, null);
//        while(cursor.moveToNext())
//        {
//            nameRoot = cursor.getString(cursor.getColumnIndex(KEY_NAME));
//            if(name == nameRoot) {
//                //String name = cursor.getString(cursor.getColumnIndex(KEY_NAME));
//                String id_ =cursor.getString(cursor.getColumnIndex(KEY_DATA_1));
//                String Humidity = cursor.getString(cursor.getColumnIndex(KEY_DATA_2));
//                String Rainfall = cursor.getString(cursor.getColumnIndex(KEY_DATA_3));
//                String Sun_altitude = cursor.getString(cursor.getColumnIndex(KEY_DATA_4));
//                String Sun_azimuth = cursor.getString(cursor.getColumnIndex(KEY_DATA_5));
//                String Sun_irradiance = cursor.getString(cursor.getColumnIndex(KEY_DATA_6));
//                String Sun_zenith = cursor.getString(cursor.getColumnIndex(KEY_DATA_7));
//                String Temperature = cursor.getString(cursor.getColumnIndex(KEY_DATA_8));
//                String UV_index = cursor.getString(cursor.getColumnIndex(KEY_DATA_9));
//                String Wind_direction = cursor.getString(cursor.getColumnIndex(KEY_DATA_10));
//                String Wind_speed = cursor.getString(cursor.getColumnIndex(KEY_DATA_11));
//                asd = new AssetDetail(name, id_,Humidity,Rainfall,Sun_altitude,Sun_azimuth,Sun_irradiance,Sun_zenith,Temperature,UV_index,Wind_direction,Wind_speed);
//                return asd;
//            }
//        }
//        return asd;
//    }
    public void deleteTable()
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        onUpgrade(sqLiteDatabase, 1, 1);
    }
//    // Deleting single contact
//    public void deleteContact(Contact contact) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        db.delete(TABLE_CONTACTS, KEY_ID + " = ?",
//                new String[] { String.valueOf(contact.getID()) });
//        db.close();
//    }

    // Getting contacts Count
    public int getContactsCount() {
        String countQuery = "SELECT  * FROM " + TABLE_CONTACTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

}