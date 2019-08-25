package org.eniencheres.bll;

import java.util.List;

import org.eniencheres.bo.Categorie;
import org.eniencheres.dal.DALException;
import org.eniencheres.dal.DAO;
import org.eniencheres.dal.DAOFactory;

/**
 * Classe CategorieManager
 *
 */
public class CategorieManager {
	
	//Déclaration des variables
	private static DAO<Categorie> categorieDAO; 
	private static CategorieManager instance;
	
	// Création d'une Design Pattern Singleton
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
	 * @return listeCat
	 * @throws BLLException
	 */
	public List<Categorie> getCategorie() throws BLLException{
		List<Categorie> listeCat = null; 
		try {
			listeCat = categorieDAO.selectAll(); 
			
		}catch (DALException e) {
			throw new BLLException("Erreur sur la récupération de la liste d'article sans paramètre " + e.getMessage());
		}
		return listeCat; 
	}

}

