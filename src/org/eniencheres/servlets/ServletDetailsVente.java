package org.eniencheres.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.Local;
import org.eniencheres.bll.ArticleVenduManager;
import org.eniencheres.bll.BLLException;
import org.eniencheres.bll.CategorieManager;
import org.eniencheres.bll.EncheresManager;
import org.eniencheres.bll.UtilisateurManager;
import org.eniencheres.bo.ArticleSelect;
import org.eniencheres.bo.ArticleVendu;
import org.eniencheres.bo.Categorie;
import org.eniencheres.bo.ContratUrl;
import org.eniencheres.bo.Enchere;
import org.eniencheres.bo.Utilisateur;

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
		request.setCharacterEncoding("UTF-8");
		int prop = Integer.parseInt(request.getParameter("proposition"));
		System.out.println(prop);
		int montantEnchere = Integer.parseInt(request.getParameter("montantEnchere"));
		System.out.println(montantEnchere);
		Utilisateur user= (Utilisateur) request.getSession().getAttribute("utilisateur");
		EncheresManager em= EncheresManager.getInstance();
		Enchere ench = new Enchere();
		ench.setDateEnchere(new Date());
		ench.setMontantEnchere(montantEnchere);
		ench.setNoArticle(Integer.parseInt(request.getParameter("numArticle")));
		ench.setNoUtilisateur(user.getNoUtilisateur());
		
		ArticleVenduManager avm = ArticleVenduManager.getInstance();
		UtilisateurManager um = UtilisateurManager.getInstance();
		
		
		if(prop>montantEnchere && (user.getCredit()-prop>0)) {
			try {
				em.insertEnchere(ench);
				avm.updatePrixVente(prop);
				int credit = user.getCredit()-prop;
				um.getUpdateCreditUser(credit, user.getNoUtilisateur());				
				
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("messageErreur", e.getMessage());
				request.getRequestDispatcher(ContratUrl.URL_ACCUEIL).forward(request, response);
			}			
		}
		
		
		
		
	
	
	
	}

}
