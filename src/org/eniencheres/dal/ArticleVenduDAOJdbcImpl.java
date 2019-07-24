package org.eniencheres.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eniencheres.bo.ArticleVendu;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
/**
 * ArticleVenduDAOJdbcImpl implémente l'interface DAOArticleVendu
 * @author Fanny
 *
 */
public class ArticleVenduDAOJdbcImpl implements DAOArticleVendu{
	/**
	 * Constante de requête sql : insertion des articles, modification des articles
	 * suppression d'article, selection de tous les articles, sélection par id
	 */
	private static final String SQL_SELECT_ALL="SELECT * FROM ARTICLES_VENDUS;"; 
	@Override
	public void insert(ArticleVendu pObject) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(ArticleVendu pObject) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(ArticleVendu pObject) throws DALException {
		// TODO Auto-generated method stub
		
	}
/**
 * Sélection de tous les articles vendus
 */
	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		List<ArticleVendu> articleVendu = new ArrayList<ArticleVendu>();
		ArticleVendu article = null; 
		Statement stmt = null; 
		Connection cnx=ConnectionProvider.getConnection(); 
		ResultSet rs=null; 
		try {
			stmt=cnx.createStatement(); 
			rs=stmt.executeQuery(SQL_SELECT_ALL); 
			while(rs.next()) {
				article = new ArticleVendu(rs.getInt("no_article"), rs.getString("nom_article"),
						rs.getString("description"), rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"), 
						rs.getInt("prix_initial"), rs.getInt("prix_vente"), rs.getInt("no_utilisateur"), rs.getInt("no_categorie"), rs.getInt("no_retrait"));
				articleVendu.add(article); 
			}
		}catch (SQLException e) {
			throw new DALException("Probleme sur la méthode de selectAll de articleVendu", e); 
		}finally {
			try {
				stmt.close();
				cnx.close();
			}catch (SQLException e) {
				e.getMessage(); 
			}
		}
		return articleVendu; 
	}

	@Override
	public ArticleVendu selectById(ArticleVendu pObject) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
