package org.eniencheres.bll;

import org.eniencheres.bo.Enchere;
import org.eniencheres.dal.DALException;
import org.eniencheres.dal.DAO;
import org.eniencheres.dal.DAOFactory;

public class EncheresManager {
	private static EncheresManager instance;
	private DAO<Enchere> enchereDao = null;
	private Enchere enchere = null;

	
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
			enchereDao.insert(pObject);
		}catch(DALException e) {
			throw new BLLException("Une erreur est survenue pendant l'insertion d'une enchere" + e.getMessage());
		}
	}
	
	
}
