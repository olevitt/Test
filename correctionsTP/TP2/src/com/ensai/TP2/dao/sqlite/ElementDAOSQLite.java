package com.ensai.TP2.dao.sqlite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ensai.TP2.core.Element;
import com.ensai.TP2.dao.IElementDAO;

public class ElementDAOSQLite extends SQLiteOpenHelper implements IElementDAO {

	public static final String DBNAME = "elements";
	public static final int DBVERSION = 1;

	public ElementDAOSQLite(Context context) {
		super(context,DBNAME,null,DBVERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE elements(identifiant INTEGER PRIMARY KEY, nom TEXT, description TEXT)");
		db.execSQL("INSERT INTO elements VALUES(1,'Exemple','Ceci est un exemple')");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		//Pas de mises à jour de prévu
	}

	@Override
	public void sauvegarderElements(List<Element> elements) {
		getWritableDatabase().delete("elements", null, null);
		for (Element element : elements) {
			ajouterElement(element);
		}
	}

	@Override
	public List<Element> chargerElements() {
		List<Element> elements = new ArrayList<Element>();
		Cursor cursor = getReadableDatabase().rawQuery("SELECT identifiant,nom,description FROM elements", null);
		while (cursor.moveToNext()) {
			Element element = new Element();
			element.setNom(cursor.getString(1));
			element.setDescription(cursor.getString(2));
			elements.add(element);
		}
		cursor.close();
		return elements;
	}

	@Override
	public void ajouterElement(Element element) {
		ContentValues values = new ContentValues();
		values.put("nom", element.getNom());
		values.put("description", element.getDescription());
		getWritableDatabase().insert("elements", null, values);
	}

}
