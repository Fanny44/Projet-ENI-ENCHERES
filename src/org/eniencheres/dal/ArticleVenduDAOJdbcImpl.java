package org.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eniencheres.bo.ArticleSelect;
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
	private static final String SQL_SELECT_ALL="SELECT no_article, nom_article, prix_vente, date_fin_encheres, pseudo FROM ARTICLES_VENDUS inner join utilisateurs on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur;"; 
	private static final String SQL_SELECT_ARTICLE_ID="SELECT Articles_vendus.no_article, nom_article,description,libelle, prix_vente, prix_initial,date_debut_encheres, date_fin_encheres,retraits.rue, retraits.code_postal, " + 
			"retraits.ville,pseudo FROM ARTICLES_VENDUS inner join CATEGORIES on ARTICLES_VENDUS.no_categorie=CATEGORIES.no_categorie " + 
			"inner join  RETRAITS on ARTICLES_VENDUS.no_retrait=RETRAITS.no_retrait inner join " + 
			"UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur where ARTICLES_VENDUS.no_article=?;";
	private static final String SQL_SELECT_LISTE_ENCHERES="Select no_article, nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur "
			+ "where GETDATE() between date_debut_encheres and date_fin_encheres;";
	
	private static final String SQL_SELECT__NOM="Select no_article, nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on "  
				+ "ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur where nom_article=?;";
	private static final String SQL_SELECT_CATEGORIE="Select no_article, nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on \r\n" + 
			"ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join Categories on ARTICLES_VENDUS.no_categorie= Categories.no_categorie where CATEGORIES.no_categorie=?;"; 
	private static final String SQL_SELECT_CATEGORIE_NOM="Select no_article, nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on\r\n" + 
			"		ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join CATEGORIES on ARTICLES_VENDUS.no_categorie= CATEGORIES.no_categorie where CATEGORIES.no_categorie=? and nom_article=?;";
	private static final String SQL_INSERT_ARTICLE_VENDU="insert into ARTICLES_VENDUS (nom_article,description,date_debut_encheres,date_fin_encheres,prix_initial,prix_vente,no_utilisateur,no_categorie,no_retrait)\r\n" + 
			"values(?,?,?,?,?,?,?,?,?);";
	private static final String SQL_ENCHERE_FAIT="SELECT MAX(montant_enchere) as montant, ENCHERES.no_article, nom_article, prix_vente, date_fin_encheres, pseudo FROM ARTICLES_VENDUS Inner Join \r\n" + 
			"  UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join ENCHERES on ARTICLES_VENDUS.no_article =ENCHERES.no_article \r\n" + 
			"			where ENCHERES.no_utilisateur=? group by ENCHERES.no_article, ARTICLES_VENDUS.nom_article, prix_vente, date_fin_encheres, pseudo;";
	private static final String SQL_MES_VENTES_COURS="Select no_article, nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur \r\n" + 
			"		where GETDATE() between date_debut_encheres and date_fin_encheres and ARTICLES_VENDUS.no_utilisateur=?;";
	private static final String SQL_MES_VENTES_NN_COMMENCES ="Select no_article, nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur \r\n" + 
			"		where GETDATE() < date_debut_encheres  and ARTICLES_VENDUS.no_utilisateur=?;";
	private static final String SQL_VENTES_TERMINES="Select no_article, nom_article, prix_vente, date_fin_encheres, pseudo From ARTICLES_VENDUS inner join utilisateurs on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur \r\n" + 
			"		where GETDATE() > date_fin_encheres  and ARTICLES_VENDUS.no_utilisateur=?;";
	private static final String SQL_ENCHERES_GAGNES="SELECT ARTICLES_VENDUS.no_article, nom_article, prix_vente, date_fin_encheres, pseudo FROM ARTICLES_VENDUS Inner Join UTILISATEURS on ARTICLES_VENDUS.no_utilisateur=UTILISATEURS.no_utilisateur Inner Join ENCHERES on ARTICLES_VENDUS.no_article =ENCHERES.no_article\r\n" + 
			"	where  GETDATE()>date_fin_encheres and ENCHERES.no_utilisateur=? and prix_vente = montant_enchere;";
	private static final String SQL_UPDATE_PRIX_VENTE="update ARTICLES_VENDUS set prix_vente= (SELECT MAX(montant_enchere) as montant_enchere from ENCHERES where no_article=?) where no_article=?;";
	
	/**
	 * selection d'un article par son numero d'article
	 * @param noArticle
	 * @throws DALException
	 * @return article
	 */
	@Override
	public ArticleSelect selectArticleById(int noArticle) throws DALException {
		ArticleSelect article = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); //obtention d'un connexion
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_SELECT_ARTICLE_ID);  //préparation de la requête
			pstmt.setInt(1, noArticle);
			rs=pstmt.executeQuery(); 
			//instanciation d'un objet article grace au constructeur de la classe ArticleSelect avec les données récupérer 
			if(rs.next()) {
				article = new ArticleSelect(rs.getInt("no_article"),rs.getString("nom_article"), rs.getString("description"), 
						rs.getString("libelle"), rs.getInt("prix_vente"), rs.getInt("prix_initial"),rs.getDate("date_debut_encheres"), rs.getDate("date_fin_encheres"),
						rs.getString("rue"), rs.getString("code_postal"), rs.getString("ville"), rs.getString("pseudo"));
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode selectArticleById de l'ArticleVenduJdbcImpl" + e.getMessage()); //en cas d'erreur
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture de cnx, pstmt
		}			  
		
		return article;	
	}	
	
	
	/**
	 * méthode insert implementant la DAOArticleVendu permet l'insertion d'un
	 * article dans la base de donnée
	 * @param ArticleVendu pObject
	 * @throws DALException	 * 
	 */
	
	@Override
	public void insert(ArticleVendu pObject) throws DALException {
		Connection cnx = ConnectionProvider.getConnection(); //obtention d'un connexion
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = cnx.prepareStatement(SQL_INSERT_ARTICLE_VENDU, Statement.RETURN_GENERATED_KEYS); //préparation de la requête récupération de l'id correspondant à l'insertion 
			pstmt.setString(1, pObject.getNomArticle());
			pstmt.setString(2, pObject.getDescription());
			pstmt.setDate(3, new java.sql.Date(pObject.getDateDebutEncheres().getTime()));
			pstmt.setDate(4, new java.sql.Date(pObject.getDateFinEncheres().getTime()));
			pstmt.setInt(5, pObject.getMiseAPrix());
			pstmt.setInt(6, pObject.getPrixVente());
			pstmt.setInt(7, pObject.getNoUtilisateur());
			pstmt.setInt(8, pObject.getNoCategorie());
			pstmt.setInt(9, pObject.getNoretrait());
			int num = pstmt.executeUpdate();
			if (num == 1) {
				rs = pstmt.getGeneratedKeys();
				if (rs.next()) {
					pObject.setNoArticle(rs.getInt(1)); //association de l'id à l'object inserer
				}
			}
		} catch (SQLException e) {
			throw new DALException("problème sur la méthode INSERT l'articleVendu" + e.getMessage()); //en cas d'erreur

		} finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture de pstmt, cnx
		}
	}
	
	
