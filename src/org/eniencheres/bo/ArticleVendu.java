package org.eniencheres.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe ArticleVendu
 * @author Fanny
 * crée le 22/07/2019
 *
 */

public class ArticleVendu implements Serializable{
		
	/**
	 * Pour la sérialisation
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
	
	//Attribut d'association 
	private Utilisateur vendeur; 
	private List<Enchere> listeEnchere=new ArrayList<>();; 
	private Categorie categorie; 
	private Retrait retrait; 
	
	
	
	/**
	 * constructeurs : 
	 * 1 constructeurs vide 
	 * 1 constructeurs avec paramétres respectant le principe d'encapsulation
	 */
	
	public ArticleVendu() {

	}
	
	public ArticleVendu(int pNoArticle, String pNomArticle, String pDescription, Date pDateDebutEncheres, Date pDateFinEncheres, int pMiseAPrix, int pPrixVente,
			int pNoUtilisateur, int pNoCategorie,int pNoRetrait ) {
		setNoArticle(pNoArticle); 
		setNomArticle(pNomArticle); 
		setDescription(pDescription); 
		setDateDebutEncheres(pDateDebutEncheres); 
		setDateFinEncheres(pDateFinEncheres); 
		setMiseAPrix(pMiseAPrix); 
		setPrixVente(pPrixVente); 
		setNoUtilisateur(pNoUtilisateur); 
		setNoCategorie(pNoCategorie); 
		setNoRetrait(pNoRetrait); 
		
	}
	
	public ArticleVendu(int pNoArticle, String pNomArticle, String pDescription, Date pDateDebutEncheres, Date pDateFinEncheres, 
			int pMiseAPrix, int pPrixVente, int pEtatVente, int pNoUtilisateur, int pNoCategorie, int pNoRetrait, 
			Utilisateur pVendeur, Categorie pCategorie, Retrait pRetrait) {
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
		
		this.categorie=pCategorie; 
		this.vendeur=pVendeur; 
		this.retrait=pRetrait; 		
		

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
	public void setNoArticle(int pNoArticle) {
		this.noArticle = pNoArticle;
	}

	/**
	 * modifie le nom de l'article
	 */
	public void setNomArticle(String pNomArticle) {
		this.nomArticle = pNomArticle;
	}

	/**
	 * modifie la description
	 */
	public void setDescription(String pDescription) {
		this.description = pDescription;
	}

	/**
	 * modifie la date de debut de l'enchere
	 */
	public void setDateDebutEncheres(Date pDateDebutEncheres) {
		this.dateDebutEncheres = pDateDebutEncheres;
	}

	/**
	 * modifie lea date de fin encheres
	 */
	public void setDateFinEncheres(Date pDateFinEncheres) {
		this.dateFinEncheres = pDateFinEncheres;
	}

	/**
	 * modifie la mmise à prix
	 */
	public void setMiseAPrix(int pMiseAPrix) {
		this.miseAPrix = pMiseAPrix;
	}

	/**
	 * modifie le prix de vente
	 */
	public void setPrixVente(int pPrixVente) {
		this.prixVente = pPrixVente;
	}

	/**
	 * modifie l'etat de vente 
	 */
	public void setEtatVente(int pEtatVente) {
		this.etatVente = pEtatVente;
	}

	/**
	 * modifie le numero de l'utilisateur
	 */
	public void setNoUtilisateur(int pNoUtilisateur) {
		this.noUtilisateur = pNoUtilisateur;
	}

	/**
	 * modifie le numero de catégorie
	 */
	public void setNoCategorie(int pNoCategorie) {
		this.noCategorie = pNoCategorie;
	}

	/**
	 * modifie le numero de retrait 
	 */
	public void setNoRetrait(int pNoretrait) {
		this.noRetrait = pNoretrait;
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
