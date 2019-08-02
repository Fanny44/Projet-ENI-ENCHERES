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
		if (request.getSession().getAttribute("connecter") != null && (boolean)request.getSession().getAttribute("connecter")) {
			int articleV =Integer.parseInt(request.getParameter("numArticle"));
			
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
		}else {
			request.getRequestDispatcher(ContratUrl.URL_CONNEXION).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		int prop = Integer.parseInt(request.getParameter("proposition"));
		int montantEnchere = Integer.parseInt(request.getParameter("montantEnchere"));		
		int noArticle = Integer.parseInt(request.getParameter("numArticle"));
		
		Utilisateur user= (Utilisateur) request.getSession().getAttribute("utilisateur");
		EncheresManager em= EncheresManager.getInstance();
		ArticleVenduManager avm = ArticleVenduManager.getInstance();
		UtilisateurManager um = UtilisateurManager.getInstance();
		
		Enchere ench = new Enchere();
		ench.setDateEnchere(new Date());
		ench.setMontantEnchere(prop);
		ench.setNoArticle(noArticle);
		ench.setNoUtilisateur(user.getNoUtilisateur());
		
		int creditDebite= user.getCredit()-prop;
//		System.out.println(user.getCredit());
//		System.out.println(creditDebite);
		
		if(prop>montantEnchere && (creditDebite>0)) {
			try {
				em.insertEnchere(ench);
				avm.updatePrixVente(noArticle);				
				um.getUpdateCreditUser(creditDebite, user.getNoUtilisateur());	
				//recrediter les users qui on fait une offre
				
			} catch (BLLException e) {
				e.printStackTrace();
				request.setAttribute("messageErreur", e.getMessage());
			}			
		}
		
		response.sendRedirect(request.getContextPath()+"/Accueil");
	}

}
