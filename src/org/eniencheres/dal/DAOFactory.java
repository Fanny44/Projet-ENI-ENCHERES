package org.eniencheres.dal;

import org.eniencheres.bo.Categorie;

/**
 * DAO Factory
 * @author Christophe Michard
 * @since Créé le 23/07/2019
 */

//fabrique d'objet 
//délégation de l'instanciation d'objet à cette classe
//permet d'obtenir des objets des classe DAOJDBCImpl
public class DAOFactory {

	/**
	 * création d'une instance de la classe UtilisateurDAOJdbcImpl
	 * @return utilisateurDAO
	 */
	public static DAOUtilisateur getUtilisateurDAO() {
		DAOUtilisateur utilisateurDAO = null;
		
		try {
			utilisateurDAO = (DAOUtilisateur ) Class.forName("org.eniencheres.dal.UtilisateurDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return utilisateurDAO; 
	}
	
	/**
	 * création d'une instance de la classe ArticleVenduDAOJdbcImpl
	 * @return articleVenduDAO
	 */
	public static DAOArticleVendu getArticleVenduDAO() {
		DAOArticleVendu articleVenduDAO = null; 
		
		try {
			articleVenduDAO = (DAOArticleVendu ) Class.forName("org.eniencheres.dal.ArticleVenduDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return articleVenduDAO; 
	}
	
	/**
	 * création d'une instance de la classe CategorieDAOJdbcImpl
	 * @return categorieDAO
	 */
	@SuppressWarnings("unchecked")
	public static DAO<Categorie> getCategorieDAO() {
		DAO<Categorie> categorieDAO = null; 
		
		try {
			categorieDAO = (DAO<Categorie>) Class.forName("org.eniencheres.dal.CategorieDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return categorieDAO; 
	}
	
	
	/**
	 * création d'une instance de la classe RetraitDAOJdbcImpl
	 * @return Retraitdao
	 */
	
	public static DAORetrait getRetraitDAO() {
		DAORetrait Retraitdao = null; 
		
		try {
			Retraitdao = (DAORetrait) Class.forName("org.eniencheres.dal.RetraitDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return Retraitdao; 
	}
	
	/**
	 * création d'une instance de la classe EnchereDAOJdbcImpl
	 * @return Encheredao
	 */
	
	public static DAOEnchere getEnchereDAO() {
		DAOEnchere Encheredao = null; 
		
		try {
			Encheredao = (DAOEnchere)Class.forName("org.eniencheres.dal.EnchereDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return Encheredao; 
	}
}
