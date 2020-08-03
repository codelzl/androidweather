package com.example.tdemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDbHelper extends SQLiteOpenHelper {

	private static int DB_VERSION=1;
	private static String DB_NAME="MyDb。db";
	public static String DB_TABLE="Chat_List";
	public MyDbHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase sqlliteDatabase) {
		// TODO Auto-generated method stubs
		String sql="create table if not exists"+DB_TABLE+"(Id integer primarykey,Img text,Name text,Mes text, Chatdate text)";
		sqlliteDatabase.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase sqlliteDatabase, int oldversion, int newversion) {
		// TODO Auto-generated method stub
		String sql="DROP TABLE IF EXISTS"+DB_TABLE;
		sqlliteDatabase.execSQL(sql);
		onCreate(sqlliteDatabase);

	}

}
