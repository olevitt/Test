package com.ensai.TP2.dao;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import com.ensai.TP2.core.Element;

public class ElementDAO {

	public static final String FILENAME = "elements.txt";

	public static void sauvegarderElements(Context context,List<Element> elements) {
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

	public static List<Element> chargerElements(Context context) {
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
	
	public static void ajouterElement(Context context, Element element) {
		List<Element> elements = chargerElements(context);
		elements.add(element);
		sauvegarderElements(context, elements);
	}

}