/**
 * méthode modifiant le prix de vente de l'article 
 * @param noArticle
 * @throws DALException 
 */
	@Override
	public void update(int noArticle) throws DALException {
		Connection cnx = ConnectionProvider.getConnection(); //obtention d'une connexion 
		PreparedStatement pstmt = null;
		try {
			cnx.setAutoCommit(false);
			pstmt=cnx.prepareStatement(SQL_UPDATE_PRIX_VENTE); //préparation de la requete
			pstmt.setInt(1, noArticle);
			pstmt.setInt(2, noArticle);
			pstmt.executeUpdate();
			cnx.commit();
		}catch (SQLException e) {
			throw new DALException("Erreur du l'update du prix de vente" + e.getMessage()); //en, cas d'erreur
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture de la connexion et du pstmt
		}
		
	}

/**
 * Sélection de tous les articles vendus
 * @throws DALException 
 * @return articleVendu
 */
	@Override
	public List<ListeEncheres> selectArticles() throws DALException {
		List<ListeEncheres> articleVendu = new ArrayList<ListeEncheres>();
		ListeEncheres liste = null; 
		Statement stmt = null; 
		Connection cnx=ConnectionProvider.getConnection();  //obtention d'une connexion 
		ResultSet rs=null; 
		try {
			stmt=cnx.createStatement(); 
			rs=stmt.executeQuery(SQL_SELECT_ALL); //exécution de la requête
			while(rs.next()) {
				liste = new ListeEncheres ();
				liste.setNoArticle(rs.getInt("no_article"));
				liste.setArticle(rs.getString("nom_article"));
				liste.setMontant(rs.getInt("prix_vente"));
				liste.setDateFin(rs.getDate("date_fin_encheres"));
				liste.setVendeur(rs.getString("pseudo"));
				articleVendu.add(liste);  //ajout des objets instancié dans une liste 
			}
		}catch (SQLException e) {
			throw new DALException("Probleme sur la méthode de selectArticles de l'articleVendu" + e.getMessage()); //en cas d'erreur
		}finally {
			ConnectionProvider.seDeconnecter(stmt, cnx); //fermeture de cnx, stmt
		}
		return articleVendu; 
	}


	/**
	 * méthode ArticleListeEncheres implementant la DAOArticleVendu permet de sélectionner les articles 
	 * dont la date du jour en cours est compris entre la date de début de l'enchère et la date de fin 
	 * @return listeEncheres 
	 * @throws DALException 
	 */	

	@Override
	public List<ListeEncheres> ArticleListeEncheres() throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>(); 
		ListeEncheres liste= null; 
		Statement stmt=null; 
		Connection cnx = ConnectionProvider.getConnection();  //obtention d'une connexion 
		ResultSet rs= null; 
		try {
			stmt=cnx.createStatement(); 
			rs=stmt.executeQuery(SQL_SELECT_LISTE_ENCHERES);  //preparation de la requete
			while(rs.next()) {
				liste = new ListeEncheres ();
				liste.setNoArticle(rs.getInt("no_article"));
				liste.setArticle(rs.getString("nom_article"));
				liste.setMontant(rs.getInt("prix_vente"));
				liste.setDateFin(rs.getDate("date_fin_encheres"));
				liste.setVendeur(rs.getString("pseudo"));
			listeEncheres.add(liste); //ajout des objets instancié dans la listeEncheres
			}
		}catch (SQLException e) {
			throw new DALException("Problème sur la méthode ArticleListeEnchere de l'articleVendu" + e.getMessage()); //en cas d'erreur
		}finally {
			ConnectionProvider.seDeconnecter(stmt, cnx); //fermeture cnx et stmt 
		}
			
		return listeEncheres;
	}
	
	/**
	 * méthode ArticleListeEncheresNom implementant la DAOArticleVendu permet de sélectionner les articles 
	 * grâce au nom
	 * @param nom 
	 * @throws DALException
	 * @return listeEncheres 
	 */	
	@Override
	public List<ListeEncheres> ArticleListeEncheresNom(String nom) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection();  //obtention d'une connexion 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_SELECT__NOM);  //preparation de la requete
			pstmt.setString(1, nom);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setNoArticle(rs.getInt("no_article"));
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste);   //ajout des objets instancié dans la listeEncheres
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode ArticleListeEncheresNom de l'articleVendu : " + e.getMessage()); //en cas d'erreur
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture cnx et pstmt
		}			 
		
		return listeEncheres;	
	}
	
	
	/**
	 * méthode ArticleListeEncheresNom implementant la DAOArticleVendu permet de sélectionner les articles 
	 * grâce à la catégorie
	 * @param catégorie 
	 * @throws DALException 
	 * @return listeEncheres 
	 */		

	@Override
	public List<ListeEncheres> ArticleListeEncheresCat(int categorie) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection();  //obtention d'une connexion
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_SELECT_CATEGORIE);  //preparation de la requete
			pstmt.setInt(1, categorie);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setNoArticle(rs.getInt("no_article"));
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste);   //ajout des objets instancié dans la listeEncheres
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode ArticleListeEncheresCat de l'articleVendu : " + e.getMessage()); //en cas d'erreur
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture cnx et pstmt
		}			  
		
		return listeEncheres;
	}
	
	
	/**
	 * méthode ArticleListeEncheresNom implementant la DAOArticleVendu permet de sélectionner les articles 
	 * grâce au pseudo et à la catégorie
	 * @param pseudo et catégorie 
	 * @throws DALException
	 * @return listeEncheres 
	 */	
	@Override
	public List<ListeEncheres> ArticleListeEncheresNomCat(String pseudo, int categorie) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection();  //obtention d'une connexion
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_SELECT_CATEGORIE_NOM); //preparation de la requete
			pstmt.setString(1, pseudo);
			pstmt.setInt(2, categorie);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setNoArticle(rs.getInt("no_article"));
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste); //ajout des objets instancié dans la listeEncheres
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode ArticleListeEncheresNomCat de l'articleVendu : " + e.getMessage()); //en cas d'erreur
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture cnx et pstmt
		}			 
		
		return listeEncheres;
	}

	
	/**
	 * méthode EncheresFaites implementant la DAOArticleVendu permet de sélectionner les articles 
	 * dont l'user à fait au moins une enchère
	 * @param noUtilisateur
	 * @throws DALException 
	 * @return listeEncheres 
	 */		

	@Override
	public List<ListeEncheres> EncheresFaite(int noUtilisateur) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); //obtention d'une connexion
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_ENCHERE_FAIT); //preparation de la requete
			pstmt.setInt(1, noUtilisateur);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setNoArticle(rs.getInt("no_article"));
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste); //ajout des objets instancié dans la listeEncheres
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode EncheresFaites de l'articleVendu : " + e.getMessage()); //en cas d'erreur
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture cnx et pstmt
		}			 
		
		return listeEncheres;
	}

	
	/**
	 * méthode MesVentesCours implementant la DAOArticleVendu permet de sélectionner les articles 
	 * faite par l'user en cours
	 * @param noUtilisateur
	 * @throws DALException
	 * @return listeEncheres 
	 */		

	@Override
	public List<ListeEncheres> MesVentesCours (int noUtilisateur) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection();  //obtention d'une connexion
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_MES_VENTES_COURS);  //preparation de la requete
			pstmt.setInt(1, noUtilisateur);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setNoArticle(rs.getInt("no_article"));
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste); //ajout des objets instancié dans la listeEncheres
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode MesVentesCours de l'articleVendu : " + e.getMessage()); //en cas d'erreur
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture cnx et pstmt
		}			 
		
		return listeEncheres;
	}
	
	
	/**
	 * méthode MesVentesNonCommences implementant la DAOArticleVendu permet de sélectionner les articles 
	 * faite par l'user non commences
	 * @param noUtilisateurs 
	 * @throws DALException
	 * @return listeEncheres 
	 */		

	@Override
	public List<ListeEncheres> MesVentesNonCommences (int noUtilisateur) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); //obtention d'une connexion
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_MES_VENTES_NN_COMMENCES);  //preparation de la requete
			pstmt.setInt(1, noUtilisateur);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setNoArticle(rs.getInt("no_article"));
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste); //ajout des objets instancié dans la listeEncheres
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode MesVentesNonCommences de l'articleVendu : " + e.getMessage()); //en cas d'erreur
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture cnx et pstmt
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
		Connection cnx = ConnectionProvider.getConnection();  //obtention d'une connexion
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_VENTES_TERMINES);  //preparation de la requete
			pstmt.setInt(1, noUtilisateur);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setNoArticle(rs.getInt("no_article"));
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste); //ajout des objets instancié dans la listeEncheres
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode MesVentesNonCommences de l'articleVendu : " + e.getMessage()); //en cas d'erreur
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture cnx et pstmt
		}			 
		
		return listeEncheres;
	}	
	
	/**
	 * méthode MesEncheresRemporte implementant la DAOArticleVendu permet de sélectionner les articles 
	 * dont l'user à remporter les encheres
	 * @param noUtilisateurs 
	 * @throws DALException 
	 * @return listeEncheres 
	 */		

	@Override
	public List<ListeEncheres> MesEncheresRemportes (int noUtilisateur) throws DALException {
		List<ListeEncheres> listeEncheres = new ArrayList<>();
		ListeEncheres liste = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection();  //obtention d'une connexion
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_ENCHERES_GAGNES);  //preparation de la requete
			pstmt.setInt(1, noUtilisateur);
			rs=pstmt.executeQuery(); 
			
			while(rs.next()) {
				liste = new ListeEncheres(); 
					liste.setNoArticle(rs.getInt("no_article"));
					liste.setArticle(rs.getString("nom_article"));
					liste.setMontant(rs.getInt("prix_vente")); 
					liste.setDateFin(rs.getDate("date_fin_encheres")); 
					liste.setVendeur(rs.getString("pseudo"));
				listeEncheres.add(liste); //ajout des objets instancié dans la listeEncheres
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode MesEncheresRemportes de l'articleVendu : " + e.getMessage()); //en cas d'erreur
		}finally { 
				ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture cnx et pstmt
		}			 
		
		return listeEncheres;
	}

	//méthode non utiliser
	@Override
	public ArticleVendu selectById(ArticleVendu pObject) throws DALException {
		return null;
	}

	@Override
	public void update(ArticleVendu pObject) throws DALException {
		
	}

	@Override
	public void delete(ArticleVendu pObject) throws DALException {
		
	}

	@Override
	public List<ArticleVendu> selectAll() throws DALException {
		return null;
	}

}
