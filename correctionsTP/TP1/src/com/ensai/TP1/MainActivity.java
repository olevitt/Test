package com.ensai.TP1;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.ensai.TP1.R;

public class MainActivity extends Activity implements OnClickListener {

	public static String TAG = "TechnoMobile";
	private Button boutonConvertir;
	private EditText champMontant;
	private TextView champResultat;
	private Spinner spinner1;
	private Spinner spinner2;
	private static final double[] TAUX = new double[] {1,1.1,4.15};
	private static final String[] SYMBOLES = new String[] {"€","$","zl"};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(TAG, "Appel à onCreate");
		boutonConvertir = (Button) findViewById(R.id.boutonConvertir);
		boutonConvertir.setOnClickListener(this);
		champMontant = (EditText) findViewById(R.id.champMontant);
		champResultat = (TextView) findViewById(R.id.champResultat);
		champResultat.setVisibility(View.INVISIBLE);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
	}

	@Override
	public void onClick(View v) {
		try {
			double tauxDeChange = getTauxDeChange();
			double montant = Double.parseDouble(champMontant.getText().toString());
			Toast.makeText(this, montant+SYMBOLES[spinner1.getSelectedItemPosition()]+" = "+(montant * tauxDeChange)+SYMBOLES[spinner2.getSelectedItemPosition()], Toast.LENGTH_SHORT).show();
			champResultat.setVisibility(View.VISIBLE);
			champResultat.setText(montant+SYMBOLES[spinner1.getSelectedItemPosition()]+" = "+(montant * tauxDeChange)+SYMBOLES[spinner2.getSelectedItemPosition()]);
		}
		catch (NumberFormatException e) {
			Toast.makeText(this, R.string.montantinvalide, Toast.LENGTH_SHORT).show();
		}	
	}
	
	private double getTauxDeChange() {
		int devise1 = spinner1.getSelectedItemPosition();
		int devise2 = spinner2.getSelectedItemPosition();
		return TAUX[devise2] / TAUX[devise1];
	}
}
