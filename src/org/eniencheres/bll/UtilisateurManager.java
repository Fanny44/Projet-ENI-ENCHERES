package org.eniencheres.bll;

import org.eniencheres.bo.Utilisateur;
import org.eniencheres.dal.DALException;
import org.eniencheres.dal.DAOFactory;
import org.eniencheres.dal.DAOUtilisateur;

public class UtilisateurManager {

	// déclaration des variables
	private static UtilisateurManager instance;
	private DAOUtilisateur dao = null;
	private Utilisateur utilisateur = null;

	// Création d'une Design Pattern Singleton
	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}

	// constructeur afin de créer une instance d'UtilisateurManager
	private UtilisateurManager() {
		dao = DAOFactory.getUtilisateurDAO(); // GET d'une instance de DAOUtilisateur
	}

	/**
	 * Classe verifIdentite Vérifications concernant l'identité
	 * 
	 * @param identifiant
	 * @param motdepasse
	 * @return utilisateur
	 */
	public Utilisateur verifIdentite(String identifiant, String motdepasse) {

		if ((isValidPseudo(identifiant) || isValidEmail(identifiant)) && !isValidPassword(motdepasse)) {
			utilisateur = null;
		}

		return utilisateur;

	}

	// Méthode pour savoir si le mot de passe fait huit caractères à l'aide d'une
	// expression régulière
	public boolean isValidPassword(String motdepasse) {
//		String regEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-z^A-Z^0-9]).{8,}$";
//		return motdepasse.matches(regEx);
		boolean valide = false;
		
		valide = motdepasse.length()>= 8 && utilisateur.getMotDePasse().equals(motdepasse);	
			
		return valide;
	}

	// Méthode pour savoir si l'identifiant correspond à une adresse mail
	public boolean isValidEmail(String identifiant) {
		boolean valide = false;

		try {
			utilisateur = dao.selectByEmail(identifiant);
			if (utilisateur != null && utilisateur.getEmail().equals(identifiant)) {
				valide = true;
			}

		} catch (DALException e) {
			e.printStackTrace();
		}

		return valide;
	}

	// Méthode qui dit si l'identifiant existe en tant que pseudo dans la bdd
	public boolean isValidPseudo(String identifiant) {
		boolean valide = false;

		try {
			utilisateur = dao.selectByPseudo(identifiant);
			if (utilisateur != null && utilisateur.getPseudo().equals(identifiant)) {
				valide = true;
			}

		} catch (DALException e) {
			e.printStackTrace();
		}

		return valide;
	}
}
