package org.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eniencheres.bo.Retrait;

public class RetraitDAOJdbcImpl implements DAO<Retrait> {

	private static final String SQL_INSERT_INTO_RETRAIT = "INSERT INTO RETRAITS(rue, code_postal, ville) values (?,?,?);";
	private static final String SQL_SELECT_ALL_RETRAIT = "SELECT no_retrait, rue, code_postal, ville from RETRAITS;";

	@Override
	public void insert(Retrait pObject) throws DALException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs=null;

		try {
			pstmt = cnx.prepareStatement(SQL_INSERT_INTO_RETRAIT, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, pObject.getRue());
			pstmt.setString(2, pObject.getCodePostal());
			pstmt.setString(3, pObject.getVille());
			int nbRows = pstmt.executeUpdate();
			
			if (nbRows == 1) {
				rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					pObject.setNoRetrait(rs.getInt(1));
				}
			}
			
			

		} catch (SQLException e) {
			throw new DALException("Probleme sur la méthode de insert retrait", e);
		} finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx);
		}
	}
	/**
	 * Sélection de tous les Retraits
	 */
		@Override
		public List<Retrait> selectRetraits() throws DALException {
			List<Retrait> retraits = new ArrayList<Retrait>();
			Retrait retrait = null; 
			Statement stmt = null; 
			Connection cnx=ConnectionProvider.getConnection(); 
			ResultSet rs=null; 
			try {
				stmt=cnx.createStatement(); 
				rs=stmt.executeQuery(SQL_SELECT_ALL_RETRAIT); 
				while(rs.next()) {
					retrait = new Retrait ();
					retrait.setNoRetrait(rs.getInt("no_retrait"));
					retrait.setRue(rs.getString("rue"));
					retrait.setCodePostal(rs.getString("code_postal"));
					retrait.setVille(rs.getString("ville"));

					retraits.add(retrait); 
				}
			}catch (SQLException e) {
				throw new DALException("Probleme sur la méthode de selectAll de articleVendu", e); 
			}finally {
				ConnectionProvider.seDeconnecter(stmt, cnx);
			}
			return retraits; 
		}

	@Override
	public void update(Retrait pObject) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Retrait pObject) throws DALException {
		// TODO Auto-generated method stub

	}

	@Override
	public List selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Retrait selectById(Retrait pObject) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
