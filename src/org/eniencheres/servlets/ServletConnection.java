package org.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eniencheres.bll.BLLException;
import org.eniencheres.bll.UtilisateurManager;
import org.eniencheres.bo.ContratUrl;
import org.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletEniEncheres
 */
public class ServletConnection extends HttpServlet {

	/**
	 * Pour la sérialisation
	 */
	private static final long serialVersionUID = 4525437186375169345L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Affichage de la page de connexion.
		request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * @since Modifié le 31/07/2019 par Christophe Michard
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération de l'identifiant et du mot de passe saisie par l'utiliisateur sur
		// la page de connexion et stockage dans variables identifiant et motdepasse.
		String identifiant = request.getParameter("identifiant");
		String motdepasse = request.getParameter("motdepasse");

		UtilisateurManager um = UtilisateurManager.getInstance();
		
		//Vérification des identifiants
		Utilisateur utilisateur;
		try {
			utilisateur = um.verifIdentite(identifiant, motdepasse);
			if (utilisateur == null) {
				request.setAttribute("messageErreur", "Echec de connexion - Vérifiez vos identifiants !");
				request.getRequestDispatcher(ContratUrl.URL_CONNEXION).forward(request, response);
			} else {
				HttpSession session = request.getSession(); 
				session.setMaxInactiveInterval(300); ///après 5 min d'inactivité, la session est fermer et renvoie sur la page d'accueil
				response.sendRedirect(request.getContextPath()+"/Accueil");			
			}
			
			//Redirection suivant etat connexion
			request.getSession().setAttribute("connecter", utilisateur != null ? true : false);
			request.getSession().setAttribute("utilisateur", utilisateur);
		} catch (BLLException e) {
			e.getMessage();
		}


	}

}
