package com.fx.buddy.model;


public enum Sex {
	MASCULIN("Masculin"),
	FEMININ("F�minin"),
	INCONNU("Inconnu");
	
	private String name = "";
	
	Sex(String n){name = n;}
	public String toString() {return name;}
}