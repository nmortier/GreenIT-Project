package org.GreenIT.pubText.impl;

public class TextBean {

	private long id;
	private String contenu;
	private String libelle;
	
	public TextBean(long id, String libelle, String contenu) {
		this.id = id;
		this.contenu = contenu;
		this.libelle = libelle;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getContenu() {
		return contenu;
	}

	public void setContenu(String contenu) {
		this.contenu = contenu;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}