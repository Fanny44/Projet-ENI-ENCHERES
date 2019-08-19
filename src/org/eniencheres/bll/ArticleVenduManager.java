package org.eniencheres.bll;

import java.util.List;

import org.eniencheres.bo.ArticleSelect;
import org.eniencheres.bo.ArticleVendu;
import org.eniencheres.bo.ListeEncheres;
import org.eniencheres.dal.DALException;
import org.eniencheres.dal.DAOArticleVendu;
import org.eniencheres.dal.DAOFactory;

/**
 * classe ArticleVenduManager
 * 
 * @author Fanny
 *
 */

public class ArticleVenduManager {

	// déclaration des variables
	private static DAOArticleVendu articleVenduDAO;
	private static ArticleVenduManager instance;

	public static ArticleVenduManager getInstance() {
		if (instance == null) {
			instance = new ArticleVenduManager();
		}
		return instance;
	}

	// constructeurs permettant l'instanciation de la DAO ArticleVendu
	private ArticleVenduManager() {

		articleVenduDAO = DAOFactory.getArticleVenduDAO();
	}

	/**
	 * @throws BLLException méthode permettant l'insertion d'un Article vendu en
	 *                      Base de donnée @return @throws
	 */
	public void insertArticleVendu(ArticleVendu article) throws BLLException {
		// les champs de saisies ne peuvent pas être vide donc à vérifier
		if (article.getNomArticle() == null) {
			throw new BLLException("Le nom de l'article ne peut pas être vide");
		} else if (article.getDescription() == null) {
			throw new BLLException("La description de l'article ne peut pas être vide");
		} else if (article.getNoCategorie() == 0) {
			throw new BLLException("La catégorie de l'article ne peut pas être vide");
		} else if (article.getMiseAPrix() == 0) {
			throw new BLLException("La mise à prix de l'article ne peut pas être vide");
		} else if (article.getDateDebutEncheres() == null) {
			throw new BLLException("La date de début d'enchère de l'article ne peut pas être vide");
		} else if (article.getMiseAPrix() == 0) {
			throw new BLLException("La mise à prix de l'article ne peut pas être vide");
		} else {
//avoir s'il ne manque pas 		
			try {
				articleVenduDAO.insert(article);
			} catch (DALException e) {
				throw new BLLException("Une erreur est survenue pendant l'insertion de l'article\n\n" + e.getMessage());
			}
		}
	}

	/**
	 * méthode permettant la récupération de la liste de tous les Articles vendu
	 * 
	 * @return ListeArticle
	 * @throws BLLException
	 */
	public List<ListeEncheres> getSelectArticles() throws BLLException {
		List<ListeEncheres> listeArticles = null;
		try {
			listeArticles = articleVenduDAO.selectArticles();

		} catch (DALException e) {
			throw new BLLException(
					"Erreur sur la récupération de la liste d'article sans paramètre\n\n" + e.getMessage());
		}
		return listeArticles;
	}

	/**
	 * méthode permettant la récupération de la liste de tous les Articles vendu
	 * entre date debut et date fin
	 * 
	 * @return ListeArticle
	 * @throws BLLException
	 */
	public List<ListeEncheres> getArticleListeEncheres() throws BLLException {
		List<ListeEncheres> listeEncheres = null;
		try {
			listeEncheres = articleVenduDAO.ArticleListeEncheres();

		} catch (DALException e) {
			throw new BLLException(
					"Erreur sur la récupération de la liste d'article entre date debut et date fin sans paramètre\n\n"
							+ e.getMessage());
		}
		return listeEncheres;
	}

	/**
	 * Méthode permettant la récupération de la liste des article vendu en fonction
	 * du nom et de la catégorie
	 * 
	 * @return ListeArticle
	 * @throws BLLException
	 */

	public List<ListeEncheres> getListeArticleFiltre(String nom, int categorie) throws BLLException {
		List<ListeEncheres> ListeArticle = null;
		try {
			ListeArticle = articleVenduDAO.ArticleListeEncheresNomCat(nom, categorie);
		} catch (DALException e) {
			throw new BLLException(
					"Erreur sur la récupération de la liste d'article vendu avec paramètre\n\n" + e.getMessage());
		}
		return ListeArticle;
	}

	/**
	 * Méthode permettant la récupération de la liste des article vendu en fonction
	 * du nom de l'article
	 * 
	 * @return ListeArticle
	 * @throws BLLException
	 */

	public List<ListeEncheres> getListeArticleFiltreNom(String nomArticle) throws BLLException {
		List<ListeEncheres> ListeArticle = null;
		try {
			ListeArticle = articleVenduDAO.ArticleListeEncheresNom(nomArticle);
		} catch (DALException e) {
			throw new BLLException(
					"Erreur sur la récupération de la liste d'article vendu avec paramètre\n\n" + e.getMessage());
		}
		return ListeArticle;
	}

