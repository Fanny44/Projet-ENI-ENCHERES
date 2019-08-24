package org.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eniencheres.bo.Retrait;

/**
 * RetraitDAOJdbcImpl implémente l'interface DAORetrait
 *
 */
public class RetraitDAOJdbcImpl implements DAORetrait {

	/**
	 * Constante de requête paramétré sql
	 */
	private static final String SQL_INSERT_INTO_RETRAIT = "INSERT INTO RETRAITS(rue, code_postal, ville) values (?,?,?);";
	private static final String SQL_SELECT_ALL_RETRAIT = "SELECT no_retrait, rue, code_postal, ville from RETRAITS;";

	

/**
 * insertion d'un retrait 
 * @param pObjetc de type Retrait 
 * @throws DALException 	
 */
	@Override
	public void insert(Retrait pObject) throws DALException {
		Connection cnx = ConnectionProvider.getConnection(); //obtention d'une connexion 
		PreparedStatement pstmt = null;
		ResultSet rs=null;

		try {
			pstmt = cnx.prepareStatement(SQL_INSERT_INTO_RETRAIT, Statement.RETURN_GENERATED_KEYS); //préparation de la requête et retourne l'id générer
			pstmt.setString(1, pObject.getRue());
			pstmt.setString(2, pObject.getCodePostal());
			pstmt.setString(3, pObject.getVille());
			int nbRows = pstmt.executeUpdate();
			
			if (nbRows == 1) {
				rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					pObject.setNoRetrait(rs.getInt(1)); //association de cette id récupérer à cette objet
				}
			}			

		} catch (SQLException e) {
			throw new DALException("Problème sur la méthode de insert retrait" + e.getMessage()); //en cas d'erreur
		} finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture de connexion et de pstmt
		}
	}
	
//TODO pourquoi ne pas utiliser la générique ??? 	
	/**
	 * Sélection de tous les Retraits
	 * @return retraits
	 * @throws DALEXception 
	 */
		@Override
		public List<Retrait> selectRetraits() throws DALException {
			List<Retrait> retraits = new ArrayList<Retrait>();
			Retrait retrait = null; 
			Statement stmt = null; 
			Connection cnx=ConnectionProvider.getConnection();  //obtention d'une connexion 
			ResultSet rs=null; 
			try {
				stmt=cnx.createStatement(); 
				rs=stmt.executeQuery(SQL_SELECT_ALL_RETRAIT);  //préparation de la requête
				while(rs.next()) {
					retrait = new Retrait ();
					retrait.setNoRetrait(rs.getInt("no_retrait"));
					retrait.setRue(rs.getString("rue"));
					retrait.setCodePostal(rs.getString("code_postal"));
					retrait.setVille(rs.getString("ville"));

					retraits.add(retrait);  //ajout des retraits instancier à la liste
				}
			}catch (SQLException e) {
				throw new DALException("Probleme sur la méthode de selectRetraits de retrait" + e.getMessage());  //en cas d'erreur
			}finally {
				ConnectionProvider.seDeconnecter(stmt, cnx); //fermeture de la connexion et du statement
			}
			return retraits; 
		}

		
//méthode non utiliser		
	@Override
	public void update(Retrait pObject) throws DALException {
		// TODO A coder

	}

	@Override
	public void delete(Retrait pObject) throws DALException {
		// TODO A coder

	}

	@Override
	public List<Retrait> selectAll() throws DALException {
		// TODO A coder
		return null;
	}

	@Override
	public Retrait selectById(Retrait pObject) throws DALException {
		// TODO A coder
		return null;
	}

}
