package org.eniencheres.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bll.ArticleVenduManager;
import org.eniencheres.bll.BLLException;
import org.eniencheres.bll.CategorieManager;
import org.eniencheres.bo.ArticleSelect;
import org.eniencheres.bo.ArticleVendu;
import org.eniencheres.bo.Categorie;
import org.eniencheres.bo.ContratUrl;

/**
 * Servlet implementation class ServletDetailsVente
 */
@WebServlet("/ServletDetailsVente")
public class ServletDetailsVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int articleV =Integer.parseInt(request.getParameter("numArticle"));
		System.out.println(articleV);
		if(request.getParameter("nomArticle")!=null) {
			ArticleSelect article = new ArticleSelect();
			ArticleVenduManager avm = ArticleVenduManager.getInstance();
			
			try {
				article=avm.getSelectArticleById(articleV);
			}catch (BLLException e) {
				e.printStackTrace();
			}
			request.setAttribute("article", article);
		}
		request.getRequestDispatcher(ContratUrl.URL_DETAILS_VENTE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String prop = request.getParameter("proposition");
		System.out.println(prop);
		
		
	
	
	
	}

}