	/**
	 * Méthode permettant la récupération de la liste des article vendu en fonction
	 * de la catégorie
	 * 
	 * @return ListeArticle
	 * @throws BLLException
	 */

	public List<ListeEncheres> getListeArticleFiltreCat(int categorie) throws BLLException {
		List<ListeEncheres> ListeArticle = null;
		try {
			if (categorie == -1) {
				ListeArticle = articleVenduDAO.selectArticles();
			} else {
				ListeArticle = articleVenduDAO.ArticleListeEncheresCat(categorie);
			}
		} catch (DALException e) {
			throw new BLLException(
					"Erreur sur la récupération de la liste d'article vendu avec paramètre\n\n" + e.getMessage());
		}
		return ListeArticle;
	}

	/**
	 * Méthode permettant la récupération de la liste des article vendu où l'user a
	 * fait au moins une offre
	 * 
	 * @return ListeArticle
	 * @throws BLLException
	 */

	public List<ListeEncheres> getEncheresFaite(int noUtilisateur) throws BLLException {
		List<ListeEncheres> ListeArticle = null;
		try {
			ListeArticle = articleVenduDAO.EncheresFaite(noUtilisateur);

		} catch (DALException e) {
			throw new BLLException("Erreur sur la récupération de la liste d'article vendu dont l'user à fait au moins une enchère\n\n"+ e.getMessage());
		}
		return ListeArticle;
	}

	/**
	 * Méthode permettant la récupération de la liste des article vendu par l'user
	 * 
	 * @return ListeArticle
	 * @throws BLLException
	 */

	public List<ListeEncheres> getMesVentesCours(int noUtilisateur) throws BLLException {
		List<ListeEncheres> ListeArticle = null;
		try {
			ListeArticle = articleVenduDAO.MesVentesCours(noUtilisateur);

		} catch (DALException e) {
			throw new BLLException(
					"Erreur sur la récupération de la liste d'article vendu par l'user\n\n" + e.getMessage());
		}
		return ListeArticle;
	}

	/**
	 * Méthode permettant la récupération de la liste des article vendu non commencé
	 * par l'user
	 * 
	 * @return ListeArticle
	 * @throws BLLException
	 */

	public List<ListeEncheres> getMesVentesNonCommences(int noUtilisateur) throws BLLException {
		List<ListeEncheres> ListeArticle = null;
		try {
			ListeArticle = articleVenduDAO.MesVentesNonCommences(noUtilisateur);

		} catch (DALException e) {
			throw new BLLException("Erreur sur la récupération de la liste d'article vendu vente non commencés par l'user\n\n"+ e.getMessage());
		}
		return ListeArticle;
	}

	/**
	 * Méthode permettant la récupération de la liste des article vendu terminé par
	 * l'user
	 * 
	 * @return ListeArticle
	 * @throws BLLException
	 */

	public List<ListeEncheres> getMesVentesTermines(int noUtilisateur) throws BLLException {
		List<ListeEncheres> ListeArticle = null;
		try {
			ListeArticle = articleVenduDAO.MesVentesTermines(noUtilisateur);

		} catch (DALException e) {
			throw new BLLException("Erreur sur la récupération de la liste d'article vendu vente terminés par l'user\n\n"+ e.getMessage());
		}
		return ListeArticle;
	}

	/**
	 * Méthode permettant la récupération de la liste des article dont l'utilisateur
	 * a remportes l'enchères
	 * 
	 * @return ListeArticle
	 * @throws BLLException
	 */

	public List<ListeEncheres> getMesEncheresRemportes(int noUtilisateur) throws BLLException {
		List<ListeEncheres> ListeArticle = null;
		try {
			ListeArticle = articleVenduDAO.MesEncheresRemportes(noUtilisateur);

		} catch (DALException e) {
			throw new BLLException(
					"Erreur sur la récupération de la liste d'article remportés par l'user\n\n" + e.getMessage());
		}
		return ListeArticle;
	}

	/**
	 * méthode permettant la récupération de l'Articles vendu
	 * 
	 * @return article
	 * @throws BLLException
	 */
	public ArticleSelect getSelectArticleById(int noArticle) throws BLLException {
		ArticleSelect article = null;
		try {
			article = articleVenduDAO.selectArticleById(noArticle);

		} catch (DALException e) {
			throw new BLLException("Erreur sur la récupération de la liste d'article sans paramètre\n\n" + e.getMessage());
		}
		return article;
	}

	public void updatePrixVente(int noArticle) throws BLLException {
		try {
			articleVenduDAO.update(noArticle);
		} catch (DALException e) {
			throw new BLLException(
					"Une erreur est survenue pendant l'update du prix de l'article\n\n"+e.getMessage());
		}
	}
}
