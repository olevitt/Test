package com.ensai.TP3;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ensai.TP3.core.Route;
import com.ensai.TP3.dao.fichiers.RouteDAO;

public class MainActivity extends Activity {

	ListView liste;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		liste = (ListView) findViewById(R.id.liste);
		ArrayAdapter<Route> routes = new ArrayAdapter<Route>(this, android.R.layout.simple_list_item_1);
		routes.addAll(RouteDAO.getRoutes(this));
		liste.setAdapter(routes);
		Toast.makeText(this, ""+routes.getCount(), Toast.LENGTH_LONG).show();
		liste.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(MainActivity.this,HoraireActivity.class);
				intent.putExtra("idLigne", ((Route) parent.getItemAtPosition(position)).getRouteId());
				startActivity(intent);
			}
			
		});
	}
	
	
}

