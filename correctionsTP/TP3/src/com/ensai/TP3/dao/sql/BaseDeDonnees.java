package com.ensai.TP3.dao.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BaseDeDonnees extends SQLiteOpenHelper {

	private static int VERSION = 1;
	private static String NOM = "BDD";
	
	public BaseDeDonnees(Context context) {
		super(context,NOM,null,VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE route(TEXT routeId PRIMARY KEY, INTEGER agencyId, TEXT routeShortName, TEXT routeLongName, TEXT routeDesc, TEXT routeType, TEXT routeUrl, TEXT routeColor, TEXT routeTextColor)");
		db.execSQL("CREATE TABLE stop(TEXT stopId PRIMARY KEY, TEXT stopCode, TEXT stopName, TEXT stopDesc, TEXT stopLat, TEXT stopLon)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
