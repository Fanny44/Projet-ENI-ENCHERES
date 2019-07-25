package org.eniencheres.bll;

import java.util.List;

import org.eniencheres.bo.ArticleVendu;
import org.eniencheres.bo.ListeEncheres;
import org.eniencheres.dal.DALException;
import org.eniencheres.dal.DAOArticleVendu;
import org.eniencheres.dal.DAOFactory;

/**
 * classe ArticleVenduManager 
 * @author Fanny
 *
 */

public class ArticleVenduManager {
	
	private static DAOArticleVendu articleVenduDAO; 
	private static ArticleVenduManager instance;
	
	
	public static ArticleVenduManager getInstance() {
		if (instance == null) {
			instance = new ArticleVenduManager();
		}
		return instance;
	}
	
	//constructeurs permettant l'instanciation de la DAO ArticleVendu
	private ArticleVenduManager(){
		
		articleVenduDAO = DAOFactory.getArticleVenduDAO(); 
	}
	
	
	/**
	 * méthode permettant la récupération de la liste de tous les Articles vendu 
	 * @return ListeArticle
	 * @throws BLLException
	 */
	public List<ListeEncheres> getArticleListeEncheres() throws BLLException{
		List<ListeEncheres> listeEncheres = null; 
		try {
			listeEncheres = articleVenduDAO.ArticleListeEncheres(); 
		}catch (DALException e) {
			throw new BLLException("Erreur sur la récupération de la liste d'article sans paramètre",e);
		}
		return listeEncheres; 
	}
	
	/**
	 *Méthode permettant la récupération de la liste des article vendu en fonction 
	 *du nom et de la catégorie 
	 *@return ListeArticle
	 *@throws BLLException
	 */
	
//	public List<ArticleVendu> getListeArticleFiltre(String nom, String categorie) throws BLLException{
//		List<ArticleVendu> ListeArticle = null; 
//		try {
//			ListeArticle = articleVenduDAO.selectByNomEtCat(); 
//		}catch(DALException e) {
//			throw new BLLException("Erreur sur la récupération de la liste d'article vendu avec paramètre", e); 
//		}
//		return ListeArticle; 
//	}
	
	
	
	
	
	
	
	
	
}
