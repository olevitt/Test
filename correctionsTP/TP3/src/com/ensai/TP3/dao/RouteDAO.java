package com.ensai.TP3.dao;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import android.content.Context;

import com.ensai.TP3.R;
import com.ensai.TP3.core.Route;

public class RouteDAO {

	public static List<Route> getRoutes(Context context) {
		List<Route> routes = new ArrayList<Route>();
		try {
			InputStream stream = context.getResources().openRawResource(R.raw.routes);
			Iterator<CSVRecord> iterateur = new CSVParser(new InputStreamReader(stream), CSVFormat.DEFAULT).iterator();
			CSVRecord enregistrementCourant = null;
			while (iterateur.hasNext()) {
				enregistrementCourant = iterateur.next();
			}
			stream.close();
		}
		catch (Exception e) {
			
		}
		return routes;
 	}
}
