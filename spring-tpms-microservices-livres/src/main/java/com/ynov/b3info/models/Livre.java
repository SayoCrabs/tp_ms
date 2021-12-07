package com.ynov.b3info.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Livre {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Integer id;

	private String titre;
	
	private String auteur;
	
	@Temporal(TemporalType.DATE) // jour mois année (sans heure)
	private Date dateParution;
	
	
	
	public Livre() {
		super();
	}
	
	

	public Livre(String titre, String auteur, Date dateParution) {
		super();
		this.titre = titre;
		this.auteur = auteur;
		this.dateParution = dateParution;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getAuteur() {
		return auteur;
	}

	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	public Date getDateParution() {
		return dateParution;
	}

	public void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}
	
	
	
	
}
