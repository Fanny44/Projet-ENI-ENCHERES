package org.eniencheres.dal;

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
	public static DAOCategorie getCategorieDAO() {
		DAOCategorie categorieDAO = null; 
		
		try {
			categorieDAO = (DAOCategorie ) Class.forName("org.eniencheres.dal.CategorieDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return categorieDAO; 
	}
	
	
//TODO deux méthodes retournant un dao !! à changer car peut causer des erreurs 	
	/**
	 * création d'une instance de la classe RetraitDAOJdbcImpl
	 * @return dao
	 */
	
	public static DAORetrait getRetraitDAO() {
		DAORetrait dao = null; 
		
		try {
			dao = (DAORetrait) Class.forName("org.eniencheres.dal.RetraitDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return dao; 
	}
	
	/**
	 * création d'une instance de la classe EnchereDAOJdbcImpl
	 * @return dao
	 */
	
	public static DAOEnchere getEnchereDAO() {
		DAOEnchere dao = null; 
		
		try {
			dao = (DAOEnchere)Class.forName("org.eniencheres.dal.EnchereDAOJdbcImpl").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return dao; 
	}
}
