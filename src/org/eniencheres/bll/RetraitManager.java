package org.eniencheres.bll;

import java.util.List;

import org.eniencheres.bo.Retrait;
import org.eniencheres.dal.DALException;
import org.eniencheres.dal.DAOFactory;
import org.eniencheres.dal.DAORetrait;
/**
 * Classe RetraitManager
 *
 */
public class RetraitManager {

	// déclaration des variables
	private static RetraitManager instance;
	private DAORetrait dao = null;

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

	
	/**
	 * méthode permettant l'insertion d'un retrait 
	 * @param pRetrait
	 * @return null
	 * @throws BLLException
	 */
	public Retrait insert(Retrait pRetrait) throws BLLException {
		try {
			boolean retraitExiste = false;
			List<Retrait> list = dao.selectRetraits(); //vérification que le retrait n'est pas dans la déjà dans la BD
			for (Retrait retrait : list) {
				if (pRetrait.equals(retrait)) {
					retraitExiste = true;
					return retrait;
				}
			}

			if (!retraitExiste) {
				dao.insert(pRetrait);
				return pRetrait;
			}

		} catch (DALException e) {
			throw new BLLException("Une erreur est survenue pendant l'insertion de l'article" + e.getMessage());
		}
		return null;
	}

}
