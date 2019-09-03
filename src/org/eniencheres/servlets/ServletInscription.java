package org.eniencheres.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
 * Servlet implementation class ServletInscription
 * @author Christophe Michard
 * @since Créé le 23/07/2019
 */
public class ServletInscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletInscription() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher(ContratUrl.URL_INSCRIPTION);
				rd.forward(request, response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); //pour l'encodage 
		
		//instanciation du nouvel utilisateur
		Utilisateur utilisateur = new Utilisateur();
		utilisateur.setPseudo(request.getParameter("txtPseudo"));
		utilisateur.setNom(request.getParameter("txtNom"));
		utilisateur.setPrenom(request.getParameter("txtPrenom"));
		utilisateur.setEmail(request.getParameter("txtEmail"));
		utilisateur.setTelephone(request.getParameter("txtTelephone"));
		utilisateur.setRue(request.getParameter("txtRue"));
		utilisateur.setCodePostal(request.getParameter("txtCodePostal"));
		utilisateur.setVille(request.getParameter("txtVille"));
		utilisateur.setMotDePasse(request.getParameter("txtMotDePasse"));
		utilisateur.setCredit(100);
		
		UtilisateurManager um = UtilisateurManager.getInstance();
		
		boolean actionOk = false;
		
		//insertion de l'utilisateur 
		try {
			um.insert(utilisateur, (String) request.getParameter("txtConfirmMotDePasse"));
			actionOk = true;
		} catch (BLLException e) {
			e.printStackTrace();
			request.setAttribute("messageErreur", e.getMessage());
		}	
		
		RequestDispatcher rd = null;
		HttpSession hs = request.getSession(); 

		//Redirection
		if(actionOk) {
			hs.setAttribute("connecter", true);
			hs.setAttribute("utilisateur", utilisateur);
			//rd = request.getRequestDispatcher(ContratUrl.URL_SERVLET_ACCUEIL);
			response.sendRedirect("/Projet-ENI-ENCHERES/Accueil");
			return;
		}else {
			rd = request.getRequestDispatcher(ContratUrl.URL_INSCRIPTION);
		}

		rd.forward(request, response);
	}

}
