package org.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.eniencheres.bo.Enchere;

public class EnchereDAOJdbcImpl implements DAO<Enchere>{
	private static final String SQL_INSERT_ENCHERE="insert into ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values (?,?,?,?);";


	@Override
	public void insert(Enchere pObject) throws DALException {
		Connection cnx = ConnectionProvider.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			pstmt = cnx.prepareStatement(SQL_INSERT_ENCHERE, Statement.RETURN_GENERATED_KEYS);
			pstmt.setDate(1, new java.sql.Date(pObject.getDateEnchere().getTime()));
			pstmt.setInt(2, pObject.getMontantEnchere());
			pstmt.setInt(3, pObject.getNoArticle()); 
			pstmt.setInt(4,pObject.getNoUtilisateur());
			int nbRows = pstmt.executeUpdate();
			
			if (nbRows == 1) {
				rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					pObject.setNoEnchere(rs.getInt(1));
				}
			}
			
		} catch (SQLException e) {
			throw new DALException("problème sur la méthode INSERT d'articles"+e.getMessage());
		
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx);}		
	}
		
	

	@Override
	public void update(Enchere pObject) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Enchere pObject) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Enchere> selectAll() throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Enchere selectById(Enchere pObject) throws DALException {
		// TODO Auto-generated method stub
		return null;
	}

}
