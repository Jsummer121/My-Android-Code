package com.example.middletest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQL_DB extends SQLiteOpenHelper {
	public SQL_DB(Context context){
		super(context,"data.db",null,2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE note_data(name VARCHAR(30) PRIMARY KEY,cont_data text)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}
}
