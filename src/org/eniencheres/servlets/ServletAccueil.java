package org.eniencheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.eniencheres.bll.ArticleVenduManager;
import org.eniencheres.bll.BLLException;
import org.eniencheres.bo.ContratUrl;
import org.eniencheres.bo.ListeEncheres;


/**
 * Servlet implementation class ServletAccueil
 */
public class ServletAccueil extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVenduManager avm = ArticleVenduManager.getInstance(); 
		
		try {
			List<ListeEncheres> listeEncheres = avm.getArticleListeEncheres();
			request.setAttribute("listeEncheres", listeEncheres);
			
		} catch (BLLException e) {
			e.printStackTrace();
			request.setAttribute("messageErreur", e);
		} 

		//Initialisation des variables de session
		if (request.getSession().getAttribute("connecter") == null) {
			request.getSession().setAttribute("connecter", false); //Un utilisateur est-il connecté (true, false)
		}
		if(request.getSession().getAttribute("utilisateur") == null) {
			request.getSession().setAttribute("utilisateur", null); //Utilisateur connecté de type (Utilisateur) si connecté sinon null
		}
		
		request.getRequestDispatcher(ContratUrl.URL_ACCUEIL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("null")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVenduManager avm = ArticleVenduManager.getInstance(); 
		List<ListeEncheres> le= null;
		String recherche = request.getParameter("recherche"); 
		String categorie = request.getParameter("categorie");
		try {
			if(recherche!=null || !recherche.isEmpty()) {
				le = avm.getListeArticleFiltreNom(recherche);
			} else if(categorie!="Toutes") {
				le=avm.getListeArticleFiltreCat(categorie);
			}else{
				le= avm.getListeArticleFiltre(recherche, categorie);
			}
		}catch(BLLException e) {
			e.printStackTrace();
			request.setAttribute("messageErreur", e);
		}

		for (ListeEncheres listeEncheres : le) {
			System.out.println(listeEncheres);
		}
		
		request.setAttribute("listeEncheres", le);
		request.getRequestDispatcher(ContratUrl.URL_ACCUEIL).forward(request, response);
	}
		
}
