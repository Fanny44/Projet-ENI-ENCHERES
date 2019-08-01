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
	
	private static final String SQL_SELECT__NOM="Select nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on "  
				+ "ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur where nom_article=?;";
	private static final String SQL_SELECT_CATEGORIE="Select nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on \r\n" + 
			"ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join Categories on ARTICLES_VENDUS.no_categorie= Categories.no_categorie where CATEGORIES.no_categorie=?;"; 
	private static final String SQL_SELECT_CATEGORIE_NOM="Select nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on\r\n" + 
			"		ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join CATEGORIES on ARTICLES_VENDUS.no_categorie= CATEGORIES.no_categorie where CATEGORIES.no_categorie=? and nom_article=?;";
	private static final String SQL_INSERT_ARTICLE_VENDU="insert into ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie,no_retrait)\r\n" + 
			"values(?,?,?,?,?,?,?,?,?);";
	private static final String SQL_ENCHERE_FAIT="SELECT nom_article, prix_vente, date_fin_encheres, pseudo FROM ARTICLES_VENDUS Inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join ENCHERES on ARTICLES_VENDUS.no_article =ENCHERES.no_article\r\n" + 
			"	where ENCHERES.no_utilisateur=?;";
	private static final String SQL_MES_VENTES_COURS="Select nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur \r\n" + 
			"		where GETDATE() between date_debut_encheres and date_fin_encheres and ARTICLES_VENDUS.no_utilisateur=?;";
	private static final String SQL_MES_VENTES_NN_COMMENCES ="Select nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur \r\n" + 
			"		where GETDATE() < date_debut_encheres  and ARTICLES_VENDUS.no_utilisateur=?;";
	private static final String SQL_VENTES_TERMINES="Select nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur \r\n" + 
			"		where GETDATE() > date_fin_encheres  and ARTICLES_VENDUS.no_utilisateur=?;";
	private static final String SQL_ENCHERES_GAGNES="SELECT nom_article, prix_vente, date_fin_encheres, pseudo FROM ARTICLES_VENDUS Inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join ENCHERES on ARTICLES_VENDUS.no_article =ENCHERES.no_article\r\n" + 
			"	where  GETDATE()>date_fin_encheres and ENCHERES.no_utilisateur=? and prix_vente = montant_enchere;";
	// TODO private static final String SQL_UPDATE_PRIX_VENTE=""
	/**
	 * méthode insert implementant la DAOArticleVendu permet l'insertion d'un article dans la base de donnée
	 * 
	 */
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
			pstmt.executeQuery();
			
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
	
	/**
	 * méthode ArticleListeEncheres implementant la DAOArticleVendu permet de sélectionner les articles 
	 * dont la date du jour en cours est compris entre la date de début de l'enchère et la date de fin 
	 * @return listeEncheres 
	 */	

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
	/**
	 * méthode ArticleListeEncheresNom implementant la DAOArticleVendu permet de sélectionner les articles 
	 * grâce au nom
	 * @param nom 
	 * @return listeEncheres 
	 */	
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
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste);
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode selectById de l'utilisateur : " + e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return listeEncheres;	
	}
	
	/**
	 * méthode ArticleListeEncheresNom implementant la DAOArticleVendu permet de sélectionner les articles 
	 * grâce à la catégorie
	 * @param catégorie 
	 * @return listeEncheres 
	 */		

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
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste);
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode selectById de l'utilisateur : " + e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return listeEncheres;
	}
	
	/**
	 * méthode ArticleListeEncheresNom implementant la DAOArticleVendu permet de sélectionner les articles 
	 * grâce au pseudo et à la catégorie
	 * @param pseudo et catégorie 
	 * @return listeEncheres 
	 */	
	@Override
	public List<ListeEncheres> ArticleListeEncheresNomCat(String pseudo, int categorie) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_SELECT_CATEGORIE_NOM); 
			pstmt.setString(1, pseudo);
			pstmt.setInt(2, categorie);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
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
	/**
	 * méthode EncheresFaites implementant la DAOArticleVendu permet de sélectionner les articles 
	 * dont l'user à fait au moins une enchère
	 * @param pObjetc 
	 * @return listeEncheres 
	 */		

	@Override
	public List<ListeEncheres> EncheresFaite(int noUtilisateur) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_ENCHERE_FAIT); 
			pstmt.setInt(1, noUtilisateur);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste);
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode EncheresFaites de l'utilisateur : " + e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return listeEncheres;
	}

	/**
	 * méthode MesVentesCours implementant la DAOArticleVendu permet de sélectionner les articles 
	 * faite par l'user en cours
	 * @param pObjetc 
	 * @return listeEncheres 
	 */		

	@Override
	public List<ListeEncheres> MesVentesCours (int noUtilisateur) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_MES_VENTES_COURS); 
			pstmt.setInt(1, noUtilisateur);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste);
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode MesVentesCours de l'utilisateur : " + e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return listeEncheres;
	}
	/**
	 * méthode MesVentesNonCommences implementant la DAOArticleVendu permet de sélectionner les articles 
	 * faite par l'user non commences
	 * @param noUtilisateurs 
	 * @return listeEncheres 
	 */		

	@Override
	public List<ListeEncheres> MesVentesNonCommences (int noUtilisateur) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_MES_VENTES_NN_COMMENCES); 
			pstmt.setInt(1, noUtilisateur);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste);
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode MesVentesNonCommences de l'utilisateur : " + e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return listeEncheres;
	}
	
	/**
	 * méthode MesVentesNonCommences implementant la DAOArticleVendu permet de sélectionner les articles 
	 * faite par l'user qui sont terminés
	 * @param noUtilisateurs 
	 * @return listeEncheres 
	 */		

	@Override
	public List<ListeEncheres> MesVentesTermines (int noUtilisateur) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_VENTES_TERMINES); 
			pstmt.setInt(1, noUtilisateur);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste);
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode MesVentesNonCommences de l'utilisateur : " + e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return listeEncheres;
	}	
	/**
	 * méthode MesEncheresRemporte implementant la DAOArticleVendu permet de sélectionner les articles 
	 * dont l'user à remporter les encheres
	 * @param noUtilisateurs 
	 * @return listeEncheres 
	 */		

	@Override
	public List<ListeEncheres> MesEncheresRemportes (int noUtilisateur) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_ENCHERES_GAGNES); 
			pstmt.setInt(1, noUtilisateur);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste);
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode MesEncheresRemportes de l'utilisateur : " + e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return listeEncheres;
	}
}
