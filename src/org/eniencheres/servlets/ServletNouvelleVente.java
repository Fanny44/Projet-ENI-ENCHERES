package org.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eniencheres.bo.ContratUrl;
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
		
		//ramène l'adresse de l'utilisateur en session
		Utilisateur utilisateur = (Utilisateur)request.getSession().getAttribute("utilisateur");		
		String rue = utilisateur.getRue();
		String codePostal = utilisateur.getCodePostal();
		String ville = utilisateur.getVille();
		
		//affiche l'adresse de l'utillisateur
		
//		<p>Prix : <c:out value="${liste.montant}" /></p>
//		<p>Fin de l'enchère :<c:out value="${liste.dateFin}" /></p>
//		<p>Vendeur :<a href="./profil?profilVendeur=${liste.vendeur}"> <c:out value="${liste.vendeur}" /> </a></p>		
		

		//lien vers affichage jsp
		request.getRequestDispatcher(ContratUrl.URL_NOUVELLE_VENTE).forward(request, response);

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
}}
