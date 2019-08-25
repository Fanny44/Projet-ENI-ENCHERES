package org.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bll.BLLException;
import org.eniencheres.bll.UtilisateurManager;
import org.eniencheres.bo.ContratUrl;
import org.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletSupprimerMonCompte
 * @author Christophe Michard
 * @since Créé le 30/07/2019
 * @since Modifié le 31/07/2019
 */
public class ServletSupprimerMonCompte extends HttpServlet {
	
	private static final long serialVersionUID = -4187158381818025786L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//vérification de la connexion 
		if (request.getSession().getAttribute("connecter") != null && (boolean)request.getSession().getAttribute("connecter")) {
			request.getRequestDispatcher(ContratUrl.URL_SUPPRIMER_COMPTE).forward(request, response);
		}else {
			request.getRequestDispatcher(ContratUrl.URL_CONNEXION).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Contrôle si le mot de passe actuel à été saisie et est correct, pour valider les modifications
		Utilisateur uTemp = (Utilisateur) request.getSession().getAttribute("utilisateur");
		
		//Contrôle du mot de passe
		if(request.getParameter("txtMotDePasse") == null || request.getParameter("txtMotDePasse").isEmpty() || !request.getParameter("txtMotDePasse").trim().equals(uTemp.getMotDePasse())) {
			request.setAttribute("messageErreur", "Erreur lors de la suppression du compte : Le mot de passe saisie est incorrect !");
			request.getRequestDispatcher(ContratUrl.URL_MON_PROFIL).forward(request, response);
			return;
		}
		
		UtilisateurManager um = UtilisateurManager.getInstance();
		
		try {
			um.delete(uTemp);
			response.sendRedirect(request.getContextPath()+"/deconnexion");
		} catch (BLLException e) {
			request.setAttribute("messageErreur", "Erreur lors de la suppression du compte : "+e.getMessage());
			request.getRequestDispatcher(ContratUrl.URL_MON_PROFIL).forward(request, response);
		}
	}

}
