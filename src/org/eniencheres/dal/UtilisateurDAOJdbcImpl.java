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
	private static final String SQL_INSERT = "INSERT into Utilisateurs (pseudo, nom, prenom, email, telephone, rue, code_postal"
			+ "ville, mot_de_passe, credit, administrateur) values(?,?,?,?,?,?,?,?,?,?,?);";
	
	
	
	
	
	
	
	@Override
	public void insert(Utilisateur pObject) throws DALException, SQLException{
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
	public Utilisateur update(Utilisateur pObject) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean delete(Utilisateur pObject) throws DALException {
		// TODO Auto-generated method stub
		return false;
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
		// TODO Auto-generated method stub
		return null;
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

			if(rs!=null){
				utilisateur = new Utilisateur(rs.getInt("noUtilisateur"),
						rs.getString("pseudo"), rs.getString("nom"), rs.getString("prenom"),
						rs.getString("email"), rs.getString("telephone"), rs.getString("rue"), 
						rs.getString("codePostale"), rs.getString("ville"), rs.getString("motDePasse"),
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
