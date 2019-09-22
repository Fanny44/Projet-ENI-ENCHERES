package org.eniencheres.servlets;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bll.ArticleVenduManager;
import org.eniencheres.bll.BLLException;
import org.eniencheres.bo.ArticleSelect;
import org.eniencheres.bo.ContratUrl;

/**
 * Servlet implementation class ServletModificationVente
 */
public class ServletModificationVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RequestDispatcher rd = request.getRequestDispatcher(ContratUrl.URL_MODIFICATION_VENTE);
		rd.forward(request, response);
		
				
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		ArticleSelect article = new ArticleSelect();
		article.setNoArticle(Integer.parseInt(request.getParameter("numArticle")));
		article.setNomArticle(request.getParameter("nomArticle"));
		article.setDescription(request.getParameter("description"));
		article.setLibelle(request.getParameter("categorieArticle"));
		article.setMiseAPrix(Integer.parseInt(request.getParameter("miseAPrix")));
		try {
			article.setDebutEnchere((Date) sdf.parse(request.getParameter("debutEnchere")));
			article.setFinEnchere((Date) sdf.parse(request.getParameter("finEnchere")));
		} catch (ParseException e) {
			e.printStackTrace();
			request.setAttribute("messageErreur", e.getMessage());
		}	
	
		try {
			ArticleVenduManager avm = ArticleVenduManager.getInstance();
			avm.getUpdateArticle(article);
			response.sendRedirect(request.getContextPath()+"/Accueil");
		}catch (BLLException e) {
			e.printStackTrace();
			request.setAttribute("messageErreur", e.getMessage());
		}
		
		
	//TODO finir l'update de l'article, bll et servlet
		
	}

}
