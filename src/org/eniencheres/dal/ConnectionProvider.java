package org.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Création de la classe Connection Provider
 * va permettre de nous connecter à la base de donnée
 * @author Fanny
 *
 */
public class ConnectionProvider {

	//TODO revoir cette partie pour bien l'expliquer 
	private static DataSource dataSource; 
	
	static {
		Context context = null; 
		
		try {
			context = new InitialContext(); 
			//Recherche de la dataSource
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
		}catch(NamingException e) {
			throw new RuntimeException("Impossible d'accéder à la base de donnée" + e.getMessage()); 
		}
	}
	
	/**
	 * méthode retournant une connection opérationnelle issue du pool de connexion vers la BD 
	 * @return cnx
	 * @throws DALException
	 */
	public static Connection getConnection() throws DALException{
		Connection cnx = null; 
		
		//Demande d'une connection
		try {
			cnx = ConnectionProvider.dataSource.getConnection(); 
		}catch(SQLException e) {
			throw new DALException("impossible d'obtenir la connexion" + e.getMessage()); 
		}
		return cnx; 
	}
	
	/**
	 * méthode permettant de se déconnecter de la bdd
	 * @param pstmt, cnx
	 * @throws DALException
	 */
	public static void seDeconnecter (PreparedStatement pstmt, Connection cnx)throws DALException{
		//fermeture du prepareStatement
		try {
			pstmt.close();
		}catch(SQLException e) {
			throw new DALException("La fermeture de PrepareStatement n'a pas pu se faire correctement" + e.getMessage()); 
		}
		//fermeture de la connection
		try {
			cnx.close();
		}catch(SQLException e) {
			throw new DALException("La fermeture de la connection à la BD ne s'est pas faite correctement" + e.getMessage()); 
		}
	}
	
	/**
	 * méthode permettant de se déconnecter de la bdd 
	 * @param stmt, cnx
	 * @throws DALException
	 */
	public static void seDeconnecter (Statement stmt, Connection cnx)throws DALException{
		//fermeture du prepareStatement
		try {
			stmt.close();
		}catch(SQLException e) {
			throw new DALException("La fermeture de Statement n'a pas pu se faire correctement" + e.getMessage()); 
		}
		//fermeture de la connection
		try {
			cnx.close();
		}catch(SQLException e) {
			throw new DALException("La fermeture de la connection à la BD ne s'est pas faite correctement" + e.getMessage()); 
		}
	}
}
