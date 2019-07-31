package org.eniencheres.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bll.ArticleVenduManager;
import org.eniencheres.bll.BLLException;
import org.eniencheres.bll.CategorieManager;
import org.eniencheres.bo.ArticleVendu;
import org.eniencheres.bo.Categorie;
import org.eniencheres.bo.ContratUrl;
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
		CategorieManager categorieManager = CategorieManager.getInstance();
		try {
			List<Categorie> categories = categorieManager.getCategorie();
			request.setAttribute("categories", categories);
		// lien d' affichage jsp
		request.getRequestDispatcher(ContratUrl.URL_NOUVELLE_VENTE).forward(request, response);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String article = request.getParameter("article");
		String description = request.getParameter("description");
		String categorieArticle = request.getParameter("categorieArticle");
//		String photoArticle = request.getParameter("photoArticle");
		String miseAPrix = request.getParameter("miseAPrix");
		String dateDebutEnchere = request.getParameter("dateDebutEnchere");
		String dateFinEnchere = request.getParameter("dateFinEnchere");
//		String rue = request.getParameter("rue");
//		String codepostal = request.getParameter("codepostal");
//		String ville = request.getParameter("ville");
		System.out.println(categorieArticle);
				
		
		ArticleVenduManager avm = ArticleVenduManager.getInstance();	
		
		
		ArticleVendu articleVendu = new ArticleVendu();
		Categorie categorie = new Categorie();
		int map = Integer.parseInt(miseAPrix);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dde = null;
		try {
			dde = sdf.parse(dateDebutEnchere);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Date ddf = null;
		try {
			ddf = sdf.parse(dateFinEnchere);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	

	//	noCategorie = 
		
		articleVendu.setNomArticle(article);
		articleVendu.setDescription(description);
		//articleVendu.setNoCategorie(noCategorie);
		articleVendu.setMiseAPrix(map);
		articleVendu.setDateDebutEncheres(dde);
//		pstmt.setDate(3, new java.sql.Date(pObject.getDateDebutEncheres().getTime())); 
		articleVendu.setDateFinEncheres(ddf);
//		articleVendu.setNoRetrait(noRetrait);
		
		
		try {
			avm.insertArticleVendu(articleVendu);
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		
		
}}
