package org.eniencheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			request.getSession().setAttribute("listeEncheres", listeEncheres);
			
		} catch (BLLException e) {
			e.printStackTrace();
			request.setAttribute("messageErreur", e);
		} 
		
		request.getSession().setAttribute("connecter", false); //Un utilisateur est-il connecté (true, false)
		request.getSession().setAttribute("utilisateur", null); //Utilisateur connecté de type (Utilisateur) si connecté sinon null
		
		request.getRequestDispatcher(ContratUrl.URL_ACCUEIL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArticleVenduManager avm = ArticleVenduManager.getInstance(); 
	}
		
}
