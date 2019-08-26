package org.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eniencheres.bo.Utilisateur;
/**
 * UtilisateurDAOJdbcImpl implémente l'interface DAOUtilisateur
 * @author Fanny
 *
 */
public class UtilisateurDAOJdbcImpl implements DAOUtilisateur{
		
	/**
	 * Constante de requête paramétré sql concernant les utilisateurs
	 */
	//Constante car elles ne doivent pas être modifier ! 
	
	private static final String SQL_SELECT_ALL ="SELECT * FROM Utilisateurs;";
	private static final String SQL_SELECT_BY_PSEUDO="SELECT * FROM Utilisateurs WHERE  pseudo=?;";
	private static final String SQL_INSERT = "INSERT into Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal,"
			+ "ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SQL_SELECT_BY_ID="Select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, "
			+ "ville, mot_de_passe, credit, administrateur From Utilisateurs where no_utilisateur=?;"; 
	private static final String SQL_DELETE = "DELETE From Utilisateurs where no_utilisateur=?; "; 
	private static final String SQL_UPDATE="UPDATE Utilisateurs set pseudo=?, nom=?, prenom=?, email=?,"
			+ "telephone=?, rue=?, code_postal=?, ville=?, mot_de_passe =?, credit=?, administrateur=? where pseudo=?;";
	private static final String SQL_SELECT_BY_EMAIL="SELECT * FROM Utilisateurs WHERE  email=?;";
	private static final String SQL_UPDATE_CREDIT_NVOFRRE="update UTILISATEURS set credit =? where no_utilisateur=?;";
	private static final String SQL_MODIF_CREDIT_OFFRE_BASSE="UPDATE UTILISATEURS set credit=(SELECT credit from UTILISATEURS where no_utilisateur=(SELECT no_utilisateur from ENCHERES where no_article=? and montant_enchere=?))+ ? "
			+ "where no_utilisateur=(SELECT no_utilisateur from ENCHERES where no_article=? and montant_enchere=?)";
			
	
	/**
	 * méthode d'insertion d'un user dans BDD
	 * @param pObject de type Utilisateur
	 * @throws DALException
	 */
	@Override
	public void insert(Utilisateur pObject) throws DALException{
		PreparedStatement pstmt = null; 
		Connection cnx=ConnectionProvider.getConnection();  //récupération d'une connexion avec la BDD
		
		try {
			pstmt = cnx.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS); //préparation de la requête qui nous retournera un id qui sera générer à son exécution 
			//ajout des paramètres nécessaires à la requête
			pstmt.setString(1, pObject.getPseudo());  
			pstmt.setString(2, pObject.getNom());
			pstmt.setString(3, pObject.getPrenom());
			pstmt.setString(4, pObject.getEmail());
			pstmt.setString(5, pObject.getTelephone());
			pstmt.setString(6, pObject.getRue());
			pstmt.setString(7, pObject.getCodePostal());
			pstmt.setString(8, pObject.getVille());
			pstmt.setString(9, pObject.getMotDePasse());
			pstmt.setInt(10, pObject.getCredit());
			pstmt.setBoolean(11, pObject.isAdministrateur());
			
			pstmt.executeUpdate(); //exécution de la requête
			ResultSet rs=pstmt.getGeneratedKeys(); //stockage de l'id dans rs
			if(rs.next()) {
				pObject.setNoUtilisateur(rs.getInt(1));      //attribution de l'ide récupérer au noUtilisateur de cet objet 
			}			
		}catch (SQLException e) {
			throw new DALException("Problème sur la méthode insert d'utilisateur" + e.getMessage());  //en cas d'erreur message 
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx);  //déconnexion de la BDD et fermeture du preparedStatement
		}
	}
	
