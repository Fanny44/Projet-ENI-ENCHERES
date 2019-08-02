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
 * Servlet implementation class ServletMonProfil
 * @author Christophe Michard
 * @since Créé le 29/07/2019
 * @since Modifié le 30/07/2019
 */
public class ServletMonProfil extends HttpServlet {
	
	private static final long serialVersionUID = -957511012209359498L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute("connecter") != null && (boolean)request.getSession().getAttribute("connecter")) {
			request.getRequestDispatcher(ContratUrl.URL_MON_PROFIL).forward(request, response);
		}else {
			request.getRequestDispatcher(ContratUrl.URL_CONNEXION).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		boolean actionOk = false;

		//Contrôle si le mot de passe actuel à été saisie et est correct, pour valider les modifications
		Utilisateur uTemp = (Utilisateur) request.getSession().getAttribute("utilisateur");
		if(request.getParameter("txtMotDePasseActuel") == null || request.getParameter("txtMotDePasseActuel").isEmpty() || !request.getParameter("txtMotDePasseActuel").trim().equals(uTemp.getMotDePasse())) {
			request.setAttribute("messageErreur", "Veuillez saisir le mot de passe actuel pour valider les modifications");
			request.getRequestDispatcher(ContratUrl.URL_MON_PROFIL).forward(request, response);
			return;
		}
		
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(request.getParameter("txtPseudo"));
		utilisateur.setNom(request.getParameter("txtNom"));
		utilisateur.setPrenom(request.getParameter("txtPrenom"));
		utilisateur.setEmail(request.getParameter("txtEmail"));
		utilisateur.setTelephone(request.getParameter("txtTelephone"));
		utilisateur.setRue(request.getParameter("txtRue"));
		utilisateur.setCodePostal(request.getParameter("txtCodePostal"));
		utilisateur.setVille(request.getParameter("txtVille"));
		utilisateur.setCredit(uTemp.getCredit());
		
		//Est-ce que le mot de passe à été modifié
		boolean mdpModifier = false;
		if(!request.getParameter("txtMotDePasseNouveau").isEmpty() && !request.getParameter("txtMotDePasseActuel").equals(request.getParameter("txtMotDePasseNouveau"))) {
			//Si le mot de passe à été modifié on recupère le nouveau
			utilisateur.setMotDePasse(request.getParameter("txtMotDePasseNouveau"));
			mdpModifier = true;
		}else {
			//Sinon on récupère le mot de passe actuel
			utilisateur.setMotDePasse(uTemp.getMotDePasse());
		}
		
		HttpSession hs = request.getSession();
		UtilisateurManager um = UtilisateurManager.getInstance();
		
		try {
			if(mdpModifier) {
				um.update(utilisateur, (String) request.getParameter("txtConfirmMotDePasse"));
			}else {
				um.update(utilisateur);
			}
			
			actionOk = true;
		} catch (BLLException e) {
			e.printStackTrace();
			request.setAttribute("messageErreur", e.getMessage());
		}	

		//Redirection
		if(actionOk) {
			hs.setAttribute("utilisateur", utilisateur);
			response.sendRedirect(request.getContextPath()+"/Accueil");
			return;
		}else {
			request.getRequestDispatcher(ContratUrl.URL_MON_PROFIL).forward(request, response);
		}
	}

}
