package org.eniencheres.dal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.eniencheres.bo.Categorie;

/**
 * CategorieDAOJdbcImpl implémente l'interface DAOCategorie
 * @author Fanny
 */
public class CategorieDAOJdbcImpl implements DAOCategorie {

	/**
	 * Constante de requête paramétré sql
	 */
		
	public static final String SQL_SELECT_CATEGORIE = "SELECT no_categorie, libelle FROM CATEGORIES;";

	/**
	 * méthode selectAll permet de sélectionner toutes les catégories
	 * @return categorie
	 * @throws DALException
	 */
	@Override
	public List<Categorie> selectAll() throws DALException {
		List<Categorie> categorie = new ArrayList<Categorie>();
		Categorie cat = null;
		Statement stmt = null;
		Connection cnx = ConnectionProvider.getConnection(); //obtention d'une connexion 
		ResultSet rs = null;
		try {
			stmt = cnx.createStatement(); //création de la requete
			rs = stmt.executeQuery(SQL_SELECT_CATEGORIE);  //exécution de la requete 
			while (rs.next()) {
				cat = new Categorie();
				cat.setNoCategorie(rs.getInt("no_categorie"));
				cat.setLibelle(rs.getString("libelle"));
				
				categorie.add(cat); //ajout des objets obtenur dans la liste
			}
		} catch (SQLException e) {
			throw new DALException("Probleme sur la méthode de selectAll de catégorie" + e.getMessage()); //en cas d'erreur
		} finally {
			ConnectionProvider.seDeconnecter(stmt, cnx); //fermeture de la connexion et du stmt
		}
		return categorie;
	}
	
	
//méthode non utilisées
	
	@Override
	public void insert(Categorie pObject) throws DALException {

	}

	@Override
	public void update(Categorie pObject) throws DALException {

	}

	@Override
	public void delete(Categorie pObject) throws DALException {

	}


	@Override
	public Categorie selectById(Categorie pObject) throws DALException {
		return null;
	}

	
}
