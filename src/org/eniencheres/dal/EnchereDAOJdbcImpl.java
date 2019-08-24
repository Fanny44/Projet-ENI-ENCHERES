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
/**
 * EnchereDAOJdbcImpl implémente l'interface DAOEnchere
 * @author Fanny
 *
 */
public class EnchereDAOJdbcImpl implements DAOEnchere{
	
	/**
	 * Constante de requête paramétré sql
	 */
	
	private static final String SQL_INSERT_ENCHERE="insert into ENCHERES(date_enchere, montant_enchere, no_article, no_utilisateur) values (?,?,?,?);";
	private static final String SQL_PSEUDO_ENCHERE=" select pseudo from utilisateurs where no_utilisateur=(select no_utilisateur from encheres where montant_enchere=? and no_article=?);";
	

/**
 * méthode permettant l'insertion d'une enchère dans la table enchère
 * @param pObject de type Enchere
 * @throws DALException 
 */
	@Override
	public void insertEnchere(Enchere pObject) throws DALException {
		Connection cnx = ConnectionProvider.getConnection(); //obtention d'un connexion 
		PreparedStatement pstmt = null;
		ResultSet rs=null;
		try {
			pstmt = cnx.prepareStatement(SQL_INSERT_ENCHERE, Statement.RETURN_GENERATED_KEYS); //préparation de la requête et récupération de l'id qui sera générer lors de l'insertion 
			pstmt.setDate(1, new java.sql.Date(pObject.getDateEnchere().getTime()));
			pstmt.setInt(2, pObject.getMontantEnchere());
			pstmt.setInt(3, pObject.getNoArticle()); 
			pstmt.setInt(4,pObject.getNoUtilisateur());
			int nbRows = pstmt.executeUpdate(); 
			
			if (nbRows == 1) {
				rs = pstmt.getGeneratedKeys();
				if(rs.next()) {
					pObject.setNoEnchere(rs.getInt(1)); //association de l'id récupérer au noEnchere de cet objet passé en paramètre 
				}
			}
			
		} catch (SQLException e) {
			throw new DALException("problème sur la méthode insertEnchere d'enchere" + e.getMessage()); // en cas d'erreur message
		
		}finally {
			ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture de la connexion et du preparedStatement 
			}		
	}

//TODO pourquoi ici et pas dans UtilisateurDAOJDBCImpl ? 	
	/**
	 * selection l'utilisateur ayant fait l'enchere avec le montant passé en paramètre et sur l'article passé en paramètre
	 * @param montantEnchere, noArticle
	 * @throws DALException 
	 * @return user
	 */
	@Override
	public Utilisateur selectPseudo(int montantEnchere, int noArticle) throws DALException {
		Utilisateur user=null; 
		PreparedStatement pstmt = null; 
		Connection cnx = ConnectionProvider.getConnection(); //obtention d'une connexion 
		ResultSet rs= null; 
		
		try {
			pstmt=cnx.prepareStatement(SQL_PSEUDO_ENCHERE); //préparation de la requête
			pstmt.setInt(1, montantEnchere);
			pstmt.setInt(2, noArticle);
			rs=pstmt.executeQuery(); 
			//instanciation d'un object grâce au constructeur de Utilisateur user avec donnée récupérer 
			if(rs.next()) {
				user = new Utilisateur(rs.getString("pseudo"));			
			}
		}catch (SQLException e) {
				throw new DALException("Problème sur la méthode selectPseudo dans enchereDAO" + e.getMessage()); //en cas d'erreur
		}finally {
				ConnectionProvider.seDeconnecter(pstmt, cnx); //fermeture de la connexion et de pstmt
		}			 
		
		return user;	
	}	

	
	//méthode non utiliser 
	
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
