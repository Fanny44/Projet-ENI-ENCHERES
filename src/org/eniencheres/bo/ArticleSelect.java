package org.eniencheres.bo;

import java.io.Serializable;
import java.sql.Date;

public class ArticleSelect implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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

	public Date getDebutEnchere() {
		return debutEnchere;
	}

	public void setDebutEnchere(Date debutEnchere) {
		this.debutEnchere = debutEnchere;
	}

	public int getNoArticle() {
		return noArticle;
	}

	public void setNoArticle(int noArticle) {
		this.noArticle = noArticle;
	}

	public String getNomArticle() {
		return nomArticle;
	}

	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public int getMontantEnchere() {
		return montantEnchere;
	}

	public void setMontantEnchere(int montantEnchere) {
		this.montantEnchere = montantEnchere;
	}

	public int getMiseAPrix() {
		return miseAPrix;
	}

	public void setMiseAPrix(int miseAPrix) {
		this.miseAPrix = miseAPrix;
	}

	public Date getFinEnchere() {
		return finEnchere;
	}

	public void setFinEnchere(Date finEnchere) {
		this.finEnchere = finEnchere;
	}

	public String getRue() {
		return rue;
	}

	public void setRue(String rue) {
		this.rue = rue;
	}

	public String getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	@Override
	public String toString() {
		return "ArticleSelect [getNoArticle()=" + getNoArticle() + ", getNomArticle()=" + getNomArticle()
				+ ", getDescription()=" + getDescription() + ", getLibelle()=" + getLibelle() + ", getMontantEnchere()="
				+ getMontantEnchere() + ", getMiseAPrix()=" + getMiseAPrix() + ", getDebutEnchere() =" + getDebutEnchere() +", getFinEnchere()=" + getFinEnchere()
				+ ", getRue()=" + getRue() + ", getCodePostal()=" + getCodePostal() + ", getVille()=" + getVille()
				+ ", getPseudo()=" + getPseudo() + "]";
	}
	
	
}
