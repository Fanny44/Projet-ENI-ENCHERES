package org.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.ha.backend.Sender;
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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération de l'identifiant et du mot de passe saisie par l'utiliisateur sur
		// la page de connexion et stockage dans variables identifiant et motdepasse.
		String identifiant = request.getParameter("identifiant");
		String motdepasse = request.getParameter("motdepasse");

		RequestDispatcher rd = null;
		UtilisateurManager um = UtilisateurManager.getInstance();
		
		//Vérification des identifiants
		Utilisateur utilisateur = um.verifIdentite(identifiant, motdepasse);

		//Redirection suivant etat connexion
		if (utilisateur == null) {
			request.setAttribute("echec", "Echec de connexion");
			rd = request.getRequestDispatcher(ContratUrl.URL_CONNEXION);
		} else {
			response.sendRedirect(request.getContextPath()+"/Accueil");
			//rd = request.getRequestDispatcher(request.getContextPath()+"/Accueil");
		}

		request.getSession().setAttribute("connecter", utilisateur != null ? true : false);
		request.getSession().setAttribute("utilisateur", utilisateur);

		//rd.forward(request, response);
	}

}
