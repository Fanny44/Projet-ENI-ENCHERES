package org.eniencheres.dal;

import java.util.List;

import org.eniencheres.bo.ArticleVendu;
import org.eniencheres.bo.ListeEncheres;

public interface DAOArticleVendu extends DAO<ArticleVendu>{
	
	List<ListeEncheres> ArticleListeEncheres() throws DALException;
	
	List<ListeEncheres> ArticleListeEncheresNom(String nom) throws DALException;
	
	List<ListeEncheres> ArticleListeEncheresCat(String categorie) throws DALException;
	
	List<ListeEncheres> ArticleListeEncheresNomCat(String nom, String categorie) throws DALException;

}
