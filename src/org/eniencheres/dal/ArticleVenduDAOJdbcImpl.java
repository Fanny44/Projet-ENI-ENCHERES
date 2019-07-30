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
	private static final String SQL_SELECT_ALL="SELECT nom_article, prix_vente, date_fin_encheres, pseudo FROM ARTICLES_VENDUS inner join utilisateurs on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur;"; 
	
	private static final String SQL_SELECT_LISTE_ENCHERES="Select nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur "
			+ "where GETDATE() between date_debut_encheres and date_fin_encheres;";
	
	private static final String SQL_SELECT__NOM="Select nom_article, prix_vente, date_fin_encheres, nom From ARTICLES_VENDUS inner join utilisateurs on "  
				+ "ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur where nom_article=?;";
	private static final String SQL_SELECT_CATEGORIE="Select nom_article, prix_vente, date_fin_encheres, nom From ARTICLES_VENDUS inner join utilisateurs on \r\n" + 
			"ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join Categories on ARTICLES_VENDUS.no_categorie= Categories.no_categorie where CATEGORIES.no_categorie=?;"; 
	private static final String SQL_SELECT_CATEGORIE_NOM="Select nom_article, prix_vente, date_fin_encheres, nom From ARTICLES_VENDUS inner join utilisateurs on\r\n" + 
			"		ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join CATEGORIES on ARTICLES_VENDUS.no_categorie= CATEGORIES.no_categorie where CATEGORIES.no_categorie=? and nom_article=?;";
	private static final String SQL_INSERT_ARTICLE_VENDU="insert into ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie,no_retrait)\r\n" + 
			"values(?,?,?,?,?,?,?,?,?);";
	
	@Override
	public void insert(ArticleVendu pObject) throws DALException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = cnx.prepareStatement(SQL_INSERT_ARTICLE_VENDU, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, pObject.getNomArticle());
			pstmt.setString(2, pObject.getDescription());
			pstmt.setDate(3, new java.sql.Date(pObject.getDateDebutEncheres().getTime())); 
			pstmt.setDate(4,new java.sql.Date(pObject.getDateFinEncheres().getTime()));
			pstmt.setInt(5, pObject.getMiseAPrix());
			pstmt.setInt(6, pObject.getPrixVente());
			pstmt.setInt(7, pObject.getNoUtilisateur());
			pstmt.setInt(8, pObject.getNoCategorie());
			pstmt.setInt(9, pObject.getNoretrait());
			
		} catch (SQLException e) {
			throw new DALException("problème sur la méthode INSERT d'articles"+e.getMessage());
		
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx);}
		
		
		
		
		
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
	public List<ListeEncheres> selectArticles() throws DALException {
		List<ListeEncheres> articleVendu = new ArrayList<ListeEncheres>();
		ListeEncheres liste = null; 
		Statement stmt = null; 
		Connection cnx=ConnectionProvider.getConnection(); 
		ResultSet rs=null; 
		try {
			stmt=cnx.createStatement(); 
			rs=stmt.executeQuery(SQL_SELECT_ALL); 
			while(rs.next()) {
				liste = new ListeEncheres ();
				liste.setArticle(rs.getString("nom_article"));
				liste.setMontant(rs.getInt("prix_vente"));
				liste.setDateFin(rs.getDate("date_fin_encheres"));
				liste.setVendeur(rs.getString("pseudo"));
				articleVendu.add(liste); 
			}
		}catch (SQLException e) {
			throw new DALException("Probleme sur la méthode de selectAll de articleVendu", e); 
		}finally {
			ConnectionProvider.seDeconnecter(stmt, cnx);
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
				liste.setVendeur(rs.getString("pseudo"));
			listeEncheres.add(liste);
			}
		}catch (SQLException e) {
			throw new DALException("Problème sur la méthode ArticleListeEnchere, " + e.getMessage());
		}finally {
			ConnectionProvider.seDeconnecter(stmt, cnx);
		}
			
		return listeEncheres;
	}

	@Override
	public List<ListeEncheres> ArticleListeEncheresNom(String nom) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_SELECT__NOM); 
			pstmt.setString(1, nom);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("nom"));
				listeEncheres.add(liste);
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode selectById de l'utilisateur : " + e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return listeEncheres;	
	}

	@Override
	public List<ListeEncheres> ArticleListeEncheresCat(int categorie) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_SELECT_CATEGORIE); 
			pstmt.setInt(1, categorie);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("nom"));
				listeEncheres.add(liste);
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode selectById de l'utilisateur : " + e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return listeEncheres;
	}

	@Override
	public List<ListeEncheres> ArticleListeEncheresNomCat(String nom, int categorie) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_SELECT_CATEGORIE_NOM); 
			pstmt.setString(1, nom);
			pstmt.setInt(2, categorie);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("nom"));
				listeEncheres.add(liste);
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode selectById de l'utilisateur : " + e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return listeEncheres;
	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
