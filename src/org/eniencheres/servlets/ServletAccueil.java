package org.eniencheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bll.ArticleVenduManager;
import org.eniencheres.bll.BLLException;
import org.eniencheres.bll.CategorieManager;
import org.eniencheres.bo.Categorie;
import org.eniencheres.bo.ContratUrl;
import org.eniencheres.bo.ListeEncheres;
import org.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletAccueil
 */
public class ServletAccueil extends HttpServlet {
	/**
	 * pour la sérialisation
	 */
	
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVenduManager avm = ArticleVenduManager.getInstance();

		//affichage des catégories
		listeCat(request);
		
		try {
			List<ListeEncheres> listeArticles = avm.getSelectArticles(); //pour l'affichage de la liste de tous les articles
			request.setAttribute("listeArticles", listeArticles);

		} catch (BLLException e) {
			e.printStackTrace();
			request.setAttribute("messageErreur", e);
		}

		// Initialisation des variables de session
		if (request.getSession().getAttribute("connecter") == null) {
			request.getSession().setAttribute("connecter", false); // Un utilisateur est-il connecté (true, false)
		}
		if (request.getSession().getAttribute("utilisateur") == null) {
			request.getSession().setAttribute("utilisateur", null); // Utilisateur connecté de type (Utilisateur) si
																	// connecté sinon null
		}

		request.getRequestDispatcher(ContratUrl.URL_ACCUEIL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //pour l'encodage
		
		ArticleVenduManager avm = ArticleVenduManager.getInstance();
		List<ListeEncheres> le = null;
		//récupération des champs
		String recherche = request.getParameter("recherche");
		String categorie = request.getParameter("categorie");
		String achat = request.getParameter("achat");
		listeCat(request);
		
		//récupération des listes d'articles en fonction des filtres fait par l'user (par nom, catégorie ou les deux)
		try {
			if (!recherche.isEmpty()) {
				le = avm.getListeArticleFiltreNom(recherche);
			} else if (!categorie.isEmpty()) {
				int cat = Integer.parseInt(categorie);  //car request.getParameter renvoie un string
				le = avm.getListeArticleFiltreCat(cat);
			} else {
				int cat = Integer.parseInt(categorie);
				le = avm.getListeArticleFiltre(recherche, cat);
			}
		} catch (BLLException e) {
			e.printStackTrace();
			request.setAttribute("messageErreur", e);
		}

		//en mode connecter, l'user a à disposition 6 autres filtres
		if ((boolean) request.getSession().getAttribute("connecter") == true) {
			try {
				Utilisateur uTemp = (Utilisateur) request.getSession().getAttribute("utilisateur");
				if (achat != null) {
					switch (achat) {
					case "enOu":
						le = avm.getArticleListeEncheres();
						break;
					case "mesEn":
						le = avm.getEncheresFaite(uTemp.getNoUtilisateur());
						break;
				case "mesEnRem":
					le=avm.getMesEncheresRemportes(uTemp.getNoUtilisateur());
					break;
					case "venteCours":
						le = avm.getMesVentesCours(uTemp.getNoUtilisateur());
						break;
					case "venteNnDebut":
						le = avm.getMesVentesNonCommences(uTemp.getNoUtilisateur());
						break;
					case "venteTerm":
						le = avm.getMesVentesTermines(uTemp.getNoUtilisateur());
						break;

					}
				}
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("messageErreur", e);
			}

		}

		request.setAttribute("listeArticles", le); //renvoie de la liste d'article en fonction des filtres
		request.getRequestDispatcher(ContratUrl.URL_ACCUEIL).forward(request, response);
	}

	/**
	 * méthode permettant l'affichage de la liste des catégories
	 * 
	 * @param request
	 */
	private void listeCat(HttpServletRequest request) {
		CategorieManager cm = CategorieManager.getInstance();
		try {
			List<Categorie> listeCat = cm.getCategorie();
			request.setAttribute("listeCat", listeCat);
		} catch (BLLException e) {
			e.printStackTrace();
			request.setAttribute("messageErreur", e);
		}

	}

}
