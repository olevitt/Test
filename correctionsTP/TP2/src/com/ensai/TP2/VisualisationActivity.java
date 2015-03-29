package com.ensai.TP2;

import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ensai.TP2.core.Element;
import com.ensai.TP2.dao.ElementDAO;

public class VisualisationActivity extends Activity {

	TextView nom, description;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_visualisation);
		nom = (TextView) findViewById(R.id.nom);
		description = (TextView) findViewById(R.id.description);
		
		String nomItem = getIntent().getExtras().getString("nom");
		setTitle(nomItem);
		nom.setText(nomItem);
		List<Element> elements = ElementDAO.chargerElements(this);
		for (Element element : elements) {
			if (element.getNom().equals(nomItem)) {
				description.setText(element.getDescription());
			}
		}
	}
	
	

	
}
