package org.eniencheres.bo;

import java.io.Serializable;

/**
 * Classe de retrait
 * @author Christophe Michard
 * @since Créé le 22/07/2019
 */
public class Retrait implements Serializable{

	/**
	 * Pour la sérialisation
	 */
	private static final long serialVersionUID = 1135483863914556923L;

	//### Début attributs ###
	private int 	noRetrait;
	private String 	rue;
	private String 	codePostal;
	private String 	ville;
	//### Fin attributs ###
	
	//Attribut d'association 
	private ArticleVendu article; 
	
	//### Début constructeurs ###
	/**
	 * Constructeur
	 */
	public Retrait() {}
	
	/**
	 * Constructeur
	 * @param pRue
	 * @param pCodePostal
	 * @param pVille
	 */
	public Retrait(String pRue, String pCodePostal, String pVille, ArticleVendu pArticle) {
		setRue(pRue);
		setCodePostal(pCodePostal);
		setVille(pVille);
		
		this.article=pArticle; 
	}
	//### Fin constructeurs ###

	
	//### Début accesseurs ###
	/**
	 * Lit le numéro de retrait
	 * @return
	 */
	public int getNoRetrait() {
		return noRetrait;
	}
	
	/**
	 * Modifie le numéro de retrait
	 * @param noRetrait
	 */
	public void setNoRetrait(int noRetrait) {
		this.noRetrait = noRetrait;
	}

	/**
	 * Lit la rue
	 * @return
	 */
	public String getRue() {
		return rue;
	}
	 /**
	  * Modifie la rue
	  * @param rue
	  */
	public void setRue(String rue) {
		this.rue = rue;
	}
	 /**
	  * Lit le code postal
	  * @return
	  */
	public String getCodePostal() {
		return codePostal;
	}
	
	/**
	 * Modifie le code postal
	 * @param codePostal
	 */
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	
	/**
	 * Lit la ville
	 * @return
	 */
	public String getVille() {
		return ville;
	}
	
	/**
	 * Modifie La ville
	 * @param ville
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	//### Fin accesseurs ###

	/**
	 * Redéfinission toString()
	 */
	@Override
	public String toString() {
		return "Retrait [rue=" + rue + ", codePostal=" + codePostal + ", ville=" + ville + "]";
	}
	
	
}
