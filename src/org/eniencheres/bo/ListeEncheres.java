package org.eniencheres.bo;

import java.io.Serializable;
import java.sql.Date;


public class ListeEncheres implements Serializable {
	
	private String article; 
	private String vendeur; 
	private int montant; 
	private Date dateFin; 
	
	public ListeEncheres() {

	}
	
	public ListeEncheres(String pArticle, String  pVendeur, int pMontant, Date pDateFin) {
		setArticle(pArticle); 
		setVendeur(pVendeur);
		setMontant(pMontant); 
		setDateFin(pDateFin); 

	}

	public String getArticle() {
		return article;
	}

	public void setArticle(String article) {
		this.article = article;
	}

	public String getVendeur() {
		return vendeur;
	}

	public void setVendeur(String vendeur) {
		this.vendeur = vendeur;
	}

	public int getMontant() {
		return montant;
	}

	public void setMontant(int montant) {
		this.montant = montant;
	}

	public Date getDateFin() {
		return dateFin;
	}

	public void setDateFin(Date dateFin) {
		this.dateFin = dateFin;
	}

	@Override
	public String toString() {
		return "ListeEncheres [article=" + article + ", vendeur=" + vendeur + ", montant=" + montant + ", dateFin="
				+ dateFin + "]";
	}
	
	
	
	
}
