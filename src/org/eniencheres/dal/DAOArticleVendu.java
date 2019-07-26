package org.eniencheres.dal;

import java.util.List;

import org.eniencheres.bo.ArticleVendu;
import org.eniencheres.bo.ListeEncheres;

public interface DAOArticleVendu extends DAO<ArticleVendu>{
	
	List<ListeEncheres> ArticleListeEncheres() throws DALException;
	
	List<ListeEncheres> ArticleListeEncheresNom(ListeEncheres pObject) throws DALException;
	
	List<ListeEncheres> ArticleListeEncheresCat(ListeEncheres pObject) throws DALException;
	
	List<ListeEncheres> ArticleListeEncheresNomCat(String nom, String categorie) throws DALException;

}
