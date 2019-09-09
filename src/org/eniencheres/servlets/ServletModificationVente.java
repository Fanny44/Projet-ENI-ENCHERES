package org.eniencheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bo.ContratUrl;
import org.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletModificationVente
 */
public class ServletModificationVente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//vérification de la connexion et redirection en fonction de son état
		Utilisateur user = (Utilisateur) request.getSession().getAttribute("utilisateur");
		String userSession = user.getPseudo();
		String vendeur = request.getParameter("vendeur"); 
		
		
				if (vendeur.equals(userSession)) {
					request.getRequestDispatcher(ContratUrl.URL_MODIFICATION_VENTE).forward(request, response);
				}else {
					request.getRequestDispatcher(ContratUrl.URL_ACCUEIL).forward(request, response);
				}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
