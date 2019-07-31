package org.eniencheres.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eniencheres.bo.Categorie;

import sun.font.CreatedFontTracker;


public class CategorieDAOJdbcImpl implements DAOCategorie{
	
	public static final String SQL_SELECT_CATEGORIE="SELECT no_categorie, libelle FROM CATEGORIES;";
	private static final String SQL_SELECT_NO_CATEGORIE="SELECT NO_CATEGORIE FROM CATEGORIES where libelle='VêTEMENTS';";

	@Override
	public void insert(Categorie pObject) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Categorie pObject) throws DALException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Categorie pObject) throws DALException {
		// TODO Auto-generated method stub
		
	}
	/**
	 * méthode selectAll implementant la DAOCategorie permet de sélectionner tous les articles 
	 * @return categorie 
	 */	
	@Override
	public List<Categorie> selectAll() throws DALException {
		List<Categorie> categorie = new ArrayList<Categorie>();
		Categorie cat = null; 
		Statement stmt = null; 
		Connection cnx=ConnectionProvider.getConnection(); 
		ResultSet rs = null; 
		try {
			stmt=cnx.createStatement(); 
			rs=stmt.executeQuery(SQL_SELECT_CATEGORIE); 
			while(rs.next()) {
				cat = new Categorie();
				cat.setNoCategorie(rs.getInt("no_categorie"));
				cat.setLibelle(rs.getString("libelle"));
				
		categorie.add(cat); 
			}
		}catch (SQLException e) {
			throw new DALException("Probleme sur la méthode de selectAll de articleVendu", e); 
		}finally {
			ConnectionProvider.seDeconnecter(stmt, cnx);
		}
		return categorie; 
	}
	

	@Override
	public Categorie selectById(Categorie pObject) throws DALException {
		Connection cnx = ConnectionProvider.getConnection();
		Statement stmt = null;
		Categorie categorie = new Categorie();
		
		try {
			stmt = cnx.createStatement();
			stmt.executeQuery(SQL_SELECT_NO_CATEGORIE);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return categorie;
	}
	
	

}
