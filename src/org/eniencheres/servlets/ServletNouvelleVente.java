package org.eniencheres.servlets;
/**
 * DAO Factory
 * @author Stéphane Thomarat
 * @since Créé le 26/07/2019
 */
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bll.ArticleVenduManager;
import org.eniencheres.bll.BLLException;
import org.eniencheres.bll.CategorieManager;
import org.eniencheres.bll.RetraitManager;
import org.eniencheres.bo.ArticleVendu;
import org.eniencheres.bo.Categorie;
import org.eniencheres.bo.ContratUrl;
import org.eniencheres.bo.Retrait;
import org.eniencheres.bo.Utilisateur;
	


	/**
	 * Servlet implementation class ServletNouvelleVente
	 */
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("connecter") != null && (boolean)request.getSession().getAttribute("connecter")) {
			CategorieManager categorieManager = CategorieManager.getInstance();
			
			try {
				List<Categorie> categories = categorieManager.getCategorie();
				request.setAttribute("categories", categories);
			// lien d' affichage jsp
			request.getRequestDispatcher(ContratUrl.URL_NOUVELLE_VENTE).forward(request, response);
			} catch (BLLException e) {
				
				e.printStackTrace();
			}
		}else {
			request.getRequestDispatcher(ContratUrl.URL_CONNEXION).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//récupération des paramètres de l'article dans le formulaire
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		String categorieArticle = request.getParameter("categorieArticle");
		//String photoArticle = request.getParameter("photoArticle");
		String miseAPrix = request.getParameter("miseAPrix");
		String dateDebutEnchere = request.getParameter("dateDebutEnchere");
		String dateFinEnchere = request.getParameter("dateFinEnchere");
		
		//récupération de l'adresse dans le formulaire
		String rue = request.getParameter("rue");
		String codepostal = request.getParameter("codepostal");
		String ville = request.getParameter("ville");
		
		
		// instanciation d'un articlevendu
		Retrait retrait = new Retrait(); 
		RetraitManager rm = RetraitManager.getInstance();
		//insertion de  l'adresse dans table retrait
		retrait.setRue(rue);
		retrait.setCodePostal(codepostal);
		retrait.setVille(ville);
		try {
			retrait = rm.insert(retrait);
		} catch (BLLException e2) {
			e2.printStackTrace();
		}
				
		//instanciation d'un articlevendumanager
		ArticleVenduManager avm = ArticleVenduManager.getInstance();
		
		// récupération du numéro de l'utilisateur en session
		Utilisateur uTemp = (Utilisateur) request.getSession().getAttribute("utilisateur");
		int le = uTemp.getNoUtilisateur();
		
		// instanciation d'un articlevendu
		ArticleVendu articleVendu = new ArticleVendu();
		
		//definition d'une variable pour parser mise à prix
		int map = Integer.parseInt(miseAPrix);
		
		//mise des dates au bon format
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dde = null;
		try {
			dde = sdf.parse(dateDebutEnchere);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		Date ddf = null;
		try {
			ddf = sdf.parse(dateFinEnchere);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}	

		

		//insertion de  l'adresse dans table articlevendu
		articleVendu.setNomArticle(article);
		articleVendu.setDescription(description);
		articleVendu.setDateDebutEncheres(dde);
		articleVendu.setDateFinEncheres(ddf);
		articleVendu.setMiseAPrix(map);
		articleVendu.setPrixVente(map);
		articleVendu.setNoUtilisateur(le);
		articleVendu.setNoCategorie(Integer.parseInt(categorieArticle));
		articleVendu.setNoRetrait(retrait.getNoRetrait());

		//  insertion articleVendu
		try {
			avm.insertArticleVendu(articleVendu);
			response.sendRedirect("Accueil");
		} catch (BLLException e) {
			e.printStackTrace();
			request.setAttribute("messageErreur", e.getMessage());
			request.getRequestDispatcher(ContratUrl.URL_NOUVELLE_VENTE).forward(request, response);;
		}
		
	
		

	
		
		
}}
