package org.eniencheres.bo;

import java.io.Serializable;
import java.sql.Date;

/**
 * Classe ArticleVendu
 * @author Fanny
 * crée le 23/07/2019
 *
 */
public class ArticleSelect implements Serializable {

	/**
	 * Pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;
	
	//Attribut d'instance
	private int noArticle;
	private String nomArticle;
	private String description;
	private String libelle;
	private int montantEnchere;
	private int miseAPrix;
	private Date debutEnchere;
	private Date finEnchere;
	private String rue;
	private String codePostal;
	private String ville;
	private String pseudo;
	
	
	//Constructeurs 
	//1 vide
	//1 avec des paramètres
	public ArticleSelect() {
		
	}
	
	
	public ArticleSelect(int noArticle, String nomArticle, String description, String libelle, int montantEnchere,
			int miseAPrix, Date debutEnchere, Date finEnchere, String rue, String codePostal, String ville, String pseudo) {
		this.noArticle = noArticle;
		this.nomArticle = nomArticle;
		this.description = description;
		this.libelle = libelle;
		this.montantEnchere = montantEnchere;
		this.miseAPrix = miseAPrix;
		this.debutEnchere=debutEnchere;
		this.finEnchere = finEnchere;
		this.rue = rue;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pseudo = pseudo;
	}

	
//Getteur (Acceusseur) permettant d'afficher les attributs
	
	public Date getDebutEnchere() {
		return debutEnchere;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public String getLibelle() {
		return libelle;
	}
	
	public int getMontantEnchere() {
		return montantEnchere;
	}
	
	public int getMiseAPrix() {
		return miseAPrix;
	}
	
	public Date getFinEnchere() {
		return finEnchere;
	}
	
	public String getPseudo() {
		return pseudo;
	}
	
	public String getRue() {
		return rue;
	}
	
	public String getCodePostal() {
		return codePostal;
	}
	
	public String getVille() {
		return ville;
	}
	
	
//setter (modificateur) permettant de modifier les attributs
	
	public void setDescription(String pDescription) {
		this.description = pDescription;
	}

	public void setLibelle(String pLibelle) {
		this.libelle = pLibelle;
	}
	
	public void setNomArticle(String pNomArticle) {
		this.nomArticle = pNomArticle;
	}

	public void setDebutEnchere(Date pDebutEnchere) {
		this.debutEnchere = pDebutEnchere;
	}
	
	public void setNoArticle(int pNoArticle) {
		this.noArticle = pNoArticle;
	}

	public void setMontantEnchere(int pMontantEnchere) {
		this.montantEnchere = pMontantEnchere;
	}

	public void setMiseAPrix(int pMiseAPrix) {
		this.miseAPrix = pMiseAPrix;
	}

	public void setFinEnchere(Date pFinEnchere) {
		this.finEnchere = pFinEnchere;
	}

	public void setRue(String pRue) {
		this.rue = pRue;
	}

	public void setCodePostal(String pCodePostal) {
		this.codePostal = pCodePostal;
	}

	public void setVille(String pVille) {
		this.ville = pVille;
	}

	public void setPseudo(String pPseudo) {
		this.pseudo = pPseudo;
	}

	
//méthode d'écrire un message
	
	@Override
	public String toString() {
		return "ArticleSelect [getNoArticle()=" + getNoArticle() + ", getNomArticle()=" + getNomArticle()
				+ ", getDescription()=" + getDescription() + ", getLibelle()=" + getLibelle() + ", getMontantEnchere()="
				+ getMontantEnchere() + ", getMiseAPrix()=" + getMiseAPrix() + ", getDebutEnchere() =" + getDebutEnchere() +", getFinEnchere()=" + getFinEnchere()
				+ ", getRue()=" + getRue() + ", getCodePostal()=" + getCodePostal() + ", getVille()=" + getVille()
				+ ", getPseudo()=" + getPseudo() + "]";
	}
	
	
}
