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
	public Categorie(int pNoCategorie, String pLibelle) {
		setNoCategorie(pNoCategorie);
		setLibelle(pLibelle);
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
	  * @param noCategorie
	  */
	public void setNoCategorie(int noCategorie) {
		this.noCategorie = noCategorie;
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
	 * @param libelle
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
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
