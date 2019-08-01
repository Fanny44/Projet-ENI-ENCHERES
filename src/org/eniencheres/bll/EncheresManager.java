package org.eniencheres.bll;

import org.eniencheres.bo.Enchere;
import org.eniencheres.dal.DAO;
import org.eniencheres.dal.DAOFactory;

public class EncheresManager {
	private static EncheresManager instance;
	private DAO<Enchere> dao = null;
	private Enchere enchere = null;

	
	public static EncheresManager getInstance() {
		if (instance == null) {
			instance = new EncheresManager();
		}
		return instance;
	}

	// constructeur afin de créer une instance d'UtilisateurManager
	private EncheresManager() {
		dao = DAOFactory.getEnchereDAO(); // GET d'une instance de DAOUtilisateur
	}
	
	
	
}
