package org.eniencheres.bo;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Classe Enchere
 * @author Christophe Michard
 * @since Créé le 22/07/2019
 * @since Modifié le 23/07/2019
 */
public class Enchere implements Serializable{

	/**
	 * Pour la sérialisation
	 */
	private static final long serialVersionUID = -5229708420047827311L;

	//### Début Attributs ###
	private int 		noEnchere;
	private Date 	dateEnchere;
	private int 		montantEnchere;
	private int 		noArticle;
	private int 		noUtilisateur;
	//### Fin Attributs ###
	
	
//attributs d'association
	private Utilisateur utilisateur; 
	private ArticleVendu article; 

	//### Début Constructeurs ###
	/**
	 * Constructeur
	 */
	public Enchere() {}
	
	/**
	 * Constructeur
	 * @param pNoEnchere
	 * @param pDateEnchere
	 * @param pMontantEnchere
	 * @param pNoArticle
	 * @param pNoUtilisateur
	 */
	public Enchere(int pNoEnchere, Date pDateEnchere, int pMontantEnchere, int pNoArticle, 
			int pNoUtilisateur, Utilisateur pUtilisateur, ArticleVendu pArticle) {
		setNoEnchere(pNoEnchere);
		setDateEnchere(pDateEnchere);
		setMontantEnchere(pMontantEnchere);
		setNoArticle(pNoArticle);
		setNoUtilisateur(pNoUtilisateur);
		
		this.utilisateur=pUtilisateur; 
		this.article=pArticle; 
	}
	//### Fin Constructeurs ###

	//### Début Accesseurs ###
	/**
	 * Lit le numéro d'enchère
	 * @return
	 */
	public int getNoEnchere() {
		return noEnchere;
	}

	/**
	 * Modifie le numéro d'enchère
	 * @param pNoEnchere
	 */
	public void setNoEnchere(int pNoEnchere) {
		this.noEnchere = pNoEnchere;
	}

	/**
	 * Lit la date d'enchère
	 * @return
	 */
	public Date getDateEnchere() {
		return dateEnchere;
	}

	/**
	 * Modifie la date d'enchère
	 * @param pDateEnchere
	 */
	public void setDateEnchere(Date pDateEnchere) {
		this.dateEnchere = pDateEnchere;
	}

	/**
	 * Lit le montant de l'enchère
	 * @return
	 */
	public int getMontantEnchere() {
		return montantEnchere;
	}

	/**
	 * Modifie le montant de l'enchère
	 * @param pMontantEnchere
	 */
	public void setMontantEnchere(int pMontantEnchere) {
		this.montantEnchere = pMontantEnchere;
	}

	/**
	 * Lit le numéro de l'article
	 * @return
	 */
	public int getNoArticle() {
		return noArticle;
	}

	/**
	 * modifie le numéro de l'article
	 * @param pNoArticle
	 */
	public void setNoArticle(int pNoArticle) {
		this.noArticle = pNoArticle;
	}

	/**
	 * Lit le numéro d'utilisateur
	 * @return
	 */
	public int getNoUtilisateur() {
		return noUtilisateur;
	}

	/**
	 * Modifie le numéro d'utilisateur
	 * @param pNoUtilisateur
	 */
	public void setNoUtilisateur(int pNoUtilisateur) {
		this.noUtilisateur = pNoUtilisateur;
	}
	//### Fin Accesseurs ###
	
	/**
	 * Redéfinission toString()
	 */
	@Override
	public String toString() {
		return "Enchere [getNoEnchere()=" + getNoEnchere() + ", getDateEnchere()=" + getDateEnchere()
				+ ", getMontantEnchere()=" + getMontantEnchere() + ", getNoArticle()=" + getNoArticle()
				+ ", getNoUtilisateur()=" + getNoUtilisateur() + "]";
	}
	
	
	
}
