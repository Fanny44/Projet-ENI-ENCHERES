package org.eniencheres.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bll.ArticleVenduManager;
import org.eniencheres.bll.BLLException;
import org.eniencheres.bll.EncheresManager;
import org.eniencheres.bll.UtilisateurManager;
import org.eniencheres.bo.ArticleSelect;
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
				
					if(article.getMontantEnchere()!=0) {
						
						int meilleurOffre = article.getMontantEnchere();
						Utilisateur user = new Utilisateur(); 
						EncheresManager em = EncheresManager.getInstance(); 
						try {
							user=em.getSelectPseudo(meilleurOffre, articleV);
						} catch (BLLException e) {
							e.printStackTrace();
						}
						request.setAttribute("user", user);
						
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
		String dateD=request.getParameter("debutEnchere");
		String date=request.getParameter("finEnchere");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateEnchere = null;
		Date dateDebut = null;
		try {
			dateEnchere= sdf.parse(date);
			dateDebut=sdf.parse(dateD);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		
		Utilisateur user= (Utilisateur) request.getSession().getAttribute("utilisateur");
		String vendeur =request.getParameter("vendeur");
		EncheresManager em= EncheresManager.getInstance();
		ArticleVenduManager avm = ArticleVenduManager.getInstance();
		UtilisateurManager um = UtilisateurManager.getInstance();
		
		Enchere ench = new Enchere();
		ench.setDateEnchere(new Date());
		ench.setMontantEnchere(prop);
		ench.setNoArticle(noArticle);
		ench.setNoUtilisateur(user.getNoUtilisateur());
		
		int creditDebite= user.getCredit()-prop;
		Date syst=new Date();
		
		
		if(!vendeur.equals(user.getPseudo())) {
			if(prop>montantEnchere && (creditDebite>0) && dateEnchere.after(syst) && dateDebut.before(syst) || dateDebut.equals(syst)) {
				try {
					em.insertEnchere(ench);
					um.getModifCredAncienUser(noArticle, montantEnchere); //recrediter/modification du crédit de l'utilisateur qui a fait l'enchère précédente 
					avm.updatePrixVente(noArticle);				
					um.getUpdateCreditUser(creditDebite, user.getNoUtilisateur());	//modification du credit de l'user en cours qui fait l'enchere
					user.setCredit(creditDebite);
					request.getSession().setAttribute("utilisateur", user);
					response.sendRedirect(request.getContextPath()+"/Accueil");
					
			
				} catch (BLLException e) {
					request.setAttribute("messageErreur", "Une erreur s'est produite lors de l'enchère :" + e.getMessage());
					erreurEnchere(request, response);
				}			
			}else {
				if(prop<montantEnchere) {
					request.setAttribute("messageErreur", "la proposition n'est pas supérieur à la meilleure offre ! ");
				} else if (creditDebite<0) {
					request.setAttribute("messageErreur", "Vous ne pouvez pas enchérir car votre crédit est insuffisant ! ");
				}else {
				request.setAttribute("messageErreur", "Vous ne pouvez pas enchérir car l'enchère n'est pas ouverte ! ");
				}
				erreurEnchere(request, response);
			}
		}else {
			request.setAttribute("messageErreur", "Vous ne pouvez pas enchérir sur un article que vous vendez ! ");
		erreurEnchere(request, response);
		}

	}
	
	
	private void erreurEnchere(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Utilisateur user= (Utilisateur) request.getSession().getAttribute("utilisateur");
		int noArticle = Integer.parseInt(request.getParameter("numArticle"));
		ArticleVenduManager avm = ArticleVenduManager.getInstance();
		request.setAttribute("user", user);
		ArticleSelect article = new ArticleSelect();
		try {
			article=avm.getSelectArticleById(noArticle);
		}catch (BLLException e) {
			e.printStackTrace();
		}
		request.setAttribute("article", article);
		request.getRequestDispatcher(ContratUrl.URL_DETAILS_VENTE).forward(request, response);
	}

			

}
