package com.ensai.TP3.core;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Arret {

	String stop;
	String route;
	String direction;
	List<Departure> departures = new ArrayList<Departure>();


	public Arret(JSONObject arretJSON) throws JSONException {
		setStop(arretJSON.getString("stop"));
		setRoute(arretJSON.getString("route"));
		setDirection(arretJSON.getString("direction"));
		JSONArray departures = arretJSON.getJSONObject("departures").getJSONArray("departure");
		for (int j = 0; j < departures.length(); j++) {
			Departure departure = new Departure();
			departure.setContent(departures.getJSONObject(j).getString("content"));
			getDepartures().add(departure);
		}
	}

	public List<Departure> getDepartures() {
		return departures;
	}
	public void setDepartures(List<Departure> departures) {
		this.departures = departures;
	}
	public String getStop() {
		return stop;
	}
	public void setStop(String stop) {
		this.stop = stop;
	}
	public String getRoute() {
		return route;
	}
	public void setRoute(String route) {
		this.route = route;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}


}
