package org.eniencheres.bo;

import java.io.Serializable;

/**
 * Classe Utilisateur
 * @author Christophe Michard
 * @since Créé le 22/07/2019
 */
public class Utilisateur implements Serializable{

	/**
	 * Pour la sérialisation
	 */
	private static final long serialVersionUID = 5203968668103667956L;
	
	//### Début Attributs ###
	private int 	noUtilisateur;
	private String 	pseudo;
	private String 	nom;
	private String 	prenom;
	private String 	email;
	private String 	telephone;
	private String 	rue;
	private String 	codePostal;
	private String 	ville;
	private String 	motDePasse;
	private int 	credit;
	private boolean administrateur;
	//### Fin attributs ###
	
	//### Début constructeurs ###
	/**
	 * Constructeur
	 */
	public Utilisateur() {}
	
	/**
	 * Constructeur
	 * @param pNoUtilisateur
	 * @param pPseudo
	 * @param pNom
	 * @param pPrenom
	 * @param pEmail
	 * @param pTelephone
	 * @param pRue
	 * @param pCodePostal
	 * @param pVille
	 * @param pMotDePasse
	 * @param pCredit
	 * @param pAdministrateur
	 */
	public Utilisateur(int pNoUtilisateur, String pPseudo, String pNom, String pPrenom, String pEmail, String pTelephone,
			String pRue, String pCodePostal, String pVille, String pMotDePasse, int pCredit, boolean pAdministrateur) {
		setNoUtilisateur(pNoUtilisateur);
		setPseudo(pPseudo);
		setNom(pNom);
		setPrenom(pPrenom);
		setEmail(pEmail);
		setTelephone(pTelephone);
		setRue(pRue);
		setCodePostal(pCodePostal);
		setVille(pVille);
		setMotDePasse(pMotDePasse);
		setCredit(pCredit);
		setAdministarteur(pAdministrateur);
	}
	//### Fin constructeurs ###
	
	//### Début accesseurs ###
	/**
	 * Lit le numéro d'utilisateur
	 * @return
	 */
	public int getNoUtilisateur() {
		return noUtilisateur;
	}
	
	/**
	 * Modifie le numéro d'utilisateur
	 * @param noUtilisateur
	 */
	public void setNoUtilisateur(int noUtilisateur) {
		this.noUtilisateur = noUtilisateur;
	}
	
	/**
	 * Lit le pseudo
	 * @return
	 */
	public String getPseudo() {
		return pseudo;
	}
	
	/**
	 * Modifie le pseudo
	 * @param pseudo
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	/**
	 * Lit le nom
	 * @return
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Modifie le nom
	 * @param nom
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Lit le prénom
	 * @return
	 */
	public String getPrenom() {
		return prenom;
	}
	
	/**
	 * Modifie le prénom
	 * @param prenom
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	
	/**
	 * Lit l'email
	 * @return
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Modifie l'email
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * Lit le numéro de téléphone
	 * @return
	 */
	public String getTelephone() {
		return telephone;
	}
	
	/**
	 * Modifie le numéro de téléphone
	 * @param telephone
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	 * Modifie la ville
	 * @param ville
	 */
	public void setVille(String ville) {
		this.ville = ville;
	}
	
	/**
	 * Lit le mot de passe
	 * @return
	 */
	public String getMotDePasse() {
		return motDePasse;
	}
	
	/**
	 * Modifie le mot de passe
	 * @param motDePasse
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	
	/**
	 * Lit le crédit
	 * @return
	 */
	public int getCredit() {
		return credit;
	}
	
	/**
	 * Modifie le crédit
	 * @param pCredit
	 */
	public void setCredit(int pCredit) {
		this.credit = pCredit;
	}
	
	/**
	 * Lit si l'utilisateur est administrateur
	 * @return
	 */
	public boolean isAdministrateur() {
		return administrateur;
	}
	
	/**
	 * Modifie le rôle de l'utilisateur
	 * @param administrateur
	 */
	public void setAdministarteur(boolean administrateur) {
		this.administrateur = administrateur;
	}
	//### Fin accesseurs ###

	/**
	 * Redéfinission de toString()
	 */
	@Override
	public String toString() {
		return "Utilisateur [noUtilisateur=" + getNoUtilisateur() + ", pseudo=" + getPseudo() + ", nom=" + getNom() + ", prenom="
				+ getPrenom() + ", email=" + getEmail() + ", telephone=" + getTelephone() + ", rue=" + getRue() + ", codePostal="
				+ getCodePostal() + ", ville=" + getVille() + ", motDePasse=" + getMotDePasse() + ", credit=" + getCredit()
				+ ", administrateur=" + isAdministrateur() + "]";
	}
	
	
}
