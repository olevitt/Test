package com.ensai.TP3;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.os.StrictMode.ThreadPolicy;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.setThreadPolicy ( new ThreadPolicy . Builder ( ) . permitAll ( ) . build ( ) ) ;
		try {
			HttpURLConnection urlConnection = (HttpURLConnection) new URL("http://data.keolis-rennes.com/json/?cmd=getmetrostatus&version=2.2&key=1RJLZ38TUFZSWTW").openConnection();
			InputStream in = new BufferedInputStream(urlConnection.getInputStream());
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			String reponse = "";
			String ligne = null;
			while ((ligne = reader.readLine()) != null) {
				reponse += ligne;
			}
			Toast.makeText(this, reponse, Toast.LENGTH_LONG).show();
			//readStream(in);
			urlConnection.disconnect();
		}
		catch (Exception e) {
			Log.e("TP3", "Erreur",e);
		}
	}
}

