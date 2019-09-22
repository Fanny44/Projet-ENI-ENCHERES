package org.eniencheres.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
public class ServletDetailsVente extends HttpServlet {
	/**
	 * pour la sérialisation
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// vérification que l'user est connecter
		if (request.getSession().getAttribute("connecter") != null
				&& (boolean) request.getSession().getAttribute("connecter")) {
			int articleV = Integer.parseInt(request.getParameter("numArticle"));

			if (request.getParameter("nomArticle") != null) {
				ArticleSelect article = new ArticleSelect();
				ArticleVenduManager avm = ArticleVenduManager.getInstance();

				try {
					article = avm.getSelectArticleById(articleV); // récupération des données de l'article dont l'user
																	// veut voir le détail
				} catch (BLLException e) {
					e.printStackTrace();
				}

				if (article.getMontantEnchere() != 0) { // Pour l'affichage du pseudo de l'user qui a fait la meilleure
														// offre pour cet articleV

					int meilleurOffre = article.getMontantEnchere();
					Utilisateur user = new Utilisateur();
					EncheresManager em = EncheresManager.getInstance();
					try {
						user = em.getSelectPseudo(meilleurOffre, articleV);
					} catch (BLLException e) {
						e.printStackTrace();
					}
					request.setAttribute("user", user);

				}

				request.getSession().setAttribute("article", article);
			}

			request.getRequestDispatcher(ContratUrl.URL_DETAILS_VENTE).forward(request, response);
		} else {
			request.getRequestDispatcher(ContratUrl.URL_CONNEXION).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		// gestion des encheres

		String proposition = request.getParameter("proposition");
		int prop = 0;
		try {
			prop = Integer.parseInt(proposition);
		} catch (Exception e) {
			// TODO
			// erreur traitée mais reste dans la console
			e.printStackTrace();
		}

		int montantEnchere = Integer.parseInt(request.getParameter("montantEnchere"));
		int noArticle = Integer.parseInt(request.getParameter("numArticle"));
		String dateD = request.getParameter("debutEnchere");
		String date = request.getParameter("finEnchere");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date dateEnchere = null;
		Date dateDebut = null;
		try {
			dateEnchere = sdf.parse(date);
			dateDebut = sdf.parse(dateD);
		} catch (ParseException e1) {
			e1.printStackTrace();
		}

		Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String vendeur = request.getParameter("vendeur");

		EncheresManager em = EncheresManager.getInstance();
		ArticleVenduManager avm = ArticleVenduManager.getInstance();
		UtilisateurManager um = UtilisateurManager.getInstance();

		Enchere ench = new Enchere();
		ench.setDateEnchere(new Date());
		ench.setMontantEnchere(prop);
		ench.setNoArticle(noArticle);
		ench.setNoUtilisateur(user.getNoUtilisateur());

		int creditDebite = user.getCredit() - prop;
		Date syst = new Date();

			if (!vendeur.equals(user.getPseudo()) && prop > 0 && dateEnchere.after(syst) && dateDebut.before(syst) || dateDebut.equals(syst)) {
				if (prop > montantEnchere && (creditDebite > 0)) {
					try {
						em.insertEnchere(ench);
						um.getModifCredAncienUser(noArticle, montantEnchere); // recrediter/modification du crédit de
																				// l'utilisateur qui a fait l'enchère
																				// précédente
						avm.updatePrixVente(noArticle);
						um.getUpdateCreditUser(creditDebite, user.getNoUtilisateur()); // modification du credit de
																						// l'user en cours qui fait
																						// l'enchere
						user.setCredit(creditDebite);
						request.getSession().setAttribute("utilisateur", user);
						response.sendRedirect(request.getContextPath() + "/Accueil");

					} catch (BLLException e) {
						request.setAttribute("messageErreur",
								"Une erreur s'est produite lors de l'enchère :" + e.getMessage());
						erreurEnchere(request, response);
					}
				} else {
					if (prop < montantEnchere) {
						request.setAttribute("messageErreur",
								"la proposition n'est pas supérieur à la meilleure offre ! ");
					} else if (creditDebite < 0) {
						request.setAttribute("messageErreur",
								"Vous ne pouvez pas enchérir car votre crédit est insuffisant ! ");
					} else {
						request.setAttribute("messageErreur",
								"Vous ne pouvez pas enchérir car l'enchère n'est pas ouverte ! ");
					}
					erreurEnchere(request, response);
				}
			} else {
				if (vendeur.equals(user.getPseudo())) {
					request.setAttribute("messageErreur",
							"Vous ne pouvez pas enchérir sur un article que vous vendez ! ");
					erreurEnchere(request, response);
				} else {
					request.setAttribute("messageErreur", "Vous n'avez pas fait de proposition ! ");
					erreurEnchere(request, response);
				}
			}
		

		// modification du crédit du vendeur quand l'enchère est terminée
		String acheteur = request.getParameter("acheteur");
		if (dateEnchere.before(syst) && acheteur != null) {
			try {
				um.getUpdateCreditVendeur(montantEnchere, vendeur);
			} catch (BLLException e) {
				request.setAttribute("messageErreur",
						"Une erreur s'est produite lors de la recréditation du compte du vendeur :" + e.getMessage());
				erreurEnchere(request, response);
			}
		}

		// suppression de l'article

		String supprimer = request.getParameter("supprimer");

		if (supprimer != null) {
			try {
				avm.getDelete(noArticle);
				response.sendRedirect(request.getContextPath() + "/Accueil");
			} catch (BLLException e) {
				request.setAttribute("messageErreur", "L'article n'a pas pu être supprimer :" + e.getMessage());
				erreurEnchere(request, response);
			}
		}
		

	}

	private void erreurEnchere(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");
		int noArticle = Integer.parseInt(request.getParameter("numArticle"));
		ArticleVenduManager avm = ArticleVenduManager.getInstance();
		request.setAttribute("user", user);
		ArticleSelect article = new ArticleSelect();
		try {
			article = avm.getSelectArticleById(noArticle);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		request.setAttribute("article", article);
		request.getRequestDispatcher(ContratUrl.URL_DETAILS_VENTE).forward(request, response);
	}

}
