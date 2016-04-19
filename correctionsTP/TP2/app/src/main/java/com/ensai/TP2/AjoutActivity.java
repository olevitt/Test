package com.ensai.TP2;

import java.io.BufferedWriter;
import java.io.PrintWriter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import com.ensai.TP2.core.Element;
import com.ensai.TP2.dao.ElementDAOFactory;

public class AjoutActivity extends Activity implements OnClickListener {

	Button boutonAjouter = null;
	EditText nom, description;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ajout);
		boutonAjouter = (Button) findViewById(R.id.boutonajouter);
		boutonAjouter.setOnClickListener(this);
		nom = (EditText) findViewById(R.id.nom);
		description = (EditText) findViewById(R.id.description);
	}

	@Override
	public void onClick(View v) {
		Element element = new Element(nom.getText().toString(),description.getText().toString());
		ElementDAOFactory.getElementDAO(this).ajouterElement(element);
		finish();
	}
	
	

	
}
