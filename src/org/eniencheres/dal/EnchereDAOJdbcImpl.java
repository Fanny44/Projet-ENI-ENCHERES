package org.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.eniencheres.bo.ArticleSelect;
import org.eniencheres.bo.Enchere;
import org.eniencheres.bo.Utilisateur;

public class EnchereDAOJdbcImpl implements DAOEnchere{
	private static final String SQL_INSERT_ENCHERE="insert into ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values (?,?,?,?);";
	private static final String SQL_PSEUDO_ENCHERE=" select pseudo from utilisateurs where no_utilisateur=(select no_utilisateur from encheres where montant_enchere=? and no_article=?);";
	

/**
 * méthode permettant l'insertion d'une enchère dans la table enchère
 */
	@Override
	public void insertEnchere(Enchere pObject) throws DALException {
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
			throw new DALException("problème sur la méthode insertEnchere d'enchere\n\n"+e.getMessage());
		
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx);}		
	}
		
	/**
	 * selection d'un article par son numero d'article
	 */
	@Override
	public Utilisateur selectPseudo(int montantEnchere, int noArticle) throws DALException {
		Utilisateur user=null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_PSEUDO_ENCHERE); 
			pstmt.setInt(1, montantEnchere);
			pstmt.setInt(2, noArticle);
			rs=pstmt.executeQuery(); 
			
			if(rs.next()) {
				user = new Utilisateur(rs.getString("pseudo"));			
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode selectPseudo dans enchereDAO"+e.getMessage());
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx);
		}			 
		
		return user;	
	}	

	@Override
	public void update(Enchere pObject) throws DALException {
		// TODO A Coder
		
	}

	@Override
	public void delete(Enchere pObject) throws DALException {
		// TODO A Coder
		
	}

	@Override
	public List<Enchere> selectAll() throws DALException {
		// TODO A Coder
		return null;
	}

	@Override
	public Enchere selectById(Enchere pObject) throws DALException {
		// TODO A Coder
		return null;
	}



	@Override
	public void insert(Enchere pObject) throws DALException {
		// TODO A Coder
		
	}





}
