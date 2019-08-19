package org.eniencheres.bll;

import org.eniencheres.bo.ArticleSelect;
import org.eniencheres.bo.Enchere;
import org.eniencheres.bo.Utilisateur;
import org.eniencheres.dal.DALException;
import org.eniencheres.dal.DAOEnchere;
import org.eniencheres.dal.DAOFactory;
import org.eniencheres.dal.EnchereDAOJdbcImpl;

public class EncheresManager {
	private static EncheresManager instance;
	private DAOEnchere enchereDao = null;
	
	public static EncheresManager getInstance() {
		if (instance == null) {
			instance = new EncheresManager();
		}
		return instance;
	}

	// constructeur afin de créer une instance d'UtilisateurManager
	private EncheresManager() {
		enchereDao = DAOFactory.getEnchereDAO(); // GET d'une instance de DAOUtilisateur
	}
	
/**
 * insertion d'une enchère	
 * @param pObject
 * @throws BLLException
 */
	public void insertEnchere(Enchere pObject) throws  BLLException{
		try {
			enchereDao.insertEnchere(pObject);
		}catch(DALException e) {
			throw new BLLException("Une erreur est survenue pendant l'insertion d'une enchere\n\n" + e.getMessage());
		}
	}
	
	public Utilisateur getSelectPseudo(int montantEnchere, int noArticle) throws BLLException {
		Utilisateur user = null;
		try {
			user = enchereDao.selectPseudo(montantEnchere, noArticle);

		} catch (DALException e) {
			throw new BLLException("Erreur sur la récupération du pseudo de la meilleure enchère faite" + e.getMessage());
		}
		return user;
	}
	
	
}
