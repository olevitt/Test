package com.ensai.TP2.dao.fichiers;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.ensai.TP2.core.Element;
import com.ensai.TP2.dao.IElementDAO;

public class ElementDAOFichiers implements IElementDAO {

	public static final String FILENAME = "elements.txt";
	Context context;
	
	public ElementDAOFichiers(Context context) {
		this.context = context;
	}

	public void sauvegarderElements(List<Element> elements) {
		try {
			FileOutputStream stream = context.openFileOutput(FILENAME, Context.MODE_PRIVATE);
			PrintWriter writer = new PrintWriter(stream);
			for (Element element : elements) {
				writer.println(element.getNom()+"#"+element.getDescription().replaceAll(System.getProperty("line.separator"), "[retourALaLigne]"));
			}
			writer.close();
		}
		catch (Exception e) {
			Log.e("TP2", "",e);
		}
	}

	public List<Element> chargerElements() {
		List<Element> liste = new ArrayList<Element>();
		try {
			FileInputStream stream = context.openFileInput(FILENAME);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			String ligne = null;
			while ((ligne = reader.readLine()) != null) {
				String[] data = ligne.split("#");
				Element element = new Element(data[0],"");
				if (data.length >= 2) {
					element.setDescription(data[1].replaceAll("\\[retourALaLigne\\]",System.getProperty("line.separator")));
				}
				liste.add(element);
			}
			reader.close();
		}
		catch (Exception e) {
			Log.e("TP2", "",e);
		}
		return liste;
	}
	
	public void ajouterElement(Element element) {
		List<Element> elements = chargerElements();
		elements.add(element);
		sauvegarderElements(elements);
	}

}
