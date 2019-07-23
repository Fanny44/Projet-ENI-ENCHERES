package org.eniencheres.bll;

import org.eniencheres.bo.Utilisateur;
import org.eniencheres.dal.DALException;
import org.eniencheres.dal.DAOFactory;
import org.eniencheres.dal.DAOUtilisateur;

public class UtilisateurManager {
	
	// déclaration des variables
	private static UtilisateurManager instance;
	private DAOUtilisateur dao = null;
	// Création d'une Design Pattern Singleton
	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	//constructeur afin de créer une instance d'UtilisateurManager
	private UtilisateurManager() {
		dao = DAOFactory.getUtilisateurDAO();	// GET d'une instance de DAOUtilisateur
	}
	
	
	
	
	
	/** Classe verifIdentite
	 * Vérifications concernant l'identité
	 * @param identifiant
	 * @param motdepasse
	 * @return utilisateur
	 */
	public Utilisateur verifIdentite(String identifiant, String motdepasse){
		Utilisateur utilisateur = null; // initialisation de la variable utilisateur
			
		try {
			//Vérifie que le mot de passe fasse bien huit caractères
			if(isValidpassword(motdepasse))
			
			// Contrôle pour savoir si l'identification se fait par email	
			if(isValidEmail(identifiant)) {
				utilisateur = dao.selectByEmail(identifiant);
				
			}
			// Contrôle pour savoir si l'identification se fait par pseudo	

			else {
				utilisateur = dao.selectByPseudo(identifiant);
			}

		} catch (DALException e) {
			e.printStackTrace();
		}	
			// Vérifie que le mot de passe soit bien renseigné
		if (utilisateur != null) {
			// Vérifie que le mot de passe corresponde bien au mot de passe de cet utilisateur en base de donnée
			if (motdepasse != utilisateur.getMotDePasse() ) {
				utilisateur = null;
			}
		}
		
		return utilisateur;
	}
			// Méthode pour savoir si l'identification se fait par mail ou par pseudo à l'aide d'une expression régulière
	 public static boolean isValidEmail( String email ) {
	        String exReg = "^[_a-z0-9-]+(\\.[_a-z0-9-]+)*@[a-z0-9-]+(\\.[a-z0-9-]+)+$";
	        return email.matches( exReg );
	    }
	 		//Méthode pour savoir si le mot de passe fait huit caractères à l'aide d'une expression régulière
	 public static boolean isValidpassword( String motdepasse ) {
	        String regEx = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-z^A-Z^0-9]).{8,}$";
	        return motdepasse.matches( regEx );
	    }
}
	
	
	