//TODO Commit à ajouter dans toute les méthodes	
	/**
	 * Modification d'un user dans la BDD
	 * @param pObject de type Utilisateur
	 * @throws DALException 
	 * 
	 */
	@Override
	public void update(Utilisateur pObject) throws DALException {
		Connection cnx = ConnectionProvider.getConnection(); //récupération d'une connexion avec la BDD
		PreparedStatement pstmt = null; 
		
		try {
			cnx.setAutoCommit(false); //désactive le commit automatique, permet de ne pas valider le commit en cas d'erreur
			pstmt = cnx.prepareStatement(SQL_UPDATE); //préparation de la requête
			//ajout des paramètres nécessaires à la requête
			pstmt.setString(1, pObject.getPseudo());
			pstmt.setString(2, pObject.getNom());
			pstmt.setString(3, pObject.getPrenom());
			pstmt.setString(4, pObject.getEmail());
			pstmt.setString(5, pObject.getTelephone());
			pstmt.setString(6, pObject.getRue());
			pstmt.setString(7, pObject.getCodePostal());
			pstmt.setString(8, pObject.getVille());
			pstmt.setString(9, pObject.getMotDePasse());
			pstmt.setInt(10, pObject.getCredit());
			pstmt.setBoolean(11, pObject.isAdministrateur());
			pstmt.setString(12, pObject.getPseudo());
			
			cnx.commit();
		}catch (SQLException e) {
			try {
				cnx.rollback();
			} catch (SQLException e1) {
				throw new DALException("Problème sur la méthode update de l'utilisateur" + e1.getMessage()); 
			}
			
			throw new DALException("Problème sur la méthode update de l'utilisateur" + e.getMessage()); //en cas d'erreur message 
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx); //déconnexion de la BDD et fermeture du preparedStatement
		}
	}

	/**
	 * Modification d'un crédit de l'utilisateur qui fait la nouvelle enchère
	 * @param credit, noUtilisateur
	 * @throws DALException
	 */
	@Override
	public void updateCreditUser(int credit, int noUtilisateur) throws DALException {
		Connection cnx = ConnectionProvider.getConnection(); //récupération d'une connexion avec la BDD
		PreparedStatement pstmt = null; 
		
		try {
			pstmt = cnx.prepareStatement(SQL_UPDATE_CREDIT_NVOFRRE); //préparation de la requête
			
			pstmt.setInt(1, credit);
			pstmt.setInt(2, noUtilisateur);
			pstmt.executeUpdate();						

		}catch (SQLException e) {			
			throw new DALException("Problème sur la méthode updateCreditUser de l'utilisateur" + e.getMessage()); //en cas d'erreur
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture de la connexion et du preparedStatement
		}
	}
	
	/**
	 * Modification d'un crédit de l'utilisateur qui a fait une ancienne offre trop basse
	 * remboursement de cet utilisateur
	 * @param noArticle, montantEnchere
	 * @throws DALExeption
	 */
	@Override
	public void modifCredAncienUser(int noArticle, int montantEnchere) throws DALException {
		Connection cnx = ConnectionProvider.getConnection();  //obtention d'une connexion 
		PreparedStatement pstmt = null; 
		
		try {
			pstmt = cnx.prepareStatement(SQL_MODIF_CREDIT_OFFRE_BASSE); //préparation de la requête
			
			pstmt.setInt(1, noArticle);
			pstmt.setInt(2, montantEnchere);
			pstmt.setInt(3, montantEnchere);
			pstmt.setInt(4, noArticle);
			pstmt.setInt(5, montantEnchere);
			pstmt.executeUpdate();						

		}catch (SQLException e) {			
			throw new DALException("Problème sur la méthode modifCredAncienUser de l'utilisateur" + e.getMessage()); //en cas d'erreur
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture connextion et preparedStatement
		}
	}
	
	
	/**
	 * Suppression d'un user dans BDD 
	 * @param pObject de type Utilisateur
	 * @throws DALException
	 */
	@Override
	public void delete(Utilisateur pObject) throws DALException {
		Connection cnx = ConnectionProvider.getConnection();  //obtention de la connexion
		PreparedStatement pstmt = null; 
		try {
			pstmt=cnx.prepareStatement(SQL_DELETE); //préparation de la requête
			pstmt.setInt(1, pObject.getNoUtilisateur());
			pstmt.executeUpdate(); 
		}catch(SQLException e) {
			throw new DALException("Problème sur la méthode delete de l'utilisateur\n\n"+e.getMessage()); //en cas d'erreur
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture connexion et praparedStatement
		}

	}
	
