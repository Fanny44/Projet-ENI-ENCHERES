package org.eniencheres.dal;

import java.util.List;

import org.eniencheres.bo.ArticleVendu;
import org.eniencheres.bo.ListeEncheres;

public interface DAOArticleVendu extends DAO<ArticleVendu>{
	
	List<ListeEncheres> ArticleListeEncheres() throws DALException; 

}
