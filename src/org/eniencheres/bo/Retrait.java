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
	public Retrait(String pRue, String pCodePostal, String pVille) {
		setRue(pRue);
		setCodePostal(pCodePostal);
		setVille(pVille);
		
		
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codePostal == null) ? 0 : codePostal.hashCode());
		result = prime * result + ((rue == null) ? 0 : rue.hashCode());
		result = prime * result + ((ville == null) ? 0 : ville.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Retrait other = (Retrait) obj;
		if (codePostal == null) {
			if (other.codePostal != null)
				return false;
		} else if (!codePostal.equals(other.codePostal))
			return false;
		if (rue == null) {
			if (other.rue != null)
				return false;
		} else if (!rue.equals(other.rue))
			return false;
		if (ville == null) {
			if (other.ville != null)
				return false;
		} else if (!ville.equals(other.ville))
			return false;
		return true;
	}
	
	
}
