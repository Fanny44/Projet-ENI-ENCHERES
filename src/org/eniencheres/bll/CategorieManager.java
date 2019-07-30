package org.eniencheres.bll;

import java.util.List;

import org.eniencheres.bo.Categorie;
import org.eniencheres.bo.ListeEncheres;
import org.eniencheres.dal.DALException;
import org.eniencheres.dal.DAOCategorie;
import org.eniencheres.dal.DAOFactory;

public class CategorieManager {
	private static DAOCategorie categorieDAO; 
	private static CategorieManager instance;
	
	
	public static CategorieManager getInstance() {
		if (instance == null) {
			instance = new CategorieManager();
		}
		return instance;
	}
	
	//constructeurs permettant l'instanciation de la DAO ArticleVendu
	private CategorieManager() {
		
		categorieDAO = DAOFactory.getCategorieDAO(); 
	}
		
	/**
	 * méthode permettant la récupération de la liste de toutes les categorie
	 * @return liste categorie
	 * @throws BLLException
	 */
	public List<Categorie> getCategorie() throws BLLException{
		List<Categorie> listeCat = null; 
		try {
			listeCat = categorieDAO.selectAll(); 
			
		}catch (DALException e) {
			throw new BLLException("Erreur sur la récupération de la liste d'article sans paramètre",e);
		}
		return listeCat; 
	}
}

