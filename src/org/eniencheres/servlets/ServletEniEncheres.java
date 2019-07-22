package org.eniencheres.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletEniEncheres
 */
public class ServletEniEncheres extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// D�finition de deux variables identifiant et mot de passe.
	String identifiant ;
	String motdepasse ;
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Affichage de la page de connexion.
		request.getRequestDispatcher("/WEB-INF/jsp/connexion.jsp").forward(request, response);//
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//R�cup�ration de l'identifiant et du mot de passe tap�s par l'utiliisateur sur la page de connexion et stockage dans variables identifiant et motdepasse.
		 identifiant = request.getParameter("identifiant");
		 motdepasse = request.getParameter("motdepasse");
		 
		
	}

}
