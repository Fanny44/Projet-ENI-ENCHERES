package org.eniencheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ServletDeconnexion
 */
public class ServletDeconnexion extends HttpServlet {

	/**
	 * Pour la sérialisation
	 */
	private static final long serialVersionUID = 6549534714813739439L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        request.getSession().setAttribute("connecter", false);
        request.getSession().setAttribute("utilisateur", null);
        
        response.sendRedirect(request.getContextPath()+"/Accueil");
	}

}
