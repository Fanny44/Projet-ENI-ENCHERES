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
 * Servlet implementation class ServletProfil
 * @author Christophe Michard
 * @since Créé le 25/07/2019
 */
public class ServletProfil extends HttpServlet {
       
	/**
	 * Pour la sérialisation 
	 */
	private static final long serialVersionUID = -6779009875016616925L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		//pour l'affichage du profil du vendeur 
			if(request.getParameter("profilVendeur") != null) {
				Utilisateur utilisateur = new Utilisateur();
				UtilisateurManager um = UtilisateurManager.getInstance();
				
				try {
					utilisateur = um.selectByPseudo((String)request.getParameter("profilVendeur")); //récupération du pseudo du vendeur passer en parametre
				} catch (BLLException e) {
					request.setAttribute("messageErreur", e.getMessage());
				}
				
				request.setAttribute("profilVendeur", utilisateur);
			}
			
			request.getRequestDispatcher(ContratUrl.URL_PROFIL).forward(request, response); //renvoi sur la page profil vendeur
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
	
	
	}

}
