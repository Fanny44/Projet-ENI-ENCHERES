package org.eniencheres.dal;

import java.util.List;

import org.eniencheres.bo.ArticleSelect;
import org.eniencheres.bo.ArticleVendu;
import org.eniencheres.bo.ListeEncheres;

//DAOArticleVendu hérite des méthodes de DAO<T> 
//Mais possède aussi ses propres méthodes  
/**
 * DAOArticleVendu héritant de DAO<T>
 * @author Fanny
 *
 */
public interface DAOArticleVendu extends DAO<ArticleVendu>{
	
/**
 * 	
 * @return une liste d'enchère
 * @throws DALException
 */
	List<ListeEncheres> ArticleListeEncheres() throws DALException;

/**
 * 
 * @return liste d'article
 * @throws DALException
 */
	List<ListeEncheres> selectArticles()throws DALException;

/**
 * 	
 * @param nom
 * @return liste d'article correspondant au nom passé en paramètre
 * @throws DALException
 */
	List<ListeEncheres> ArticleListeEncheresNom(String nom) throws DALException;
/**
 * 	
 * @param categorie
 * @return liste d'article correspondant au numéro de catégorie passé en paramètre
 * @throws DALException
 */
	List<ListeEncheres> ArticleListeEncheresCat(int categorie) throws DALException;
/**
 * 	
 * @param nom
 * @param categorie
 * @return liste d'article correspondant au numéro de catégorie et au nom passé en paramètre
 * @throws DALException
 */
	List<ListeEncheres> ArticleListeEncheresNomCat(String nom, int categorie) throws DALException;
/**
 * 
 * @param noUtilisateur
 * @return liste d'enchère dont l'utilisater passé en paramètre à fait au moins une enchère
 * @throws DALException
 */
	List<ListeEncheres> EncheresFaite(int noUtilisateur) throws DALException;
/**
 * 
 * @param noUtilisateur
 * @return liste des ventes en cours de l'utilisateur passé en paramère 
 * @throws DALException
 */
	List<ListeEncheres> MesVentesCours(int noUtilisateur) throws DALException;
/**
 * 
 * @param noUtilisateur
 * @return liste des ventes non commencés de l'utilisateur passé en paramère 
 * @throws DALException
 */
	List<ListeEncheres> MesVentesNonCommences(int noUtilisateur) throws DALException;
/**
 * 
 * @param noUtilisateur
 * @return liste des ventes terminés de l'utilisateur passé en paramère 
 * @throws DALException
 */
	List<ListeEncheres> MesVentesTermines(int noUtilisateur) throws DALException;
/**
 * 
 * @param noUtilisateur
 * @return liste des ventes remportés par l'utilisateur passé en paramère 
 * @throws DALException
 */
	List<ListeEncheres> MesEncheresRemportes(int noUtilisateur) throws DALException;
/**
 * 
 * @param noArticle
 * @return l'article dont le numéro à été passé en paramètre
 * @throws DALException
 */
	ArticleSelect selectArticleById(int noArticle) throws DALException;
/**
 * modification de l'article dont le numéro est passé en paramètre
 * @param noArticle
 * @throws DALException
 */
	void update(int noArticle) throws DALException;

	

}
