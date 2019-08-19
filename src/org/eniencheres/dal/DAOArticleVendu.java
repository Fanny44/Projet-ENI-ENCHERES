package org.eniencheres.dal;

import java.util.List;

import org.eniencheres.bo.ArticleSelect;
import org.eniencheres.bo.ArticleVendu;
import org.eniencheres.bo.ListeEncheres;

public interface DAOArticleVendu extends DAO<ArticleVendu>{
	
	List<ListeEncheres> ArticleListeEncheres() throws DALException;
	
	List<ListeEncheres> selectArticles()throws DALException;
	
	List<ListeEncheres> ArticleListeEncheresNom(String nom) throws DALException;
	
	List<ListeEncheres> ArticleListeEncheresCat(int categorie) throws DALException;
	
	List<ListeEncheres> ArticleListeEncheresNomCat(String nom, int categorie) throws DALException;

	List<ListeEncheres> EncheresFaite(int noUtilisateur) throws DALException;

	List<ListeEncheres> MesVentesCours(int noUtilisateur) throws DALException;

	List<ListeEncheres> MesVentesNonCommences(int noUtilisateur) throws DALException;

	List<ListeEncheres> MesVentesTermines(int noUtilisateur) throws DALException;

	List<ListeEncheres> MesEncheresRemportes(int noUtilisateur) throws DALException;

	ArticleSelect selectArticleById(int noArticle) throws DALException;

	void update(int noArticle) throws DALException;

	

}
