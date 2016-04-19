package com.ensai.TP2;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.ensai.TP2.core.Element;
import com.ensai.TP2.dao.ElementDAOFactory;
import com.ensai.TP2.dao.fichiers.ElementDAOFichiers;
import com.ensai.TP2.dao.sqlite.ElementDAOSQLite;

public class MainActivity extends Activity implements OnClickListener, OnItemClickListener {

	private ArrayAdapter<Element> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		adapter = new ArrayAdapter<Element>(this, android.R.layout.simple_list_item_1);
		ListView liste = (ListView) findViewById(R.id.listview);
		liste.setAdapter(adapter);
		chargerDonnees();
		Button bouton = (Button) findViewById(R.id.bouton);
		bouton.setOnClickListener(this);
		liste.setOnItemClickListener(this);
	}

	private void chargerDonnees() {
		adapter.clear();
		//Liste générée à la volée
		List<Element> liste = new ArrayList<Element>();
		liste.add(new Element("Commencer le TP2",""));
		liste.add(new Element("Continuer le TP2",""));
		liste.add(new Element("Continuer le TP2",""));
		liste.add(new Element("Continuer le TP2",""));
		liste.add(new Element("Continuer le TP2",""));
		liste.add(new Element("Continuer le TP2",""));
		liste.add(new Element("Continuer le TP2",""));
		liste.add(new Element("Finir le TP2",""));

		//Liste chargée à partir du fichier
		liste = ElementDAOFactory.getElementDAO(this).chargerElements();
		for (Element element : liste) {
			adapter.add(element);
		}
		adapter.notifyDataSetChanged();
	}
	
	

	@Override
	protected void onResume() {
		super.onResume();
		chargerDonnees();
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(this,AjoutActivity.class);
		startActivity(intent);
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		List<Element> elements = ElementDAOFactory.getElementDAO(this).chargerElements();
		Intent intent = new Intent(this,VisualisationActivity.class);
		intent.putExtra("nom", elements.get(position).getNom());
		startActivity(intent);
	}
	
}
