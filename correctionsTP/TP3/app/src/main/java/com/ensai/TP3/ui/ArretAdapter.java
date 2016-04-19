package com.ensai.TP3.ui;

import java.util.List;

import android.content.Context;
import android.location.LocationManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ensai.TP3.R;
import com.ensai.TP3.core.Arret;

public class ArretAdapter extends BaseAdapter {

	private Context context;
	private List<Arret> arrets;

	public ArretAdapter(Context context,List<Arret> arrets) {
		this.arrets = arrets;
		this.context = context;
	}

	@Override
	public int getCount() {
		return arrets.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arrets.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;
		if (convertView == null) {
			if ( convertView == null ) {
				view = ( ( LayoutInflater )
						context . getSystemService ( Context . LAYOUT_INFLATER_SERVICE ) ) . inflate ( R.layout.layoutarret, parent , false ) ;
			}
		}
		else {
			view = convertView;
		}

		Arret arret = (Arret) getItem(position);
		TextView nom = (TextView) view.findViewById(R.id.nom);
		TextView passage = (TextView) view.findViewById(R.id.passage);
		nom.setText(arret.getStop());
		passage.setText((arret.getDepartures().size() == 0 ? "Pas de passage" : arret.getDepartures().get(0).getContent().toString()));
		return view;
	}





}
