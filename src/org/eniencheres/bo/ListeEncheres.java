package org.eniencheres.bo;

import java.io.Serializable;
import java.sql.Date;


public class ListeEncheres implements Serializable {
	
	//attribut d'instance 
	
	private String article; 
	private String vendeur; 
	private int montant; 
	private Date dateFin; 
	private String categorie; 
	
	
/**
 * Constructeurs sans et avec paramètres	
 */
	public ListeEncheres() {

	}
	
	public ListeEncheres(String pArticle, String  pVendeur, int pMontant, Date pDateFin, String pCategorie) {
		setArticle(pArticle); 
		setVendeur(pVendeur);
		setMontant(pMontant); 
		setDateFin(pDateFin); 
		setCategorie(pCategorie); 
	

	}

	//*************getter
	
	public String getArticle() {
		return article;
	}

	public String getVendeur() {
		return vendeur;
	}

	public int getMontant() {
		return montant;
	}

	public Date getDateFin() {
		return dateFin;
	}
	
	public String getCategorie() {
		return categorie;
	}

	//****************setter
	
	public void setArticle(String article) {
		this.article = article;
	}
	public void setVendeur(String vendeur) {
		this.vendeur = vendeur;
	}
	public void setMontant(int montant) {
		this.montant = montant;
	}
	
	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

//*******Méthodes 
	
	@Override
	public String toString() {
		return "ListeEncheres [article=" + article + ", vendeur=" + vendeur + ", montant=" + montant + ", dateFin="
				+ dateFin + ", categorie=" + categorie + "]";
	}

	
	
	
	
	
}
