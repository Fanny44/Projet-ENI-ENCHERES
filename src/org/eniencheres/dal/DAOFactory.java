package org.eniencheres.dal;

import org.eniencheres.bo.Retrait;

/**
 * DAO Factory
 * @author Christophe Michard
 * @since Créé le 23/07/2019
 */
public class DAOFactory {

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
	
	@SuppressWarnings("unchecked")
	public static DAO<Retrait> getRetraitDAO() {
		DAO<Retrait> dao = null; 
		
		try {
			dao = (DAO<Retrait> ) Class.forName("org.eniencheres.dal.RetraitDAOJdbcImpl").newInstance();
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
