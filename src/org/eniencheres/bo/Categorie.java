package org.eniencheres.bo;

import java.io.Serializable;

/**
 * Classe Catégorie
 * @author Christophe Michard
 * @since Créé le 22/07/2019
 */
public class Categorie implements Serializable{

	/**
	 * Pour la sérialisation
	 */
	private static final long serialVersionUID = -6172273035728974754L;
	
	//### Début Attributs ###
	private int 	noCategorie;
	private String 	libelle;
	//### Fin Attributs ###
	
	//Attribut d'association 
	private ArticleVendu article; 
	
	//### Début Constructeurs ###
	/**
	 * Constructeur
	 */
	public Categorie() {}
	
	/**
	 * Constructeur
	 * @param pNoCategorie
	 * @param pLibelle
	 */
	public Categorie(int pNoCategorie, String pLibelle, ArticleVendu pArticle) {
		setNoCategorie(pNoCategorie);
		setLibelle(pLibelle);
		
		this.article=pArticle; 
	}
	//### Fin Constructeurs ###
	
	//### Début Accesseurs ###
	/**
	 * Lit le numéro de la catégorie
	 * @return
	 */
	public int getNoCategorie() {
		return noCategorie;
	}
	 /**
	  * Modifie le numéro de catégorie
	  * @param pNoCategorie
	  */
	public void setNoCategorie(int pNoCategorie) {
		this.noCategorie = pNoCategorie;
	}
	
	/**
	 * Lit le libellé de la catégorie
	 * @return
	 */
	public String getLibelle() {
		return libelle;
	}
	
	/**
	 * Modifie le libellé de la catégorie
	 * @param pLibelle
	 */
	public void setLibelle(String pLibelle) {
		this.libelle = pLibelle;
	}
	//### Fin Accesseurs ###

	/**
	 * Redéfinission de toString()
	 */
	@Override
	public String toString() {
		return "Categorie [noCategorie=" + getNoCategorie() + ", libelle=" + getLibelle() + "]";
	}
	
	
}
