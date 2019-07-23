package org.eniencheres.bll;

import org.eniencheres.bo.Utilisateur;
import org.eniencheres.dal.DALException;
import org.eniencheres.dal.DAOFactory;
import org.eniencheres.dal.DAOUtilisateur;

public class UtilisateurManager {
	private static UtilisateurManager instance;
	
	public static UtilisateurManager getInstance() {
		if (instance == null) {
			instance = new UtilisateurManager();
		}
		return instance;
	}
	
	private UtilisateurManager() {
	}
	
	public Utilisateur verifIdentite(String pseudo, String motdepasse){
		
		DAOUtilisateur dao = DAOFactory.getUtilisateurDAO();
		Utilisateur utilisateur = null;
		try {
			utilisateur = dao.selectByPseudo(pseudo);
		} catch (DALException e) {
			e.printStackTrace();
		}	
		if (utilisateur != null) {
			if (motdepasse != utilisateur.getMotDePasse() ) {
				utilisateur = null;
			}
		}
		
		return utilisateur;
	}
	
 
}
	
	
	