//TODO vérifier si il faut la garder ou pas dans le temps ?	
	/**
	 * Selection de tout les users
	 * @throws DALException
	 * @return utilisateurs
	 */
	
	@Override
	public List<Utilisateur> selectAll() throws DALException {
//		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>(); 
//		Utilisateur user = null; 
//		Statement stmt = null; 
//		Connection cnx = ConnectionProvider.getConnection(); 
//		ResultSet rs=null; 
//		try {
//			stmt=cnx.createStatement(); 
//			rs=stmt.executeQuery(SQL_SELECT_ALL); 
//			while (rs.next()) {
//				user = new Utilisateur(rs.getInt("no_utilisateur"),
//						rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
//						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), 
//						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
//						rs.getInt("credit"), rs.getBoolean("administrateur"));						
//				utilisateurs.add(user); 
//			}
//		}catch (SQLException e) {
//			throw new DALException("Problème sur le méthode selectAll de l'utilisateur" + e.getMessage()); 
//		}finally {
//			ConnectionProvider.seDeconnecter(stmt, cnx);
//		}
//
//		return utilisateurs; 
		return null;
	}
	
	
	/**
	 * selection d'un user par son numero d'utilisateur
	 * @param pObject de type Utilisateur
	 * @throws DALException 
	 * @return utilisateur
	 */
	@Override
	public Utilisateur selectById(Utilisateur pObject) throws DALException {
		Utilisateur utilisateur = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection();  //obtention de la connexion 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_SELECT_BY_ID); //reparation de la requête
			pstmt.setInt(1, pObject.getNoUtilisateur());
			rs=pstmt.executeQuery(); 
			
			//instanciation de utilisateur avec les données récupérer lors de la requete. 
			//fait appel au constructeur de la classe utilisateur dans la couche BO
			if(rs.next()) {    
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),
						rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), 
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode selectById de l'utilisateur" + e.getMessage());  //en cas d'erreur
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);   //fermeture connexion et preparedStatement
		}			 
		
		return utilisateur;	
	}
	
	/**
	 * selection d'un utilisateur grace à son pseudo 	
	 * @param pseudo
	 * @throws DALException 
	 * @return utilisateur
	 */
	@Override
	public Utilisateur selectByPseudo(String pseudo) throws DALException {
		Utilisateur utilisateur = null;  
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); //obtention d'un connexion 
		ResultSet rs=null; 
		try {
			pstmt =cnx.prepareStatement(SQL_SELECT_BY_PSEUDO);  //préparation de la requete
			pstmt.setString(1, pseudo);
			rs=pstmt.executeQuery(); //exécution de la requete
			//instanciation d'un tuilisateur avec données récupérer 
			if(rs.next()){
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),
						rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), 
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));						
			}
			  
		}catch (SQLException e) {
			throw new DALException("Problème sur le méthode selectByPseudo de l'utilisateur" + e.getMessage()); // en cas d'erreur
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture de la connexion et du preparedStatement
		}
		return utilisateur; 
	} 
	
	
/**
 * selection d'un user grace à son email
 * @param email 
 * @throws DALException 
 * @return utilisateur 
 */
	@Override
	public Utilisateur selectByEmail(String email) throws DALException {
		Utilisateur utilisateur = null;  
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection();  //obtention d'un connexion 
		ResultSet rs=null; 
		try {
			pstmt =cnx.prepareStatement(SQL_SELECT_BY_EMAIL); //preparation de la requête
			pstmt.setString(1, email);
			rs=pstmt.executeQuery(); 
			//instanciation d'un nouvelle utilisateur avec les données récupérer
			if(rs.next()){
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),
						rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), 
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));						
			}
			  
		}catch (SQLException e) {
			throw new DALException("Problème sur le méthode selectByEmail de l'utilisateur" + e.getMessage()); //en cas d'erreur
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture connexion et preparedStatement
		}
		return utilisateur; 
	}


}
