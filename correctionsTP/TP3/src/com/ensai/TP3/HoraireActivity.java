package com.ensai.TP3;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import webservice.Requete;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.ensai.TP3.core.Arret;
import com.ensai.TP3.ui.ArretAdapter;

public class HoraireActivity extends Activity {

	private String idRoute = null;
	ListView liste;
	ArretAdapter adapter;
	private List<Arret> arrets = new ArrayList<Arret>();
	LocationListener locationListener;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_horaire);
		liste = (ListView) findViewById(R.id.liste);
		adapter = new ArretAdapter(this,arrets);
		liste.setAdapter(adapter);
		idRoute = getIntent().getStringExtra("idLigne");
		if (idRoute == null) {
			Toast.makeText(this, "Il faut l'idLigne pour cet écran", Toast.LENGTH_LONG).show();
			finish();
		}
		//StrictMode.setThreadPolicy ( new ThreadPolicy . Builder ( ) . permitAll ( ) . build ( ) ) ;
		StrictMode.setThreadPolicy(new ThreadPolicy.Builder ().detectNetwork().penaltyDeathOnNetwork().build()) ;
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				requeteHTTP();
			}
		}).start();
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationListener = new LocationListener() {
			
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				toastGPS();
			}
		};
		locationManager.requestLocationUpdates(
		LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
		toastGPS();
		
	}
	
	
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		locationManager.removeUpdates(locationListener);
	}
	
	private void toastGPS() {
		LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		if (location != null) {
			Toast.makeText(this, "Position "+location.getLatitude()+" / "+location.getLongitude(), Toast.LENGTH_LONG).show();
		}
	}

	private void requeteHTTP() {
		try {
			Requete requete = new Requete("getbusnextdepartures", "2.2");
			requete.addParam("mode", "line");
			requete.addParam("route", idRoute);
			requete.addParam("direction","1");
			HttpURLConnection urlConnection = (HttpURLConnection) new URL(requete.getURL()).openConnection();
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			String reponse = readStream(in);
			urlConnection.disconnect();
			JSONObject object = new JSONObject(reponse);
			final JSONArray array = object.getJSONObject("opendata").getJSONObject("answer").getJSONObject("data").getJSONArray("stopline");
			final List<Arret> arrets = new ArrayList<Arret>();
			for (int i = 0; i < array.length(); i++) {
				try {
					JSONObject arretJSON = array.getJSONObject(i);
					Arret arret = new Arret(arretJSON);
					arrets.add(arret);
				}
				catch (JSONException e) {
					Log.e("TP3", "Erreur",e);
				}
			}
			runOnUiThread(new Runnable() {
				
				@Override
				public void run() {
					Toast.makeText(HoraireActivity.this, "Nb arrets : "+arrets.size(), Toast.LENGTH_LONG).show();
					chargerDonnees(arrets);
				}
			});
		}
		catch (Exception e) {
			Log.e("TP3", "Erreur",e);
		}
	}
	
	private void chargerDonnees(List<Arret> arrets) {
		this.arrets.clear();
		this.arrets.addAll(arrets);
		this.adapter.notifyDataSetChanged();
	}
	
	private String readStream(InputStream in) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		String reponse = "";
		String ligne = null;
		while ((ligne = reader.readLine()) != null) {
			reponse += ligne;
		}
		return reponse;
	}
	
}
