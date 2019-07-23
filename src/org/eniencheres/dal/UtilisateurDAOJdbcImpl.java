package org.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eniencheres.bo.Utilisateur;

public class UtilisateurDAOJdbcImpl implements DAOUtilisateur{
		
	private static final String SQL_SELECT_ALL ="SELECT * FROM Utilisateurs;";
	private static final String SQL_SELECT_BY_PSEUDO="SELECT * FROM Utilisateurs WHERE  pseudo=?;";
	private static final String SQL_INSERT = "INSERT into Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal,"
			+ "ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?);";
	private static final String SQL_SELECT_BY_ID="Select no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, "
			+ "ville, mot_de_passe, credit, administrateur From Utilisateurs where no_utilisateur=?;"; 
	private static final String SQL_DELETE = "DELETE From Utilisateurs where no_utilisateur=?; "; 
	private static final String SQL_UPDATE="UPDATE Utilisateurs set pseudo=?, nom=?, prenom=?, email=?"
			+ "telephone=?, rue=?, code_postale=? ville=?,mot_de_passe =?, credit=?, administrateur=? where no_utilisateur=?;";
	
	
	@Override
	public void insert(Utilisateur pObject) throws DALException{
		PreparedStatement pstmt = null; 
		Connection cnx=ConnectionProvider.getConnection(); 
		try {
			pstmt = cnx.prepareStatement(SQL_INSERT,PreparedStatement.RETURN_GENERATED_KEYS); 
			pstmt.setString(1,pObject.getPseudo());
			pstmt.setString(2, pObject.getNom());
			pstmt.setString(3, pObject.getPrenom());
			pstmt.setString(4, pObject.getEmail());
			pstmt.setString(5, pObject.getTelephone());
			pstmt.setString(6, pObject.getRue());
			pstmt.setString(7, pObject.getCodePostal());
			pstmt.setString(8, pObject.getVille());
			pstmt.setString(9, pObject.getMotDePasse());
			pstmt.setInt(10, pObject.getCredit());
			pstmt.setBoolean(11, pObject.isAdministarteur());
			pstmt.executeUpdate(); 
			ResultSet rs=pstmt.getGeneratedKeys(); 
			if(rs.next()) {
				pObject.setNoUtilisateur(rs.getInt(1));
			}			
		}catch (SQLException e) {
			throw new DALException("Problème sur la méthode insert(pObject)", e); 
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx);
		}
	}
	
	@Override
	public void update(Utilisateur pObject) throws DALException {
		Connection cnx = ConnectionProvider.getConnection(); 
		PreparedStatement pstmt = null; 
		try {
			pstmt=cnx.prepareStatement(SQL_UPDATE); 
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
			pstmt.setBoolean(11, pObject.isAdministarteur());
			pstmt.executeUpdate(); 
		}catch (SQLException e) {
			throw new DALException("Problème sur la méthode update(pObject) de l'utilisateur", e); 
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx);
		}
	}
	@Override
	public void delete(Utilisateur pObject) throws DALException {
		Connection cnx = ConnectionProvider.getConnection(); 
		PreparedStatement pstmt = null; 
		try {
			pstmt=cnx.prepareStatement(SQL_DELETE); 
			pstmt.setInt(1, pObject.getNoUtilisateur());
			pstmt.executeUpdate(); 
		}catch(SQLException e) {
			throw new DALException("Problème sur la méthode delete de l'utilisateur ", e); 
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx);
		}

	}
	@Override
	public List<Utilisateur> selectAll() throws DALException {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>(); 
		Utilisateur user = null; 
		Statement stmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs=null; 
		try {
			stmt=cnx.createStatement(); 
			rs=stmt.executeQuery(SQL_SELECT_ALL); 
			while (rs.next()) {
				user = new Utilisateur(rs.getInt("noUtilisateur"),
						rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), 
						rs.getString("codePostale"), rs.getString("ville"), rs.getString("motDePasse"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));						
			}
			 utilisateurs.add(user); 
		}catch (SQLException e) {
			throw new DALException("Problème sur le méthode selectAll de l'utilisateur ", e); 
		}finally {
			try {
				stmt.close();
				cnx.close();
			} catch (SQLException e) {
				e.getMessage(); 
			}
		}
		return utilisateurs; 
	}
	@Override
	public Utilisateur selectById(Utilisateur pObject) throws DALException {
		Utilisateur utilisateur = null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_SELECT_BY_ID); 
			pstmt.setInt(1, pObject.getNoUtilisateur());
			rs=pstmt.executeQuery(); 
			
			if(rs.next()) {
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),
						rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), 
						rs.getString("code_postal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode selectById de l'utilisateur", e);
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return utilisateur;	
	}
		
		
	@Override
	public Utilisateur selectByPseudo(String pseudo) throws DALException {
		Utilisateur utilisateur = null;  
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs=null; 
		try {
			pstmt =cnx.prepareStatement(SQL_SELECT_BY_PSEUDO); 
			pstmt.setString(1, pseudo);
			rs=pstmt.executeQuery(); 

			if(rs.next()){
				utilisateur = new Utilisateur(rs.getInt("no_utilisateur"),
						rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), 
						rs.getString("codePostal"), rs.getString("ville"), rs.getString("mot_de_passe"),
						rs.getInt("credit"), rs.getBoolean("administrateur"));						
			}
			  
		}catch (SQLException e) {
			throw new DALException("Problème sur le méthode selectByPseudo de l'utilisateur ", e); 
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx);
		}
		return utilisateur; 
	} 



}
