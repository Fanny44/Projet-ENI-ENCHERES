package org.eniencheres.bo;

import java.io.Serializable;
import java.sql.Date;

/**
 * Classe ArticleVendu
 * @author Fanny
 * crée le 22/07/2019
 *
 */

public class ListeEncheres implements Serializable {
	
	/**
	 * Pour la sérialisation 
	 */
	
	private static final long serialVersionUID = 1L;
	
	//attribut d'instance 
	private int noArticle;
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
	
	public ListeEncheres(int pNoArticle, String pArticle, String  pVendeur, int pMontant, Date pDateFin, String pCategorie) {
		setNoArticle(pNoArticle);
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

	public int getNoArticle() {
		return noArticle;
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

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}
	
	
//*******Méthodes 
	


	@Override
	public String toString() {
		return "ListeEncheres [article=" + article + ", vendeur=" + vendeur + ", montant=" + montant + ", dateFin="
				+ dateFin + ", categorie=" + categorie + "]";
	}

	
	
	
	
	
}
