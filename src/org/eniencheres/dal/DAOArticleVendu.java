package org.eniencheres.dal;

import java.util.List;

import org.eniencheres.bo.ArticleVendu;
import org.eniencheres.bo.ListeEncheres;
import org.eniencheres.bo.Utilisateur;

public interface DAOArticleVendu extends DAO<ArticleVendu>{
	
	List<ListeEncheres> ArticleListeEncheres() throws DALException;
	
	List<ListeEncheres> selectArticles()throws DALException;
	
	List<ListeEncheres> ArticleListeEncheresNom(String nom) throws DALException;
	
	List<ListeEncheres> ArticleListeEncheresCat(int categorie) throws DALException;
	
	List<ListeEncheres> ArticleListeEncheresNomCat(String nom, int categorie) throws DALException;

	List<ListeEncheres> EncheresFaite(int noUtilisateur) throws DALException;

}
