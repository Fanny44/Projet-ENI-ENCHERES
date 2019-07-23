package org.eniencheres.bo;

import java.io.Serializable;
import java.util.Date;

/**
 * Classe ArticleVendu
 * @author Fanny
 * cr�e le 22/07/2019
 *
 */
public class ArticleVendu implements Serializable{
		
	/**
	 * Pour la s�rialisation
	 */
	private static final long serialVersionUID = 6309215381242291092L;
	
	//attribut de d'instance
	
	private int noArticle; 
	private String nomArticle; 
	private String description; 
	private Date dateDebutEncheres; 
	private Date dateFinEncheres; 
	private int miseAPrix; 
	private int prixVente; 
	private int etatVente; 
	private int noUtilisateur; 
	private int noCategorie; 
	private int noRetrait; 
	
	/**
	 * constructeurs : 
	 * 1 constructeurs vide 
	 * 1 constructeurs avec param�tres respectant le principe d'encapsulation
	 */
	
	public ArticleVendu() {

	}
	
	public ArticleVendu(int pNoArticle, String pNomArticle, String pDescription, Date pDateDebutEncheres, Date pDateFinEncheres, 
			int pMiseAPrix, int pPrixVente, int pEtatVente, int pNoUtilisateur, int pNoCategorie, int pNoRetrait) {
		setNoArticle(pNoArticle); 
		setNomArticle(pNomArticle); 
		setDescription(pDescription); 
		setDateDebutEncheres(pDateDebutEncheres); 
		setDateFinEncheres(pDateFinEncheres); 
		setMiseAPrix(pMiseAPrix); 
		setPrixVente(pPrixVente); 
		setEtatVente(pEtatVente); 
		setNoUtilisateur(pNoUtilisateur); 
		setNoCategorie(pNoCategorie); 
		setNoRetrait(pNoRetrait); 

	}

	
//************Getteurs	
	/**
	 * lit le numero de l'article 
	 */
	public int getNoArticle() {
		return noArticle;
	}
	/**
	 * lit le nom de l'article
	 */
	public String getNomArticle() {
		return nomArticle;
	}
	/**
	 * lit la description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * lit la date du debut de l'enchere
	 */
	public Date getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	/**
	 * lit la date de fin de l'enchere 
	 */
	public Date getDateFinEncheres() {
		return dateFinEncheres;
	}
	/**
	 * lit la mise à prix de l'article
	 */
	public int getMiseAPrix() {
		return miseAPrix;
	}
	/**
	 * lit le prix de vente
	 */
	public int getPrixVente() {
		return prixVente;
	}
	/**
	 * lit l'etat de la vente
	 */
	public int getEtatVente() {
		return etatVente;
	}
	/**
	 * lit le numero de l'utilisateur
	 */
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	/**
	 * lit le numero de la categorie
	 */
	public int getNoCategorie() {
		return noCategorie;
	}
	/**
	 * lit le numero de retrait 
	 */
	public int getNoretrait() {
		return noRetrait;
	}
	
	//************setters
	/**
	 * modifie le numero de l'article
	 */
	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	/**
	 * modifie le nom de l'article
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	/**
	 * modifie la description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * modifie la date de debut de l'enchere
	 */
	public void setDateDebutEncheres(Date dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}

	/**
	 * modifie lea date de fin encheres
	 */
	public void setDateFinEncheres(Date dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}

	/**
	 * modifie la mmise à prix
	 */
	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	/**
	 * modifie le prix de vente
	 */
	public void setPrixVente(int prixVente) {
		this.prixVente = prixVente;
	}

	/**
	 * modifie l'etat de vente 
	 */
	public void setEtatVente(int etatVente) {
		this.etatVente = etatVente;
	}

	/**
	 * modifie le numero de l'utilisateur
	 */
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}

	/**
	 * modifie le numero de catégorie
	 */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
	}

	/**
	 * modifie le numero de retrait 
	 */
	public void setNoRetrait(int noretrait) {
		this.noRetrait = noretrait;
	}
	
	//Méthode toString
	/**
	 * méthode to string permettant de lire un message 
	 */
	@Override
	public String toString() {
		return "ArticleVendu [noArticle=" + noArticle + ", nomArticle=" + nomArticle + ", description=" + description
				+ ", dateDebutEncheres=" + dateDebutEncheres + ", dateFinEncheres=" + dateFinEncheres + ", miseAPrix="
				+ miseAPrix + ", prixVente=" + prixVente + ", etatVente=" + etatVente + ", noUtilisateur="
				+ noUtilisateur + ", noCategorie=" + noCategorie + ", noretrait=" + noRetrait + "]";
	}
	


}
