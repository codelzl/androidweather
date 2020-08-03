package com.example.pfweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MyDBHelper extends SQLiteOpenHelper {
	private final static String DATABASE_NAME = "pfweather.db";
	private final static int DATABASE_VERSION = 1;
	private final static String TABLE_NAME1 = "city_table";
	public final static String CITY_ID = "city_id";
	public final static String CITY_NAME = "city_name";
	private final static String TABLE_NAME2 = "memory_table";
	public final static String MEMORY_ID = "memeory_id";
	public final static String MEMORY_CONTENT = "memeory_content";
	public final static String MEMORY_DATE = "memeory_date";

	public MyDBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String sql = "CREATE TABLE " + TABLE_NAME1 + " (" + CITY_ID
				+ " INTEGER primary key autoincrement, " + CITY_NAME + " text"
				+ " );";
		Log.e("mylog", sql);
		db.execSQL(sql);

		String sql2 = "CREATE TABLE " + TABLE_NAME2 + " (" + MEMORY_ID
				+ " INTEGER primary key autoincrement, " + MEMORY_CONTENT
				+ " text , " + MEMORY_DATE + " text );";
		Log.e("mylog", sql2);
		db.execSQL(sql2);

	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub

	}

	public Cursor select(String mytbname) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(mytbname, null, null, null, null, null, null);
		return cursor;
	}

	public void city_insert(String cityname) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(CITY_NAME, cityname);
		db.insert(TABLE_NAME1, null, cv);
	}

	public void mem_insert(String memory_content, String memory_date) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(MEMORY_CONTENT, memory_content);
		cv.put(MEMORY_DATE, memory_date);
		db.insert(TABLE_NAME2, null, cv);
	}

	

}
