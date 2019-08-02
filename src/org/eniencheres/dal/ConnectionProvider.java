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
 * Cr�ation de la classe Connection Provider
 * va permettre de nous connecter � la base de donn�e
 * @author Fanny
 *
 */
public class ConnectionProvider {
	private static DataSource dataSource; 
	
	static {
		Context context = null; 
		
		try {
			context = new InitialContext(); 
			//Recherche de la dataSource
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/pool_cnx");
		}catch(NamingException e) {
			throw new RuntimeException("Impossible d'accéder à la base de donnée\n\n"+e.getMessage()); 
		}
	}
	
	/**
	 * m�thode retournant une connection op�rationnelle issue du pool de connexion vers la BD 
	 * @return cnx
	 * @throws SQLException
	 */
	public static Connection getConnection() throws DALException{
		Connection cnx = null; 
		
		//Demande d'une connection
		try {
			cnx = ConnectionProvider.dataSource.getConnection(); 
		}catch(SQLException e) {
			throw new DALException("impossible d'obtenir la connexion\n\n"+e.getMessage()); 
		}
		return cnx; 
	}
	
	/**
	 * m�thode permettant de se d�connecter de la bdd
	 * @param(Statement stmt, Connection cnx)
	 */
	public static void seDeconnecter (PreparedStatement pstmt, Connection cnx)throws DALException{
		//fermeture du prepareStatement
		try {
			pstmt.close();
		}catch(SQLException e) {
			throw new DALException("La fermeture de PrepareStatement n'a pas pu se faire correctement\n\n"+e.getMessage()); 
		}
		//fermeture de la connection
		try {
			cnx.close();
		}catch(SQLException e) {
			throw new DALException("La fermeture de la connection à la BD ne s'est pas faite correctement\n\n"+e.getMessage()); 
		}
	}
	
	/**
	 * m�thode permettant de se d�connecter de la bdd 
	 * @param(Statement stmt, Connection cnx)
	 */
	public static void seDeconnecter (Statement stmt, Connection cnx)throws DALException{
		//fermeture du prepareStatement
		try {
			stmt.close();
		}catch(SQLException e) {
			throw new DALException("La fermeture de Statement n'a pas pu se faire correctement\n\n"+e.getMessage()); 
		}
		//fermeture de la connection
		try {
			cnx.close();
		}catch(SQLException e) {
			throw new DALException("La fermeture de la connection à la BD ne s'est pas faite correctement\n\n"+e.getMessage()); 
		}
	}
}
