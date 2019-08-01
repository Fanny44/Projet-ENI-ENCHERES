package org.eniencheres.bll;

import org.eniencheres.bo.Retrait;
import org.eniencheres.bo.Utilisateur;
import org.eniencheres.dal.DALException;
import org.eniencheres.dal.DAO;
import org.eniencheres.dal.DAOFactory;

public class RetraitManager {

		// déclaration des variables
		private static RetraitManager instance;
		private DAO<Retrait> dao = null;
		private Utilisateur utilisateur = null;

		// Création d'un Design Pattern Singleton
		public static RetraitManager getInstance() {
			if (instance == null) {
				instance = new RetraitManager();
			}
			return instance;
		}

		// constructeur afin de créer une instance RetraitManager
		private RetraitManager() {
			dao = DAOFactory.getRetraitDAO(); // GET d'une instance de DAOUtilisateur
		}
		
		
	public void insert(Retrait pRetrait)throws BLLException{
		try {
			dao.insert(pRetrait);
		} catch (DALException e) {
			throw new BLLException("Une erreur est survenue pendant l'insertion de l'article" + " " + e.getMessage());
		}
	}
		
}
