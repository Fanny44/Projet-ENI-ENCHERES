package org.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bll.UtilisateurManager;
import org.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletEniEncheres
 */
public class ServletConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Affichage de la page de connexion.
		request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//R�cup�ration de l'identifiant et du mot de passe tap�s par l'utiliisateur sur la page de connexion et stockage dans variables identifiant et motdepasse.
			String identifiant = request.getParameter("identifiant");
			String motdepasse = request.getParameter("motdepasse");
			UtilisateurManager um = UtilisateurManager.getInstance();
			Utilisateur utilisateur = um.verifIdentite(identifiant, motdepasse);
			
			if(utilisateur == null){
				System.out.println("Erreur");
			}
			
	}
	
	

}
