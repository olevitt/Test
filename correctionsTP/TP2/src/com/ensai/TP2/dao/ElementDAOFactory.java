package com.ensai.TP2.dao;

import com.ensai.TP2.dao.sqlite.ElementDAOSQLite;

import android.content.Context;

public abstract class ElementDAOFactory {

	public static IElementDAO getElementDAO(Context context) {
		return new ElementDAOSQLite(context);
	}
}
