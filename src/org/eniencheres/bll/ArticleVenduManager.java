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
//	bof
	
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
	 * @throws BLLException 
	 * méthode permettant l'insertion d'un Article vendu en Base de donnée
	 * @return 
	 * @throws 
	 */
	public void insertArticleVendu(ArticleVendu article) throws BLLException{
		try {
			articleVenduDAO.insert(article);
		} catch (DALException e) {
			throw new BLLException("Une erreur est survenue pendant l'insertion de l'article" + " " + e.getMessage());
		}
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
			throw new BLLException("Erreur sur la récupération de la liste d'article sans paramètre"+ e.getMessage());
		}
		return listeEncheres; 
	}
	
	/**
	 *Méthode permettant la récupération de la liste des article vendu en fonction 
	 *du nom et de la catégorie 
	 *@return ListeArticle
	 *@throws BLLException
	 */
	
	public List<ListeEncheres> getListeArticleFiltre(String nom, int categorie) throws BLLException{
		List<ListeEncheres> ListeArticle = null; 
		try {
			ListeArticle = articleVenduDAO.ArticleListeEncheresNomCat(nom, categorie); 
		}catch(DALException e) {
			throw new BLLException("Erreur sur la récupération de la liste d'article vendu avec paramètre" + e.getMessage()); 
		}
		return ListeArticle; 
	}
	
	
	/**
	 *Méthode permettant la récupération de la liste des article vendu en fonction 
	 *du nom de l'article
	 *@return ListeArticle
	 *@throws BLLException
	 */
	
	public List<ListeEncheres> getListeArticleFiltreNom(String nomArticle) throws BLLException{
		List<ListeEncheres> ListeArticle = null; 
		try {
			ListeArticle = articleVenduDAO.ArticleListeEncheresNom(nomArticle); 
		}catch(DALException e) {
			throw new BLLException("Erreur sur la récupération de la liste d'article vendu avec paramètre" + e.getMessage()); 
		}
		return ListeArticle; 
	}
	
	/**
	 *Méthode permettant la récupération de la liste des article vendu en fonction 
	 *de la catégorie 
	 *@return ListeArticle
	 *@throws BLLException
	 */
	
	public List<ListeEncheres> getListeArticleFiltreCat(int categorie) throws BLLException{
		List<ListeEncheres> ListeArticle = null; 
		try {
			ListeArticle = articleVenduDAO.ArticleListeEncheresCat(categorie); 
		}catch(DALException e) {
			throw new BLLException("Erreur sur la récupération de la liste d'article vendu avec paramètre" + e.getMessage()); 
		}
		return ListeArticle; 
	}
	
	
	
	
	
	
	
}
