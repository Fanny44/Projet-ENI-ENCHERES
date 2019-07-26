package org.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bo.ContratUrl;

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
		request.getRequestDispatcher(ContratUrl.URL_PROFIL).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
