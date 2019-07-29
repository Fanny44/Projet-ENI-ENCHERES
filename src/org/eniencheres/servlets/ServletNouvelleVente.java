package org.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletNouvelleVente
 */
public class ServletNouvelleVente extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");		
		String rue = utilisateur.getRue();
		String codePostal = utilisateur.getCodePostal();
		String ville = utilisateur.getVille();
		
		
		System.out.println(rue);
		System.out.println(codePostal);
		System.out.println(ville);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
			

}}
