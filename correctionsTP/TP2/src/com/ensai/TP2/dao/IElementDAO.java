package com.ensai.TP2.dao;

import java.util.List;

import com.ensai.TP2.core.Element;

public interface IElementDAO {

	public void sauvegarderElements(List<Element> elements);

	public List<Element> chargerElements();

	public void ajouterElement(Element element);
}
