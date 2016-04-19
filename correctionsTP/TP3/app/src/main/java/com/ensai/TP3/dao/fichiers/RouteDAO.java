package com.ensai.TP3.dao.fichiers;

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
			iterateur.next();
			while (iterateur.hasNext()) {
				Route route = new Route();
				enregistrementCourant = iterateur.next();
				route.setRouteId(enregistrementCourant.get(0));
				route.setAgencyId(Integer.parseInt(enregistrementCourant.get(1)));
				route.setRouteShortName(enregistrementCourant.get(2));
				route.setRouteLongName(enregistrementCourant.get(3));
				route.setRouteDesc(enregistrementCourant.get(4));
				route.setRouteType(enregistrementCourant.get(5));
				route.setRouteUrl(enregistrementCourant.get(6));
				route.setRouteColor(enregistrementCourant.get(7));
				route.setRouteTextColor(enregistrementCourant.get(8));
				routes.add(route);
			}
			stream.close();
		}
		catch (Exception e) {
			
		}
		return routes;
 	}
}
