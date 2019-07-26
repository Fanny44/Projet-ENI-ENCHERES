package org.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eniencheres.bo.ArticleVendu;
import org.eniencheres.bo.ListeEncheres;
import org.eniencheres.bo.Utilisateur;

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
	
	private static final String SQL_SELECT_LISTE_ENCHERES="Select nom_article, prix_vente, date_fin_encheres, nom From ARTICLES_VENDUS inner join utilisateurs on "
			+ "ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur"
			+"where GETDATE() between date_debut_encheres and date_fin_encheres ;";
	
	private static final String SQL_SELECT__NOM="Select nom_article, prix_vente, date_fin_encheres, nom From ARTICLES_VENDUS inner join utilisateurs on "  
				+ "ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur where nom_article=?;";
	private static final String SQL_SELECT_CATEGORIE="Select nom_article, prix_vente, date_fin_encheres, nom From ARTICLES_VENDUS inner join utilisateurs on "  
			+ "ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join Categories on ARTICLES_VENDUS.no_categorie= Categories.no_categorie where libelle=?;"; 
	private static final String SQL_SELECT_CATEGORIE_NOM="Select nom_article, prix_vente, date_fin_encheres, nom From ARTICLES_VENDUS inner join utilisateurs on "
			+ "ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join Categories on ARTICLES_VENDUS.no_categorie= Categories.no_categorie where libelle=?, nom_article=?;";
	
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

	@Override
	public List<ListeEncheres> ArticleListeEncheres() throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>(); 
		ListeEncheres liste= null; 
		Statement stmt=null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		try {
			stmt=cnx.createStatement(); 
			rs=stmt.executeQuery(SQL_SELECT_LISTE_ENCHERES); 
			while(rs.next()) {
				liste = new ListeEncheres ();
				liste.setArticle(rs.getString("nom_article"));
				liste.setMontant(rs.getInt("prix_vente"));
				liste.setDateFin(rs.getDate("date_fin_encheres"));
				liste.setVendeur(rs.getString("nom"));
			listeEncheres.add(liste);
			}
		}catch (SQLException e) {
			throw new DALException("Problème sur la méthode ArticleListeEnchere, " + e.getMessage());
		}finally {
			try {
				stmt.close();
				cnx.close();
			}catch (SQLException e) {
				e.getMessage(); 
			}
		}
			
		return listeEncheres;
	}

	@Override
	public List<ListeEncheres> ArticleListeEncheresNom(ListeEncheres pObject) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_SELECT__NOM); 
			pstmt.setString(1, pObject.getArticle());
			rs=pstmt.executeQuery(); 
			
			if(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("nom"));
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode selectById de l'utilisateur : " + e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return listeEncheres;	
	}

	@Override
	public List<ListeEncheres> ArticleListeEncheresCat(ListeEncheres pObject) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_SELECT_CATEGORIE); 
			pstmt.setString(1, pObject.getCategorie());
			rs=pstmt.executeQuery(); 
			
			if(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("nom"));
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode selectById de l'utilisateur : " + e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return listeEncheres;
	}

	@Override
	public List<ListeEncheres> ArticleListeEncheresNomCat(String nom, String categorie) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_SELECT_CATEGORIE_NOM); 
			pstmt.setString(1, nom);
			pstmt.setString(2, categorie);
			rs=pstmt.executeQuery(); 
			
			if(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("nom"));
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode selectById de l'utilisateur : " + e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return listeEncheres;
	}

}
