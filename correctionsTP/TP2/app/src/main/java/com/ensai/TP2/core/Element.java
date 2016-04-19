package com.ensai.TP2.core;

public class Element {

	private String nom, description;

	
	public Element() {
		
	}
	
	public Element(String nom, String description) {
		super();
		this.nom = nom;
		this.description = description;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return nom;
	}
	
	
	
}
